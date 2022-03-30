package com.example.gameapp.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.GameApi;

public class MathGame extends AppCompatActivity {

    Button btnStart, btnAnswer0,btnAnswer1,btnAnswer2,btnAnswer3, backButton;
    ProgressBar progressBar;
    TextView tv_score, tv_timer,tv_message,tv_question;
    int gems;
    Game g = new Game();
    GameApi gameApi= GameApi.getInstance();
    boolean updateGems;
    //Connection to FireStore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference= db.collection("Users");
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    //CountdownTimer Variables
    int secondsRemaining=30;
    int maxProgress=30;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        btnStart=findViewById(R.id.btn_start);
        btnAnswer0=findViewById(R.id.btn_answer0);
        btnAnswer1=findViewById(R.id.btn_answer1);
        btnAnswer2=findViewById(R.id.btn_answer2);
        btnAnswer3=findViewById(R.id.btn_answer3);
        backButton=findViewById(R.id.btn_back);
        firebaseAuth =  FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MathGame.this,MenuScreen.class);
                startActivity(intent);
            }
        });
        progressBar=findViewById(R.id.progress_bar);
        tv_score=findViewById(R.id.tv_score);
        tv_timer=findViewById(R.id.tv_timer);
        tv_message=findViewById(R.id.tv_message);
        tv_question=findViewById(R.id.tv_question);

        tv_score.setText("0pts");
        tv_timer.setText("0Sec");
        tv_message.setText("Press Go");
        tv_question.setText("");

        progressBar.setProgress(0);



         View.OnClickListener startBtnClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button startButton = (Button) view;
                startButton.setVisibility(View.INVISIBLE);
                btnAnswer0.setEnabled(true);
                btnAnswer1.setEnabled(true);
                btnAnswer2.setEnabled(true);
                btnAnswer3.setEnabled(true);
                g = new Game();
                progressBar.setProgress(0);
                gems=gameApi.getGems();
                updateGems=true;
                secondsRemaining=30;
                maxProgress=30;
                progressBar.setMax(maxProgress);
                if(timer!=null)
                {
                    timer.cancel();
                }
                updateTimer(maxProgress);
                NextTurn();
                timer.start();
            }
        };
        View.OnClickListener answerBtnClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btnClicked = (Button) view;
                float answerSelected = Float.parseFloat(btnClicked.getText().toString());
                g.CheckAnswer(answerSelected);
                tv_score.setText(Integer.toString(g.getScore())+" pts");
                secondsRemaining=secondsRemaining+g.getSecondsRemainingAddon();
                maxProgress=maxProgress+g.getSecondsRemainingAddon();
                progressBar.setMax(maxProgress);
                timer.cancel();
                updateTimer(maxProgress);
                NextTurn();
                timer.start();
            }
        };
         btnStart.setOnClickListener(startBtnClickListner);

         btnAnswer0.setOnClickListener(answerBtnClickListner);
         btnAnswer1.setOnClickListener(answerBtnClickListner);
         btnAnswer2.setOnClickListener(answerBtnClickListner);
         btnAnswer3.setOnClickListener(answerBtnClickListner);
    }
     void NextTurn()
     {
         //Create New Questions
         g.MakeNewQuestion(0);
         float[] answers = g.getCurrentQuestion().getAnswers();
         tv_question.setText(g.getCurrentQuestion().getQuestionPhrase());
         //Set Text on Answer Buttons
         btnAnswer0.setText(Float.toString(answers[0]));
         btnAnswer1.setText(Float.toString(answers[1]));
         btnAnswer2.setText(Float.toString(answers[2]));
         btnAnswer3.setText(Float.toString(answers[3]));
         //Enable Answer Button
         btnAnswer0.setEnabled(true);
         btnAnswer1.setEnabled(true);
         btnAnswer2.setEnabled(true);
         btnAnswer3.setEnabled(true);
         //Start Timer
         tv_message.setText(g.getNumberCorrect()+"/"+(g.getTotalQuestions()-1));
     }
     void updateTimer(int maxProg)
     {
         timer = new CountDownTimer(maxProg*1000,1000) {
             @Override
             public void onTick(long l) {
                 if(secondsRemaining<0)
                 {
                     if(user!=null) {
                         if (g.getScore() > gameApi.getHighScore()) {
                             UpdateHighScore(g.getScore());
                         }
                         gems = gems + (g.getScore() / 100);
                         if (gems > 0 && updateGems) {
                             UpdateGems(gems);
                             gameApi.setGems(gems);
                             updateGems = false;
                         }
                     }
                     else
                     {
                         Database_class databaseHelper = new Database_class(MathGame.this);
                         if (g.getScore() > gameApi.getHighScore()) {
                             databaseHelper.UpdateHighScore(g.getScore());
                             gameApi.setHighScore((double)g.getScore());
                         }
                         gems = gems + (g.getScore() / 1);
                         if (gems > 0 && updateGems) {
                             databaseHelper.UpdateGems(gems);
                             gameApi.setGems(gems);
                             updateGems = false;
                         }
                     }
                     timer.onFinish();
                     return;
                 }
                 secondsRemaining--;
                 tv_timer.setText(Integer.toString(secondsRemaining) + " sec");
                 progressBar.setProgress(maxProg - secondsRemaining);
             }

             @Override
             public void onFinish() {
                 btnAnswer0.setEnabled(false);
                 btnAnswer1.setEnabled(false);
                 btnAnswer2.setEnabled(false);
                 btnAnswer3.setEnabled(false);
                 tv_message.setText("Time up! " + g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));


                 final Handler handler = new Handler();
                 handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                            btnStart.setVisibility(View.VISIBLE);
                     }
                 },4000);
             }};
     }
    private void UpdateHighScore(int newHighScore)
    {
        Map<String,Object> details = new HashMap<>();

        details.put("HighScore",(double)newHighScore);
        collectionReference.whereEqualTo("UserID",gameApi.getUserID())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String documentId= documentSnapshot.getId();
                    collectionReference.document(documentId).update(details).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MathGame.this,"Updating HighScore",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MathGame.this,"Updating HighScore Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(MathGame.this,"Cannot Find Document",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void UpdateGems(int newGemCount)
    {
        Map<String,Object> details = new HashMap<>();

        details.put("Gems",Integer.toString(newGemCount));
        collectionReference.whereEqualTo("UserID",gameApi.getUserID())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String documentId= documentSnapshot.getId();
                    collectionReference.document(documentId).update(details).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MathGame.this,"Updating GemCount",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MathGame.this,"Updating GemCount Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(MathGame.this,"Cannot Find Document",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}