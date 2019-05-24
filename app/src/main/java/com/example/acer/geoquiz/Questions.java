package com.example.acer.geoquiz;

public class Questions {


    private int mTextResId;
    private boolean mAnswerTrue;

    public Questions(int textResid, boolean ans){

        mTextResId=textResid;
        mAnswerTrue=ans;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }


}
