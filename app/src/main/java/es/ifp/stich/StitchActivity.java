package es.ifp.stich;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StitchActivity extends AppCompatActivity {

    protected ListView lista1;

    private DataBase db;

    private ArrayList<String> filas = new ArrayList<String>();
    private ArrayAdapter<String> adaptador;

    private String contenido="";
    private String[] partes;
    private Intent intent1=new Intent();

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stitch);

        db= new DataBase(this);

        lista1= (ListView) findViewById(R.id.lista1_stitch);

        filas=db.getAllNotes();
        adaptador= new ArrayAdapter<String>(StitchActivity.this, android.R.layout.simple_list_item_1, filas);
        lista1.setAdapter(adaptador);

        lista1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                contenido=parent.getItemAtPosition(position).toString();
                if (contenido.equals("Añadir una nueva nota")) {
                    Toast.makeText(StitchActivity.this, "No hay ninguna nota que borrar", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    contenido=parent.getItemAtPosition(position).toString();
                    AlertDialog.Builder builder = new AlertDialog.Builder(StitchActivity.this);
                    builder.setTitle("Borrar nota");
                    builder.setMessage("¿Seguro que quieres borrar la nota?");        // add the buttons
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Nota n= db.getNota(contenido);
                            int ide=n.getId();
                            db.deleteNote(ide);
                            Toast.makeText(StitchActivity.this, "Nota borrada correctamente", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(getIntent());
                        }
                    });

                    builder.setNegativeButton("Cancelar", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }

                return false;
            }
        });
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contenido=parent.getItemAtPosition(position).toString();
                if (contenido.equals("Añadir una nueva nota")) {
                    intent1 = new Intent(StitchActivity.this, CrearNotaActivity.class);
                    startActivity(intent1);
                }
                else
                {
                    intent1 = new Intent(StitchActivity.this, VerNotaActivity.class);
                    intent1.putExtra("TITULO", contenido);
                    finish();
                    startActivity(intent1);
                }

            }
        });
    }
    }
