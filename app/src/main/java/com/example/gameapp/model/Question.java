package com.example.gameapp.model;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Question {
    // These are the number of Operands we have at a particular round
    private int numberOfOperands;
    // This is an array of operands we have got. The length is equal to numberOfOperands
    private int[] operands;
    // These are the 4 answer choices
    private float[] answers;
    // This is the correct answer
    private float answer;
    // This is the upper limit of Operands
    private int upperLimit;
    // This is the final Question in string
    private String questionPhrase;
    // Position where correct answer is set to appear
    private int answerPosition;
    // Type of Question Calculator-Min-Max-Area-Volume
    private int type;
    // Type of Question Math-Physics
    private int questionType;

    public Question(int upperLimit, int numberOfOperands, int type, int questionType) {

        this.upperLimit = upperLimit;
        this.numberOfOperands = numberOfOperands;
        this.operands = new int[numberOfOperands];
        this.questionType=questionType;
        Random randomNumberMaker = new Random();
        this.questionPhrase="";
        if (this.questionType == 0)
        {
            switch (type) {
                case 0:
                    for (int i = 0; i < numberOfOperands; i++) {
                        this.operands[i] = randomNumberMaker.nextInt(upperLimit * 2);
                    }
                    int[] operators = new int[numberOfOperands - 1];
                    for (int i = 0; i < numberOfOperands - 1; i++) {
                        operators[i] = ChooseOperator(this.operands[i + 1]);
                    }
                    this.answer = (float) GetMath(operators[0], this.operands[0], this.operands[1]);
                    this.questionPhrase = "(" + this.operands[0] + GetOperator(operators[0]) + this.operands[1] + ")";
                    for (int i = 1; i < numberOfOperands - 1; i++) {
                        this.answer = (float) GetMath(operators[i], answer, this.operands[i + 1]);
                        this.questionPhrase = "(" + this.questionPhrase + GetOperator(operators[i]) + this.operands[i + 1] + ")";
                    }
                    break;
                case 1:
                    for (int i = 0; i < numberOfOperands; i++) {
                        this.operands[i] = randomNumberMaker.nextInt(upperLimit * 2);
                    }


                    int min_max = randomNumberMaker.nextInt(2);

                    for (int i = 0; i < numberOfOperands; i++) {
                        this.questionPhrase += this.operands[i] + ", ";
                    }
                    Arrays.sort(this.operands);
                    if (min_max == 0) {
                        this.answer = (float) this.operands[0];
                        this.questionPhrase += " Find the Minimum Number";
                    } else {
                        this.answer = (float) this.operands[this.operands.length - 1];
                        this.questionPhrase += " Find the Maximum Number";
                    }
                    break;
                case 2:
                    this.operands[0] = randomNumberMaker.nextInt((int) upperLimit);
                    this.operands[1] = randomNumberMaker.nextInt((int) upperLimit);
                    this.operands[2] = randomNumberMaker.nextInt((int) upperLimit);
                    int figure = randomNumberMaker.nextInt(8);
                    switch (figure) {
                        case 0:
                            this.answer = (float) (Math.PI * this.operands[0] * this.operands[0]);
                            this.questionPhrase = "Find Area of Circle with radius " + this.operands[0];
                            break;
                        case 1:
                            this.answer = (float) this.operands[0] * this.operands[0];
                            this.questionPhrase = "Find Area of Square with side " + this.operands[0];
                            break;
                        case 2:
                            this.answer = (float) this.operands[0] * this.operands[1];
                            this.questionPhrase = "Find Area of Rectangle with Sides " + this.operands[0] + " and " + this.operands[1];
                            break;
                        case 3:
                            float s = (float) (this.operands[0] + this.operands[1] + this.operands[2]) / 2;
                            this.answer = (float) Math.sqrt(s * (s - this.operands[0]) * (s - this.operands[1]) * (s - this.operands[2]));
                            this.questionPhrase = "Find Approximate Area of Triangle with Sides " +
                                    this.operands[0] + "  " + this.operands[1] + " and " + this.operands[2];
                        case 4:
                            this.answer = (float) (Math.PI * 2 * this.operands[0]);
                            this.questionPhrase = "Find Circumference of Circle with radius " + this.operands[0];
                            break;
                        case 5:
                            this.answer = (float) 4 * this.operands[0];
                            this.questionPhrase = "Find Perimeter of Square with sides " + this.operands[0];
                            break;
                        case 6:
                            this.answer = (float) 2 * this.operands[0] * this.operands[1];
                            this.questionPhrase = "Find Perimeter of Rectangle with sides " + this.operands[0] + " and " + this.operands[1];
                            break;
                        case 7:
                            this.answer = (float) this.operands[0] + this.operands[1] + this.operands[2];
                            this.questionPhrase = "Find Perimeter of Triangle with sides " +
                                    this.operands[0] + "  " + this.operands[1] + " and " + this.operands[2];
                            break;
                    }
                case 3:
                    this.operands[0] = randomNumberMaker.nextInt(upperLimit);
                    this.operands[1] = randomNumberMaker.nextInt(upperLimit);
                    this.operands[2] = randomNumberMaker.nextInt(upperLimit);
                    int shape = randomNumberMaker.nextInt(2);
                    switch (shape) {
                        case 0:
                            this.answer = (float) (1.33 * Math.PI * Math.pow(this.operands[0], 3));
                            this.questionPhrase = "Find the Volume of Sphere with Radius " + this.operands[0];
                            break;
                        case 1:
                            this.answer = (float) (0.67 * Math.PI * Math.pow(this.operands[0], 3));
                            this.questionPhrase = "Find the Volume of Hemisphere with Radius " + this.operands[0];
                            break;
                        case 2:
                            this.answer = (float) Math.pow(this.operands[0], 3);
                            this.questionPhrase = "Find the Volume of Cube with sides " + this.operands[0];
                            break;
                        case 3:
                            this.answer = (float) this.operands[0] * this.operands[1] * this.operands[2];
                            this.questionPhrase = "Find the Volume of Cuboid with sides " + this.operands[0] + ", " +
                                    this.operands[1] + " and " + this.operands[2];
                            break;
                        case 4:
                            this.answer = (float) (Math.PI * Math.pow(this.operands[0], 2) * this.operands[1]);
                            this.questionPhrase = "Find the Approximate Volume of Cylinder with radius " + this.operands[0] + " and Height " +
                                    this.operands[1];
                            break;
                        case 5:
                            this.answer = (float) ((Math.PI * Math.pow(this.operands[0], 2) * this.operands[1]) / 3);
                            this.questionPhrase = "Find the Approximate Volume of Cone with radius " + this.operands[0] + " and Height " +
                                    this.operands[1];
                            break;
                    }
                    break;
                case 4:
                    this.operands[0] = randomNumberMaker.nextInt((int) upperLimit);
                    this.operands[1] = randomNumberMaker.nextInt((int) upperLimit);
                    this.operands[2] = randomNumberMaker.nextInt((int) upperLimit);
                    shape = randomNumberMaker.nextInt(11);
                    switch (shape) {
                        case 0:
                            this.answer = (float) (4 * Math.PI * Math.pow(this.operands[0], 2));
                            this.questionPhrase = "Find the Surface Area of a Sphere of Radius " + this.operands[0];
                            break;
                        case 1:
                            this.answer = (float) (3 * Math.PI * Math.pow(this.operands[0], 2));
                            this.questionPhrase = "Find the Total Surface Area of a Hemisphere of Radius " + this.operands[0];
                            break;
                        case 2:
                            this.answer = (float) (2 * Math.PI * Math.pow(this.operands[0], 2));
                            this.questionPhrase = "Find the Curved Surface Area of a Hemisphere of Radius " + this.operands[0];
                            break;
                        case 3:
                            this.answer = (float) (6 * Math.pow(this.operands[0], 2));
                            this.questionPhrase = "Find the Total Surface Area of a Cube of Side " + this.operands[0];
                            break;
                        case 4:
                            this.answer = (float) (4 * Math.pow(this.operands[0], 2));
                            this.questionPhrase = "Find the Lateral Surface Area of a Cube of Side " + this.operands[0];
                            break;
                        case 5:
                            this.answer = (float) 2 * (this.operands[0] * this.operands[1] + this.operands[1] * this.operands[2] + this.operands[0] * this.operands[2]);
                            this.questionPhrase = "Find the Total Surface Area of a Cuboid of Side " + this.operands[0] +
                                    ", " + this.operands[1] + " and " + this.operands[2];
                            break;
                        case 6:
                            this.answer = (float) 2 * (this.operands[0] * this.operands[2] + this.operands[1] * this.operands[2]);
                            this.questionPhrase = "Find the Lateral Surface Area of a Cuboid of Length " + this.operands[0] +
                                    ", Width " + this.operands[1] + " and Height " + this.operands[2];
                            break;
                        case 7:
                            this.answer = (float) ((2 * Math.PI * this.operands[0] * this.operands[1]) + (2 * Math.PI * this.operands[0] * this.operands[0]));
                            this.questionPhrase = "Find the Total Surface Area of a Cylinder of Radius " + this.operands[0] +
                                    ", Height " + this.operands[1];
                            break;
                        case 8:
                            this.answer = (float) (2 * Math.PI * this.operands[0] * this.operands[1]);
                            this.questionPhrase = "Find the Curved Surface Area of a Cylinder of Radius " + this.operands[0] +
                                    ", Height " + this.operands[1];
                            break;
                        case 9:
                            this.answer = (float) ((Math.PI * this.operands[0]) * (this.operands[0] + Math.sqrt(Math.pow(this.operands[0], 2) + Math.pow(this.operands[1], 2))));
                            this.questionPhrase = "Find the Total Surface Area of a Cylinder of Radius " + this.operands[0] +
                                    ", Height " + this.operands[1];
                            break;
                        case 10:
                            this.answer = (float) (Math.PI * this.operands[0] * Math.sqrt(Math.pow(this.operands[0], 2) + Math.pow(this.operands[1], 2)));
                            this.questionPhrase = "Find the Curved Surface Area of a Cylinder of Radius " + this.operands[0] +
                                    ", Height " + this.operands[1];
                            break;
                    }
            }
        }
        else
        {
            this.operands[0] = randomNumberMaker.nextInt((int) upperLimit);
            this.operands[1] = randomNumberMaker.nextInt((int) upperLimit);
            this.operands[2] = randomNumberMaker.nextInt((int) upperLimit);
            //speed = dist/time
            switch(type) {
                case 0:
                    int n = randomNumberMaker.nextInt(3);
                    switch (n) {
                        case 0:
                            this.answer = (float) this.operands[0] / this.operands[1];
                            this.questionPhrase = "Find the speed of a car if it travels " +
                                    this.operands[0] + " miles in " + this.operands[1] + " hours";
                            break;
                        case 1:
                            this.answer = (float) this.operands[0] * this.operands[1];
                            this.questionPhrase = "Find the distance travelled by a car in " +
                                    this.operands[0] + "hours, if it moves at " + this.operands[1] + " miles/hours";
                            break;
                        case 2:
                            this.answer = (float) this.operands[0] / this.operands[1];
                            this.questionPhrase = "Find the time taken by a car to travel " +
                                    this.operands[0] + " miles, if it moves at " + this.operands[1] + " miles/hours";
                            break;
                        case 3:
                            this.answer = (float) this.operands[1] / this.operands[0];
                            this.questionPhrase = " What is the acceleration of the " + this.operands[0] +
                                    " kg box that has " + this.operands[1] + " N of force applied to the right?";
                            break;
                        case 4:
                            if (this.operands[1] > this.operands[2])
                                this.answer = (float) (this.operands[1] - this.operands[2]) / this.operands[0];
                            else
                                this.answer = (float) (this.operands[2] - this.operands[1]) / this.operands[0];
                            this.questionPhrase = " What is the acceleration of the " + this.operands[0] +
                                    " kg box that has " + this.operands[1] + " N of force applied to the right and " + this.operands[2] + " to the left ?";
                            break;
                        case 5:
                            this.answer = (float) (this.operands[1] * this.operands[0]);
                            this.questionPhrase = " How much force is required to move " + this.operands[0] +
                                    " kg box by " + this.operands[1] + " m/s^2 ?";
                            break;
                        case 6:
                            this.answer = (float) (this.operands[0] * 9.8);
                            this.questionPhrase = " What is the Force acting on freely falling body" +
                                    " if it has a mass of " + this.operands[0] +
                                    " g=9.8m/s";
                            break;
                    }
                    break;
                case 1:
                    n = randomNumberMaker.nextInt(3);
                    switch(n)
                    {
                        case 0:
                            this.answer = (float) (this.operands[0] + (this.operands[1] * this.operands[2]));
                            this.questionPhrase = "What is the velocity of a car after "+this.operands[2]+"secs if " +
                                    "it has an initial velocity of" +
                                    +this.operands[0]+ "m/s and accelerates at "+this.operands[1]+" m/s^2";
                            break;
                        case 1:
                            this.answer = (float) (this.operands[1] - (this.operands[0] * this.operands[2]));
                            this.questionPhrase =" What was the initial velocity of a car with uniform acceleration of " +
                                    this.operands[0]+" m/s^2 if it moves at "+this.operands[1]+" m/s after "+this.operands[2]
                                    +" secs";
                            break;
                        case 2:
                            this.answer = (float) ((this.operands[1] - this.operands[0])/ this.operands[2]);
                            this.questionPhrase =" What was the acceleration of a car if it starts at a velocity of " +
                                this.operands[0]+" m/s and reaches"+this.operands[1]+" m/s in "+this.operands[2]+" secs ";
                            break;
                        case 3:
                            this.answer = (float) ((this.operands[1] - this.operands[0])/ this.operands[2]);
                            this.questionPhrase =" What was the time taken by a car car if it starts at a velocity of " +
                                    this.operands[0]+" m/s and reaches"+this.operands[1]+" m/s " +
                                    "accelerating uniformly at "+this.operands[2]+" m/s^2 ";
                            break;
                    }
                    break;
            }
        }
            this.answerPosition = randomNumberMaker.nextInt(4);
            this.answers = new float[]{0, 1, 2, 3};
            int randBuffer = randomNumberMaker.nextInt(5);
            int pos= randomNumberMaker.nextInt(4);
            this.answers[0] = this.answer + randBuffer + 1;
            this.answers[1] = this.answer - randBuffer - 1;
            this.answers[2] = this.answer + randBuffer + 2;
            this.answers[3] = this.answer - randBuffer - 2;
            this.answers[pos] = this.answer;
    }


    int ChooseOperator(int nextNum)
    {
        Random randomNumberMaker = new Random();
        int operator = randomNumberMaker.nextInt(4);
        if(operator==3 && nextNum==0)
        {
            ChooseOperator(nextNum);
        }
        return operator;
    }
    String GetOperator(int operator)
    {
        String op="";
        switch (operator)
        {
            case 0:
                op = "+";
                break;
            case 1:
                op = "-";
                break;
            case 2:
                op = "*";
                break;
            case 3:
                op = "/";
                break;
        }
        return op;
    }
    float GetMath(int operator,float firstNumber, float secondNumber)
    {
        float ans=0;
        switch (operator)
        {
            case 0:
                ans = firstNumber+secondNumber;
                break;
            case 1:
                ans = firstNumber-secondNumber;
                break;
            case 2:
                ans = firstNumber*secondNumber;
                break;
            case 3:
                ans = firstNumber/secondNumber;
                break;
        }
        return ans;
    }


    // Getters and Setters
    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public float[] getAnswers() {
        return answers;
    }

    public void setAnswers(float[] answers) {
        this.answers = answers;
    }

    public float getAnswer() {
        return answer;
    }

    public void setAnswer(float answer) {
        this.answer = answer;
    }

    public int getNumberOfOperands() {
        return numberOfOperands;
    }

    public void setNumberOfOperands(int numberOfOperands) {
        this.numberOfOperands = numberOfOperands;
    }

    public int[] getOperands() {
        return operands;
    }

    public void setOperands(int[] operands) {
        this.operands = operands;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
}
