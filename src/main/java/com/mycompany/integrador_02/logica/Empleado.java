
package com.mycompany.integrador_02.logica;

import java.util.Date;

public class Empleado extends Persona {
    private String fechaIngreso;
    private String sueldo;
    private String diasTrabajo;
    private String horariosTrabajo;

    public Empleado() {
    }

    public Empleado(String fechaIngreso, String sueldo, String diasTrabajo, String horariosTrabajo, int id, String nombre, String apellido, String dni, Date fechNac, String telefono, String genero) {
        super(id, nombre, apellido, dni, fechNac, telefono, genero);
        this.fechaIngreso = fechaIngreso;
        this.sueldo = sueldo;
        this.diasTrabajo = diasTrabajo;
        this.horariosTrabajo = horariosTrabajo;
    }
    
  

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getDiasTrabajo() {
        return diasTrabajo;
    }

    public void setDiasTrabajo(String diasTrabajo) {
        this.diasTrabajo = diasTrabajo;
    }

    public String getHorariosTrabajo() {
        return horariosTrabajo;
    }

    public void setHorariosTrabajo(String horariosTrabajo) {
        this.horariosTrabajo = horariosTrabajo;
    }
    
    
}
