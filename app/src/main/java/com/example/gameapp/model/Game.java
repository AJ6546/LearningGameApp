package com.example.gameapp.model;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    ArrayList<Question> questions;
    int numberCorrect;
    int numberIncorrect;
    int totalQuestions;
    int score;
    Question currentQuestion;
    int numberOfOperands;
    int typeLmt;
    int secondsRemainingAddon;



    public Game() {
        numberCorrect = 0;
        numberIncorrect = 0;
        totalQuestions = 0;
        score = 0;
        currentQuestion = new Question(10,3,0,0);
        questions = new ArrayList<Question>();
        numberOfOperands=2;
        typeLmt=2;
        secondsRemainingAddon =0;
    }

    public void MakeNewQuestion(int questionType)
    {
        if(questionType==1)
        {numberOfOperands=3;}
        Random randomNumberMaker = new Random();
        if(numberCorrect>0 && numberCorrect%10==0)
        {
            if(typeLmt<5 && questionType==0)
                typeLmt++;
        }
        int type= randomNumberMaker.nextInt(typeLmt);
        if(numberCorrect>0 && numberCorrect%10==0)
        {
            if(numberOfOperands<7)
                numberOfOperands++;
        }
        currentQuestion = new Question(totalQuestions*2+5,numberOfOperands,type, questionType);
        totalQuestions++;
        questions.add(currentQuestion);
    }
    public boolean CheckAnswer(double submittedAnswer)
    {
        boolean isCorrect;
        if(currentQuestion.getAnswer()==submittedAnswer)
        {
            numberCorrect++;
            isCorrect=true;
        }
        else{
            numberIncorrect++;
            isCorrect=false;
        }

        score =  numberCorrect*10 -numberIncorrect*5;
        if(isCorrect)
            secondsRemainingAddon =10;
        else
            secondsRemainingAddon =0;
        return isCorrect;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
    public int getNumberOfOperands() {
        return numberOfOperands;
    }

    public void setNumberOfOperands(int numberOfOperands) {
        this.numberOfOperands = numberOfOperands;
    }

    public int getTypeLmt() {
        return typeLmt;
    }

    public void setTypeLmt(int typeLmt) {
        this.typeLmt = typeLmt;
    }

    public int getSecondsRemainingAddon() {
        return secondsRemainingAddon;
    }

    public void setSecondsRemainingAddon(int secondsRemainingAddon) {
        this.secondsRemainingAddon = secondsRemainingAddon;
    }
}
