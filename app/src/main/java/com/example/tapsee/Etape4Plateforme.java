package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Etape4Plateforme extends AppCompatActivity {

    private CheckBox indiff;

    private List<CheckBox> listVOD;

    private boolean isIndiff;

    private CheckBox nouveautes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape4_plateforme);

        this.indiff = (CheckBox) findViewById(R.id.checkBoxIndifff);
        this.nouveautes= (CheckBox)findViewById(R.id.checkBoxNouveautes);
        this.listVOD = new ArrayList<>();

        isIndiff = false;

        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxNetflix));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxAmazon));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxApple));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxCanal));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxDisney));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxHulu));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxOCS));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxSalto));
        this.listVOD.add((CheckBox) findViewById(R.id.checkBoxSFR));
        if(Data.platChoices!=null){
            int i=0;
            for(CheckBox c : this.listVOD){
                c.setChecked(Data.platChoices[i]);
                i++;
            }
        }
        if(Data.newChoice==true){
            this.nouveautes.setChecked(true);
        }

        this.indiff.setOnClickListener(new CheckBox.OnClickListener(){

            @Override
            public void onClick(View view) {
                for(CheckBox cb : listVOD){
                    cb.setEnabled(isIndiff);
                }
                isIndiff= !isIndiff;
            }
        });
    }


    /**
     * Methode retournant à l'activité précédente
     */
    public void launchPreviousActivity(View view){
        updateData();
        finish();
    }

    /**
     * Methode lancant l'activité suivante
     */
    public void launchNextActivity(View view){
        Intent intent = new Intent(this, Etape5Duree.class);
        updateData();
        startActivity(intent);
    }

    private void updateData(){
        Data.newChoice=nouveautes.isChecked();
        int size=this.listVOD.size();
        Data.platChoices= new boolean[size];
        if(this.indiff.isChecked()){
            for(int i=0;i<size;i++){
                Data.platChoices[i]=true;
            }
        }
        else{
            size=0; //On réutilise size comme compteur
            for(CheckBox c : this.listVOD){
                Data.platChoices[size]=c.isChecked();
                size++;
            }
        }
    }
}