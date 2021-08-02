package com.cs446.covidsafe.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cs446.covidsafe.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class MC extends Question{
    String[] answers;

    public MC(int questionNum){
        super(questionNum);
    }

    @Override
    public void updateQuestion(View view, Context context) {
        int resID = 0;

        //Get question from xml
        resID = context.getResources().getIdentifier("q" + questionNum, "string", context.getPackageName());

        question = questionNum + ". " + context.getString(resID);

        //Get answers to question from xml
        resID = context.getResources().getIdentifier("a" + questionNum, "array", context.getPackageName());
        answers = context.getResources().getStringArray(resID);

        //Set question
        String questionTitle = questionNum + "." + "First question";
        resID = context.getResources().getIdentifier("q" + questionNum + "_question",
                "id", context.getPackageName());
        TextView t = (TextView)view.findViewById(resID);
        t.setText(question);

        //Set check box answer
        String baseCheckBoxID = "q" + questionNum;
        for(int i = 0; i < 4; i++) {
            resID = context.getResources().getIdentifier(baseCheckBoxID + "_c" + Integer.toString(i + 1),
                    "id", context.getPackageName());
            CheckBox c = (CheckBox) view.findViewById(resID);
            if(i < answers.length) {
                c.setText(answers[i]);
            } else {
                c.setVisibility(View.GONE);
            }
        }
    }
}
