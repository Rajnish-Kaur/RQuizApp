package com.example.rquizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragement_question extends Fragment {

    TextView quesText;
    int question;
    int color;

    public static Fragement_question newInstance(int quesID,int colorId) {

        Bundle args = new Bundle();
        args.putInt("QuestionId",quesID);
        args.putInt("ColorId",colorId);
        Fragement_question fragment = new Fragement_question();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //   Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.question_fragment, container, false);
        quesText = (TextView) view.findViewById(R.id.question);
        question = getArguments().getInt("QuestionId");
        color = getArguments().getInt("ColorId");
        quesText.setText(question);
        quesText.setBackgroundResource(color);
        return view;
    }

}


