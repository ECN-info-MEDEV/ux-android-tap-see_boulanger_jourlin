package com.example.tapsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Etape2Genre extends AppCompatActivity {

    CustomAdapter customAdapter;
    ListView listView;
    String names[]={"Romance","Comédie","Drame","Enfant","Adulte","Animé","Sombre","Policier","Action","Histoire","Science fiction","Horreur","Fantaisie","Fantastique","Western","Reportage"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape2_genre);

        listView = findViewById(R.id.listview);
        List<String > listItems = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            listItems.add(names[i]);

        }
        customAdapter= new CustomAdapter(listItems,this);

        if(Data.genreChoices!=null) {
            customAdapter.setCheckList(Data.genreChoices);
        }


        listView.setAdapter(customAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search_view);
        SearchView searchView =(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                customAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id==R.id.search_view){
           return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void backToMenu(View view){
        Data.genreChoices=customAdapter.getCheckList();
        finish();
    }

    public void launchNextStep(View view){
        Intent intent = new Intent(this, Etape2Genre.class);
        Data.genreChoices=customAdapter.getCheckList();
        startActivity(intent);
    }


    public class CustomAdapter extends BaseAdapter implements Filterable {
        private List<String> strList;
        private List<String> strListFiltered;
        private boolean[] checkList;
        private Context context;

        public CustomAdapter(List<String> strList,  Context context) {
            this.strList = strList;
            this.strListFiltered = strList;
            this.context = context;
            this.checkList = new boolean[strList.size()];
            for(int i =0;i<strList.size();i++){
                this.checkList[i]=false;
            }
        }

        public boolean[] getCheckList() {
            return checkList;
        }

        public void setCheckList(boolean[] checkList) {
            this.checkList = checkList;
        }

        public List<String> getStrList() {
            return strList;
        }

        public void setStrList(List<String> strList) {
            this.strList = strList;
        }

        public List<String> getStrListFiltered() {
            return strListFiltered;
        }

        public void setStrListFiltered(List<String> strListFiltered) {
            this.strListFiltered = strListFiltered;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public int getCount(){
            return strListFiltered.size();
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
            itemName.setText(strListFiltered.get(i));
            CheckBox checkBox= view.findViewById(R.id.checkBox);
            int j = strList.indexOf(strListFiltered.get(i));
            checkBox.setChecked(checkList[j]);
            checkBox.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    checkList[j]=!checkList[j];
                }
            });
            return view;
        }


        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults=new FilterResults();
                    if(charSequence==null || charSequence.length()==0){
                        filterResults.count=strList.size();
                        filterResults.values=strList;
                    }
                    else{
                        String searchStr = charSequence.toString().toLowerCase();
                        List<String> resultData = new ArrayList<>();
                        for(String str:strList){
                            if(str.contains(searchStr) )
                                resultData.add(str);
                        }
                        filterResults.count= resultData.size();
                        filterResults.values= resultData;
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    strListFiltered = (List<String>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}