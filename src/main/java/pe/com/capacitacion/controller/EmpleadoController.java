package pe.com.capacitacion.controller;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.bean.ResponseMsg;
import pe.com.capacitacion.service.EmpleadoService;
import pe.com.capacitacion.util.UtilJeager;

/**
 * EmpleadoController
 * @author cguerra
 **/
 @RestController
 @RequestMapping( "/employeeservice" )
 public class EmpleadoController extends UtilJeager{
	
		private static final Logger LOGGER = LoggerFactory.getLogger( EmpleadoController.class );
		
		@Autowired
		private EmpleadoService objEmpleadoService; 
 
	   /**
	    * agregarEmpleado	
	    * @param  empleado
	    * @return ResponseMsg
	    **/
		@PostMapping( "/post/empleados" )
		public ResponseMsg agregarEmpleado( @RequestBody Empleado empleado ){ 
			   LOGGER.info( "Empleado 'agregarEmpleado': {}", empleado );
			   this.jaegerAlertTracer(); 
			   
			   //Ejecutar:  
			   ResponseMsg objResponseMsg = this.objEmpleadoService.agregarEmpleadoService( empleado ); 
			   return objResponseMsg; 
		}
		
	   /**
	    * consultarEmpleadosAll	
	    * @return ResponseMsg
	    **/
		@GetMapping( "/get/empleados" )
		public ResponseMsg consultarEmpleadosAll(){
			   LOGGER.info( "Empleado 'consultarEmpleadosAll'" );
			   this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = this.objEmpleadoService.consultarEmpleadosAllService(); 
			   return objResponseMsg; 
		}
		
	   /**
	    * consultarEmpleadosPorId	
	    * @param  id
	    * @return ResponseMsg
	    **/
		@GetMapping( "/get/empleados/{id}" )
		public ResponseMsg consultarEmpleadosPorId( @PathVariable( "id" ) Long id ){
			   LOGGER.info( "Empleado 'consultarEmpleadosPorId': id={}", id );
			   this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = this.objEmpleadoService.consultarEmpleadosPorIdService( id );
			   return objResponseMsg; 
		}
		
	   /**
	    * consultarEmpleadosPorDepartamento	
	    * @param  departmentId
	    * @return ResponseMsg
	    **/
		@GetMapping( "/get/departamentos/{departmentId}/empleados" )
		public ResponseMsg consultarEmpleadosPorDepartamento( @PathVariable( "departmentId" ) Long departmentId ){
			   LOGGER.info( "Empleado 'consultarEmpleadosPorDepartamento': departmentId={}", departmentId );
			   this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = this.objEmpleadoService.consultarEmpleadosPorDepartamentoService( departmentId );
			   return objResponseMsg; 
		}
 
 }

 