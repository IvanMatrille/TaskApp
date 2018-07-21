package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;

public class TareaRepositorioDBImpl implements TareaRepositorio {
    private String LOG_TAG = "TareaRepositorioDBImpl";

    private ConexionDb conexionDb;
    private static final String CAMPO_TABLA = "tarea";
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_DESCRICION = "descripcion";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_FECHA_COMPLETADO = "fecha_completado";
    private static final String CAMPO_ESTADO = "estado";
    private static final String CAMPO_USUARIO_CREADOR_ID = "usuario_creador_id";
    private static final String CAMPOO_USUARIO_ASIGNADO_ID = "usuario_asignado_id";
    private static final String CAMPO_CATEGORIA_ID = "categoria_id";

    public TareaRepositorioDBImpl(Context context){
        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Tarea tarea) {
        Log.i(LOG_TAG, "Guardando tarea" + tarea.toString());

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, tarea.getNombre());
        cv.put(CAMPO_DESCRICION, tarea.getDescripcion());
        cv.put(CAMPO_FECHA, tarea.getFecha().getTime());
        cv.put(CAMPO_FECHA_COMPLETADO, tarea.getFechaterminado().getTime());
        cv.put(CAMPO_ESTADO, tarea.getEstado().toString());
        cv.put(CAMPO_USUARIO_CREADOR_ID, tarea.getUsuarioCreador().getId());
        cv.put(CAMPOO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAsignado().getId());
        cv.put(CAMPO_CATEGORIA_ID, tarea.getCategoria().getId());

        SQLiteDatabase db = conexionDb.getReadableDatabase();

        if (tarea.getId() != null && tarea.getId() >0){
            Long cantidad = db.insert(CAMPO_TABLA, null, cv);
        }


        return false;
    }

    @Override
    public Categoria buscar(int id) {
        return null;
    }

    @Override
    public List<Tarea> buscarAsignaA(Usuario usuario) {
        return null;
    }

    @Override
    public List<Tarea> buscarCreadaPor(Usuario usuario) {
        return null;
    }
}