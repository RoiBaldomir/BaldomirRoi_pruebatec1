package org.empleados.persistencia;

import org.empleados.logica.Empleado;

import java.util.List;

public class ControladoraPersistencia {
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();

    // Llamada a los métodos del controlador JPA
    public void crearEmpleado (Empleado empleado) {
        empleadoJpa.create(empleado);
    }

    public void eliminarEmpleado(int id) {
        empleadoJpa.destroy(id);
    }

    public Empleado buscarEntidad(int id) {
        return empleadoJpa.findEntity(id);
    }
    public void editarEmpleado(Empleado empleado) {
        empleadoJpa.edit(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoJpa.findEmpleadoEntities();
    }

    public List<Empleado> buscarEmpleadoPorCargo(String cargo) {
        return empleadoJpa.findEmpleado(cargo);
    }
}
