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
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
public class SetActivity extends Activity {
    private ImageButton backButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.set);
        backButton2 = (ImageButton)findViewById(R.id.backButton2);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backbutton2OnClick();
            }
        });
    }

    private void backbutton2OnClick(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
