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
import android.widget.*;
import com.example.seekrim.EntityConstant.EntityConstent;
import com.example.seekrim.util.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-16
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class PoilistActivity extends Activity {
    private ImageButton backRadioButton;
    private Button listRadioButton;
    private TextView scopeText;
    private TextView nameRadioText;
    private String title3;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String,?>>();
    private ListView poiListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poilist2);
        backRadioButton = (ImageButton)findViewById(R.id.backRadioButton);
        listRadioButton = (Button) findViewById(R.id.listRadioButton);
        scopeText = (TextView)findViewById(R.id.scopeText);
        nameRadioText = (TextView) findViewById(R.id.nameRadioText);
        Intent intent = getIntent();
        data = Tools.getAdapterAllDataWithIndex(intent.getIntExtra("firstIndex", 0), intent.getIntExtra("secondIndex", 0), EntityConstent.THRID_DATA);


        title3 = intent.getStringExtra("name");
        nameRadioText.setText(title3);


        poiListView =(ListView) findViewById(R.id.poilistView);
        data = Tools.getAdapterData(EntityConstent.TEMP1);
        data = Tools.getAdapterData(EntityConstent.TEMP2);
        data = Tools.getAdapterData(EntityConstent.TEMP3);


        final BaseAdapter baseAdapter = new BaseAdapter() {


            public int getCount() {
                return data.size();
            }


            public Object getItem(int position) {
                return data.get(position);  //To change body of implemented methods use File | Settings | File Templates.
            }


            public long getItemId(int position) {
                return position;  //To change body of implemented methods use File | Settings | File Templates.
            }


            public View getView(final int position, View converView, ViewGroup parent) {

                if(converView == null){
                    LayoutInflater layoutInflater = getLayoutInflater();
                    converView = layoutInflater.inflate(R.layout.poi_list_item,parent,false);
                }
                Map<String,Object> itemData = (Map<String,Object>)getItem(position);
                TextView poiNameTextView= (TextView)converView.findViewById(R.id.poiNameTextView);
                TextView poiAddressTextView= (TextView)converView.findViewById(R.id.poiaddressTextView);
                TextView poiDistanceTextView= (TextView)converView.findViewById(R.id.poidistanceTextView);
                poiNameTextView.setText(itemData.get("index_content_list_textView").toString());
                poiAddressTextView.setText(itemData.get("index_content_list_textView").toString());
                poiDistanceTextView.setText(itemData.get("index_content_list_textView").toString());
                listRadioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PoilistActivity.this,PoiMapActivity.class);
                         intent.putExtra("name",title3);
                        startActivity(intent);
                    }
                });
                backRadioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       PoilistActivity.this.finish();
                    }
                });
                converView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PoilistActivity.this,MapparActivity.class);
                        intent.putExtra("name",data.get(position).get("index_content_list_textView").toString());
                        startActivity(intent);
                    }
                });

                return converView;

            }
        };

        poiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {

                //selectedPosition = position;
                baseAdapter.notifyDataSetChanged();
            }
        });
        poiListView.setAdapter(baseAdapter);
    }



//    private void moreButtonOnClick(){
//        AlertDialog.Builder builder= new AlertDialog.Builder(this);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.poimoreloading,null);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT,
//                LinearLayout.LayoutParams.FILL_PARENT);
//        view.setLayoutParams(params);
//        builder.setView(view);
//
//        loadDialog = builder.create();
//        loadDialog.show();
//    }

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

}
