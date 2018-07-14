package ado.edu.itla.taskapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;

public class Registro extends AppCompatActivity {

    private static final String LOG_TAG = "Registro";
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText txtEmail = (EditText)findViewById(R.id.et_email);
        EditText txtNombre = (EditText)findViewById(R.id.et_nombre);
        EditText txtContrasena = (EditText)findViewById(R.id.et_contrasena);
        EditText txtContrasenaConfirmada = (EditText)findViewById(R.id.et_conformaContrasena);
        RadioButton rbTipoUsuarioNormal = (RadioButton)findViewById(R.id.rb_normal);
        RadioButton rbTipoUsuarioTecnico = (RadioButton) findViewById(R.id.rb_tecnico);



    }
}
