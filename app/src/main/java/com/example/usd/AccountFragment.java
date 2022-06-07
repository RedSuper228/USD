package com.example.usd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment implements View.OnClickListener {

    //TODO - think of way to make accounts useful, maybe by saving special dates and favorite qoutes

    public AccountFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://usd-school-project-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference databaseReference = database.getReference("userdata");


        TextView tv_username = (TextView) getView().findViewById(R.id.tv_username);
        TextView tv_email = (TextView) getView().findViewById(R.id.tv_email);
        TextView tv_tel_num = (TextView) getView().findViewById(R.id.tv_tel_num);
        Button bt_edit = (Button) getView().findViewById(R.id.bt_edit);
        ScrollView sv_userdata = (ScrollView) getView().findViewById(R.id.sv_userdata);

        EditText et_username = (EditText) getView().findViewById(R.id.et_username);
        EditText et_email = (EditText) getView().findViewById(R.id.et_email);
        EditText et_tel_num = (EditText) getView().findViewById(R.id.et_tel_num);
        Button bt_save = (Button) getView().findViewById(R.id.bt_save);
        ScrollView sv_edit_userdata = (ScrollView) getView().findViewById(R.id.sv_edit_userdata);

        et_username.setVisibility(View.INVISIBLE);
        et_email.setVisibility(View.INVISIBLE);
        et_tel_num.setVisibility(View.INVISIBLE);
        bt_save.setVisibility(View.INVISIBLE);
        sv_edit_userdata.setVisibility(View.INVISIBLE);

        // Read from the database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserData value = dataSnapshot.getValue(UserData.class);
                tv_username.setText(value.username);
                tv_email.setText(value.email);
                tv_tel_num.setText(value.tel_num);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        // Edit Account
        bt_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == bt_edit){
                    et_username.setVisibility(View.VISIBLE);
                    et_email.setVisibility(View.VISIBLE);
                    et_tel_num.setVisibility(View.VISIBLE);
                    bt_save.setVisibility(View.VISIBLE);
                    sv_edit_userdata.setVisibility(View.VISIBLE);

                    tv_username.setVisibility(View.INVISIBLE);
                    tv_email.setVisibility(View.INVISIBLE);
                    tv_tel_num.setVisibility(View.INVISIBLE);
                    bt_edit.setVisibility(View.INVISIBLE);
                    sv_userdata.setVisibility(View.INVISIBLE);
                }
            }

        });

        // Save changes
        bt_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == bt_save){
                    UserData userData = new UserData(
                            et_username.getText().toString(),
                            et_email.getText().toString(),
                            et_tel_num.getText().toString());
                    databaseReference.setValue(userData);

                    et_username.setVisibility(View.INVISIBLE);
                    et_email.setVisibility(View.INVISIBLE);
                    et_tel_num.setVisibility(View.INVISIBLE);
                    bt_save.setVisibility(View.INVISIBLE);
                    sv_edit_userdata.setVisibility(View.INVISIBLE);

                    tv_username.setVisibility(View.VISIBLE);
                    tv_email.setVisibility(View.VISIBLE);
                    tv_tel_num.setVisibility(View.VISIBLE);
                    bt_edit.setVisibility(View.VISIBLE);
                    sv_userdata.setVisibility(View.VISIBLE);
                }
            }

        });

    }



    @Override
    public void onClick(View v) { }

}