package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        ContentValues cv = new ContentValues();

        cv.put(CAMPO_NOMBRE, categoria.getDescripcion());
        SQLiteDatabase db = conexionDb.getWritableDatabase();

        Long id = db.insert(TABLA_CATEGORIA, null, cv);

        if (id.intValue() > 0){
            categoria.setId(id.intValue());
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizar(Categoria categoria) {
        return false;
    }

    @Override
    public Categoria buscar(int id) {
        return null;
    }

    @Override
    public List<Categoria> buscar(String nombre) {
        return null;
    }
}


