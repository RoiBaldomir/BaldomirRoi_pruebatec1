package org.empleados.logica;

import org.empleados.persistencia.ControladoraPersistencia;

import java.util.List;

// Esta clase trae los métodos para trabajar con la BD del paquete de persistencia al paquete de lógica
public class Controladora {
    ControladoraPersistencia cp = new ControladoraPersistencia();

    public void crearEmpleado(Empleado empleado) {
        cp.crearEmpleado(empleado);
    }

    public void eliminarEmpleado(int id) {
        cp.eliminarEmpleado(id);
    }

    public Empleado buscarEntidad(int id) {
        return cp.buscarEntidad(id);
    }

    public void editarEmpleado(Empleado empleado) {
        cp.editarEmpleado(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return cp.listarEmpleados();
    }

    public List<Empleado> buscarEmpleadoPorCargo(String cargo) {
        return cp.buscarEmpleadoPorCargo(cargo);
    }
}
