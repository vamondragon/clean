package com.example.admin.appclean.models;

import java.util.Date;


public class UserGenericModel {

    private long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    private Date date;


    public UserGenericModel(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, Date date) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.date = date;
    }

    /**
     * This constructor should be used when we are converting an already existing  item to this POJO, so we already have
     * an id variable.
     */
    public UserGenericModel(long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, Date date) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.date = date;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFullName() {
        return (!nombre.isEmpty() ? nombre + " " : " ") +
                (!apellidoPaterno.isEmpty() ? apellidoPaterno + " " : " ") +
                (!apellidoMaterno.isEmpty() ? apellidoMaterno + " " : " ");
    }

    @Override
    public String toString() {
        return "UserGenericModel{" +
                "mId=" + id +
                ", mNombre='" + nombre + '\'' +
                ", mApellidoPaterno='" + apellidoPaterno + '\'' +
                ", mApellidoMaterno=" + apellidoMaterno +
                '}';
    }
}
