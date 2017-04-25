package com.example.richashastri29.lists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Science extends AppCompatActivity {
    private int count = 0;
    private HashMap<String, String> dictionary;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> defcollection;
    private HashMap<String, ArrayList<String>> mulchoice;

    private static final String[] HELLO = {"Aspirin comes from which of the following ?",
            "Willow bark",
            "Oak tree",
            "Acacia",
            "Eucalyptus",
            "Carrot is orange in color because ?",
            "[A] It grows in the soil",
            "[B] It is not exposed to sunlight",
            "[C] It contains carotene",
            "[D] The entire plant is orange in color",
            "The medulla oblongata is a part of human",
            "[A] Heart",
            "[B] Brain",
            "[C] Liver",
            "[D] Sex Organ",
            "The lining of marrow cavity is called",
            "[A] Episteum",
            "[B] Periosteum",
            "[C] Endosteum",
            "[D] Sarcolemma",
            "The largest part of the human brain is",
            "[A] Medulla oblongata",
            "[B] Cerebellum",
            "[C] Cerebrum",
            "[D] Mid-brain",
            "The largest organ of human body is ?",
            "[A] Brain",
            "[B] Heart",
            "[C] Skin",
            "[D] Liver",
            "The function of hemoglobin is ?",
            "[A] To transport oxygen",
            "[B] Destruction of bacteria",
            "[C] Prevention of anemia",
            "[D] Utilization of energy "
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);


        dictionary = new HashMap<String, String>();
        list = new ArrayList<String>();
        defcollection = new ArrayList<String>();
        mulchoice = new HashMap<String, ArrayList<String>>();

        //choice = new ArrayList<String>();

        //for (int i = 0; i < HELLO.length; i += 5) {
         //   choice.add(HELLO[i + 1]);
           // choice.add(HELLO[i + 2]);
            //choice.add(HELLO[i + 3]);
            //choice.add(HELLO[i + 4]);
            //mulchoice.put(HELLO[i], choice);
            //choice.clear();
        //}


        readnum();
        generatenumber();

    }


    private void readnum() {




        Scanner scan = new Scanner(getResources().openRawResource(R.raw.my));
        while (scan.hasNextLine()) {
            ArrayList<String> choice = new ArrayList<String>();
            String question = scan.nextLine();
            String answer = scan.nextLine();
            String m = scan.nextLine();
            choice.add(m);
            String m1 = scan.nextLine();
            choice.add(m1);
            String m2 = scan.nextLine();
            choice.add(m2);
            String m3 = scan.nextLine();
            choice.add(m3);
            list.add(question);
            dictionary.put(question, answer);
            mulchoice.put(question,choice);


        }

    }


    private void generatenumber() {

        ArrayList<String> wordscollection = new ArrayList<String>();
        Collections.shuffle(list);
        for (int i = 0; i < 4; i++) {
            wordscollection.add(list.get(i));
        }

        final String chosenword = wordscollection.get(0);
        TextView deftext = (TextView) findViewById(R.id.textid);
        deftext.setText(chosenword);

        TextView points = (TextView) findViewById(R.id.point);
        points.setText("Points:  " + count);

        defcollection.clear();

        System.out.println(mulchoice.get(chosenword));
        //defcollection = mulchoice.get(chosenword);

        for (int i = 0; i < 4; i++) {
            defcollection.add(dictionary.get(wordscollection.get(i)));
        }


        Collections.shuffle(defcollection);

        if (adapter == null) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, defcollection);

        } else {
            adapter.notifyDataSetChanged();
        }


        ListView listView = (ListView) findViewById(R.id.sciencelist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selecteddef = defcollection.get(position);
                String rightdef = dictionary.get(chosenword);
                if (selecteddef.equals(rightdef)) {
                    Toast.makeText(Science.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    count++;
                } else {
                    Toast.makeText(Science.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    count--;

                }
                generatenumber();
            }
        });
    }
}


