

IMPORTANTE:
----------
- CONSIDERAR que los DNS, deben estar registrados en el archivo HOST del S.O. 
- El 'ARCHIVO DE CONFIGURACION' para el MICROSERVICIO: 'employee-service', se manejara configurara dentro del SCRIPT: [3_employee-service_[Deployment].yml] 
- Dentro del archivo: 'DOCKERFILE' se estan manejando 'VARIABLES DE ENTORNO' para algunas configuraciones.   

Los LINK [GET] son:

1. consultarEmpleadosAll: [capacitacion.microservicios.employee/employeeservice/get/empleados]
   http://capacitacion.microservicios.employee/employeeservice/get/empleados

2. consultarEmpleadosPorId: [capacitacion.microservicios.employee/employeeservice/get/empleados/{id}]  
   http://capacitacion.microservicios.employee/employeeservice/get/empleados/1
                                                                  
3. consultarEmpleadosPorDepartamento: [capacitacion.microservicios.employee/employeeservice/get/departamentos/{departmentId}/empleados]   
   http://capacitacion.microservicios.employee/employeeservice/get/departamentos/1/empleados
 
 
DETALLE:
------- 
- Para DETALLES del MICROSERVICIO, apoyarse en ACTUATOR ingresando a: 'http://capacitacion.microservicios.employee/actuator'

- Para acceder a 'PHOMETHEUS' acceder por medio de ACTUATOR asi: 'http://capacitacion.microservicios.employee/actuator/prometheus'  

