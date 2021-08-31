package es.ifp.stich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class VerNotaActivity extends AppCompatActivity {

    protected TextView label1;



    protected EditText caja1;
    protected EditText caja2;


    protected Button boton1;
    protected Button boton2;

    protected String contenidoCaja1="";
    protected String contenidoCaja2="";


    private Bundle extras;

    private String paquete1="";
    private String paquete2="";

    private String titulo="";


    private Integer id=0;


    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);

        db= new DataBase(this);

        label1= (TextView) findViewById(R.id.label1_ver);



        caja1=(EditText) findViewById(R.id.caja1_ver);
        caja2=(EditText) findViewById(R.id.caja2_ver);


        boton1= (Button) findViewById(R.id.boton1_ver);
        boton2=(Button) findViewById(R.id.boton2_ver);




        extras=getIntent().getExtras();
        titulo= extras.getString("TITULO");



        Nota n= db.getNota(titulo);
        if (n!= null){


            paquete1=n.getTitulo();
            paquete2=n.getComment();



            id=n.getId();


            caja1.setText(paquete1);

            caja2.setText(paquete2);





        }

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1=caja1.getText().toString();
                contenidoCaja2=caja2.getText().toString();




                if (contenidoCaja1.equals(""))
                {
                    Toast.makeText(VerNotaActivity.this, "El campo titulo no puede estar vacio", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    db.updateNote(id,contenidoCaja1,contenidoCaja2);
                    Toast.makeText(VerNotaActivity.this,"Nota creada correctamente", Toast.LENGTH_SHORT).show();

                    TimerTask myTimerTask = new TimerTask() {
                        @Override
                        public void run() {
                            Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                            finish();
                            startActivity(pasarPantalla);
                        }
                    };

                    Timer timer = new Timer();
                    timer.schedule(myTimerTask, 1000);


                }

            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerTask myTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                        finish();
                        startActivity(pasarPantalla);
                    }
                };

                Timer timer = new Timer();
                timer.schedule(myTimerTask, 1000);
            }
        });
    }
}
