package com.salim3dd.favorite;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listIndex = new ArrayList<String>();
    ArrayList<List_itme_Index> List_Favorite = new ArrayList<List_itme_Index>();
    TextView textView_Title;
    ArrayAdapter<String> arrayAdapter;
    DB_Sqlite_Favorite db_fav = new DB_Sqlite_Favorite(this);
    String List_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView_main);
        textView_Title = (TextView) findViewById(R.id.textView_Title);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "font.otf");
        textView_Title.setTypeface(typeface);

        list_index();

    }

    public void list_index() {
        listIndex.clear();
        List_Type = "Index";
        textView_Title.setText(getString(R.string.app_name));
        try {
            InputStream input = getAssets().open("index.txt");
            InputStreamReader rd = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(rd);
            String line;
            while ((line = br.readLine()) != null) {
                listIndex.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.textView_itme, listIndex);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView Title = (TextView) view.findViewById(R.id.textView_itme);
                Toast.makeText(MainActivity.this, Title.getText(), Toast.LENGTH_SHORT).show();
                int PageNum = 0;
                if (List_Type.equals("Index")) {
                    PageNum = position;
                } else if (List_Type.equals("Favorite")) {
                    PageNum = List_Favorite.get(position).getPage_id();
                }

                Intent intent = new Intent(MainActivity.this, Webhtml.class);
                intent.putExtra("pageNum", PageNum);
                intent.putExtra("Title", Title.getText());
                startActivity(intent);

            }
        });

    }

    public void btn_favorite(View view) {
        List_Type = "Favorite";
        textView_Title.setText(getString(R.string.favorite));
        List_Favorite = db_fav.get_All_Favorite();
        if (List_Favorite.size() == 0) {
            Toast.makeText(MainActivity.this, R.string.favorite_is_Empty, Toast.LENGTH_SHORT).show();
            list_index();
            textView_Title.setText(getString(R.string.app_name));
        } else {
            listIndex.clear();
            for (int i = 0; i < List_Favorite.size(); i++) {
                listIndex.add(List_Favorite.get(i).getMain_Title());
            }

            arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.textView_itme, listIndex);
            listView.setAdapter(arrayAdapter);
        }
    }
    public void btn_book(View view) {
        list_index();
    }
    public void btn_Close(View view) {
        finish();
    }
}
