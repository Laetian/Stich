package es.ifp.stich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected ImageView ima1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        label1 = (TextView) findViewById(R.id.label1_start);
        ima1 = (ImageView) findViewById(R.id.ima1_start);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                Intent pasarPantalla = new Intent(StartActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        };

        Timer timer = new Timer();
        timer.schedule(myTimerTask, 3000);
    }
}