

IMPORTANTE:
----------
* CONSIDERAR que los DNS, deben estar registrados en el archivo HOST del S.O (Las IPs manejadas deben de ser FIJAS). 
* La 'ARCHIVO DE CONFIGURACION' para el MICROSERVICIO: 'employee-service', se manejara por medio de los SCRIPTs:

  - 1_employee-service_[ConfigMap-Secret].yml
  - 2_employee-service_[Deployment-Service].yml
  
* Dentro del SCRIPT: 'DOCKERFILE' se estan manejando tambien 'VARIABLES DE ENTORNO' para algunos requerimientos en el MICROSERVICIO.   

(HOLA ESTA ES UNA PRUEA)

SWAGGER:
--------
CREAR EL 'CONTRADO/API' ONLINE USANDO:
http://editor.swagger.io/

UNA VEZ DESPLEGADO EL 'MICROSERVICIO' ACCEDER A:  
http://capacitacion.microservicios.employee/swagger-ui.html 


Los LINKs del 'MICROSERVICIO' son:
---------------------------------

  1. Las 'URI' de tipo [GET] son:
     ---------------------------
  
     - consultarEmpleadosAll: 
	   http://capacitacion.microservicios.employee/employeeservice/get/empleados
	
     - consultarEmpleadosPorId:   
	   http://capacitacion.microservicios.employee/employeeservice/get/empleados/1
	                                                                  
     - consultarEmpleadosPorDepartamento:   
	   http://capacitacion.microservicios.employee/employeeservice/get/empleados-departamento/1
 
 
  2. Las 'URI' de tipo [POST] son:
     ----------------------------
     
     - agregarEmpleado:   
	   http://capacitacion.microservicios.employee/employeeservice/post/empleados
 
	   {   
		"nombre": "PAOLO GUERRERO", 
		 "edad":   35, 
		"rol":    "CONSULTOR",
		  
		"idDep":  "1" 
	   }
 
 
  3. Las 'URI' de tipo [DELETE] son:
     ------------------------------
 
     - eliminarEmpleado:   
	   http://capacitacion.microservicios.employee/employeeservice/delete/empleados/1
	   
 
DETALLE:
------- 
* Para INFORMACIÓN interna del MICROSERVICIO, apoyarse en ACTUATOR ingresando a: 'http://capacitacion.microservicios.employee/actuator'
* Para acceder a 'PHOMETHEUS' acceder por medio de ACTUATOR asi: 'http://capacitacion.microservicios.employee/actuator/prometheus'

