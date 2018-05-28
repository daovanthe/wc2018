package th.wc2018.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.the.wcservice.WcService;

import th.wc2018.R;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Intent serviceIntent = new Intent();
        serviceIntent.setPackage("th.wc2018");
        serviceIntent.setClass(this, WcService.class);
        startService(serviceIntent);
    }
}
