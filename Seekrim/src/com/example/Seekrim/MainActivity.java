package com.example.seekrim;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.seekrim.EntityConstant.EntityConstent;
import com.example.seekrim.util.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private AlertDialog loginDialog;
    private ListView listView1;
    private ImageButton searchButton1;
    private ImageButton locationButton;
    private ImageButton setButton;
    private String addrsStr;
    private BDListenner bdListenner = new BDListenner();
    private LocationClient locationClient  = null;
    //private Button stairButton;
    private TextView showLocationTextView;
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

        LocationClientOption option = new LocationClientOption();
        option.setAddrType("all");
        option.setOpenGps(true);//打开gps
        option.setCoorType("bd09ll");     //设置坐标类型
        locationClient = new LocationClient(this);
        locationClient.setLocOption(option);
        locationClient.setAK("258d1c1785a5767ab5685c3b1824db51");
        locationClient.registerLocationListener(bdListenner);
        locationClient.start();

        listView1 = (ListView)findViewById(R.id.listView1);
        searchButton1 = (ImageButton) findViewById(R.id.searchButton1);
        locationButton =(ImageButton) findViewById(R.id.locationButton);
        setButton = (ImageButton) findViewById(R.id.setButton);

        showLocationTextView = (TextView) findViewById(R.id.locationTextView);

        data = Tools.getAdapterData(EntityConstent.FIRST_DATA);
       // data = Tools.getAdapterAllDataWithIndex(1,EntityConstent.THRID_DATA);

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

            @Override              //   当前位置
            public View getView( final int position, View convertView, ViewGroup parent) {

                if (convertView == null){
                    LayoutInflater layoutInflater = getLayoutInflater();

                    convertView = layoutInflater.inflate(R.layout.two_chat_item,parent,false);

                }
                Map<String,Object>  itemData = (Map<String,Object>) getItem(position);

                TextView nameTextView =(TextView) convertView.findViewById(R.id.index_content_list_textView);
                nameTextView.setText(itemData.get("index_content_list_textView").toString());


               //  获取 BUTTON 的  值
                Button button = (Button) convertView.findViewById(R.id.index_content_next_button);
                   button.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent intent = new Intent(MainActivity.this ,TwoActivity.class);

                           intent.putExtra("firstIndex",position);
                           intent.putExtra("name",data.get(position).get("index_content_list_textView").toString());
                           startActivity(intent);
                       }
                   });

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,PoilistActivity.class);
                        intent.putExtra("back",1);
                        intent.putExtra("name",data.get(position).get("index_content_list_textView").toString());
                        startActivity(intent);
                    }
                });



                return convertView;
            }
        };

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                Log.d("TAG","-----------------------------");
                selectedPosition = position;
                baseAdapter.notifyDataSetChanged();
            }
        });
        listView1.setAdapter(baseAdapter);


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


            locationClient.requestLocation();


           MyAsynTask myAsynTask = new MyAsynTask();
            myAsynTask.execute(0);

    }
    private  class MyAsynTask extends AsyncTask{

        @Override
        protected void onPreExecute() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

            View view = inflater.inflate(R.layout.loading, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            view.setLayoutParams(params);
            builder.setView(view);
            loginDialog = builder.create();
            showLocationTextView.setText("定位中...");
            loginDialog.show();
            super.onPreExecute();    //To change body of overridden methods use File | Settings | File Templates.
        }
        @Override
        protected Object doInBackground(Object... objects) {
            if(locationClient!=null&&locationClient.isStarted()){

                locationClient.requestLocation();
            }
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        protected void onPostExecute(Object o) {
            loginDialog.dismiss();

            if(addrsStr!=null){
               showLocationTextView.setText(addrsStr);
            }else{
                showLocationTextView.setText("定位失败！");
            }
            super.onPostExecute(o);    //To change body of overridden methods use File | Settings | File Templates.
        }

    }

    private class BDListenner implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
          //  Log.d("seekirm", "onReceiveLocation" + bdLocation);

             if(bdLocation==null){
                  return;
             }

                addrsStr = bdLocation.getAddrStr();

            bdLocation.getLatitude();
            bdLocation.getLongitude();
        }

        @Override
        public void onReceivePoi(BDLocation bdLocation) {

        }
    }

}
