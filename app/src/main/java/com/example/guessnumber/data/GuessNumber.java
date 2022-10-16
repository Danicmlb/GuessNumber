package com.example.guessnumber.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Random;

/**
 * <h1>Projecto 01 - Guess Number</h1>
 * <h3>POJO GuessNumber</h3>
 *
 * Esta POJO recaba toda la información necesaria para el funcionamiento del juego:
 * <li>Nombre</li>
 * <li>Intentos</li>
 * <li>Contador</li>
 * <li>Numero a adivinar</li>
 * <li>Victoria</li>
 *
 * @author Daniel
 * @version 1.0.0
 */

public class GuessNumber implements Serializable, Parcelable {

    private String user;
    Random rand = new Random();
    private int randomNumber;
    private int num;
    private int intentos = 0;
    public boolean victoria = true;

    public GuessNumber(String user, int num) {
        this.user = user;
        this.num = num;
    }

    public GuessNumber(String user, int randomNumber, int num  , int intentos, boolean victoria) {
        this.user = user;
        this.randomNumber = randomNumber;
        this.num = num;
        this.intentos = intentos;
        this.victoria = victoria;
    }

    public GuessNumber() {
    }

    protected GuessNumber(Parcel in) {
        user = in.readString();
        randomNumber = in.readInt();
        num = in.readInt();
        intentos = in.readInt();
        victoria = in.readByte() != 0;
    }


    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }


    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "" + num;
    }

    public void Aleatorio(){
        randomNumber = rand.nextInt(1000);
    }

    public int Incremento(){return intentos++;}

    //region PARCELABLE

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user);
        dest.writeInt(randomNumber);
        dest.writeInt(num);
        dest.writeInt(intentos);
        dest.writeByte((byte) (victoria ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GuessNumber> CREATOR = new Creator<GuessNumber>() {
        @Override
        public GuessNumber createFromParcel(Parcel in) {
            return new GuessNumber(in);
        }

        @Override
        public GuessNumber[] newArray(int size) {
            return new GuessNumber[size];
        }
    };
    // endregion
}

