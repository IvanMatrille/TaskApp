package ado.edu.itla.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.vista.CategoriaActivity;
import ado.edu.itla.taskapp.vista.CategoriaListAdapter;
import ado.edu.itla.taskapp.vista.CategoriaListaActivity;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCategoria = (Button)findViewById(R.id.btnCategoria);

        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, CategoriaListaActivity.class);
                startActivity(intent);
            }
        });
    }




    /*private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Categoria cat = new Categoria();

        cat.setDescripcion("Categoria 1");
        cat.setId(2);

        Log.i(LOG_TAG, cat.toString());
    }*/
}
