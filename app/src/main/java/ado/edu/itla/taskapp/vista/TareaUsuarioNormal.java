package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.LoginName;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDBImpl;

public class TareaUsuarioNormal extends AppCompatActivity {
    private static final String LOG_TAG = "TareaUsuarioNormal";
    Button btnCreaTarea;
    TareaRepositorio tareaR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_normal);

        btnCreaTarea = (Button)findViewById(R.id.btnCreaTarea);
        tareaR = new TareaRepositorioDBImpl(this);
        final ListView lvTarea = (ListView)findViewById(R.id.lvTareas);

        final Usuario usuario = new Usuario();
        usuario.setId(LoginName.getInstance().getUsuario().getId());

        List<Tarea> tareas = tareaR.buscarCreadaPor(usuario);
        Log.i(LOG_TAG, "Total de tareas : " + tareas.size());

        final TareaListAdaptarNormal tareaListAdaptarNormal = new TareaListAdaptarNormal(this, tareas);
        lvTarea.setAdapter(tareaListAdaptarNormal);

        lvTarea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Tarea tarea2 = new Tarea();
                Tarea tarea = (Tarea) parent.getItemAtPosition(position);

                long tareaLong = tareaListAdaptarNormal.getItemId(position);
                Toast.makeText(getApplicationContext(), ""+tarea, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TareaUsuarioNormal.this, DetalleTareaNormal.class);
                intent.putExtra("id", tarea.getId());
                startActivity(intent);
            }
        });
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