package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.LoginName;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDBImpl;

public class TareaUsuarioTecnico extends AppCompatActivity {

    ListView listView;
    TareaRepositorio tareaR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_tecnico);

        listView = (ListView)findViewById(R.id.lvTareaTecnico);
        tareaR = new TareaRepositorioDBImpl(this);

        Usuario usuario = new Usuario();
        usuario.setId(LoginName.getInstance().getUsuario().getId());
        final List<Tarea> tareaList = tareaR.buscarAsignaA(usuario);

        final TareaListAdapterTecnico adapterTecnico = new TareaListAdapterTecnico(this, tareaList);
        listView.setAdapter(adapterTecnico);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarea tarea = (Tarea) parent.getItemAtPosition(position);

                Intent intent = new Intent(TareaUsuarioTecnico.this, DetalleTareaTecnico.class);
                intent.putExtra("tarea", tarea.getId());
                startActivity(intent);
            }
        });
    }
}
