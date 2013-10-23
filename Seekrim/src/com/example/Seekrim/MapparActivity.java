package com.example.seekrim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.*;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-16
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
public class MapparActivity extends Activity {

    private TextView topText;
    private ImageButton backButton;
    private ImageButton topButton;
    private MapView mapView;
    public static final String strKey = "FF49f1c9b6d2940e875b52fbe378936f";
    public static BMapManager bMapManager;
    private MapController mapController;
    private LayoutInflater layoutInflater;
    private View mapPopWindow;
    private PoiOverlay<OverlayItem> itemItemizedOverlay;
    private MyLocationOverlay myLocationOverlay;


    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        bMapManager = new BMapManager(this);
        boolean initResult = bMapManager.init(strKey,new MKGeneralListener() {
            @Override
            public void onGetNetworkState(int iError) {
                if(iError == MKEvent.ERROR_NETWORK_CONNECT){
                    Toast.makeText(MapparActivity.this,"您的网络出现问题啦！",Toast.LENGTH_LONG).show();
                }else if(iError==MKEvent.ERROR_NETWORK_DATA){
                    Toast.makeText(MapparActivity.this,"输入正确的检索条件",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onGetPermissionState(int iError) {
                   if(iError == MKEvent.ERROR_PERMISSION_DENIED){
                  Toast.makeText(MapparActivity.this,"请在 DemoApplication.java文件输入正确的授权Key！！",Toast.LENGTH_LONG).show();
                   }
            }
        });
         if(!initResult){
             Toast.makeText(MapparActivity.this,"请在 DemoApplication.java文件输入正确的授权Key！！",Toast.LENGTH_LONG).show();
         };
        setContentView(R.layout.particulars);
        backButton = (ImageButton) findViewById(R.id.Tbackbutton);
        topText = (TextView) findViewById(R.id.map_head_Text);
        Intent intent = getIntent();
        topText.setText(intent.getStringExtra("name"));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapparActivity.this.finish();
            }
        });
        //给  xml 放      地图
        layoutInflater =LayoutInflater.from(this);

        mapView = (MapView) findViewById(R.id.bmapView);
        //允许地图进行缩放
        mapView.setBuiltInZoomControls(true);

         //设置卫星图层开启
//        mapView.setSatellite(true);
        //设置交通层开启
//        mapView.setTraffic(true);
        //      获取地图控制器
        mapController = mapView.getController();
        //控制缩放等级
        mapController.setZoom(14);
        //移动到经纬度点
        final GeoPoint geoPoint = new GeoPoint((int) (34.25934463685013 * 1E6), (int) (108.94721031188965 * 1E6));
        //设置中心点
         mapController.setCenter(geoPoint);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              mapController.animateTo(geoPoint);
            }
        },5000);

        Drawable marker = getResources().getDrawable(R.drawable.icon_marka);

        itemItemizedOverlay = new PoiOverlay<OverlayItem>(marker,mapView);

        mapView.getOverlays().add(itemItemizedOverlay);

       // 添加弹出窗口
        mapPopWindow = layoutInflater.inflate(R.layout.map_pop_window,null);
        mapPopWindow.setVisibility(View.GONE);
        mapView.addView(mapPopWindow);




      }
    class PoiOverlay<OverlayItem> extends ItemizedOverlay{

        public PoiOverlay(Drawable drawable, MapView mapView) {
            super(drawable, mapView);
        }
        protected boolean onTap(int i){
           com.baidu.mapapi.map.OverlayItem item = itemItemizedOverlay.getItem(i);
            GeoPoint geoPoint = item.getPoint();
            String title = item.getTitle();
            String content =item.getSnippet();

            TextView titleTextView = (TextView) mapPopWindow.findViewById(R.id.titleTextView);
            TextView contentTextView = (TextView) mapPopWindow.findViewById(R.id.contentTextView);
            titleTextView.setText(title);
            contentTextView.setText(content);

            MapView.LayoutParams layoutParams =new MapView.LayoutParams(
                    MapView.LayoutParams.WRAP_CONTENT,
                    MapView.LayoutParams.WRAP_CONTENT,
                    geoPoint,
                    0,
                    -40,
                    MapView.LayoutParams.BOTTOM_CENTER);

            mapPopWindow.setVisibility(View.VISIBLE);
            mapPopWindow.setLayoutParams(layoutParams);
            mapController.animateTo(geoPoint);
            return super.onTap(i);
        };
        public boolean onTap(GeoPoint geoPoint, MapView mapView){

          mapPopWindow.setVisibility(View.GONE);
            return super.onTap(geoPoint,mapView);
        };
    }
}
