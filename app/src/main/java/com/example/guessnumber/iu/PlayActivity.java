package com.example.guessnumber.iu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.PictureInPictureModeChangedInfo;

import com.example.guessnumber.data.GuessNumber;
import com.example.guessnumber.databinding.ActivityPlayBinding;

/**
 * <h1>Projecto 01 - Guess Number</h1>
 * <h3>Activity Play</h3>
 *
 * Esta activity es la de el desarrollo del juego en interacción completa con el *POJO GuessNumber*
 * Además de mostrarnos la información del quien esta jugando y con cuantos intentos, nos dice si el número
 * a adivinar es mayor o menor y cuantos intentos nos quedan.
 *
 * @author Daniel
 * @version 1.0.0
 */


public class PlayActivity extends AppCompatActivity {

    public ActivityPlayBinding binding;
    public GuessNumber guessNumber;
    public GuessNumber resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        guessNumber = bundle.getParcelable("guessNumber");

        binding.tvUsuario.setText(guessNumber.getUser());
        binding.tvIntentos.setText(guessNumber.toString());

        binding.btComprobar.setOnClickListener(view -> Comprobar());
        binding.btReintento.setOnClickListener(view -> Reintentar());
        binding.btReintento.setEnabled(false);

        guessNumber.Aleatorio();
    }

    public void Comprobar() {
        if (binding.etNumber.getText().toString().isEmpty())
            Toast.makeText(this, "Inserte datos", Toast.LENGTH_SHORT).show();
        else {
            if (Integer.parseInt(String.valueOf(binding.tvIntentos.getText())) > guessNumber.getIntentos()) {
                guessNumber.Incremento();
                if (Integer.parseInt(binding.etNumber.getText().toString()) > guessNumber.getRandomNumber()) {
                    binding.tvResultado.setText("El número a adivinar es menor, te quedan " + (Integer.parseInt(binding.tvIntentos.getText().toString()) - guessNumber.getIntentos()) + " intentos.");
                    binding.btComprobar.setEnabled(false);
                    binding.btReintento.setEnabled(true);
                }

                if (Integer.parseInt(binding.etNumber.getText().toString()) < guessNumber.getRandomNumber()) {
                    binding.tvResultado.setText("El número a adivinar es mayor, te quedan " + (Integer.parseInt(binding.tvIntentos.getText().toString()) - guessNumber.getIntentos()) + " intentos.");
                    binding.btComprobar.setEnabled(false);
                    binding.btReintento.setEnabled(true);
                }

                if (Integer.parseInt(binding.etNumber.getText().toString()) == guessNumber.getRandomNumber()){
                    guessNumber.victoria = true;
                    EnvioBundle();
                }
            }

            if (Integer.parseInt(String.valueOf(binding.tvIntentos.getText())) == guessNumber.getIntentos()) {
                guessNumber.victoria = false;
                EnvioBundle();
            }

        }
    }

    public void Reintentar(){
        binding.etNumber.setText("");
        binding.tvResultado.setText("");
        binding.btComprobar.setEnabled(true);
        binding.btReintento.setEnabled(false);

    }

    public void EnvioBundle() {
        resultado = new GuessNumber(binding.tvUsuario.getText().toString(), guessNumber.getRandomNumber(), Integer.parseInt(guessNumber.toString()), guessNumber.getIntentos() , guessNumber.victoria);
        Bundle bundle = new Bundle();
        bundle.putParcelable("resultado", resultado);
        Intent intent = new Intent(this, EndPlayActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
