package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ado.edu.itla.taskapp.R;

public class TareaUsuarioNormal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_usuario_normal);
        Button btnCreaTarea = (Button)findViewById(R.id.btnCreaTarea);

        btnCreaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent integer = new Intent(TareaUsuarioNormal.this, AsignarTareaUsuarioNormal.class);
                startActivity(integer);
            }
        });
    }
}
