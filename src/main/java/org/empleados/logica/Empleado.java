package org.empleados.logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

// Anotación Entity que permite a la clase ser reconocida por JPA
@Entity
public class Empleado implements Serializable {
    // Anotación Id para indicar cuál será el ID de la tabla creada
    @Id
    // Se selecciona el tipo de generación como identity, ya que mySQL admite la generación automática de claves primarias
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Atributos
    private int id; // ID numérica correspondiente al empleado
    private String nombre; // Nombre del empleado
    private String apellido; // Apellido del empleado
    private String cargo; // Cargo del empleado
    private double salario; // Salario del empleado
    private LocalDate fechaInicio; // Fecha de inicio en el puesto del empleado

    // Constructores
    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String cargo, double salario, LocalDate fechaInicio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // Método toString() para muestra de datos por pantalla
    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", fechaInicio=" + fechaInicio;

    }
}
