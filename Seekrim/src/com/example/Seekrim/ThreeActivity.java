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
public class ThreeActivity extends Activity {
    private ListView listView ;
    private ArrayList<HashMap<String,?>> data = new ArrayList<HashMap<String,?>>();
    //private int selectedPosition =-1;
    private TextView textView;
    private String title;
    private int firstIndex;
    private int secondIndex;
    private String mainTitle;
    private ImageButton button1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.three_itemize);
        listView = (ListView) findViewById(R.id.three_listView);
        button1 = (ImageButton) findViewById(R.id.three_itemize_title_button);
        textView = (TextView) findViewById(R.id.two_itemize_title_TextView);
        Intent position = getIntent();
        title = position.getStringExtra("name");
        mainTitle = position.getStringExtra("title");
        textView.setText(title);
        button1 = (ImageButton) findViewById(R.id.three_itemize_title_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThreeActivity.this.finish();

//                Intent intent = new Intent(ThreeActivity.this,TwoActivity.class);
//                intent.putExtra("firstIndex",firstIndex);
//                intent.putExtra("name",mainTitle);
//                startActivity(intent);
            }
        });
        firstIndex = position.getIntExtra("firstIndex", 0);
        secondIndex =   position.getIntExtra("secondIndex", 0);
        data = Tools.getAdapterAllDataWithIndex(firstIndex,secondIndex, EntityConstent.THRID_DATA);
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
                    convertView = layoutInflater.inflate(R.layout.three_chat_item, parent, false);
                }
                Map<String, Object> itemData = (Map<String, Object>) getItem(position);

                Button threeNameButton = (Button) convertView.findViewById(R.id.threeItemButton);

                threeNameButton.setText(itemData.get("index_content_list_textView").toString());
                threeNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ThreeActivity.this,PoilistActivity.class);
                        intent.putExtra("back",3);
                        intent.putExtra("name",data.get(position).get("index_content_list_textView").toString());
                        startActivity(intent);

//                        Log.d("====", "" + data.get(position));
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

}