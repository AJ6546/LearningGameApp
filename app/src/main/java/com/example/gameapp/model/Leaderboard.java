package com.example.gameapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.GameApi;

public class Leaderboard extends AppCompatActivity {
    Button backButton;
    private AdView mAdView;
    TextView rank1, rank2,rank3, rank4, rank5, yourRank;
    GameApi gameApi= GameApi.getInstance();
    List<TextView> rankList = new ArrayList<>();
    //Connection to FireStore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference= db.collection("Users");
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        backButton = findViewById(R.id.btn_back);
        rank5=findViewById(R.id.tv_rank05);
        rank4=findViewById(R.id.tv_rank04);
        rank3=findViewById(R.id.tv_rank03);
        rank2=findViewById(R.id.tv_rank02);
        rank1=findViewById(R.id.tv_rank01);
        yourRank =findViewById(R.id.tv_your_rank);
        rankList.add(rank1);
        rankList.add(rank2);
        rankList.add(rank3);
        rankList.add(rank4);
        rankList.add(rank5);

        firebaseAuth =  FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Leaderboard.this,MenuScreen.class);
                startActivity(intent);
            }
        });
        if(user!=null)
        {DisplayScoreBoard();}
        else
        {
            yourRank.setText("Your Highest Score is:"+ Double.toString(gameApi.getHighScore()));
        }
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder ().build();
        mAdView.loadAd(adRequest);
    }
    void DisplayScoreBoard()
    {
        List<String> userNameList = new ArrayList<String>();
        List<Double> highscoreList = new ArrayList<Double>();
        collectionReference.orderBy("HighScore").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                if(error!=null)
                    return;
                assert  queryDocumentSnapshots!=null;
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                        if(snapshot.getString("UserID").equals(gameApi.getUserID()))
                        {
                            userNameList.add("You");
                        }
                        else {
                            userNameList.add(snapshot.getString("UserName"));
                        }
                        highscoreList.add(snapshot.getDouble("HighScore"));
                    }

                    Collections.reverse(userNameList);
                    Collections.reverse(highscoreList);
                    for(int i=4;i>=0;i--)
                    {
                        if(i < userNameList.size())
                            rankList.get(i).setText(userNameList.get(i)+"\nScore:"+Double.toString(highscoreList.get(i)));
                    }
                    for(int i=0;i<userNameList.size();i++)
                    {
                        if(userNameList.get(i).equals(("You")))
                            yourRank.setText("You: Rank #"+Integer.toString(i+1)+"\nScore:"+Double.toString(highscoreList.get(i)));

                    }
                }
            }
        });
    }
}