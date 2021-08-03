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

public class TF extends Question{
    String[] answers;

    public TF(int questionNum){
        super(questionNum);
    }

    @Override
    public void updateQuestion(View view, Context context) {
        int resID = 0;

        super.getQuestion(context);
        super.setQuestion(view, context);

        //Set question
        String questionTitle = questionNum + "." + "First question";
        resID = context.getResources().getIdentifier("q" + questionNum + "_question",
                "id", context.getPackageName());
        TextView t = (TextView)view.findViewById(resID);
        t.setText(question);

        super.getCorrectAnswer(context);

        //Set check box answer
        String baseCheckBoxID = "q" + questionNum;
        for(int i = 0; i < 4; i++) {
            resID = context.getResources().getIdentifier(baseCheckBoxID + "_c" + Integer.toString(i + 1),
                    "id", context.getPackageName());
            CheckBox c = (CheckBox) view.findViewById(resID);
            switch (i){
                case 0:
                    c.setText("True");
                    break;
                case 1:
                    c.setText("False");
                    break;
                default:
                    c.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int validateAnswer(View view, Context context){
        int mark = 0;
        String correctAnswerBox = "q" + questionNum + "_c" + Integer.toString(correctAnswer);
        int resID = context.getResources().getIdentifier(correctAnswerBox, "id", context.getPackageName());
        CheckBox c = (CheckBox) view.findViewById(resID);
        if(c.isChecked()){
            mark = TFFullMark;
        }
        return mark;
    }
}
