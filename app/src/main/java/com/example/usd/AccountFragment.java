package com.example.usd;

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

public class AccountFragment extends Fragment implements View.OnClickListener {


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
        bt_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == bt_save){
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