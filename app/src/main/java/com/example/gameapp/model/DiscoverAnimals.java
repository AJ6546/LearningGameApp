package com.example.gameapp.model;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;


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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Util.GameApi;

public class DiscoverAnimals extends AppCompatActivity {
    Button backButton,nextButton,prevButton,unlockButton;
    TextView  tv_animal_desc,tv_animal_name,gemCount,animalCount;
    ArrayList<Integer> iv_animals = new ArrayList<Integer>();
    ArrayList<Integer> iv_UnlockedAnimals = new ArrayList<Integer>();
    String unlockedAnimals;
    String[] unlockedAnimalsArray;
    ImageView iv_animal;
    int pos=0;
    GameApi gameApi=GameApi.getInstance();
    //Connection to FireStore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference= db.collection("Users");
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    int gems;
    private AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_animals);

        nextButton = findViewById(R.id.next_btn);
        prevButton = findViewById(R.id.prev_btn);
        backButton = findViewById(R.id.btn_back);
        unlockButton=findViewById(R.id.btn_unlock);
        firebaseAuth =  FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DiscoverAnimals.this,MenuScreen.class);
                startActivity(intent);
            }
        });
        iv_animal=findViewById(R.id.iv_animal);
        tv_animal_desc=findViewById(R.id.tv_animal_desc);
        tv_animal_name=findViewById(R.id.tv_animal_name);
        gemCount=findViewById(R.id.gemCount);
        animalCount=findViewById(R.id.animalCount);
        Animals animals = new Animals(0);
        tv_animal_name.setText(animals.name.toUpperCase());
        tv_animal_desc.setText(animals.description.toUpperCase());
        iv_animals.add(R.drawable.barnowl);
        iv_animals.add(R.drawable.antelope);
        iv_animals.add(R.drawable.bat);
        iv_animals.add(R.drawable.bear);
        iv_animals.add(R.drawable.cheetah);
        iv_animals.add(R.drawable.chicken);
        iv_animals.add(R.drawable.crocodile);
        iv_animals.add(R.drawable.deer);
        iv_animals.add(R.drawable.elephant);
        iv_animals.add(R.drawable.falson);
        iv_animals.add(R.drawable.eagle);
        gems=gameApi.getGems();
        unlockedAnimals =gameApi.getAnimals();
        unlockedAnimalsArray= unlockedAnimals.split("_");
        for(int i=0;i<iv_animals.size();i++)
        {
            if(unlockedAnimals.contains(Integer.toString(i)))
            {
                iv_UnlockedAnimals.add(iv_animals.get(i));
            }
        }
        gemCount.setText(Integer.toString(gameApi.getGems()));
        animalCount.setText(Integer.toString(unlockedAnimalsArray.length));
        DisplayAnimal(Integer.parseInt(unlockedAnimalsArray[pos]));
        View.OnClickListener nextButtonClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos<unlockedAnimalsArray.length-1) {
                    pos += 1;
                    GetNextAnimal(Integer.parseInt(unlockedAnimalsArray[pos]));
                }
                else
                {
                    Toast.makeText(DiscoverAnimals.this,"No More Animals To Show",Toast.LENGTH_SHORT).show();
                }
            }
        };
        View.OnClickListener prevButtonClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos>0) {
                    pos -= 1;
                    GetPreviousAnimal(Integer.parseInt(unlockedAnimalsArray[pos]));
                }
                else
                {
                    Toast.makeText(DiscoverAnimals.this,"No More Animals To Show",Toast.LENGTH_SHORT).show();
                }
            }
        };
        View.OnClickListener unlockButtonClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gems>=20) {
                    //Update Gems
                    gems = gameApi.getGems() - 20;
                    gameApi.setGems(gems);
                    gemCount.setText(Integer.toString(gameApi.getGems()));
                    //Unlock New Animal
                    Random randomNumberMaker = new Random();
                    int id = randomNumberMaker.nextInt(10);
                    gameApi.setAnimals(gameApi.getAnimals() + "_" + Integer.toString(id));
                    unlockedAnimals = gameApi.getAnimals();
                    unlockedAnimalsArray = unlockedAnimals.split("_");
                    for (int i = 0; i < iv_animals.size(); i++) {
                        if (unlockedAnimals.contains(Integer.toString(i))) {
                            iv_UnlockedAnimals.add(iv_animals.get(i));
                        }
                    }
                    animalCount.setText(Integer.toString(unlockedAnimalsArray.length));
                    if(user!=null) {
                        UpdateGems(gems);
                        UpdateAnimals(unlockedAnimals);
                        }
                    else
                    {
                        Database_class databaseHelper = new Database_class(DiscoverAnimals.this);
                        databaseHelper.UpdateGems(gems);
                        databaseHelper.UpdateAnimals(unlockedAnimals);
                    }
                }
                else
                {
                    Toast.makeText(DiscoverAnimals.this,"You Don't Have Enough Gems",Toast.LENGTH_SHORT).show();
                }
            }
        };
        nextButton.setOnClickListener(nextButtonClickListner);
        prevButton.setOnClickListener(prevButtonClickListner);
        unlockButton.setOnClickListener(unlockButtonClickListner);
    }
    void GetNextAnimal(int animal_id)
    {
        if(animal_id>=0 && animal_id<=94)
        {
            Animals animals = new Animals(animal_id);
            tv_animal_desc.setText(animals.description.toUpperCase());
            tv_animal_name.setText(animals.name.toUpperCase());
            iv_animal.setImageResource(iv_animals.get(animal_id));
        }
    }
    void GetPreviousAnimal(int animal_id)
    {
        if(animal_id>=0 && animal_id<=94)
        {
            Animals animals = new Animals(animal_id);
            tv_animal_desc.setText(animals.description.toUpperCase());
            tv_animal_name.setText(animals.name.toUpperCase());
            iv_animal.setImageResource(iv_animals.get(animal_id));
        }
    }

    void DisplayAnimal(int animal_id)
    {
        if(animal_id>=0 && animal_id<=94)
        {
            Animals animals = new Animals(animal_id);
            tv_animal_desc.setText(animals.description.toUpperCase());
            tv_animal_name.setText(animals.name.toUpperCase());
            iv_animal.setImageResource(iv_animals.get(animal_id));
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
                            Toast.makeText(DiscoverAnimals.this,"Updating GemCount",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DiscoverAnimals.this,"Updating GemCount Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(DiscoverAnimals.this,"Cannot Find Document",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void UpdateAnimals(String animals)
    {
        Map<String,Object> details = new HashMap<>();

        details.put("Animals",animals);
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
                            Toast.makeText(DiscoverAnimals.this,"Updating Animals",Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(DiscoverAnimals.this,DiscoverAnimals.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DiscoverAnimals.this,"Updating Animals Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(DiscoverAnimals.this,"Cannot Find Document",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}