package com.alvaro93mg.android.serializable.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Alvaro on 24/03/14.
 */
public class Persona implements Serializable {

    //Atributos
    private static final long serialVersionUID = 0L;
    private String dni, nombre, apellido1, apellido2;
    private int edad;

    //Constructor
    public Persona(String dni, String nombre, String apellido1, String apellido2, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
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

    //Métodos de serialización
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(dni);
        out.writeObject(nombre);
        out.writeObject(apellido1);
        out.writeObject(apellido2);
        out.writeInt(edad);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        dni = (String) in.readObject();
        nombre = (String) in.readObject();
        apellido1 = (String) in.readObject();
        apellido2 = (String) in.readObject();
        edad = in.readInt();
    }

}
