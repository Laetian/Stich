package es.ifp.stich;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    protected SQLiteDatabase db;

    public DataBase(Context context) {
        super(context, "notitas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table notas (id integer primary key autoincrement not null, titulo text, comment text)");
        db.execSQL("CREATE table notas1 (id integer primary key autoincrement not null, titulo text, comment text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table IF EXISTS notas");
        db.execSQL("DROP table IF EXISTS notas1");
    }


    public void insertNote(String titulo, String comment)
    {

        db=this.getReadableDatabase();
        db.execSQL("INSERT INTO notas (titulo, comment)VALUES('"+titulo+"','"+comment+"')");
    }
    public int numberOfNotes()
    {
        int num=0;
        db=this.getReadableDatabase();
        num=(int) DatabaseUtils.queryNumEntries(db, "notas");

        return num;
    }
    public void deleteNote(int id)
    {
        db=this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id=" + id);
    }

    public void updateNote(int id, String titulo, String comment)
    {
        db=this.getWritableDatabase();
        db.execSQL("UPDATE notas SET titulo= '"+titulo+"',comment='"+comment+"' WHERE id=" + id);
    }

    public void deleteAllNotes()
    {
        db=this.getWritableDatabase();
        db.execSQL("DELETE FROM notas");
    }
    public ArrayList<String> getAllNotes()
    {
        ArrayList<String> filas=new ArrayList<String>();
        Cursor res=null;
        String contenido="";
        if(numberOfNotes()>0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas ORDER BY id ASC", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                contenido = (res.getString(res.getColumnIndex("titulo")));

                filas.add(contenido);
                res.moveToNext();
            }

        }
        else
        {
            contenido="Añadir una nueva nota";
            filas.add(contenido);

        }

        return filas;

    }

    public Nota getNota (String titulo)
    {
        Nota n=null;
        Cursor res=null;

        if(numberOfNotes()>0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas WHERE titulo='"+titulo+"'", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {

                n= new Nota(res.getInt(res.getColumnIndex("id")), res.getString(res.getColumnIndex("titulo")), res.getString(res.getColumnIndex("comment")));

                res.moveToNext();
            }

        }


        return n;

    }






}
