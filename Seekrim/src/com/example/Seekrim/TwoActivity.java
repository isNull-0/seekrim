package com.example.seekrim;

import android.app.Activity;
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
 * Time: 上午9:41
 * To change this template use File | Settings | File Templates.
 */
public class TwoActivity extends Activity {
    private ListView listView;
    private ImageButton button;
    private TextView textView;
    private int firstIndex;
    private String title;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String,?>>();
    private int selectedPosition =-1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.two_itemize);
        listView = (ListView) findViewById(R.id.two_listView);
        button = (ImageButton) findViewById(R.id.three_itemize_title_button);
        textView = (TextView) findViewById(R.id.two_itemize_title_TextView);

        Intent intent = getIntent();
        firstIndex =  intent.getIntExtra("firstIndex",-1);

        title = intent.getStringExtra("name");

        //设置 头部标签


        data = Tools.getAdapterDataWithIndex(firstIndex,EntityConstent.SECOND_DATA);
        textView.setText(title);

        final BaseAdapter baseAdapter = new BaseAdapter(){
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

                    convertView = layoutInflater.inflate(R.layout.two_chat_item, parent, false);
                }
                Map<String, Object> itemData = (Map<String, Object>) getItem(position);

                TextView nameTextView = (TextView) convertView.findViewById(R.id.index_content_list_textView);
                nameTextView.setText(itemData.get("index_content_list_textView").toString());
                Button button1 = (Button) convertView.findViewById(R.id.index_content_next_button);


//               if(Tools.getAdapterButtonDataWithIndex(firstIndex,EntityConstent.THRID_DATA).equals("")){
//                   button1.setVisibility(View.INVISIBLE);
//
//               }else {
//                button1.setVisibility(View.GONE);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Intent intent = new Intent(TwoActivity.this ,ThreeActivity.class);
                            intent.putExtra("secondIndex",position);
                            intent.putExtra("firstIndex",firstIndex);
                            intent.putExtra("title",title);
                            intent.putExtra("name",data.get(position).get("index_content_list_textView").toString());
                            startActivity(intent);

                    }
                });

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TwoActivity.this,PoilistActivity.class);
                        intent.putExtra("back",2);
                        intent.putExtra("name",data.get(position).get("index_content_list_textView").toString());
                        startActivity(intent);
                    }
                });
//               }

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
        TwoActivity.this.finish();
    }
}