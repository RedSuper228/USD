package com.example.usd;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;


public class DivineFragment extends Fragment implements View.OnClickListener {


    boolean check;
    TextToSpeech textToSpeech;


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

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.CANADA);
                }

            }
        });



        EditText et_question = (EditText) getView().findViewById(R.id.et_question);
        TextView tv_answer = (TextView) getView().findViewById(R.id.tv_answer);
        TextView tv_instructions = (TextView) getView().findViewById(R.id.tv_instructions);
        ImageView iv_text_banner_hidden = (ImageView) getView().findViewById(R.id.iv_text_banner_hidden);;
        ImageView iv_text_banner = (ImageView) getView().findViewById(R.id.iv_text_banner);
        ScrollView sv_answer = (ScrollView) getView().findViewById(R.id.sv_answer);
        LinearLayout ll_answer = (LinearLayout) getView().findViewById(R.id.ll_answer);
        Button bt_divine = (Button) getView().findViewById(R.id.bt_divine);
        Button bt_return = (Button) getView().findViewById(R.id.bt_return);

        check = true;

        iv_text_banner_hidden.setVisibility(View.INVISIBLE);

        bt_divine.setVisibility(View.VISIBLE);
        bt_divine.setBackgroundColor(Color.TRANSPARENT);

        bt_return.setVisibility(View.INVISIBLE);
        bt_return.setBackgroundColor(Color.TRANSPARENT);


        tv_answer.setVisibility(View.INVISIBLE);
        ll_answer.setVisibility(View.INVISIBLE);
        sv_answer.setVisibility(View.INVISIBLE);


        // Divine Button
        bt_divine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == bt_divine && check) {
                    check = false;
                    bt_divine.setVisibility(View.INVISIBLE);
                    bt_return.setVisibility(View.VISIBLE);

                    SearchingClass searcher = new SearchingClass();
                    tv_answer.setText(searcher.divine());

                    Animation fadein = AnimationUtils.loadAnimation(getContext(),R.anim.fadein);
                    sv_answer.startAnimation(fadein);
                    ll_answer.startAnimation(fadein);
                    tv_answer.startAnimation(fadein);
                    Animation fadeout = AnimationUtils.loadAnimation(getContext(),R.anim.fadeout);
                    et_question.startAnimation(fadeout);
                    tv_instructions.startAnimation(fadeout);
                    iv_text_banner.startAnimation(fadeout);
                    iv_text_banner_hidden.startAnimation(fadein);

                    et_question.getText().clear();

                    textToSpeech.speak(tv_answer.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        // Ask Another question
        bt_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == bt_return && !check) {
                    textToSpeech.stop();

                    check = true;
                    bt_divine.setVisibility(View.VISIBLE);
                    bt_return.setVisibility(View.INVISIBLE);
                    Animation fadeout = AnimationUtils.loadAnimation(getContext(),R.anim.fadeout);
                    sv_answer.startAnimation(fadeout);
                    ll_answer.startAnimation(fadeout);
                    tv_answer.startAnimation(fadeout);
                    iv_text_banner_hidden.startAnimation(fadeout);
                    Animation fadein = AnimationUtils.loadAnimation(getContext(),R.anim.fadein);
                    et_question.startAnimation(fadein);
                    tv_instructions.startAnimation(fadein);
                    iv_text_banner.startAnimation(fadein);
                }
            }
        });


    }


    @Override
    public void onClick(View v) {

    }

}