package com.salim3dd.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;


public class Webhtml extends AppCompatActivity {
    int pageNum;
    WebView webView;
    String Title;
    DB_Sqlite_Favorite db_fav = new DB_Sqlite_Favorite(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);



        Intent data = getIntent();
        pageNum = data.getExtras().getInt("pageNum");
        Title= data.getExtras().getString("Title");

        webView = (WebView)findViewById(R.id.webView1);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("file:///android_asset/html/page" + pageNum +".html" );

    }

    public void btn_favorite(View view) {
        int check = db_fav.get_check_List_Favorite(Title);
        if (check>0){
            Toast.makeText(Webhtml.this, "عفوا العنوان موجود في المفضلة", Toast.LENGTH_SHORT).show();
        }else{
            db_fav.Insert_to_favorite(Title,pageNum);
            Toast.makeText(Webhtml.this,"تم الاضافة في المفضلة", Toast.LENGTH_SHORT).show();
        }

    }
    public void btn_Home(View view) {
        finish();
    }

}
