package pe.com.capacitacion.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import io.opentracing.Scope;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import io.opentracing.Scope;
import io.opentracing.Tracer;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.bean.ResponseMsg;
import pe.com.capacitacion.service.EmpleadoService;

/**
 * EmpleadoController
 * @author cguerra
 **/
 @RestController
 @RequestMapping( "/employeeservice" )
 public class EmpleadoController{
	
		private static final Logger LOGGER = LoggerFactory.getLogger( EmpleadoController.class );
		
		@Autowired
		private EmpleadoService objEmpleadoService; 
		 
	    @Autowired
	    private Tracer tracer;
		
		
		@PostMapping( "/post/empleados" )
		public ResponseMsg agregarEmpleado( @RequestBody Empleado empleado ){ 
			   LOGGER.info( "Empleado 'agregarEmpleado': {}", empleado );
			   
			   //Ejecutar:  
			   ResponseMsg objResponseMsg = this.objEmpleadoService.agregarEmpleadoService( empleado ); 
			   return objResponseMsg; 
		}
		
		@GetMapping( "/get/empleados" )
		public ResponseMsg consultarEmpleadosAll(){
			   LOGGER.info( "Empleado 'consultarEmpleadosAll'" );
			   
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = this.objEmpleadoService.consultarEmpleadosAllService(); 
			   return objResponseMsg; 
		}
		
		@GetMapping( "/get/empleados/{id}" )
		public ResponseMsg consultarEmpleadosPorId( @PathVariable( "id" ) Long id ){
			   LOGGER.info( "Empleado 'consultarEmpleadosPorId': id={}", id );
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = this.objEmpleadoService.consultarEmpleadosPorIdService( id );
			   return objResponseMsg; 
		}
		 
		@GetMapping( "/get/departamentos/{departmentId}/empleados" )
		public ResponseMsg consultarEmpleadosPorDepartamento( @PathVariable( "departmentId" ) Long departmentId ){
			   LOGGER.info( "Empleado 'consultarEmpleadosPorDepartamento': departmentId={}", departmentId );
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = this.objEmpleadoService.consultarEmpleadosPorDepartamentoService( departmentId );
			   return objResponseMsg; 
		}
 
		//-----------------------------------------------------------------------------------------------------// 
		
		 
	    /**
	     * @return "heads" or "tails" to emulate a coin flip
	     */
	    @RequestMapping("/flip")
	    public String flipACoin() throws Exception {
	    	LOGGER.info( "=====================>: flipACoin" );
	    	
			// Emulate the coin flip
			String flipResult = trueWithProbability(.50) ? "heads" : "tails";
	
			// Tag the current Span with the result
			tracer.activeSpan().setTag( "flipResult", flipResult );
	
			return flipResult;
	    }

	    /**
	     * Returns false based on the passed in probability. 
	     * @param probability - Expressed as a number between 0 and 1
	     * @return
	     */
	    private boolean trueWithProbability(double probability) {
	    	LOGGER.info( "=====================>: trueWithProbability" );
	    	
			// Create a new subspan called 'calculateOdds' that surrounds this logic 
			try (Scope scope = tracer.buildSpan("calculateOdds").startActive(true)) {
			    return Math.random() <= probability;
			} // By using the Java try-with-resources convention, the subspan is auto-closed
	    } 
		
 }

 