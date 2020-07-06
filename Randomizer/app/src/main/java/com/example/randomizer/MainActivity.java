package com.example.randomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randomizer.ui.main.MainFragment;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
        //setContentView(R.layout.main_activity);

        Button rollButton = (Button) findViewById(R.id.rollButton);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        rollButton.setOnClickListener(this);
    }

    protected void genRandNum() {
        Random randGen = new Random();
        TextView resultsTextView = (TextView) findViewById(R.id.resultsTextView);
        Button rollButton = (Button) findViewById(R.id.rollButton);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        int randInt = randGen.nextInt(seekBar.getProgress());
        resultsTextView.setText(randInt);
    }

    //use battery info to display current battery level in progress bar. Have visual representation of flow rate compared to current level.
    public float getBatteryPercent() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        assert batteryStatus != null;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return level / (float)scale * 100;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.rollButton:
                Toast.makeText(this, "Random Number Generated", Toast.LENGTH_SHORT).show();
                genRandNum();
                break;
        }
    }
}