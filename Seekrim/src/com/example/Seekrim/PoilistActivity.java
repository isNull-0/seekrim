package com.example.Seekrim;


import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-16
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class PoilistActivity extends TabActivity {
    private AlertDialog loadDialog;
    private RadioButton backRadioButton;
    private Button moreButton;
    private Button scopeButton;
    private TextView scopeText;
    private TabHost tabHost;
    private RadioGroup radioGroup;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poilist);
        moreButton = (Button)findViewById(R.id.moreButton);
        scopeButton = (Button)findViewById(R.id.scopeButton);
        scopeText = (TextView)findViewById(R.id.scopeText);
        tabHost = getTabHost();
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("list").setIndicator("List").setContent(new Intent(this, PoiListtoMapActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("map").setIndicator("Map").setContent(new Intent(this, PoiMaptoListActivity.class)));
        backRadioButton = (RadioButton) findViewById(R.id.backRadioButton);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.refreshRadioButton:
                        tabHost.setCurrentTabByTag("list");
                        break;
                    case R.id.mapRadioButton:
                        tabHost.setCurrentTabByTag("map");
                        break;
                    default:
                        break;

                }
            }
        });
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreButtonOnClick();
            }
        });
        backRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backRadioButtonOnClick();
            }
        });
    }

    private void moreButtonOnClick(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.poimoreloading,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        view.setLayoutParams(params);
        builder.setView(view);

        loadDialog = builder.create();
        loadDialog.show();
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
}
