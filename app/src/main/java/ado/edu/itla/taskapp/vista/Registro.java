package ado.edu.itla.taskapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;

public class Registro extends AppCompatActivity {

    private static final String LOG_TAG = "Registro";
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;
    private String tipoUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioRepositorio = new UsuarioRepositorioDBImpl(this);
        final EditText txtEmail = (EditText)findViewById(R.id.et_email);
        final EditText txtNombre = (EditText)findViewById(R.id.et_nombre);
        final EditText txtContrasena = (EditText)findViewById(R.id.et_contrasena);
        final EditText txtContrasenaConfirmada = (EditText)findViewById(R.id.et_conformaContrasena);
        final RadioButton rbTipoUsuarioNormal = (RadioButton)findViewById(R.id.rb_normal);
        final RadioButton rbTipoUsuarioTecnico = (RadioButton) findViewById(R.id.rb_tecnico);
        Button btnRegistrar = (Button)findViewById(R.id.btn_registar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //VERIFICA CUAL ES EL RADIOBUTTON QUE ESTA SELECCIONADO
                if (rbTipoUsuarioNormal.isChecked()){
                    tipoUsuario = "NORMAL";

                }else if(rbTipoUsuarioTecnico.isChecked()){
                    tipoUsuario = "TECNICO";
                }

                //CONVIERTE LOS COMPONENTES GRAFICOS A LOS TIPOS DE DATOS NECESARIOS PARA SER INSERTADOS
                final String emailSt = txtEmail.getText().toString();
                final String nombreSt = txtNombre.getText().toString();
                final String contrasenaSt = txtContrasena.getText().toString();

                String contrasena1 = txtContrasena.getText().toString();
                String contrasena2 = txtContrasenaConfirmada.getText().toString();

                //VALIDA QUE LOS CAMPOS DE CONTRASENIA SEAN IGUALES
                if(contrasena1.equals(contrasena2)){
                    Usuario usuario = new Usuario(nombreSt, emailSt, contrasenaSt, Usuario.TipoUsuario.valueOf(tipoUsuario));

                    usuarioRepositorio.guardar(usuario);

                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario: " + usuario.getNombre() + " registrado exitosamente.", Toast.LENGTH_SHORT);
                    toast.show();

                    Log.i(LOG_TAG, usuario.toString());

                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "La contraseñas no coinciden", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


    }
}
