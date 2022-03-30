package com.example.gameapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Util.GameApi;

public class HomeScreen extends AppCompatActivity {

    Button startButton, loginButton, registerButton, exitButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference collectionReference= db.collection("Users");
    private EditText emailText, passwordText, userNameText;
    private AdView mAdView;
    MediaPlayer song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        startButton = findViewById(R.id.play_as_guest_btn);
        loginButton = findViewById(R.id.login_btn);
        registerButton=findViewById(R.id.register_btn);
        exitButton = findViewById(R.id.btn_exit);
        emailText =findViewById(R.id.email);
        userNameText =findViewById(R.id.userName);
        passwordText =findViewById(R.id.Password);
        firebaseAuth=  FirebaseAuth.getInstance();

        if(song!=null)
            song.stop();
        song = MediaPlayer.create(HomeScreen.this,R.raw.springflower);
        song.start();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder ().build();
        mAdView.loadAd(adRequest);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayAsGest();
                Intent intent =new Intent(HomeScreen.this,MenuScreen.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(emailText.getText().toString())&& !TextUtils.isEmpty(passwordText.getText().toString()))
                {
                    String email=emailText.getText().toString().trim(),
                            password =passwordText.getText().toString().trim();
                    LoginUser(email, password);}
                else
                {
                    Toast.makeText(HomeScreen.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            currentUser=firebaseAuth.getCurrentUser();
            if(currentUser!=null)
            {
                AutoLogin();
            }
            else
            {
                Toast.makeText(HomeScreen.this,"Please Login or Register or Play as a Guest",Toast.LENGTH_SHORT).show();
            }
            }
        };
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(emailText.getText().toString())&&
                        !TextUtils.isEmpty(passwordText.getText().toString()) &&
                        !TextUtils.isEmpty(userNameText.getText().toString()))
                {
                    String email=emailText.getText().toString().trim(),
                            password =passwordText.getText().toString().trim(),
                            userName =userNameText.getText().toString().trim();
                    CreateUserAccount(email,userName ,password);}
                else
                {
                    Toast.makeText(HomeScreen.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
                builder.setMessage("Do you want to Exit App ?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                        finish();
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
    private void CreateUserAccount(String email, String userName,String password)
    {
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(userName))
        {
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //We take user toMenu Screen
                                currentUser=firebaseAuth.getCurrentUser();
                                assert  currentUser!=null;
                                String currentUserId=currentUser.getUid();

                                //Create User Map to create user in User Collection
                                Map<String,Object> userObj = new HashMap<>();
                                Random randomNumberMaker = new Random();
                                String animals = Integer.toString(randomNumberMaker.nextInt(10));
                                userObj.put("UserID",currentUserId);
                                userObj.put("Email",email);
                                userObj.put("UserName",userName);
                                userObj.put("HighScore",0.0);
                                userObj.put("Gems","0");
                                userObj.put("Electrons","1");
                                userObj.put("Animals",animals);;
                                // Save To FireStore Database
                                collectionReference.add(userObj).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.getResult().exists())
                                                {
                                                    GameApi gameApi=GameApi.getInstance();
                                                    gameApi.setUserID(currentUserId);
                                                    Intent intent = new Intent(HomeScreen.this,MenuScreen.class);
                                                    intent.putExtra("UserID",currentUserId);
                                                    intent.putExtra("Email",email);
                                                    intent.putExtra("UserName",userName);
                                                    intent.putExtra("HighScore",0.0);
                                                    intent.putExtra("Gems","0");
                                                    intent.putExtra("Electrons","1");
                                                    intent.putExtra("Animals","1");
                                                    startActivity(intent);
                                                    gameApi.setEmail(email);
                                                    gameApi.setUserName(userName);
                                                    gameApi.setHighScore(0.0);
                                                    gameApi.setGems(0);
                                                    gameApi.setElectrons(1);
                                                    gameApi.setAnimals(animals);
                                                }
                                                else
                                                {
                                                    Toast.makeText(HomeScreen.this,"Failed To Create Account",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(HomeScreen.this,"This Account Already Exists",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomeScreen.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(HomeScreen.this,"Empty Fields",Toast.LENGTH_SHORT).show();
        }
    }

    private void LoginUser(String email, String password)
    {
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        currentUser=firebaseAuth.getCurrentUser();
                        assert  currentUser!=null;
                        String currentUserId=currentUser.getUid();
                        collectionReference.whereEqualTo("UserID",currentUserId)
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                                    if(error!=null)
                                        return;
                                    assert  queryDocumentSnapshots!=null;
                                    if(!queryDocumentSnapshots.isEmpty())
                                    {
                                        for(QueryDocumentSnapshot snapshot: queryDocumentSnapshots)
                                        {
                                            GameApi gameApi = GameApi.getInstance();
                                            Intent intent = new Intent(HomeScreen.this,MenuScreen.class);
                                            startActivity(intent);
                                            gameApi.setUserID(snapshot.getString("UserID"));
                                            gameApi.setEmail(snapshot.getString("Email"));
                                            gameApi.setUserName(snapshot.getString("UserName"));
                                            gameApi.setHighScore(snapshot.getDouble("HighScore"));
                                            gameApi.setGems(Integer.parseInt(snapshot.getString("Gems")));
                                            gameApi.setElectrons(Integer.parseInt(snapshot.getString("Electrons")));
                                            gameApi.setAnimals(snapshot.getString("Animals"));
                                        }
                                    }
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(HomeScreen.this,"This Account Does Not Exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomeScreen.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(HomeScreen.this,"Empty Fields",Toast.LENGTH_SHORT).show();
        }
    }

    private void AutoLogin()
    {
        currentUser=firebaseAuth.getCurrentUser();
        if(currentUser!=null) {
            String currentUserId = currentUser.getUid();
            collectionReference.whereEqualTo("UserID", currentUserId)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                            if (error != null)
                                return;
                            assert queryDocumentSnapshots != null;
                            if (!queryDocumentSnapshots.isEmpty()) {
                                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                                    GameApi gameApi = GameApi.getInstance();
                                    Intent intent = new Intent(HomeScreen.this, MenuScreen.class);
                                    startActivity(intent);
                                    gameApi.setUserID(snapshot.getString("UserID"));
                                    gameApi.setEmail(snapshot.getString("Email"));
                                    gameApi.setUserName(snapshot.getString("UserName"));
                                    gameApi.setHighScore(snapshot.getDouble("HighScore"));
                                    gameApi.setGems(Integer.parseInt(snapshot.getString("Gems")));
                                    gameApi.setElectrons(Integer.parseInt(snapshot.getString("Electrons")));
                                    gameApi.setAnimals(snapshot.getString("Animals"));
                                }
                            }

                        }
                    });
        }
        else
        {
            Toast.makeText(HomeScreen.this,"Please Login or Register or Play as a Guest",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser=firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    void PlayAsGest()
    {
        Database_class databaseHelper = new Database_class(HomeScreen.this);
        try{
        databaseHelper.CreateTable();
        GameApi gameApi = GameApi.getInstance();
        Random randomNumberMaker = new Random();
        String animals = Integer.toString(randomNumberMaker.nextInt(10));
        String userID = Integer.toString(randomNumberMaker.nextInt(100));
        gameApi.setUserID(userID);
        gameApi.setEmail("Guest@game.com");
        databaseHelper.Insert("Guest", 0.0, "0","1",animals);
        Cursor c =databaseHelper.ReadData();
        if(c.moveToFirst())
        {
            do{
                gameApi.setUserName(c.getString(0));
                gameApi.setHighScore(Double.parseDouble(c.getString(1)));
                gameApi.setGems(Integer.parseInt(c.getString(2)));
                gameApi.setElectrons(Integer.parseInt(c.getString(3)));
                gameApi.setAnimals(c.getString(4));
            }
            while(c.moveToNext());
        }
        else
        {
            Toast.makeText(HomeScreen.this,"Unable to access DB",Toast.LENGTH_SHORT).show();
        }}
        catch (Exception e)
        {
            Toast.makeText(HomeScreen.this,"Dropping DB. ReStart the app",Toast.LENGTH_SHORT).show();
            databaseHelper.DropTable();
        }

    }
}