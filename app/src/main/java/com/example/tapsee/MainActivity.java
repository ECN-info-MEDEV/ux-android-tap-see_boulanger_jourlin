package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Methode lançant l'activité permettant de choisir un film
     */
    public void launchFilmActivity(View view){
        Intent intent = new Intent(this, NullActivity.class);
        startActivity(intent);
    }

    /**
     * Methode lançant les activités non codées
     */
    public void launchNullActivity(View view){
        Intent intent = new Intent(this, NullActivity.class);
        startActivity(intent);
    }
}