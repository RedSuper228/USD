package com.example.usd;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class DivineFragment extends Fragment implements View.OnClickListener {

    //todo - save bible to firebase

    String bible = Bible.bible;

    EditText et_question;
    TextView tv_answer;



    public DivineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_divine, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



        EditText et_question = (EditText) getView().findViewById(R.id.et_question);
        TextView tv_answer = (TextView) getView().findViewById(R.id.tv_answer);
        //ImageView bt_search = (ImageView) getView().findViewById(R.id.bt_search);



        Animation fadein = AnimationUtils.loadAnimation(getContext(),R.anim.fadein);
        tv_answer.startAnimation(fadein);





        /*bt_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == bt_search) {

                }
            }
        });*/


    }


    @Override
    public void onClick(View v) {

    }
}