package ru.sberbank.ma.rssviewhab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabrPool.getInstance(this);
        FragmentManager manager = getSupportFragmentManager();
        Fragment recyclerFragment = manager.findFragmentById(R.id.fl_main_id);
        if (recyclerFragment == null) {
            recyclerFragment = getFragment();
            manager.beginTransaction().add(R.id.fl_main_id, recyclerFragment, null).commit();
        }
    }

    protected Fragment getFragment() {
        return HabrViewControl.getInstance();
    }
}
