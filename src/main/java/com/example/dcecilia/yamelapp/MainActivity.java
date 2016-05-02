package com.example.dcecilia.yamelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //
        getMenuInflater().inflate(R.menu.main, menu); //
        return true; //
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, FragActivity.class);

        switch (item.getItemId()) {
            case R.id.item_prefs:
                startActivity(intent.putExtra("fragment_id",FragActivity.FRAGMENT_PREFS));
                return true;
            case R.id.item_status_update:
                startActivity(intent.putExtra("fragment_id", FragActivity.FRAGMENT_STATUS));
                return true;
            case R.id.item_refresh:
                startService(new Intent(this, RefreshService.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
