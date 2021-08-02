package com.cs446.covidsafe.model;

public class QuestionFactory {
    public Question getQuestion(int questionNumber){

        if(questionNumber <= 4){
            return new MC(questionNumber);
        } else if(questionNumber <= 6){
            return new TF(questionNumber);
        }
        return null;
    }
}
