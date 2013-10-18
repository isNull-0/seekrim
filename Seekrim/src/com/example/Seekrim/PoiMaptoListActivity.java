package com.example.Seekrim;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-17
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public class PoiMaptoListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poimaptolist);
    }
}
