package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EcranFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_final);
    }

    public void backToMenu(View view){
        finish();
        Intent i = new Intent(EcranFinal. this, MainActivity. class);
        startActivity(i);
    }
}