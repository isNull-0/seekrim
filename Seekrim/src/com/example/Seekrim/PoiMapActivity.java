package com.example.seekrim;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.baidu.mapapi.map.MapView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-16
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class PoiMapActivity extends Activity {
    private AlertDialog loadDialog;
    private ImageButton backRadioButton;
    private TextView nameRadioText;
    private TextView scopeText;
    private Button mapRadioButton;
    private int selectedPosition = -1;
    private MapView mapView;

    private String title3;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String, ?>>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poilist3);
        backRadioButton = (ImageButton)findViewById(R.id.backRadioButton1);
        mapRadioButton = (Button)findViewById(R.id.mapRadioButton);
        scopeText = (TextView)findViewById(R.id.scopeText);
        nameRadioText = (TextView) findViewById(R.id.nameRadioText1);
        Intent intent = getIntent();
        title3 = intent.getStringExtra("name");
        nameRadioText.setText(title3);


       mapRadioButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mapRadioButtonOnClick();
           }
       });

        final BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long getItemId(int position) {
                return position;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public View getView(int position, View converView, ViewGroup parent) {

                if(converView == null){
                    LayoutInflater layoutInflater = getLayoutInflater();
                    converView = layoutInflater.inflate(R.layout.poi_list_item,parent,false);
                }



                return converView;

            }
        };



        backRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PoiMapActivity.this.finish();
            }
        });
    }

    public void scopeButtonOnClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] cities = new String[]{"1000m", "2000m", "3000m", "4000m", "5000m"};
        builder.setItems(cities, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                scopeText.setText("范围:"+cities[which]);

            }
        });
        builder.setTitle("选择范围");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void backRadioButtonOnClick(){
        Intent intent = new Intent(this,ThreeActivity.class);
        startActivity(intent);
    }

    private void mapRadioButtonOnClick(){
     PoiMapActivity.this.finish();
    }
}
