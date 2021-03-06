package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;

public class UsuarioRepositorioDBImpl implements UsuarioRepositorio {

    private String LOG_TAG = "UsuarioRepositorioDBImpl";

    private  ConexionDb conexionDb;
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_EMAIL = "email";
    private static final String CAMPO_CONTRASENA = "contrasena";
    private static final String CAMPO_TIPOUSUARIO = "tipoUsuario";
    private static final String TABLA_USUARIO = "usuario";

    public UsuarioRepositorioDBImpl(Context context){
        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Usuario usuario) {

        Log.i(LOG_TAG, "Guardando usuario : " + usuario.toString());

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_CONTRASENA, usuario.getContrasena());
        cv.put(CAMPO_TIPOUSUARIO, usuario.getTipoUsuario().name());

        SQLiteDatabase db = conexionDb.getReadableDatabase();

        if (usuario.getId() != null && usuario.getId() > 0) {
            int cantidad = db.update(TABLA_USUARIO, cv, "id = ?", new String[]{usuario.getId().toString()});
            db.close();

            return cantidad > 0;

        } else {
            Long id = db.insert(TABLA_USUARIO, null, cv);
            db.close();

            if (id.intValue() > 0) {
                usuario.setId((id.intValue()));
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean actualizar(Usuario usuario) {

        ContentValues cv = new ContentValues();

        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_CONTRASENA, usuario.getContrasena());
        cv.put(CAMPO_TIPOUSUARIO, usuario.getTipoUsuario().toString());

        SQLiteDatabase db = conexionDb.getWritableDatabase();

        int cantidad = db.update(TABLA_USUARIO, cv, "id = ?", new String[]{usuario.getId().toString()});

        db.close();
        return cantidad > 0;
    }

    @Override
    public Usuario buscar(int id) {
        Usuario usuario = null;

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA, CAMPO_TIPOUSUARIO};

        Cursor cr = db.query(TABLA_USUARIO, columnas, "id = ?", new String[]{Integer.toString(id)}, null,null, null, null);
        cr.moveToFirst();

            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String email = cr.getString(cr.getColumnIndex(CAMPO_EMAIL));
            String contrasena = cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA));
            String tipoUsuario = cr.getString(cr.getColumnIndex(CAMPO_TIPOUSUARIO));

            usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setContrasena(contrasena);
            usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoUsuario));

        cr.close();
        db.close();

        return usuario;
    }

    @Override
    public List<Usuario> buscarTecnicos()
    {
        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA, CAMPO_TIPOUSUARIO};

        Cursor cr = db.query(TABLA_USUARIO, columnas, null,null, null, null,null,null);
        cr.moveToFirst();

        while (!cr.isAfterLast())
        {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex("nombre"));
            String email = cr.getString(cr.getColumnIndex("email"));
            String contrasena = cr.getString(cr.getColumnIndex("contrasena"));
            String tipoUsuario = cr.getString(cr.getColumnIndex("tipoUsuario"));

            usuarios.add(new Usuario(id, nombre, email, contrasena, Usuario.TipoUsuario.valueOf(tipoUsuario)));
            cr.moveToNext();
        }

        cr.close();
        db.close();

        return usuarios;
    }

    @Override
    public List<Usuario> buscar(String buscar) {
        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA, CAMPO_TIPOUSUARIO};

        Cursor cr = db.query(TABLA_USUARIO, columnas, null,null, null, null,null,null);
        cr.moveToFirst();

        while (!cr.isAfterLast())
        {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex("nombre"));
            String email = cr.getString(cr.getColumnIndex("email"));
            String contrasena = cr.getString(cr.getColumnIndex("contrasena"));
            String tipoUsuario = cr.getString(cr.getColumnIndex("tipoUsuario"));

            usuarios.add(new Usuario(id, nombre, email, contrasena));
            cr.moveToNext();
        }

        cr.close();
        db.close();

        return usuarios;
    }

    public Usuario UsuarioExiste(Usuario usuario)
    {
        SQLiteDatabase bd = conexionDb.getReadableDatabase();
        String columnas[] = {CAMPO_EMAIL, CAMPO_CONTRASENA};
        String email = usuario.getEmail();
        String contrasena = usuario.getContrasena();
        //String args[] = {usuario.getEmail().toString(), usuario.getContrasena().toString()};

        //Cursor cr = bd.query(TABLA_USUARIO, columnas, "email = ?, AND contrasena = ?", args,null, null, null);
        Cursor cr = bd.rawQuery("SELECT id, "+CAMPO_EMAIL+", "+CAMPO_NOMBRE+", "+CAMPO_TIPOUSUARIO +" FROM usuario WHERE email='" +email +"' AND contrasena='"+ contrasena +"'", null);

        if (cr.moveToFirst()){
             int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex("nombre"));
            String tipoUsuario = cr.getString(cr.getColumnIndex("tipoUsuario"));

            usuario.setNombre(nombre);
            usuario.setId(id);
            usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoUsuario));

            if(id > 0)
            {
                return usuario;
            }
        }

        cr.close();
        bd.close();

        return null;
    }
}
