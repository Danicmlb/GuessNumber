package com.example.guessnumber.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.guessnumber.data.GuessNumber;
import com.example.guessnumber.databinding.ActivityConfigBinding;

/**
 * <h1>Projecto 01 - Guess Number</h1>
 * <h3>Activity Config</h3>
 *
 * Esta activity es la encargada de recoger los datos de configuración de la partida
 * Tanto nombre de usuario como número de intentos.
 *
 * A través de un binding, un bundle y un intent somos capaces de guardar la información introducida y pasarla
 * a la activity que queramos.
 *
 * @author Daniel
 * @version 1.0.0
 */

public class ConfigActivity extends AppCompatActivity {

    public ActivityConfigBinding binding;
    GuessNumber guessNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btSendConfig.setOnClickListener(view -> sendNumber());
    }

    public void sendNumber() {

        if ((binding.etUser.getText().toString().isEmpty() || binding.etIntentos.getText().toString().isEmpty())) {
            Toast.makeText(this, "Inserte datos", Toast.LENGTH_SHORT).show();
        } else {
            guessNumber = new GuessNumber(binding.etUser.getText().toString(), Integer.parseInt(binding.etIntentos.getText().toString()));

            Bundle bundle = new Bundle();
            bundle.putParcelable("guessNumber", guessNumber);
            Intent intent = new Intent(this, PlayActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}