package es.ifp.stich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ListadoActivity extends AppCompatActivity {

    protected ImageButton imab1;
    protected ImageButton imab2;
    protected ImageButton imab3;
    protected ImageButton imab4;
    protected ImageButton imab5;
    protected ImageButton imab6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        imab1=(ImageButton) findViewById(R.id.imab1_listado);
        imab2=(ImageButton) findViewById(R.id.imab2_listado);
        imab3=(ImageButton) findViewById(R.id.imab3_listado);
        imab4=(ImageButton) findViewById(R.id.imab4_listado);
        imab5=(ImageButton) findViewById(R.id.imab5_listado);
        imab6=(ImageButton) findViewById(R.id.imab6_listado);


        imab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarPantalla = new Intent(ListadoActivity.this, StitchActivity.class);
                finish();
                startActivity(pasarPantalla);

            }
        });
    }
}