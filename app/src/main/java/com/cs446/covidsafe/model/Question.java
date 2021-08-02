package com.cs446.covidsafe.model;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Question {
    static public int MCFullMark = 20;
    static public int TFFullMark = 10;
    protected int correctAnswer;
    protected int questionNum;
    protected String question;
    public Question(int num){
        questionNum = num;
    }
    public abstract void updateQuestion(View v, Context context);
    public abstract int validateAnswer(View v, Context context);
    protected void getCorrectAnswer(Context context){
        //Save correct answer
        int resID = context.getResources().getIdentifier("a" + questionNum + "_correct", "string", context.getPackageName());
        correctAnswer = Integer.parseInt(context.getString(resID));
    }

    protected void getQuestion(Context context){
        //Get question from xml
        int resID = context.getResources().getIdentifier("q" + questionNum, "string", context.getPackageName());
        question = questionNum + ". " + context.getString(resID);
    }
    protected void setQuestion(View view, Context context){
        //Set question
        String questionTitle = questionNum + "." + "First question";
        int resID = context.getResources().getIdentifier("q" + questionNum + "_question",
                "id", context.getPackageName());
        TextView t = (TextView)view.findViewById(resID);
        t.setText(question);
    }
}
