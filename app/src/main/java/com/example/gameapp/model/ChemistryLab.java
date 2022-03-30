package com.example.gameapp.model;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import Util.GameApi;

public class ChemistryLab extends AppCompatActivity {
    Button backButton,plusButton,minusButton,buyButton;
    TextView tv_electron_count, tv_description,gemCount,electronCount;
    ArrayList<Integer> iv_electrons = new ArrayList<Integer>();
    ImageView iv_element;
    int maxElectrons;
    GameApi gameApi=GameApi.getInstance();
    int gems;
    //Connection to FireStore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference= db.collection("Users");
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry_lab);


        plusButton = findViewById(R.id.plus_btn);
        minusButton = findViewById(R.id.minus_btn);
        backButton = findViewById(R.id.btn_back);
        buyButton=findViewById(R.id.btn_buy);
        gemCount=findViewById(R.id.gemCount);
        electronCount=findViewById(R.id.electronCount);
        gemCount.setText(Integer.toString(gameApi.getGems()));
        electronCount.setText(Integer.toString(gameApi.getElectrons()));
        maxElectrons=gameApi.getElectrons();
        gems = gameApi.getGems();

        firebaseAuth =  FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ChemistryLab.this,MenuScreen.class);
                startActivity(intent);
            }
        });
        iv_element=findViewById(R.id.iv_element);
        tv_electron_count=findViewById(R.id.tv_electron_count);
        tv_description=findViewById(R.id.tv_element_desc);
        tv_electron_count.setText("1");
        iv_electrons.add(R.drawable.h);
        iv_electrons.add(R.drawable.he);
        iv_electrons.add(R.drawable.li);
        iv_electrons.add(R.drawable.be);
        iv_electrons.add(R.drawable.b);
        iv_electrons.add(R.drawable.c);
        iv_electrons.add(R.drawable.n);
        iv_electrons.add(R.drawable.o);
        iv_electrons.add(R.drawable._f);
        iv_electrons.add(R.drawable.ne);
        iv_electrons.add(R.drawable.na);
        iv_electrons.add(R.drawable.mg);
        iv_electrons.add(R.drawable.al);
        iv_electrons.add(R.drawable.si);
        iv_electrons.add(R.drawable.p);
        iv_electrons.add(R.drawable.s);
        iv_electrons.add(R.drawable.cl);
        iv_electrons.add(R.drawable.ar);
        iv_electrons.add(R.drawable.k);
        iv_electrons.add(R.drawable.ca);
        iv_electrons.add(R.drawable.sc);
        iv_electrons.add(R.drawable.ti);
        iv_electrons.add(R.drawable.v);
        iv_electrons.add(R.drawable.cr);
        iv_electrons.add(R.drawable.mn);
        iv_electrons.add(R.drawable.fe);
        iv_electrons.add(R.drawable.co);
        iv_electrons.add(R.drawable.ni);
        iv_electrons.add(R.drawable.cu);
        iv_electrons.add(R.drawable._f);
        iv_electrons.add(R.drawable.zn);
        iv_electrons.add(R.drawable.ga);
        iv_electrons.add(R.drawable.ge);
        iv_electrons.add(R.drawable.as);
        iv_electrons.add(R.drawable.se);
        iv_electrons.add(R.drawable.br);
        iv_electrons.add(R.drawable.kr);
        iv_electrons.add(R.drawable.rb);
        iv_electrons.add(R.drawable.sr);
        iv_electrons.add(R.drawable.y);
        iv_electrons.add(R.drawable.zr);
        iv_electrons.add(R.drawable.nb);
        iv_electrons.add(R.drawable.mo);
        iv_electrons.add(R.drawable.tc);
        iv_electrons.add(R.drawable.ru);
        iv_electrons.add(R.drawable.rh);
        iv_electrons.add(R.drawable.pd);
        iv_electrons.add(R.drawable.ag);
        iv_electrons.add(R.drawable.cd);
        iv_electrons.add(R.drawable.in);
        iv_electrons.add(R.drawable.sn);
        iv_electrons.add(R.drawable.sb);
        iv_electrons.add(R.drawable.te);
        iv_electrons.add(R.drawable.i);
        iv_electrons.add(R.drawable.xe);
        iv_electrons.add(R.drawable.cs);
        iv_electrons.add(R.drawable.ba);
        iv_electrons.add(R.drawable.la);
        iv_electrons.add(R.drawable.ce);
        iv_electrons.add(R.drawable.pr);
        iv_electrons.add(R.drawable.nd);
        iv_electrons.add(R.drawable.pm);
        iv_electrons.add(R.drawable.sm);
        iv_electrons.add(R.drawable.eu);
        iv_electrons.add(R.drawable.gd);
        iv_electrons.add(R.drawable.tb);
        iv_electrons.add(R.drawable.dy);
        iv_electrons.add(R.drawable.ho);
        iv_electrons.add(R.drawable.er);
        iv_electrons.add(R.drawable.tm);
        iv_electrons.add(R.drawable.yb);
        iv_electrons.add(R.drawable.lu);
        iv_electrons.add(R.drawable.hf);
        iv_electrons.add(R.drawable.ta);
        iv_electrons.add(R.drawable.w);
        iv_electrons.add(R.drawable.re);
        iv_electrons.add(R.drawable.os);
        iv_electrons.add(R.drawable.ir);
        iv_electrons.add(R.drawable.pt);
        iv_electrons.add(R.drawable.au);
        iv_electrons.add(R.drawable.hg);
        iv_electrons.add(R.drawable.tl);
        iv_electrons.add(R.drawable.pb);
        iv_electrons.add(R.drawable.bi);
        iv_electrons.add(R.drawable.po);
        iv_electrons.add(R.drawable.at);
        iv_electrons.add(R.drawable.rn);
        iv_electrons.add(R.drawable.fr);
        iv_electrons.add(R.drawable.ra);
        iv_electrons.add(R.drawable.ac);
        iv_electrons.add(R.drawable.th);
        iv_electrons.add(R.drawable.pa);
        iv_electrons.add(R.drawable.u);
        iv_electrons.add(R.drawable.np);
        iv_electrons.add(R.drawable.pu);

        Elements elements = new Elements(Integer.parseInt(tv_electron_count.getText().toString()));
        tv_description.setText(elements.description.toUpperCase());
        View.OnClickListener plusButtonClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int e =Integer.parseInt(tv_electron_count.getText().toString());
                if(e<maxElectrons)
                    GetNextElement();
                else
                    Toast.makeText(ChemistryLab.this,"No More Elements to Show",Toast.LENGTH_SHORT).show();
            }
        };
        View.OnClickListener minusButtonClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int e =Integer.parseInt(tv_electron_count.getText().toString());
                if(e>1)
                    GetPreviousElement();
                else
                    Toast.makeText(ChemistryLab.this,"No More Elements to Show",Toast.LENGTH_SHORT).show();
            }
        };
        View.OnClickListener buyButtonClickListner=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameApi.getGems()>=10)
                {

                    gems = gameApi.getGems() - 10;
                    gameApi.setGems(gems);
                    gemCount.setText(Integer.toString(gems));
                    maxElectrons += 1;
                    gameApi.setElectrons(maxElectrons);
                    electronCount.setText(Integer.toString(maxElectrons));
                    if(user!=null) {
                        UpdateGems(gems);
                        UpdateElectrons(maxElectrons);
                    }
                    else
                    {
                        Database_class databaseHelper = new Database_class(ChemistryLab.this);
                        databaseHelper.UpdateGems(gems);
                        databaseHelper.UpdateElectrons(maxElectrons);
                    }
                }
                else
                {
                    Toast.makeText(ChemistryLab.this,"Do not have Enough Gems",Toast.LENGTH_SHORT).show();
                }
            }
        };
        plusButton.setOnClickListener(plusButtonClickListner);
        minusButton.setOnClickListener(minusButtonClickListner);
        buyButton.setOnClickListener(buyButtonClickListner);
    }
    void GetNextElement()
    {
        int electrons= Integer.parseInt(tv_electron_count.getText().toString())+1;
        if(electrons>=1&& electrons<=94)
        {
            tv_electron_count.setText(Integer.toString(electrons));
            Elements elements = new Elements(electrons);
            tv_description.setText(elements.description.toUpperCase());
            iv_element.setImageResource(iv_electrons.get(electrons-1));
        }
    }
    void GetPreviousElement()
    {
        int electrons= Integer.parseInt(tv_electron_count.getText().toString())-1;
        if(electrons>=1 && electrons<=94)
        {
            tv_electron_count.setText(Integer.toString(electrons));
            Elements elements = new Elements(electrons);
            tv_description.setText(elements.description.toUpperCase());
            iv_element.setImageResource(iv_electrons.get(electrons-1));
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
                            Toast.makeText(ChemistryLab.this,"Updating GemCount",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChemistryLab.this,"Updating GemCount Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(ChemistryLab.this,"Cannot Find Document",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void UpdateElectrons(int newMaxElectronCount)
    {
        Map<String,Object> details = new HashMap<>();

        details.put("Electrons",Integer.toString(newMaxElectronCount));
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
                            Toast.makeText(ChemistryLab.this,"Updating ElectronCount",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChemistryLab.this,"Updating ElectronCount Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(ChemistryLab.this,"Cannot Find Document",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}