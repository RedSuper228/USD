package com.example.usd;

import com.example.usd.databinding.FragmentLibraryBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class LibraryFragment extends Fragment implements View.OnClickListener {
    FragmentLibraryBinding binding;
    DatabaseReference db_ref;
    StorageReference storage_ref;

    //todo - stores all bible or favorite chapters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLibraryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.etPdf.setOnClickListener(this);
        binding.btnPdf.setEnabled(false);
        
        storage_ref = FirebaseStorage.getInstance().getReference();
        db_ref = FirebaseDatabase.getInstance().getReference("uploadPDF");
    }

    @Override
    public void onClick(View view) {
        if (view == binding.etPdf) {
            //Toast.makeText(getActivity(), "bla bla", Toast.LENGTH_SHORT).show();
            selectPDF();
        }
    }

    private void selectPDF() {
        //pick file from storage
        Intent intent = new Intent();

    }
}