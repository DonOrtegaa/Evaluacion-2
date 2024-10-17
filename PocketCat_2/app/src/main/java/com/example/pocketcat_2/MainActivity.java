package com.example.pocketcat_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickAcceder(View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, Menu.class);
        //Llama al método startActivity() para iniciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }
}