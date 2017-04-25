package com.example.richashastri29.lists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import static android.R.id.list;


public class MainActivity extends AppCompatActivity {


    private int count = 0;
    private HashMap<String, String> dictionary;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> defcollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dictionary = new HashMap<String, String>();
        list = new ArrayList<String>();
        defcollection = new ArrayList<String>();
        readnum();
        generatenumber();
    }
        private void readnum(){

            Scanner scan = new Scanner(getResources().openRawResource(R.raw.wordlist));
            while (scan.hasNextLine()){
                String word = scan.nextLine();
                String def = scan.nextLine();
                list.add(word);
                dictionary.put(word,def);
            }

            }


        private void generatenumber() {

            ArrayList<String> wordscollection = new ArrayList<String>();
            Collections.shuffle(list);
            for (int i = 0; i < 5; i++) {
                wordscollection.add(list.get(i));
            }
            final String chosenword = wordscollection.get(0);
            TextView deftext = (TextView) findViewById(R.id.textid);
            deftext.setText(chosenword);

            TextView points = (TextView) findViewById(R.id.pointid);
            points.setText("Points:  " + count);

            defcollection.clear();
            for (int i = 0; i < 5; i++) {
                defcollection.add(dictionary.get(wordscollection.get(i)));
            }
            Collections.shuffle(defcollection);

            if (adapter == null) {
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, defcollection);

            } else {
                adapter.notifyDataSetChanged();
            }



            ListView listView = (ListView) findViewById(R.id.wordlist);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String selecteddef = defcollection.get(position);
                    String rightdef = dictionary.get(chosenword);
                    if (selecteddef.equals(rightdef)) {
                        Toast.makeText(MainActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                        count++;
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                        count--;

                    }
                    generatenumber();
                }
            });
        }
}
