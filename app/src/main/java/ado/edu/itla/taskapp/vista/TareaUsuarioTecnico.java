package ado.edu.itla.taskapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        List<Tarea> tareaList = tareaR.buscarAsignaA(usuario);

        final TareaListAdapterTecnico adapterTecnico = new TareaListAdapterTecnico(this, tareaList);
        listView.setAdapter(adapterTecnico);
    }
}
