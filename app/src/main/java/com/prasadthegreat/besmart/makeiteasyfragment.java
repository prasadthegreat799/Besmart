package com.prasadthegreat.besmart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class makeiteasyfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    Button maskquestionBtn;
    Button mAnswerqtnbtn;

    public makeiteasyfragment() {

    }


    public static makeiteasyfragment newInstance(String param1, String param2) {
        makeiteasyfragment fragment = new makeiteasyfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_makeiteasyfragment, container, false);

        mAnswerqtnbtn=(Button)view.findViewById(R.id.answerquestion);
        maskquestionBtn=(Button)view.findViewById(R.id.askquestion);

        maskquestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),makequestionActivity.class));
            }
        });

        mAnswerqtnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),makeanswerActivity.class));
            }
        });

        return  view;
    }
}