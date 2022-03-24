package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Etape5Duree extends AppCompatActivity {

    private SeekBar seekBarDureeMax;
    private SeekBar seekBarDureeMin;
    private TextView showMax;
    private TextView showMin;
    private Switch switchDuree;

    private Button nextActivity;
    private boolean nextStepCorrect;

    private boolean indifferent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape5_duree);

        //Récupération des éléments de la scène
        this.seekBarDureeMax = (SeekBar) findViewById(R.id.seekBarDureeMax);
        this.seekBarDureeMin = (SeekBar) findViewById(R.id.seekBarDureeMin);
        this.showMax = (TextView) findViewById(R.id.textShowMax);
        showMax.setText(getTime(seekBarDureeMax.getProgress()));
        this.showMin = (TextView) findViewById(R.id.textShowMin);
        showMin.setText(getTime(seekBarDureeMin.getProgress()));
        this.switchDuree = (Switch) findViewById(R.id.switchDuree);
        this.nextActivity = (Button) findViewById(R.id.buttonNext);

        //Récupération des dernières données
        this.indifferent = Data.dureeChoice;
        seekBarDureeMax.setEnabled(!indifferent);
        seekBarDureeMin.setEnabled(!indifferent);
        showMax.setEnabled(!indifferent);
        showMin.setEnabled(!indifferent);
        this.switchDuree.setChecked(Data.dureeChoice);
        this.nextStepCorrect=true;
        if(Data.dureeMinMaxChoices!=null){
            seekBarDureeMax.setProgress(Data.dureeMinMaxChoices[1]);
            showMax.setText(getTime(seekBarDureeMax.getProgress()));
            seekBarDureeMin.setProgress(Data.dureeMinMaxChoices[0]);
            showMin.setText(getTime(seekBarDureeMin.getProgress()));
        }
        compareTimers(); //Verrouiller le bouton Next si les durées ne sont pas possibles

        this.seekBarDureeMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                showMax.setText(getTime(seekBar.getProgress()));
                compareTimers();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBarDureeMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                showMin.setText(getTime(seekBar.getProgress()));
                compareTimers();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.switchDuree.setOnClickListener(new Switch.OnClickListener(){

            @Override
            public void onClick(View view) {
                seekBarDureeMax.setEnabled(indifferent);
                seekBarDureeMin.setEnabled(indifferent);
                showMax.setEnabled(indifferent);
                showMin.setEnabled(indifferent);
                indifferent = !indifferent;
                if(indifferent){
                    nextActivity.setEnabled(true);
                }
                else{
                    nextActivity.setEnabled(nextStepCorrect);
                }
            }
        });
    }

    public static String getTime(int value){
        int hours = value/60;
        int min = value - hours*60;
        String result="";
        if(min<10){
            result = hours + ":0" + min;
        }
        else{
            result = hours + ":" + min;
        }
        return result;
    }

    private void compareTimers(){
        //This method changes if we can go to the Next step by comparing the two seekbars
        boolean b = (seekBarDureeMax.getProgress() - seekBarDureeMin.getProgress()) >0;
        if(b!=nextStepCorrect){
            nextStepCorrect=b;
            nextActivity.setEnabled(b);
            if(!b){
                Toast toast = Toast.makeText(Etape5Duree.this, "Durées incompatibles !", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 20, 30);
                toast.show();
            }
        }
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
        Intent intent = new Intent(this, EcranFinal.class);
        updateData();

        startActivity(intent);
    }

    /**
     * Méthode permettant de mettre à jour les données dans l'étape 5 (indifférent à la durée et les choix de durée)
     */

    private void updateData(){
        Data.dureeChoice=this.indifferent;
        Data.dureeMinMaxChoices= new int[2];
        Data.dureeMinMaxChoices[0]=seekBarDureeMin.getProgress();
        Data.dureeMinMaxChoices[1]=seekBarDureeMax.getProgress();
    }
}