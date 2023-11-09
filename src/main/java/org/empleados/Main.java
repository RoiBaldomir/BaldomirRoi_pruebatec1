package org.empleados;

import org.empleados.logica.Controladora;
import org.empleados.logica.Empleado;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Se crea una instancia de la Controladora de la lógica para poder acceder a los métodos para trabajar con la BD
        Controladora control = new Controladora();
        //Se crea una instancia de Scanner para leer los datos introducidos por el usuario
        Scanner scanner = new Scanner(System.in);
        // Se crea un ArrayList para almacenar los empleados
        List <Empleado> empleados = new ArrayList<>();

        int op, id;

        String nombre, apellido, cargo;
        List<Integer> fechaInicio;
        double salario;
        Empleado empleadoCreado;

        System.out.println("\n---- Bienvenido al sistema de gestión de empleados ----\n");
        System.out.println("¿Qué operación desea realizar?\n");
        System.out.println("1. Crear un empleado");
        System.out.println("2. Eliminar un empleado");
        System.out.println("3. Modificar un empleado");
        System.out.println("4. Ver el listado de empleados");
        System.out.println("5. Búsqueda de empleados por cargo");
        System.out.println("0. Salir de la aplicación");

        op = scanner.nextInt(); // Selección de opción

        switch (op) {
            case 1: // Creación de empleados
                do {
                    nombre = controlNombre();
                    apellido = controlApellido();
                    cargo = controlCargo();
                    try {
                        salario = controlSalario();
                        fechaInicio = controlFechaInicio();

                        // Se crea el objeto empleado a partir de los datos introducidos por el usuario
                        empleadoCreado = new Empleado(nombre, apellido, cargo, salario,
                                                    LocalDate.of(fechaInicio.get(0), fechaInicio.get(1), fechaInicio.get(2)));

                        // Se añade el empleado a la ArrayList
                        empleados.add(empleadoCreado);

                        // Alta de los empleados en la BD
                        control.crearEmpleado(empleadoCreado);

                        System.out.println("Empleado creado correctamente.");
                        // Se muestran los datos por pantalla del empleado recién creado
                        System.out.println(empleados.get(empleados.toArray().length - 1));

                    }catch (InputMismatchException ime) {
                        System.out.println("Error: "+ime.getMessage()+". Ha introducido un valor no numérico. Introduzca un valor numérico, por favor.");
                    }

                    System.out.println("\n¿Quiere crear otro empleado? Pulse 1 para sí, 0 para no.");
                    op = scanner.nextInt();
                }while (op == 1);
                break;
            case 2: // Eliminación de empleados
                    try{
                        System.out.println("Escriba la id del empleado que desea eliminar: ");
                        id = scanner.nextInt();
                        // Baja de uno de los empleados según su id
                        control.eliminarEmpleado(id);
                    }catch (InputMismatchException ime) { // Atrapa la excepción de introducir un valor no adecuado
                        System.out.println("Error: "+ime.getMessage()+". Ha introducido un valor no numérico. Introduzca un valor numérico, por favor.");
                    }catch (EntityNotFoundException enfe) { // Atrapa la excepción entidad no encontrada
                        System.out.println("Error: "+enfe.getMessage()+". La entidad con esa id no ha podido ser encontrada.");
                    }
                    break;
            case 3: // Modificación de empleados
                // Se modifica un atributo a seleccionar de un empleado en la BD
                do{
                    System.out.println("¿Qué atributo desea modificar?\n");
                    System.out.println("1. Nombre");
                    System.out.println("2. Apellido");
                    System.out.println("3. Cargo");
                    System.out.println("4. Salario");
                    System.out.println("5. Fecha de inicio");
                    System.out.println("0. Cancelar operación");

                    op = scanner.nextInt();

                    switch (op) {
                        case 1: // Modificar nombre
                            nombre = controlNombre();
                            try {
                                System.out.println("Escriba la id del empleado que desea editar: ");
                                id = scanner.nextInt();
                                // Se trae de la BD el empleado a editar según su id
                                Empleado empleadoEditarNombre = control.buscarEntidad(id);
                                // Se sustituye el atributo correspondiente
                                empleadoEditarNombre.setNombre(nombre);
                                // Se aplica el cambio en la BD
                                control.editarEmpleado(empleadoEditarNombre);
                                System.out.println("\nNombre editado correctamente.");
                            }catch (EntityNotFoundException enfe) { // Atrapa la excepción entidad no encontrada
                                System.out.println("Error: "+enfe.getMessage()+". La entidad con esa id no ha podido ser encontrada.");
                            }catch (InputMismatchException ime) { // Atrapa la excepción de introducir un valor no adecuado
                                System.out.println("Error: "+ime.getMessage()+". Ha introducido un valor no numérico. Introduzca un valor numérico, por favor.");
                            }catch (NullPointerException npe) { // Atrapa la excepción de encontrarse con valores nulos
                                System.out.println("Error: "+npe.getMessage()+". La entidad con esa id no existe en la BD.");
                            }
                            break;
                        case 2: // Modificar apellido
                            apellido = controlApellido();
                            try {
                                System.out.println("Escriba la id del empleado que desea editar: ");
                                id = scanner.nextInt();
                                // Se trae de la BD el empleado a editar según su id
                                Empleado empleadoEditarApellido = control.buscarEntidad(id);
                                // Se sustituye el atributo correspondiente
                                empleadoEditarApellido.setApellido(apellido);
                                // Se aplica el cambio en la BD
                                control.editarEmpleado(empleadoEditarApellido);
                                System.out.println("\nApellido editado correctamente.");
                            }catch (EntityNotFoundException enfe) { // Atrapa la excepción entidad no encontrada
                                System.out.println("Error: "+enfe.getMessage()+". La entidad con esa id no ha podido ser encontrada.");
                            }catch (InputMismatchException ime) { // Atrapa la excepción de introducir un valor no adecuado
                                System.out.println("Error: "+ime.getMessage()+". Ha introducido un valor no numérico. Introduzca un valor numérico, por favor.");
                            }catch (NullPointerException npe) { // Atrapa la excepción de encontrarse con valores nulos
                                System.out.println("Error: "+npe.getMessage()+". La entidad con esa id no existe en la BD.");
                            }
                            break;
                        case 3: // Modificar cargo
                            cargo = controlCargo();
                            try {
                                System.out.println("Escriba la id del empleado que desea editar: ");
                                id = scanner.nextInt();
                                // Se trae de la BD el empleado a editar según su id
                                Empleado empleadoEditarCargo = control.buscarEntidad(id);
                                // Se sustituye el atributo correspondiente
                                empleadoEditarCargo.setCargo(cargo);
                                // Se aplica el cambio en la BD
                                control.editarEmpleado(empleadoEditarCargo);
                                System.out.println("\nCargo editado correctamente.");
                            }catch (EntityNotFoundException enfe) { // Atrapa la excepción entidad no encontrada
                                System.out.println("Error: "+enfe.getMessage()+". La entidad con esa id no ha podido ser encontrada.");
                            }catch (InputMismatchException ime) { // Atrapa la excepción de introducir un valor no adecuado
                                System.out.println("Error: "+ime.getMessage()+". Ha introducido un valor no numérico. Introduzca un valor numérico, por favor.");
                            }catch (NullPointerException npe) { // Atrapa la excepción de encontrarse con valores nulos
                                System.out.println("Error: "+npe.getMessage()+". La entidad con esa id no existe en la BD.");
                            }
                            break;
                        case 4: // Modificar salario
                            try {
                                salario = controlSalario();
                                System.out.println("Escriba la id del empleado que desea editar: ");
                                id = scanner.nextInt();
                                // Se trae de la BD el empleado a editar según su id
                                Empleado empleadoEditarSalario = control.buscarEntidad(id);
                                // Se sustituye el atributo correspondiente
                                empleadoEditarSalario.setSalario(salario);
                                // Se aplica el cambio en la BD
                                control.editarEmpleado(empleadoEditarSalario);
                                System.out.println("\nSalario editado correctamente.");
                            }catch (EntityNotFoundException enfe) { // Atrapa la excepción entidad no encontrada
                                System.out.println("Error: "+enfe.getMessage()+". La entidad con esa id no ha podido ser encontrada.");
                            }catch (InputMismatchException ime) { // Atrapa la excepción de introducir un valor no adecuado
                                System.out.println("Error: "+ime.getMessage()+". Ha introducido un valor no numérico. Introduzca un valor numérico, por favor.");
                            }catch (NullPointerException npe) { // Atrapa la excepción de encontrarse con valores nulos
                                System.out.println("Error: "+npe.getMessage()+". La entidad con esa id no existe en la BD.");
                            }
                            break;
                        case 5: // Modificar fecha inicio
                            try {
                                fechaInicio = controlFechaInicio();
                                System.out.println("Escriba la id del empleado que desea editar: ");
                                id = scanner.nextInt();
                                // Se trae de la BD el empleado a editar según su id
                                Empleado empleadoEditarFechaInicio = control.buscarEntidad(id);
                                // Se sustituye el atributo correspondiente
                                empleadoEditarFechaInicio.setFechaInicio(LocalDate.of(fechaInicio.get(0), fechaInicio.get(1), fechaInicio.get(2)));
                                // Se aplica el cambio en la BD
                                control.editarEmpleado(empleadoEditarFechaInicio);
                                System.out.println("\nFecha de inicio editada correctamente.");
                            }catch (EntityNotFoundException enfe) { // Atrapa la excepción entidad no encontrada
                                System.out.println("Error: "+enfe.getMessage()+". La entidad con esa id no ha podido ser encontrada.");
                            }catch (InputMismatchException ime) { // Atrapa la excepción de introducir un valor no adecuado
                                System.out.println("Error: "+ime.getMessage()+". Ha introducido un valor no numérico. Introduzca un valor numérico, por favor.");
                            }catch (NullPointerException npe) { // Atrapa la excepción de encontrarse con valores nulos
                                System.out.println("Error: "+npe.getMessage()+". La entidad con esa id no existe en la BD.");
                            }
                            break;
                        default:
                            System.out.println("\nOperación cancelada correctamente.");
                    }

                    System.out.println("\n¿Quiere realizar otra edición? Pulse 1 para sí, 0 para no.");
                    op = scanner.nextInt();
                }while (op == 1);
                break;
            case 4: // Listar empleados
                // Listado de los empleados de la BD
                if (!control.listarEmpleados().isEmpty()) {
                    List<Empleado> listaEmpleados = control.listarEmpleados();
                    System.out.println("\n---- Lista de empleados ----\n");
                    for(Empleado empleado : listaEmpleados){
                        System.out.println("id="+empleado.getId()+" "+ empleado);
                    }
                }else{
                    System.out.println("\nLa base de datos de empleados se encuentra vacía.");
                }
                break;
            case 5: // Búsqueda de empleados por cargo
                // Búsqueda de empleados según el cargo seleccionado por el usuario
                do {
                    System.out.println("Escriba el cargo de los empleados que desea buscar: ");
                    cargo = scanner.next();
                    if (cargo.isEmpty()){ // Si el campo se encuentra vacío, se informará al usuario.
                        System.out.println("El cargo no puede estar vacío. Por favor escriba un cargo válido.");
                    }
                    if(cargo.matches(".*\\d.*")){ // Expresión regular para detectar números en un String
                        System.out.println("El cargo no puede contener valores numéricos.");
                    }
                }while (cargo.isEmpty() || cargo.matches(".*\\d.*"));
                // Si la BD contiene empleados con el cargo seleccionado por el usuario los mostrará, si no se mostrará un mensaje por pantalla al usuario de que no existen empleados.
                if (!control.buscarEmpleadoPorCargo(cargo).isEmpty()){
                    System.out.println("\n---- Empleados con el cargo de "+cargo+" ----\n");
                    for (Empleado empleadosCargo : control.buscarEmpleadoPorCargo(cargo)) {
                        System.out.println("id="+empleadosCargo.getId()+" "+ empleadosCargo);
                    }
                }else {
                    System.out.println("\nNo existen empleados con ese cargo.");
                }
                break;
            default:
                System.out.println("\nSesión finalizada.");
        }

        System.out.println("\nFin del programa.");
    }

    // Métodos estáticos para el control de datos y su devolución
    public static String controlNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;

        do{
            System.out.println("Escriba el nombre del empleado: ");
            nombre = scanner.next();
            if (nombre.isEmpty()){ // Si el campo se encuentra vacío, se informará al usuario.
                System.out.println("El nombre no puede estar vacío. Por favor escriba un nombre válido.");
            }
            if(nombre.matches(".*\\d.*")){ // Expresión regular para detectar números en un String
                System.out.println("El nombre no puede contener valores numéricos.");
            }
        }while (nombre.isEmpty() || nombre.matches(".*\\d.*")); // El usuario no podrá continuar hasta que introduzca un valor correcto

        return nombre;
    }

    public static String controlApellido() {
        Scanner scanner = new Scanner(System.in);
        String apellido;

        do{
            System.out.println("Escriba el apellido del empleado: ");
            apellido = scanner.next();
            if (apellido.isEmpty()){ // Si el campo se encuentra vacío, se informará al usuario.
                System.out.println("El apellido no puede estar vacío. Por favor escriba un apellido válido.");
            }
            if(apellido.matches(".*\\d.*")){ // Expresión regular para detectar números en un String
                System.out.println("El apellido no puede contener valores numéricos.");
            }
        }while (apellido.isEmpty() || apellido.matches(".*\\d.*")); // El usuario no podrá continuar hasta que introduzca un valor correcto

        return apellido;
    }

    public static String controlCargo() {
        Scanner scanner = new Scanner(System.in);
        String cargo;

        do{
            System.out.println("Escriba el cargo del empleado: ");
            cargo = scanner.next();
            if (cargo.isEmpty()){ // Si el campo se encuentra vacío, se informará al usuario.
                System.out.println("El cargo no puede estar vacío. Por favor escriba un cargo válido.");
            }
            if(cargo.matches(".*\\d.*")){ // Expresión regular para detectar números en un String
                System.out.println("El cargo no puede contener valores numéricos.");
            }
        }while (cargo.isEmpty() || cargo.matches(".*\\d.*")); // El usuario no podrá continuar hasta que introduzca un valor correcto

        return cargo;
    }

    public static double controlSalario() {
        Scanner scanner = new Scanner(System.in);
        double salario;

        do {
            System.out.println("Escriba el salario del empleado: ");
            salario = scanner.nextDouble();
            if (salario < 0) { // Si el salario es negativo, se informará al usuario.
                System.out.println("El salario no puede ser negativo. Escriba un valor correcto, por favor.");
            }
        }while (salario < 0); // El usuario no podrá continuar hasta que introduzca un valor correcto

        return salario;
    }

    public static List<Integer> controlFechaInicio() {
        // Creación de un ArrayList para devolver los datos de la fecha
        List<Integer> fecha = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int anyo;
        int dia;
        int mes;

        do {
            System.out.println("Escriba el año de la fecha de inicio del empleado: ");
            anyo = scanner.nextInt();
            if (anyo < 0) { // Si el valor de año es incorrecto, se informará al usuario
                System.out.println("El valor de año no puede ser negativo. Escriba un valor correcto, por favor.");
            }
            if (anyo > 9999) { // Si el valor de año es incorrecto, se informará al usuario
                System.out.println("El valor de año no puede ser mayor de 9999. Escriba un valor correcto, por favor.");
            }
        }while (anyo < 0 || anyo > 9999); // El usuario no podrá continuar hasta que introduzca un valor correcto

        fecha.add(anyo); // Se añade el año al ArrayList

        do {
            System.out.println("Escriba el mes de la fecha de inicio del empleado: ");
            mes = scanner.nextInt();
            if (mes < 1 || mes > 12) { // Si el valor de mes es incorrecto, se informará al usuario
                System.out.println("El valor de mes es incorrecto. Escriba un valor correcto, por favor.");
            }
        }while (mes < 1 || mes > 12); // El usuario no podrá continuar hasta que introduzca un valor correcto

        fecha.add(mes); // Se añade el mes al ArrayList

        do{
            System.out.println("Escriba el día de la fecha de inicio del empleado: ");
            dia = scanner.nextInt();
            if (dia < 1 || dia > 31) { // Si el valor de día es incorrecto, se informará al usuario
                System.out.println("El valor de día es incorrecto. Escriba un valor correcto, por favor.");
            }
        }while (dia < 1 || dia > 31); // El usuario no podrá continuar hasta que introduzca un valor correcto

        fecha.add(dia); // Se añade el día al ArrayList

        return fecha; // Se retorna el ArrayList con los datos de la fecha
    }
}