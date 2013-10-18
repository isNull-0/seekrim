package com.example.Seekrim;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-17
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public class PoiListtoMapActivity extends Activity {
    private ListView poiListView;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String, ?>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poilisttomap);
        poiListView =(ListView) findViewById(R.id.poilistView);
        String [] temp1= {"黄记煌三汁焖锅","香港私家菜天子海鲜火锅","老爹红麻辣涮（竹笆市店）","小肥羊火锅店（钟楼广场店）"};
        String [] temp2 = {"西安南大街52号南附楼内3层","南大街","","中路广场"};
        String [] temp3 = {"500m","800m","1.2m","2.2m"};
        for(int i=0;i<temp1.length;i++){
            HashMap<String,Object> item = new HashMap<String, Object>();
            item.put("text1",temp1[i]);
            item.put("text2",temp2[i]);
            item.put("text3",temp3[i]);
            data.add(item);
        }
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
                    converView = layoutInflater.inflate(R.layout.two_chat_item,parent,false);
                }
                Map<String,Object> itemData = (Map<String,Object>)getItem(position);
                TextView poiNameTextView= (TextView)converView.findViewById(R.id.poiNameTextView);
                TextView poiAddressTextView= (TextView)converView.findViewById(R.id.poiaddressTextView);
                TextView poiDistanceTextView= (TextView)converView.findViewById(R.id.poidistanceTextView);
                poiNameTextView.setText(itemData.get("text1").toString());
                poiAddressTextView.setText(itemData.get("text2").toString());
                poiDistanceTextView.setText(itemData.get("text3").toString());

                return converView;

            }
        };
        poiListView.setAdapter(baseAdapter);
    }
}
