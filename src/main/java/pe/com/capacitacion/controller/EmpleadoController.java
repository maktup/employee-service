package pe.com.capacitacion.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.dto.ResponseEmplMsg; 
import pe.com.capacitacion.service.EmpleadoService;

/**
 * EmpleadoController
 * @author cguerra
 **/
 @Slf4j      //Autogenerar LOG4J. 
 @RestController
 @RequestMapping( "/employeeservice" ) //NO USAR: [server.servlet.context-path], 'BOOT-ADMIN' reconocera el 'ACTUATOR'.
 @Api( value="EmpleadoController", description="'CONTRATO/API' para la gestion de 'EMPLEADOS'." )
 public class EmpleadoController{
	 
		@Autowired
		private EmpleadoService objEmpleadoService; 
	 
        @Autowired
        private DiscoveryClient discoveryClient;
  
 
		@GetMapping( "/get/servidores" )
        public List<String> listaServidores(){
        	   log.info( "-----> Empleado 'listaServidores'" );
        	
        	   try{
        		   log.info( "-----> employee-service: "     + this.discoveryClient.getInstances( "employee-service"     ).get( 0 ).getUri() );
        		   log.info( "-----> department-service: "   + this.discoveryClient.getInstances( "department-service"   ).get( 0 ).getUri() );
        		   log.info( "-----> organization-service: " + this.discoveryClient.getInstances( "organization-service" ).get( 0 ).getUri() );
        		   log.info( "-----> utl-capadb-service: "   + this.discoveryClient.getInstances( "utl-capadb-service"   ).get( 0 ).getUri() );
        		   
        		   ServiceInstance objServiceInstance =  this.discoveryClient.getInstances( "utl-capadb-service" ).get( 0 );
        		   log.info( "-----> getUri: " + objServiceInstance.getUri() );
        		   log.info( "-----> getHost: " + objServiceInstance.getHost()  );
        		   log.info( "-----> getPort: " + objServiceInstance.getPort()  );
        		   log.info( "-----> getServiceId: " + objServiceInstance.getServiceId()  ); 
        		   
        		   //https://{service-name}.{namespace}.svc.{cluster}.local:{service-port}.
        		   String x = "employee-service.default.svc.minikube.local:";
        	   }
        	   catch( Exception e ) {
        		      e.printStackTrace();
        	   } 
        	
               return discoveryClient.getServices();
        }
		
		
	   /**
	    * agregarEmpleado	
	    * @param  empleado
	    * @return ResponseEntity<ResponseEmplMsg>
	    **/
		@PostMapping( "/post/empleados" )
	    @ApiOperation( value="Funcionalidad para [CREAR] una ENTIDAD de tipo Empleado.", nickname="agregarEmpleado", notes="Funcionalidad para [CREAR] una ENTIDAD de tipo Empleado." )
		public ResponseEntity<ResponseEmplMsg> agregarEmpleado( @RequestBody Empleado empleado ){ 
			   log.info( "Empleado 'agregarEmpleado': {}", empleado );
			   ResponseEntity<ResponseEmplMsg> objResponseMsg = this.objEmpleadoService.agregarEmpleadoService( empleado ); 
			   return objResponseMsg; 
		}
		
	   /**
	    * eliminarEmpleado	
	    * @param  id
	    * @return ResponseEntity<ResponseEmplMsg>
	    **/
		@DeleteMapping( "/delete/empleados/{id}" ) 
	    @ApiOperation( value="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Empleado por ID.", nickname="eliminarEmpleado", notes="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Empleado por ID." )
		public ResponseEntity<ResponseEmplMsg> eliminarEmpleado( @PathVariable( "id" ) Long id ){
			   log.info( "Empleado 'eliminarEmpleado': {}", id );
			   ResponseEntity<ResponseEmplMsg> objResponseMsg = this.objEmpleadoService.eliminarEmpleadoService( id ); 
			   return objResponseMsg; 
		} 
		
	   /**
	    * consultarEmpleadosAll	  
	    * @return ResponseEntity<ResponseEmplMsg>
	    **/
		@GetMapping( "/get/empleados" )
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado completa.", nickname="consultarEmpleadosAll", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado completa." )
		public ResponseEntity<ResponseEmplMsg> consultarEmpleadosAll(){
			   log.info( "Empleado 'consultarEmpleadosAll'" );
			   ResponseEntity<ResponseEmplMsg> objResponseMsg = this.objEmpleadoService.consultarEmpleadosAllService();
			   return objResponseMsg; 
		}
		
	   /**
	    * consultarEmpleadosPorId	
	    * @param  id
	    * @return ResponseEntity<ResponseEmplMsg>
	    **/
		@GetMapping( "/get/empleados/{id}" )
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por ID.", nickname="consultarEmpleadosPorId", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por ID." )
		public ResponseEntity<ResponseEmplMsg> consultarEmpleadosPorId( @PathVariable( "id" ) Long id ){
			   log.info( "Empleado 'consultarEmpleadosPorId': id={}", id );
			   ResponseEntity<ResponseEmplMsg> objResponseMsg = this.objEmpleadoService.consultarEmpleadosPorIdService( id ); 
			   return objResponseMsg; 
		}
		 
	   /**
	    * consultarEmpleadosPorDepartamento	
	    * @param  idDep
	    * @return ResponseEntity<ResponseEmplMsg> 
	    **/
		@GetMapping( "/get/empleados-departamento/{idDep}" )
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por IDDEP.", nickname="consultarEmpleadosPorDepartamento", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por IDDEP." ) 
		public ResponseEntity<ResponseEmplMsg> consultarEmpleadosPorDepartamento( @PathVariable( "idDep" ) Long idDep ){
			   log.info( "Empleado 'consultarEmpleadosPorDepartamento': idDep={}", idDep );
			   ResponseEntity<ResponseEmplMsg> objResponseMsg = this.objEmpleadoService.consultarEmpleadosPorDepartamentoService( idDep );
			   return objResponseMsg; 
		}
 
 }

 