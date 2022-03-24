package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EcranFinal extends AppCompatActivity {

    private TextView textDurees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_final);

        this.textDurees=(TextView) findViewById(R.id.dureesFilm);

        if(Data.dureeChoice){
            textDurees.setText("Une durée aléatoire");
        }
        else{
            textDurees.setText("Une durée comprise entre " + Etape5Duree.getTime(Data.dureeMinMaxChoices[0]) + " et " + Etape5Duree.getTime(Data.dureeMinMaxChoices[1]));
        }
    }

    public void backToMenu(View view){
        finish();
        Intent i = new Intent(EcranFinal. this, MainActivity. class);
        startActivity(i);
    }
}