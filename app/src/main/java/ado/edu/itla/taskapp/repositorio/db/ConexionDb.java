package ado.edu.itla.taskapp.repositorio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConexionDb extends SQLiteOpenHelper {
    private static final String LOG_TAG = "ConexionDb";
    private static final String NOMBRE_DB = "taskapp.db";
    private static final int VERSION_DB = 1;


    public ConexionDb(Context context) {
        super(context, NOMBRE_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG, "Creando la base de datos");
        db.execSQL(EstructuraDb.TABLA_CATEGORIA);
        db.execSQL(EstructuraDb.TABLA_USUARIO);
        db.execSQL(EstructuraDb.TABLA_TAREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: lo veremos en la segunda etapa
        //db.execSQL("CREATE TABLE IF NOT EXISTS tarea (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT, fecha NUMERIC, fecha_completado NUMERIC, estado TEXT NOT NULL DEFAULT 'PENDIENTE', usuario_creador_id INTEGER NOT NULL, usuario_asignado_id INTEGER NOT NULL, categoria_id INTEGER)");

    }
}
