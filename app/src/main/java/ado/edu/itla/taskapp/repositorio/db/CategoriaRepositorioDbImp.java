package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;

public class CategoriaRepositorioDbImp implements CategoriaRepositorio {

    private  ConexionDb conexionDb;
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String TABLA_CATEGORIA = "categoria";

    public CategoriaRepositorioDbImp(Context context){
        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Categoria categoria) {

        if (categoria.getId() != null && categoria.getId() > 0)
        {
            return actualizar(categoria);
        }

        ContentValues cv = new ContentValues();

        cv.put(CAMPO_NOMBRE, categoria.getNombre());
        SQLiteDatabase db = conexionDb.getWritableDatabase();

        Long id = db.insert(TABLA_CATEGORIA, null, cv);

        db.close();

        if (id.intValue() > 0){
            categoria.setId(id.intValue());
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizar(Categoria categoria) {
        ContentValues cv = new ContentValues();

        cv.put(CAMPO_NOMBRE, categoria.getNombre());
        SQLiteDatabase db = conexionDb.getWritableDatabase();

        int cantidad = db.update(TABLA_CATEGORIA, cv, "id = ?", new String[]{categoria.getId().toString()});

        db.close();
        return cantidad > 0;
    }

    @Override
    public Categoria buscar(int id) {
        SQLiteDatabase db = conexionDb.getReadableDatabase();

        String[] columnas = {"id", CAMPO_NOMBRE};

        Cursor cr = db.query(TABLA_CATEGORIA, columnas, "id = ?", new String[]{Integer.toString(id)}, null, null, null);
        if (cr.moveToNext()){
            Categoria categoria = new Categoria();

            int idCat = cr.getInt(cr.getColumnIndex("id"));
            String categoriaST = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));

            categoria.setId(idCat);
            categoria.setNombre(categoriaST);

            return categoria;
        }
        cr.close();
        db.close();

       return null;
    }

    @Override
    public List<Categoria> buscar(String buscar) {
        //TODO: buscar las categorias por su nombre
        List<Categoria> categorias = new ArrayList<>();

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE};

        Cursor cr  = db.query(TABLA_CATEGORIA, columnas, null, null, null, null, null, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()){
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));

            //Agregamos la categoria a la lista.
            categorias.add(new Categoria(id, nombre));
            cr.moveToNext();
        }
        cr.close();
        db.close();

        return categorias;
    }
}


