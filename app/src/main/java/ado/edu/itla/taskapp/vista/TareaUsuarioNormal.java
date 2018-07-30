package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.LoginName;
import ado.edu.itla.taskapp.repositorio.TareaListAdaptar;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDBImpl;

public class TareaUsuarioNormal extends AppCompatActivity {
    Button btnCreaTarea;
    ListView lvTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_usuario_normal);

        btnCreaTarea = (Button)findViewById(R.id.btnCreaTarea);
        lvTarea = (ListView)findViewById(R.id.lvTareas);
        TareaRepositorio tareaR = new TareaRepositorioDBImpl(this);

        Usuario usuario = new Usuario();
        usuario.setId(LoginName.getInstance().getUsuario().getId());

        TareaListAdaptar tareaListAdapter = new TareaListAdaptar(this, tareaR.buscarCreadaPor(usuario));
        lvTarea.setAdapter(tareaListAdapter);

        //CREA UNA TAREA.
        btnCreaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(TareaUsuarioNormal.this, AsignarTarea.class);
                startActivity(integer);
            }
        });
    }
}