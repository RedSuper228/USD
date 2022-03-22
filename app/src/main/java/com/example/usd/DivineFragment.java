package com.example.usd;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class DivineFragment extends Fragment implements View.OnClickListener {

    String bible = Bible.bible;

    EditText et_question;
    Button bt_search;
    TextView tv_answer1, tv_answer2, tv_answer3, tv_answer4, tv_answer5;



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
        ImageView bt_search = (ImageView) getView().findViewById(R.id.bt_search);
        TextView tv_answer1 = (TextView) getView().findViewById(R.id.tv_answer1);
        TextView tv_answer2 = (TextView) getView().findViewById(R.id.tv_answer2);
        TextView tv_answer3 = (TextView) getView().findViewById(R.id.tv_answer3);
        TextView tv_answer4 = (TextView) getView().findViewById(R.id.tv_answer4);
        TextView tv_answer5 = (TextView) getView().findViewById(R.id.tv_answer5);


        tv_answer1.setVisibility(View.INVISIBLE);
        tv_answer2.setVisibility(View.INVISIBLE);
        tv_answer3.setVisibility(View.INVISIBLE);
        tv_answer4.setVisibility(View.INVISIBLE);
        tv_answer5.setVisibility(View.INVISIBLE);

        bt_search.setOnClickListener(this);

        bt_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == bt_search) {
                    String question = et_question.getText().toString();
                    char[] CHbible = bible.toCharArray();
                    char[] CHquestion = question.toCharArray();
                    int answer = SearchingClass.simpleTextSearch(CHquestion, CHbible);
                    tv_answer1.setText(String.valueOf(answer));
                    tv_answer1.setVisibility(View.VISIBLE);
                }
            }
        });


    }


    @Override
    public void onClick(View v) {

    }
}