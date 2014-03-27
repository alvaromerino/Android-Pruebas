package com.alvaro93mg.android.parcelable.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alvaro on 25/03/14.
 */
public class Persona implements Parcelable {

    //Atributos
    private String dni, nombre, apellido1, apellido2;
    private int edad;

    //Constructor con todos los parámetros.
    public Persona(String dni, String nombre, String apellido1, String apellido2, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
    }

    //Constructor que recibe un Parcel (Necesario si nuestra clase implementa Parcelable)
    public Persona(Parcel in) {
        dni = in.readString();
        nombre = in.readString();
        apellido1 = in.readString();
        apellido2 = in.readString();
        edad = in.readInt();
    }

    //Getters & Setters
    public String getDni() { return dni; }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //toString
    @Override
    public String toString() {
        return  "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", edad=" + edad;
    }

    /* PARCELABLE */

    /**
     * Tanto en el constructor que recibe un parcel como en este método se debe seguir el mismo orden,
     * tanto para escribir el objeto a un Parcel, como para reconstruir el objeto desde un Parcel.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dni);
        dest.writeString(nombre);
        dest.writeString(apellido1);
        dest.writeString(apellido2);
        dest.writeInt(edad);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Interfaz que debe ser implementada y provista como un campo público llamado CREATOR
     * que lo que hace es generar instancias de tu clase Parcelable desde un Parcel.
     */
    public static final Parcelable.Creator<Persona> CREATOR = new Parcelable.Creator<Persona>() {

        @Override
        public Persona createFromParcel(Parcel source) {
            return new Persona(source);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

}
