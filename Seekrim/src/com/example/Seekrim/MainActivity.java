package com.example.Seekrim;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import com.example.Seekrim.EntityConstent.EntityConstent;
import com.example.Seekrim.util.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private AlertDialog loginDialog;
    private ListView listView;
    private ImageButton searchButton1;
    private ImageButton locationButton;
    private ImageButton setButton;
    private ImageButton stairButton;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String, ?>>();
    private int selectedPosition = -1;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        listView = (ListView)findViewById(R.id.listView1);
        searchButton1 = (ImageButton) findViewById(R.id.searchButton1);
        locationButton =(ImageButton) findViewById(R.id.locationButton);
        setButton = (ImageButton) findViewById(R.id.setButton);
        data = Tools.getAdapterData(EntityConstent.FIRST_DATA);
//        String [] temp={"餐饮服务","购物服务","生活服务","体育休闲服务","住宿服务","医疗保健服务","科教文化服务","交通设施服务","体育休闲服务"};
//        for( int i =0; i<temp.length;i++){
//            HashMap<String,Object> item = new HashMap<String, Object>();
//            item.put("text1",temp[i]);
//            data.add(item);
//        }


        searchButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                searchButton1OnClick();
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                locationButtonOnClick();
            }
        });

        setButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setButtonOnClick();
            }
        });

        final BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getItem(int posotion) {
                return data.get(posotion);  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long getItemId(int position) {
                return position;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public View getView(final  int position, View convertView, ViewGroup parent) {

                if (convertView == null){
                    LayoutInflater layoutInflater = getLayoutInflater();
                    convertView = layoutInflater.inflate(R.layout.main_chat_item,parent,false);

                }
                Map<String,Object>  itemData = (Map<String,Object>) getItem(position);
                TextView nameTextView =(TextView) convertView.findViewById(R.id.stairnameTextView);
                nameTextView.setText(itemData.get("text1").toString());

                View Button =convertView.findViewById(R.id.stairButton);

                    Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                                if(position==0){
                                Intent intent = new Intent(MainActivity.this,TwoActivity.class);
                                intent.putExtra("firstIndex",position);
                                startActivity(intent);
//                                }
                             }
                    });

                return convertView;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        listView.setAdapter(baseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                selectedPosition = position;

            }
        });

    }

    private void searchButton1OnClick() {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    private void setButtonOnClick() {
        Intent intent = new Intent(this,SetActivity.class);
        startActivity(intent);
    }

    private void stairButtonOnClick() {
        Intent intent = new Intent(this,TwoActivity.class);
        startActivity(intent);
    }

    private void locationButtonOnClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.loading, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        view.setLayoutParams(params);
        builder.setView(view);
        loginDialog = builder.create();
        loginDialog.show();
    }

}
