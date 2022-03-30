package com.example.gameapp.model;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import Util.GameApi;

public class MenuScreen extends AppCompatActivity {

    Button mathGameButton,physicsGameButton, chemistryButton, animalsButton,
    memoryGameButton,leaderboardButton;
    ImageButton signoutButton;
    TextView gemCount;
    GameApi gameApi= GameApi.getInstance();

    //Ads
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    //Connection to FireStore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private CollectionReference collectionReference= db.collection("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);


        mathGameButton = findViewById(R.id.math_game_btn);
        physicsGameButton = findViewById(R.id.physics_game_btn);
        chemistryButton =findViewById(R.id.chemistry_lab_btn);
        animalsButton =findViewById(R.id.animals_btn);
        memoryGameButton = findViewById(R.id.memory_game_btn);
        leaderboardButton =findViewById(R.id.leaderboard_btn);
        signoutButton=findViewById(R.id.iv_btn_signout);
        gemCount=findViewById(R.id.gemCount);
        gemCount.setText(Integer.toString(gameApi.getGems()));
        firebaseAuth =  FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder ().build();
        mAdView.loadAd(adRequest);
        AdRequest adRequest2= new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest2,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

        if (mInterstitialAd != null) {
            mInterstitialAd.show(MenuScreen.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
        mathGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MenuScreen.this,MathGame.class);
                startActivity(intent);
            }

        });
        physicsGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuScreen.this, PhysicsGame.class);
                startActivity(intent);
            }
        });
        chemistryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuScreen.this, ChemistryLab.class);
                startActivity(intent);
            }
        });
        animalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuScreen.this, DiscoverAnimals.class);
                startActivity(intent);
            }
        });
        memoryGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuScreen.this, MemoryGame.class);
                startActivity(intent);
            }
        });
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuScreen.this, Leaderboard.class);
                startActivity(intent);
            }
        });
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MenuScreen.this);
                builder.setMessage("Do you want to Sign Out App ?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent =new Intent(MenuScreen.this,HomeScreen.class);
                        startActivity(intent);
                        if(user!=null && firebaseAuth!=null)
                        {firebaseAuth.signOut();
                            finish();}
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}