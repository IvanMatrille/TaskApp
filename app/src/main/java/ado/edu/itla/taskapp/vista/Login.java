package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.LoginName;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;

public class Login extends AppCompatActivity {
    private UsuarioRepositorio usuarioRepositorio;
    private String LOG_TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tv_registrarse = (TextView)findViewById(R.id.tv_registrarse);
        final Button btnIniciarSesion = (Button)findViewById(R.id.btn_inicio);
        final EditText et_usuario = (EditText)findViewById(R.id.etUsuarioLogin);
        final EditText et_contrasena = (EditText)findViewById(R.id.ed_contrasenaLogin);

        //SQLiteDatabase db = new ConexionDb(this).getWritableDatabase();
        //db.execSQL(EstructuraDb.TABLA_TAREA);

        usuarioRepositorio = new UsuarioRepositorioDBImpl(this);

        //ENVIA A LA ACTIVITY REGISTRAR USUARIO.
        tv_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario(et_usuario.getText().toString(), et_contrasena.getText().toString());
                usuario = usuarioRepositorio.UsuarioExiste(usuario);

                if (usuario != null){
                    LoginName.getInstance().setUsuario(usuario);
                    Toast.makeText(getApplicationContext(), usuario.getId().toString(), Toast.LENGTH_LONG).show();

                    if (usuario.getTipoUsuario() == Usuario.TipoUsuario.NORMAL)
                    {
                        Intent intent = new Intent(Login.this, TareaUsuarioNormal.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(Login.this, TareaUsuarioTecnico.class);
                        startActivity(intent);
                    }

                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "El usuario o la contraseña son incorrecto", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });



      /*  boolean resultado = usuarioRepositorio.UsuarioExiste(new Usuario(et_usuario.getText().toString(), et_contrasena.getText().toString()));

        if(resultado == true){
            Toast toast = Toast.makeText(getApplicationContext(), "El usuario existe.", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "El usuario no existe.", Toast.LENGTH_SHORT);
            toast.show();
        }
    */

        Log.i(LOG_TAG, usuarioRepositorio.toString());

    }
}
