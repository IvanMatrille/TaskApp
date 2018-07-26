package ado.edu.itla.taskapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;

public class AsignarTareaUsuarioNormal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    UsuarioRepositorio usuarioR;
    Usuario usuario;
    Spinner spTecnicos;
    List<String> usuariosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tarea_usuario_normal);

        usuario = new Usuario();
        spTecnicos = (Spinner)findViewById(R.id.spTecnicos);
        spTecnicos.setOnItemSelectedListener(this);
        //int usuariosSize = usuarioR.buscarTecnicos().size();

        usuarioR = new UsuarioRepositorioDBImpl(this);

        //BUSCA LOS TECNICOS Y AGREGA SU NOMBRE A LA LISTA DE USUARIOS TIPO STRING
        for (int i = 0; i < usuarioR.buscarTecnicos().size(); i++){
            usuariosList.add(usuarioR.buscarTecnicos().get(i).getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, usuariosList);
        spTecnicos.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int idTecnico = usuarioR.buscarTecnicos().get(position).getId();

        Toast toast = Toast.makeText(getApplicationContext(), "El id es: " + idTecnico, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
