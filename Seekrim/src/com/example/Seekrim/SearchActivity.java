package com.example.Seekrim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-16
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class SearchActivity extends Activity {
    private ImageButton backButton ;
    private ImageButton searchButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
        backButton = (ImageButton) findViewById(R.id.backButton);
        searchButton2 = (ImageButton) findViewById(R.id.searchButton2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backbuttonOnClick();
            }
        });

        searchButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                searchButton2OnClick();
            }
        });
    }


    private void backbuttonOnClick(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void searchButton2OnClick(){

    }
}
