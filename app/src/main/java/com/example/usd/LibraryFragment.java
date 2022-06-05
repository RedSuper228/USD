package com.example.usd;

import com.example.usd.databinding.FragmentLibraryBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class LibraryFragment extends Fragment {
    FragmentLibraryBinding binding;
    DatabaseReference db_ref;
    StorageReference storage_ref;
    ActivityResultLauncher<Intent> activityResultLauncher;
    //TODO - Make a library: read bible and save favorite quotes

    // TODO: Rename parameter arguments, choose names that match

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLibraryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.etPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();
            }
        });
        binding.btnReadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReadPdf_Screen.class));
            }
        });
        storage_ref = FirebaseStorage.getInstance().getReference();
        db_ref = FirebaseDatabase.getInstance().getReference("uploadPDF");

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == getActivity().RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();

                            binding.etPdf.setText(
                                    data.getDataString().
                                            substring(data.getDataString().lastIndexOf("/") + 1));
                            binding.btnPdf.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    uploadFileToFirebase(data.getData());
                                }
                            });
                        }
                    }
                });
    }


    private void selectPDF() {
        //pick file from storage
        Intent data = new Intent();
        //data.addCategory(Intent.CATEGORY_OPENABLE);
        data.setType("application/pdf");
        data.setAction(data.ACTION_GET_CONTENT);
        data.createChooser(data, "PDF FILE SELECT");
        //startActivityForResult(intent.createChooser(intent,"PDF FILE SELECT"),12); //deprecated
        activityResultLauncher.launch(data);

    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12 & resultCode==1 && data!=null && data.getData() != null){
            binding.etPdf.setText(
                    data.getDataString().
                            substring(data.getDataString().lastIndexOf("/")+1));
            binding.btnPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadFileToFirebase(data.getData());
                }
            });
        }
    }*/

    private void uploadFileToFirebase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("File is loading...");
        progressDialog.show();

        StorageReference reference = storage_ref.child("upload" + System.currentTimeMillis());
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ; //error?
                Uri uri = uriTask.getResult();
                PutPdf putPdf = new PutPdf(binding.etPdf.getText().toString(), uri.toString());
                db_ref.child(db_ref.push().getKey()).setValue(putPdf);
                Toast.makeText(getActivity(), "File Upload", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                //show progress of download
                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                progressDialog.setMessage("File Upload..." + (int) progress + "%");
            }
        });
    }
}