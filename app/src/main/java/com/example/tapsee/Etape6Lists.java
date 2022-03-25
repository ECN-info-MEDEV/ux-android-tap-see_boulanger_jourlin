package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Etape6Lists extends AppCompatActivity {
    IncludeCustomAdapter customAdapter;
    ExcludeCustomAdapter customAdapter2;
    ListView listView1, listView2;
    String[] names1= {"Liste1","Liste2","Liste3","Liste4","Liste5","Liste6","Liste7","Liste8","Liste9","..."};
    String[] names2= {"Liste1","Liste2","Liste3","Liste4","Liste5","Liste6","Liste7","Liste8","Liste9","..."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape6_lists);

        listView1 = findViewById(R.id.listview1);
        listView2 = findViewById(R.id.listview2);


        customAdapter= new IncludeCustomAdapter(names1,this);
        customAdapter2= new ExcludeCustomAdapter(names2,this);

        if(Data.excludedChoices!=null){
            for(int i=0;i<Data.excludedChoices.length;i++){
                customAdapter2.excludedCheckList[i]=Data.excludedChoices[i];
            }
        }
        if(Data.includedChoices!=null){
            for(int i=0;i<Data.includedChoices.length;i++){
                customAdapter.includedCheckList[i]=Data.includedChoices[i];
            }
        }
        listView1.setAdapter(customAdapter);
        listView2.setAdapter(customAdapter2);
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

    private void updateData(){
        Data.excludedChoices= customAdapter2.excludedCheckList;
        Data.includedChoices= customAdapter.includedCheckList;


    }

    public class IncludeCustomAdapter extends BaseAdapter{
        private String[] includedList;
        private boolean[] includedCheckList;
        private Context context;

        public IncludeCustomAdapter(String[] includedList, Context context) {
            this.includedList = includedList;
            this.includedCheckList = new boolean[includedList.length];
            for(int i =0;i<includedList.length;i++){
                this.includedCheckList[i]=false;
            }
            this.context = context;
        }

        public boolean[] getIncludedCheckList() {
            return includedCheckList;
        }

        public void setIncludedCheckList(boolean[] includedCheckList) {
            this.includedCheckList = includedCheckList;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return includedList.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = getLayoutInflater().inflate(R.layout.row_items, null);
            TextView itemName = view.findViewById((R.id.itemName));
            itemName.setText(includedList[i]);
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            checkBox.setChecked(includedCheckList[i]);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    includedCheckList[i] = !includedCheckList[i];
                }
            });
            return view;
        }

    }

    public class ExcludeCustomAdapter extends BaseAdapter{
        private String[] excludedList;
        private boolean[] excludedCheckList;
        private Context context;

        public boolean[] getExcludedCheckList() {
            return excludedCheckList;
        }

        public void setExcludedCheckList(boolean[] excludedCheckList) {
            this.excludedCheckList = excludedCheckList;
        }

        public ExcludeCustomAdapter(String[] excludedList, Context context) {
            this.excludedList = excludedList;
            this.excludedCheckList= new boolean[excludedList.length];
            for(int i=0;i<0;i++){
                this.excludedCheckList[i]=false;
            }
            this.context = context;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return excludedList.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = getLayoutInflater().inflate(R.layout.row_items, null);
            TextView itemName = view.findViewById((R.id.itemName));
            itemName.setText(excludedList[i]);
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            checkBox.setChecked(excludedCheckList[i]);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    excludedCheckList[i] = !excludedCheckList[i];
                }
            });
            return view;
        }

    }
}