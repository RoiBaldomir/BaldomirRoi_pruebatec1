# Prueba técnica 1 - Aplicación de gestión de empleados
## Descripción
Esta aplicación permite realizar varias operaciones de gestión de registros de empleados:
- Crear empleados.
- Eliminar empleados.
- Actualizar la información de los empleados.
- Listar empleados.
- Buscar empleados por cargo.
## Cómo utilizar la aplicación
La aplicación cuenta con un menú interactivo con las siguientes opciones:
1. Crear empleado
   
   - Situada en la opción 1 del menú.
   - Siguiendo las instrucciones se introducen los datos del empleado.
   - Se crea una nueva entidad en la base de datos con los datos introducidos.
   - Se solicita al usuario si desea crear otro empleado o salir de la aplicación.
   - Cualquier error en la inserción de datos se notifica al usuario.
2. Eliminar empleado

   - Situada en la opción 1 del menú.
   - Se introduce la id del empleado a eliminar.
   - Se elimina la entidad con la id seleccionada por el usuario.
   - Si el id es incorrecto o no existe en la base de datos se informa al usuario.
3. Modificar empleado
   
   - Situada en la opción 3 del menú.
   - Se despliega un menú con el atributo a modificar.
   - Se solicita al usuario el valor del atributo a modificar.
   - Se solicita al usuario la id del empleado que desee modificar.
   - Se modifica la entidad en la base de datos con el valor correspondiente.
   - Cualquier error en la inserción de datos se notifica al usuario.
4. Ver listado de empleados
   
   - Situada en la opción 4 del menú.
   - Muestra la lista de empleados registrados en la base de datos.
5. Búsqueda de empleados por cargo

   - Situada en la opción 5 del menú.
   - Se introduce el cargo de los empleados a mostrar.
   - Muestra la lista de empleados según el cargo seleccionado por el usuario.
6. Salir de la aplicación

   - Situada en la opción 0 del menú.
## Supuestos a considerar
   - Añadir una interfaz de usuario, ya que solo es posible utilizar la aplicación desde la línea de comandos.
   - Añadir más búsquedas de empleados por otros atributos(nombre, apellido...) y filtros(salario mayor de x, fecha anterior a xxxx....).
