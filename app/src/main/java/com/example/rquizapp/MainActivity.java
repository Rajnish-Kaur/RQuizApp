package com.example.rquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    int QuestionId;
    int ColorId;
    QuestionBank obj = new QuestionBank();
    Fragement_question fragmentObj;
    Button trueButton;
    Button falseButton;
    StorageManager storageObject = new StorageManager();
    ProgressBar simpleProgressBar;
    Boolean tag;
    int totalScore;
    String getAverageDialogString;
//    int attemptCount;
//    int totalAverage;


    public void UpdateFragment(int qId, int colorId) {

        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.Fragment_container);
        fragmentObj = Fragement_question.newInstance(qId, colorId);
        manager.beginTransaction().replace(R.id.Fragment_container, fragmentObj).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Getting the saved state
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("QuestionIndex");
        }

        QuestionId = obj.questionList.get(index).question;
        ColorId = obj.colorList.get(index);
        UpdateFragment(QuestionId, ColorId);

        trueButton = (Button) findViewById(R.id.True);
        falseButton = (Button) findViewById(R.id.False);
// initiate the progress bar
        simpleProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        // 100 maximum value for the progress value
        simpleProgressBar.setMax(100);
        // 0 default progress value for the progress bar
        simpleProgressBar.setProgress(0);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonClicked();
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonClicked();
            }
        });
    }

    //Saving the state

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("QuestionIndex", index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.average: {
                String message = storageObject.GetData(MainActivity.this);
                int attemptCount = storageObject.CountNumberOfAttempts();
                int totalAverage = storageObject.CountAverageScore();
                System.out.println("Average Score = " + totalAverage);
                String dialogMessage = "Your correct answers are " + totalAverage
                        + " in " + attemptCount + " attempts !!"; //String to display in dialog box
                System.out.println(dialogMessage);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(dialogMessage);
                builder.setPositiveButton("OK", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }

            case R.id.reset_data:{
                storageObject.ResetData(MainActivity.this);
                break;
            }
        }
        return true;
    }

    // create function for True/False button click
    public void ButtonClicked() {
        if (index < obj.questionList.size()-1) {
            if(trueButton.isPressed()){
                tag = true;
            }
            else{
                tag = false;
            }
            // Check if answer is correct or incorrect
            if(tag==obj.questionList.get(index).answer){
                totalScore++;
                Toast.makeText(this, R.string.CorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            if(tag!=obj.questionList.get(index).answer) {
                Toast.makeText(this, R.string.IncorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            System.out.println("*** Total Score ****" + totalScore);

            index++;
            QuestionId = obj.questionList.get(index).question;
            ColorId = obj.colorList.get(index);
            System.out.println("****Button Clicked*****");

            UpdateFragment(QuestionId, ColorId);
            simpleProgressBar.setProgress(simpleProgressBar.getProgress()+10);


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Your Scores are"+ "\t" + totalScore +"\t"+ "out of 10 !!");
            getAverageDialogString = totalScore +"/" + 10 + "#";
            builder.setPositiveButton("Save", (dialogInterface, i) -> storageObject.SaveData
                    (MainActivity.this,getAverageDialogString));
            totalScore=0;
            builder.setNegativeButton("Ignore",null);
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            index = 0;
            Collections.shuffle(obj.questionList);
            Collections.shuffle(obj.colorList);
            UpdateFragment(obj.questionList.get(index).question,obj.colorList.get(index));
            simpleProgressBar.setProgress(0);
        }
    }
}