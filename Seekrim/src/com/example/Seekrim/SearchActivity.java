package com.example.seekrim;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends Activity {
    private ImageButton backButton ;
    private EditText editSearchText;
    private ImageButton searchButton2;
    private String SearchCondition;
    private ProgressDialog progressDialog;//进度条
    private final String addressToGeo ="https://api.weibo.com/2/location/pois/search/by_geo.json";
    private final String appKey ="436935748";
    private final String accessToken="2.00n6x4ME0mw1ZT01009563c20K9zrV";
    private String lat = "34.265860873430";
    private  String lng ="108.954454572460";
    private int page=1;//页数
    private ListView searchListView;
    private LinearLayout moreLoad;
    private static final int SUCCESS = 0;
    private static final int ERROR_SERVER = 1;
    private static final int ERROR_DATA_FORMAT = 2;
    private List<Map<String,Object>> MessageListMap = new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);

        //返回按钮
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        //搜索条件
        editSearchText = (EditText) findViewById(R.id.editSearchText);

        //搜索按钮
        searchButton2 = (ImageButton) findViewById(R.id.searchButton2);
        searchButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SearchCondition = "";
                MessageListMap = new ArrayList<Map<String, Object>>();
                SearchCondition = editSearchText.getText().toString().trim();
                MessageListMap messageListMap = new MessageListMap();
                messageListMap.execute(SearchCondition);

            }
        });
        //进度开始
        progressDialog = new ProgressDialog(SearchActivity.this);
        progressDialog.setMessage("加载中...");
       //设置进度条不可以撤销
        progressDialog.setCancelable(false);
        //初始化list 列表数据
        MessageListMap messageListMap = new MessageListMap();
        messageListMap.execute(SearchCondition);
        //LIst 信息列表 点击每一项item 触发事件
        searchListView = (ListView) findViewById(R.id.searchListView);
        searchListView.setOnItemClickListener(new onItemClickListenerImpl());

        //加载更多
        LinearLayout moreLoad = (LinearLayout)findViewById(R.id.moreLoad);
        moreLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = page+1;
                SearchCondition = "";
                SearchCondition = editSearchText.getText().toString().trim();
                MessageListMap messageListMap = new MessageListMap();
                messageListMap.execute(SearchCondition);
            }
        });



    }

    //oncreat 外面

     private class onItemClickListenerImpl implements AdapterView.OnItemClickListener{
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
             Toast.makeText(SearchActivity.this,""+MessageListMap.get(position).get("placeName"),1000).show();

         }
     }
    private class MessageListMap extends AsyncTask <String,String,Integer> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Integer integer) {

            progressDialog.dismiss();

            if(integer == SUCCESS ){

            } else if (integer == ERROR_SERVER){
                showServerErrorMessage();
            } else if (integer == ERROR_DATA_FORMAT){
                showDataErrorMessage();
            }

        }

        @Override
        protected Integer doInBackground(String... params) {

            String SearCon = params[0];
            if(SearCon!=null) {
                if(!SearCon.equals("")){
                    String range = "3000";
                    int count = 20;
                    int sort = 1;
                    String q="";
                    try {
                        q = java.net.URLEncoder.encode(SearCon,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();

                    }
                    String Url = addressToGeo+"?coordinate="+lng+","+lat+"&source"+appKey
                            +"&access_token="+accessToken+"&range="+range+"&count="+count
                            +"&page="+page+"&sr="+sort+"&q="+q;
                    Log.d("SearchActivity",Url);
                    Url.replaceAll(" ","%20");

                     try {
                            String responseStr = requestServerData(Url);
                            JSONObject jsonObject = new JSONObject(responseStr);


                            //给list添加数据

                            JSONArray jsonArray = jsonObject.optJSONArray("poilist");
                         if(jsonArray!=null){
                             int len = jsonArray.length();


                             for(int i=0;i<len;i++){
                                 JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);
                                 String title = jsonObject1.optString("name");
                                 String address =jsonObject1.optString("address");
                                 String distance= jsonObject1.optString("distance");

                                 if(address.equals("null")||address.trim().equals("")){
                                     address = "暂无地址信息...";
                                 }
                                 if (distance.equals("null")){
                                     distance = "暂无距离信息";
                                 }else{
                                     if(!(Integer.valueOf(distance)/100<=9)){
                                         Float distanceChild = Float.valueOf(distance)/1000;
                                         distance = String.valueOf(distanceChild)+"km";

                                     }   else{
                                         distance = distance +"m";
                                     }
                                 }

                                 Map<String,Object> messageMap =new HashMap<String, Object>();
                                 messageMap.put("name",title);
                                 messageMap.put("address",address);
                                 messageMap.put("space",distance);
                                 MessageListMap.add(messageMap);

                             }
                         }

                     } catch (IOException e) {
                         return ERROR_SERVER;
                     } catch (JSONException e) {
                         return ERROR_DATA_FORMAT;
                     }
                }
            }
            this.publishProgress("ok");
            return SUCCESS;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            MySimpleAdapter mySimpleAdapter = new MySimpleAdapter(SearchActivity.this,MessageListMap,R.layout.poi_list_item,
                  new String[]{"name","address","space"},
                  new int[]{R.id.poiNameTextView,R.id.poiaddressTextView,R.id.poidistanceTextView}
                    ) ;
            if(values[0].equals("ok")){
               searchListView.setAdapter(mySimpleAdapter);
                if(MessageListMap.size()!=0){
                     moreLoad = (LinearLayout)findViewById(R.id.moreLoad);
                    moreLoad.setVisibility(View.VISIBLE);

                }
            }
        }

    }

    private void showServerErrorMessage(){
        Toast.makeText(this,"不好意思，请求数据失败",1000).show();
    }

    private void showDataErrorMessage(){
        Toast.makeText(this,"数据格式错误哦",1000).show();
    }

    private String requestServerData(String url) throws IOException {
        //请求服务器
        HttpGet request = new HttpGet(url);
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpResponse response = httpClient.execute(request);
        String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");

        return resultStr;
    }
    public class MySimpleAdapter extends SimpleAdapter {
        private MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView( final int position, View convertView, ViewGroup parent) {
            return super.getView(position,convertView,parent);
            //To change body of overridden methods use File | Settings | File Templates.
        }
    }
}
