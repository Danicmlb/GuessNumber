package com.example.guessnumber.iu;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.data.GuessNumber;
import com.example.guessnumber.databinding.ActivityEndPlayBinding;

/**
 * <h1>Projecto 01 - Guess Number</h1>
 * <h3>Activity EndPlay</h3>
 *
 * Esta activity es simplemente la pantalla final del juego donde una vez terminado nos comunica si hemos ganado
 * o perdido y nos da información sobre la partida.
 *
 * @author Daniel
 * @version 1.0.0
 */


public class EndPlayActivity extends AppCompatActivity {

    public ActivityEndPlayBinding binding;
    public GuessNumber resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btVolverAJugar.setOnClickListener(view -> VolverAJugar());
        Bundle bundle = getIntent().getExtras();
        resultado = bundle.getParcelable("resultado");



        if (resultado.victoria == true) {
            binding.tvResultadoFinalVictoria.setText("¡Has Ganado!");
            binding.tvResultadoExplicacion.setText("¡Enhorabuena " + resultado.getUser() + "! Has acertado el número, era " + resultado.getRandomNumber() + ". Lo has conseguido en " + resultado.getIntentos() + " / " + resultado.toString() + " intentos.");
        } else {
            binding.tvResultadoFinalVictoria.setText("¡Has Perdido!");
            binding.tvResultadoExplicacion.setText("Otra vez será " + resultado.getUser() + ", el número era " + resultado.getRandomNumber() + ".");
        }

    }

    public void VolverAJugar() {
        startActivity(new Intent(this, ConfigActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }
}
