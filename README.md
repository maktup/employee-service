

IMPORTANTE:
----------
El 'ARCHIVO DE CONFIGURACION' para el MICROSERVICIO: 'employee-service', se manejara en la ruta del 'GITHUB': '/employee-service.properties' 

Los LINK [GET] son:

1. consultarEmpleadosAll: [http://localhost:8092/employeeservice/get/empleados]
   http://localhost:8092/employeeservice/get/empleados

2. consultarEmpleadosPorId: [http://localhost:8092/employeeservice/get/empleados/{id}]  
   http://localhost:8092/employeeservice/get/empleados/1
                                                                  
3. consultarEmpleadosPorDepartamento: [http://localhost:8092/employeeservice/get/departamentos/{departmentId}/empleados]   
   http://localhost:8092/employeeservice/get/departamentos/1/empleados
 
 
DETALLE:
-------

1. FEIGN:        Permite consumir WebService REST, apuntando directamente al 'NOMBRE' del WebService & 'URI' respectivamente, por medio de una Interface.
2. EUREKACLIENT: Permite consumir WebService REST, apuntando directamente al 'ID' de EUREKA del WebService registrado en el.


Para DETALLES del MICROSERVICIO, apoyarse en ACTUATOR ingresando a: 'http://localhost:8092/actuator'

