package com.example.Seekrim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-16
 * Time: 上午9:41
 * To change this template use File | Settings | File Templates.
 */
public class ThreeActivity extends Activity {
    private ListView listView ;
    private TextView threeItemText;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String,?>>();
    private int selectedPosition =-1;
    private ImageButton button1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.three_itemize);
        listView = (ListView) findViewById(R.id.three_listView);
        button1 = (ImageButton) findViewById(R.id.three_itemize_title_button);
        threeItemText = (TextView)findViewById(R.id.threeItemButton);
        Intent position = getIntent();
        data = Tools.getAdapterAllDataWithIndex(position.getIntExtra("firstIndex", 0), position.getIntExtra("secondIndex", 0), EntityConstent.THRID_DATA);
//        String [] temp = {"中餐厅","综合酒楼","四川菜(川菜)","广东菜(粤菜)","山东菜(鲁菜)","茶艺馆","冷饮店","糕饼店","甜品店"};
//        for(int i = 0; i <temp.length ;i ++){
//            HashMap<String, Object> item = new HashMap<String, Object>();
//            item.put("index_content_list_textView",temp[i] );
//            data.add(item);
//        }
//        for(int i = 0; i <10 ; i++){
//            HashMap<String, Object> item = new HashMap<String, Object>();
//            item.put("index_content_list_textView","菜单"+i );
//            data.add(item);
//        }


        final BaseAdapter baseAdapter = new BaseAdapter() {


             @Override
            public int getCount() {
                return data.size();  //To change body of implemented methods use File | Settings | File Templates.
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
            public View getView(final  int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater layoutInflater = getLayoutInflater();
                    convertView = layoutInflater.inflate(R.layout.three_chat_item, parent, false);
                }
                Map<String, Object> itemData = (Map<String, Object>) getItem(position);
                Button threeNameButton = (Button) convertView.findViewById(R.id.threeItemButton);
                threeNameButton.setText(itemData.get("text1").toString());
                threeNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ThreeActivity.this,PoilistActivity.class);
                        startActivity(intent);

                        Log.d("====",""+data.get(position));
                    }
                });
                return convertView;
            }
        };
        listView.setAdapter(baseAdapter);
//       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
//              selectedPosition = position;
//
//           }
//       });


    }
     public void buttonOnClick(View view){
        Intent intent = new Intent(this,TwoActivity.class);
        startActivity(intent);
     }

}