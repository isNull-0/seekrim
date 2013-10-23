package com.example.seekrim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.PoiOverlay;

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
    public static final String strKey = "258d1c1785a5767ab5685c3b1824db51";
    private BMapManager bMapManager;
    private MapController mapController;
    private LayoutInflater layoutInflater;
    private View mapPopWindow;
    private PoiOverlay itemItemizedOverlay;
    private MyLocationOverlay myLocationOverlay;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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

 }
}
