package com.cs446.covidsafe.model;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Question {
    private int mark;
    protected int questionNum;
    protected String question;
    public Question(int num){
        questionNum = num;
    }
    public abstract void updateQuestion(View v, Context context);
}
