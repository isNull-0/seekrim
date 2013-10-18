package com.example.Seekrim;

import android.app.Activity;
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

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-16
 * Time: 上午9:41
 * To change this template use File | Settings | File Templates.
 */
public class TwoActivity extends Activity {
    private ListView listView;
    private ImageButton button;
    int firstIndex;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String,?>>();
    private int selectedPosition =-1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.two_itemize);
        listView = (ListView) findViewById(R.id.listView2);
        button = (ImageButton) findViewById(R.id.three_itemize_title_button);
        Intent intent = getIntent();
        intent.getExtras();
        firstIndex =  intent.getIntExtra("firstIndex",0);
        data = Tools.getAdapterDataWithIndex(firstIndex, EntityConstent.SECOND_DATA);

//        String [] temp = {"中餐厅","外国餐厅","快餐厅","休闲餐饮场所","咖啡厅","茶艺馆","冷饮店","糕饼店","甜品店"};
//
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
            public View getView(final int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater layoutInflater = getLayoutInflater();
                    convertView = layoutInflater.inflate(R.layout.main_chat_item, parent, false);
                }
                Map<String, Object> itemData = (Map<String, Object>) getItem(position);
                TextView nameTextView = (TextView) convertView.findViewById(R.id.stairnameTextView);
                nameTextView.setText(itemData.get("text1").toString());
                View Button = convertView.findViewById(R.id.stairButton);
                Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // if(position==0){
                            Intent intent = new Intent(TwoActivity.this,ThreeActivity.class);
                             intent.putExtra("secondIndex",position);
                             intent.putExtra("firstIndex",firstIndex);
                            startActivity(intent);
                       // }
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
//           }
//       });
    }
    public void button2OnClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}