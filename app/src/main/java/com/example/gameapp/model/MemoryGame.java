package com.example.gameapp.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Util.GameApi;

public class MemoryGame extends AppCompatActivity {
    Button backButton;
    private AdView mAdView;
    private ArrayList<MemoryCard> memoryCards = new ArrayList<>();
    private ArrayList<ImageButton> imgButtons = new ArrayList<>();
    ImageButton imageButton0,imageButton1,imageButton2,imageButton3,imageButton4,
            imageButton5,imageButton6,imageButton7;
    //boolean canPlay;
    ArrayList<Integer> images = new ArrayList<>();
    int indexOfSingleSelectedCard=-1;
    //Connection to FireStore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference= db.collection("Users");
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    GameApi gameApi = GameApi.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        backButton = findViewById(R.id.btn_back);
        firebaseAuth =  FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MemoryGame.this,MenuScreen.class);
                startActivity(intent);
            }
        });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder ().build();
        mAdView.loadAd(adRequest);
        imageButton0=findViewById(R.id.imageButton0);
        imageButton1=findViewById(R.id.imageButton1);
        imageButton2=findViewById(R.id.imageButton2);
        imageButton3=findViewById(R.id.imageButton3);
        imageButton4=findViewById(R.id.imageButton4);
        imageButton5=findViewById(R.id.imageButton5);
        imageButton6=findViewById(R.id.imageButton6);
        imageButton7=findViewById(R.id.imageButton7);

        images.add(R.drawable.ic_baseline_child_care);
        images.add(R.drawable.ic_baseline_cruelty_free);
        images.add(R.drawable.ic_baseline_sports_esports);
        images.add(R.drawable.ic_baseline_two_wheeler);
        images.addAll(images);
        Collections.shuffle(images);
        for (int i=0;i<8;i++)
        {
            memoryCards.add(new MemoryCard(false,false,images.get(i)));
        }
        //canPlay=true;
        imgButtons.add(imageButton0);
        imgButtons.add(imageButton1);
        imgButtons.add(imageButton2);
        imgButtons.add(imageButton3);
        imgButtons.add(imageButton4);
        imgButtons.add(imageButton5);
        imgButtons.add(imageButton6);
        imgButtons.add(imageButton7);
        View.OnClickListener imgButtonClickListner0=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(0);
                UpdateViews();
            }
        };
        View.OnClickListener imgButtonClickListner1=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(1);
                UpdateViews();
            }
        };
        View.OnClickListener imgButtonClickListner2=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(2);
                UpdateViews();
            }
        };
        View.OnClickListener imgButtonClickListner3=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(3);
                UpdateViews();
            }
        };
        View.OnClickListener imgButtonClickListner4=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(4);
                UpdateViews();
            }
        };
        View.OnClickListener imgButtonClickListner5=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(5);
                UpdateViews();
            }
        };
        View.OnClickListener imgButtonClickListner6=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(6);
                UpdateViews();
            }
        };
        View.OnClickListener imgButtonClickListner7=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btnClicked = (ImageButton) view;
                UpdateModel(7);
                UpdateViews();
            }
        };
        imageButton0.setOnClickListener(imgButtonClickListner0);
        imageButton1.setOnClickListener(imgButtonClickListner1);
        imageButton2.setOnClickListener(imgButtonClickListner2);
        imageButton3.setOnClickListener(imgButtonClickListner3);
        imageButton4.setOnClickListener(imgButtonClickListner4);
        imageButton5.setOnClickListener(imgButtonClickListner5);
        imageButton6.setOnClickListener(imgButtonClickListner6);
        imageButton7.setOnClickListener(imgButtonClickListner7);
    }
    void UpdateModel(int pos)
    {
        MemoryCard card = memoryCards.get(pos);
        if(card.isFaceUP)
        {
            Toast.makeText(this,"Invalid move!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(indexOfSingleSelectedCard==-1)
        {
            indexOfSingleSelectedCard=pos;
            RestoreCards();
        }
        else
        {
            CheckForMatch(indexOfSingleSelectedCard,pos);
            indexOfSingleSelectedCard=-1;
        }
        card.isFaceUP =!card.isFaceUP;
    }

    private void RestoreCards() {
        for(int i=0;i<8;i++)
        {
            if(!memoryCards.get(i).isMatched)
                memoryCards.get(i).isFaceUP =false;
        }
    }

    void CheckForMatch(int position1, int position2)
    {
        if(memoryCards.get(position1).identifier==memoryCards.get(position2).identifier)
        {
            memoryCards.get(position1).isMatched=true;
            memoryCards.get(position2).isMatched=true;
        }
    }
    void UpdateViews()
    {
        int count=0;
        for(int i=0;i< imgButtons.size();i++)
        {
            if (memoryCards.get(i).isFaceUP)
            {
                count++;
                imgButtons.get(i).setImageResource(memoryCards.get(i).identifier);}
            else {
                imgButtons.get(i).setImageResource(R.drawable.ic_baseline_control_camera);
            }
        }
        if(count==8)
        {
            int gems = gameApi.getGems() + 1;
            gameApi.setGems(gems);
            if(user!=null) {

                UpdateGems(gems);
            }
            else
            {
                Database_class databaseHelper = new Database_class(MemoryGame.this);
                databaseHelper.UpdateGems(gems);
            }
        }
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
                            Toast.makeText(MemoryGame.this,"Updating GemCount",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MemoryGame.this,"Updating GemCount Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(MemoryGame.this,"Cannot Find Document",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}