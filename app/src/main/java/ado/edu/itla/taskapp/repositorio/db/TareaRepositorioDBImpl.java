package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;


public class TareaRepositorioDBImpl implements TareaRepositorio {
    private String LOG_TAG = "TareaRepositorioDBImpl";

    private ConexionDb conexionDb;
    private static final String TABLA_TAREA = "tarea";
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
        cv.put(CAMPO_USUARIO_CREADOR_ID, tarea.getUsuarioCreador());
        cv.put(CAMPOO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAsignado());
        cv.put(CAMPO_CATEGORIA_ID, tarea.getCategoria());

        SQLiteDatabase db = conexionDb.getWritableDatabase();

        if (tarea.getUsuarioCreador() != 0){
            Long cantidad = db.insert(TABLA_TAREA, null, cv);

            if (cantidad > 0){
                Log.i(LOG_TAG, "Se inserto tarea: " +cantidad);
            }else {
                Log.i(LOG_TAG, "No e inserto tarea");
            }
            Log.i(LOG_TAG, "Tarea creada" + " idTarea: "+ tarea.getId());

            return cantidad > 0;

        }
        //Log.i(LOG_TAG, "Tarea creada" + tarea.toString());

        return false;
    }

    @Override
    public Tarea buscar(int id) {
        SQLiteDatabase db = conexionDb.getReadableDatabase();

        String[] columnas = new String[]{"id", CAMPO_NOMBRE, CAMPO_DESCRICION, CAMPO_FECHA, CAMPO_FECHA_COMPLETADO, CAMPO_ESTADO, CAMPO_USUARIO_CREADOR_ID, CAMPOO_USUARIO_ASIGNADO_ID, CAMPO_CATEGORIA_ID};

        //String idST = Integer.toString(LoginName.getInstance().getUsuario().getId());
        String idST = Integer.toString(id);
        String arg[] = new String[]{idST};

        Cursor cr = db.query(TABLA_TAREA, columnas, "id = ?", arg, null, null, null, null);
        if (cr.moveToFirst()){

            Tarea tarea = new Tarea();

            int idCategoria = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRICION));
            Long fecha = cr.getLong(cr.getColumnIndex(CAMPO_FECHA));
            long fecha_completado = cr.getLong(cr.getColumnIndex(CAMPO_FECHA_COMPLETADO));
            String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));
            int usuario_creador_id = cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID));
            int usuario_asigador_id = cr.getInt(cr.getColumnIndex(CAMPOO_USUARIO_ASIGNADO_ID));
            int categoria_id = cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID));

            tarea.setId(idCategoria);
            tarea.setNombre(nombre);
            tarea.setDescripcion(descripcion);
            tarea.setFecha(new Date(fecha));
            tarea.setFechaterminado(new Date(fecha_completado));
            tarea.setEstado(Tarea.tareaestado.valueOf(estado));
            tarea.setUsuarioCreador(usuario_creador_id);
            tarea.setUsuarioAsignado(usuario_asigador_id);
            tarea.setCategoria(categoria_id);

            return tarea;
        }
        return null;
    }

    @Override
    public List<Tarea> buscarAsignaA(Usuario usuario) {

        SQLiteDatabase db = conexionDb.getReadableDatabase();

        List<Tarea> tareas = new ArrayList<>();

        String[] columnas = new String[]{"id", CAMPO_NOMBRE, CAMPO_DESCRICION, CAMPO_FECHA, CAMPO_FECHA_COMPLETADO, CAMPO_ESTADO, CAMPO_USUARIO_CREADOR_ID, CAMPOO_USUARIO_ASIGNADO_ID, CAMPO_CATEGORIA_ID};

        //String idST = Integer.toString(LoginName.getInstance().getUsuario().getId());
        String idST = Integer.toString(usuario.getId());
        String arg[] = new String[]{idST};

        Cursor cr = db.query(TABLA_TAREA, columnas, "usuario_asignado_id = ?", arg, null, null, null, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()){
            Tarea tarea = new Tarea();

            int idCategoria = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRICION));
            Long fecha = cr.getLong(cr.getColumnIndex(CAMPO_FECHA));
            long fecha_completado = cr.getLong(cr.getColumnIndex(CAMPO_FECHA_COMPLETADO));
            String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));
            int usuario_creador_id = cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID));
            int usuario_asigador_id = cr.getInt(cr.getColumnIndex(CAMPOO_USUARIO_ASIGNADO_ID));
            int categoria_id = cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID));

            tarea.setId(idCategoria);
            tarea.setNombre(nombre);
            tarea.setDescripcion(descripcion);
            tarea.setFecha(new Date(fecha));
            tarea.setFechaterminado(new Date(fecha_completado));
            tarea.setEstado(Tarea.tareaestado.valueOf(estado));
            tarea.setUsuarioCreador(usuario_creador_id);
            tarea.setUsuarioAsignado(usuario_asigador_id);
            tarea.setCategoria(categoria_id);

            tareas.add(tarea);
            cr.moveToNext();
        }

        cr.close();
        db.close();

        return tareas;
    }

    @Override
    public List<Tarea> buscarCreadaPor(Usuario usuario) {
        SQLiteDatabase db = conexionDb.getReadableDatabase();

        List<Tarea> tareas = new ArrayList<>();

        String[] columnas = new String[]{"id", CAMPO_NOMBRE, CAMPO_DESCRICION, CAMPO_FECHA, CAMPO_FECHA_COMPLETADO, CAMPO_ESTADO, CAMPO_USUARIO_CREADOR_ID, CAMPOO_USUARIO_ASIGNADO_ID, CAMPO_CATEGORIA_ID};

        //String idST = Integer.toString(LoginName.getInstance().getUsuario().getId());
        String idST = Integer.toString(usuario.getId());
        String arg[] = new String[]{idST};

        Cursor cr = db.query(TABLA_TAREA, columnas, "usuario_creador_id = ?", arg, null, null, null, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()){
            Tarea tarea = new Tarea();

                int idCategoria = cr.getInt(cr.getColumnIndex("id"));
                String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
                String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRICION));
                Long fecha = cr.getLong(cr.getColumnIndex(CAMPO_FECHA));
                long fecha_completado = cr.getLong(cr.getColumnIndex(CAMPO_FECHA_COMPLETADO));
                String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));
                int usuario_creador_id = cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID));
                int usuario_asigador_id = cr.getInt(cr.getColumnIndex(CAMPOO_USUARIO_ASIGNADO_ID));
                int categoria_id = cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID));

                tarea.setId(idCategoria);
                tarea.setNombre(nombre);
                tarea.setDescripcion(descripcion);
                tarea.setFecha(new Date(fecha));
                tarea.setFechaterminado(new Date(fecha_completado));
                tarea.setEstado(Tarea.tareaestado.valueOf(estado));
                tarea.setUsuarioCreador(usuario_creador_id);
                tarea.setUsuarioAsignado(usuario_asigador_id);
                tarea.setCategoria(categoria_id);

                tareas.add(tarea);
                cr.moveToNext();
        }

        cr.close();
        db.close();

        return tareas;
    }
}