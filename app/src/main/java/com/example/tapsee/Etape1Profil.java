package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Etape1Profil extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape1_profil);
        spinner = (Spinner)findViewById(R.id.profilSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.profil_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void backToMenu(View view){
        updateData();
        finish();
    }

    public void launchNextStep(View view){
        updateData();
        Intent intent = new Intent(this, Etape2Genre.class);
        startActivity(intent);
    }

    /**
     * Update la classe data avec le profil
     */
    private void updateData(){
        Data.profilChoice=spinner.getSelectedItemPosition();
    }
}