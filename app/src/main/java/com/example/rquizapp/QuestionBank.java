package com.example.rquizapp;

import java.util.ArrayList;

public class QuestionBank {
    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<Integer> colorList = new ArrayList<>();


    QuestionBank(){
        Question ques1 = new Question(R.string.Question1,false);
        Question ques2 = new Question(R.string.Question2,true);
        Question ques3 = new Question(R.string.Question3,false);
        Question ques4 = new Question(R.string.Question4,true);
        Question ques5 = new Question(R.string.Question5,true);
        Question ques6 = new Question(R.string.Question6,false);
        Question ques7 = new Question(R.string.Question7,true);
        Question ques8 = new Question(R.string.Question8,true);
        Question ques9 = new Question(R.string.Question9,true);
        Question ques10 = new Question(R.string.Question10,false);

        questionList.add(ques1);
        questionList.add(ques2);
        questionList.add(ques3);
        questionList.add(ques4);
        questionList.add(ques5);
        questionList.add(ques6);
        questionList.add(ques7);
        questionList.add(ques8);
        questionList.add(ques9);
        questionList.add(ques10);

        colorList.add(R.color.YellowGreen);
        colorList.add(R.color.maroon);
        colorList.add(R.color.black);
        colorList.add(R.color.Indigo);
        colorList.add(R.color.GreenYellow);
        colorList.add(R.color.Crimson);
        colorList.add(R.color.DeepPink);
        colorList.add(R.color.Tomato);
        colorList.add(R.color.DodgerBlue);
        colorList.add(R.color.Lime);




    }
}
