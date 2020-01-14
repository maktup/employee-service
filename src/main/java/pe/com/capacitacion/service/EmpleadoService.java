package pe.com.capacitacion.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.springframework.web.bind.annotation.RequestBody;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import pe.com.capacitacion.bean.Auditoria;
import pe.com.capacitacion.bean.ConfigurationData_01;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.bean.ResponseMsg;
import pe.com.capacitacion.exception.AuditoriaException;
import pe.com.capacitacion.repository.EmpleadoRepository;
import pe.com.capacitacion.util.Constantes;

/**
 * EmpleadoService
 * @author cguerra
 **/
 @Service
 public class EmpleadoService extends AuditoriaException{
		
		private static final Logger LOGGER = LoggerFactory.getLogger( EmpleadoService.class );
	 
		@Autowired
		private EmpleadoRepository objRepositorio;  
		
		@Autowired
		private Constantes constantes; 
		
		@Autowired
		private ConfigurationData_01 objConfigurationData01;  //ACCESO: inicia con [grupoconfig01]  		 
  
		@Autowired
		private AuditoriaException objAuditoriaException; 
		
	   /**	
	    * agregarEmpleadoService	
	    * @param  empleado
	    * @return ResponseMsg
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseMsg agregarEmpleadoService( @RequestBody Empleado empleado ){
			   LOGGER.info( "-----> Empleado 'agregarEmpleadoService': {}", empleado );
			   
			   ResponseMsg objResponseMsg = new ResponseMsg(); 
			   
			   String vNombreServicio = this.constantes.nombreServicio; 
			   String vValor_01       = this.constantes.valor01; 
			   String vNombres        = this.objConfigurationData01.getNombres();
			   String vDni            = this.objConfigurationData01.getDni(); 		
			   LOGGER.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" ); 
			    
			   this.objRepositorio.agregarEmpleado( empleado ); 
			   Auditoria objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK ); 
  
			   //Agregando: 
			   objResponseMsg.setListaEmpleados( new ArrayList<Empleado>() );
			   objResponseMsg.setAuditoria( objAuditoria );
			   
			   return objResponseMsg; 
		}
					
	   /**
		* consultarEmpleadosAllService	
		* @return ResponseMsg
		**/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseMsg consultarEmpleadosAllService(){ 
			   LOGGER.info( "-----> Empleado 'consultarEmpleadosAllService'" );
			   
			   ResponseMsg objResponseMsg = new ResponseMsg(); 
			   
			   String vNombreServicio = this.constantes.nombreServicio; 
			   String vValor_01       = this.constantes.valor01; 
			   String vNombres        = this.objConfigurationData01.getNombres();
			   String vDni            = this.objConfigurationData01.getDni(); 		
			   LOGGER.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" ); 
			  
			   List<Empleado> listaEmpleados = this.objRepositorio.consultarEmpleadosAll();
			   Auditoria      objAuditoria   = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK ); 
			   
			   //Agregando: 
			   objResponseMsg.setListaEmpleados( listaEmpleados );
			   objResponseMsg.setAuditoria( objAuditoria );
			   
			   return objResponseMsg; 
		}
 	
		/**
		 * consultarEmpleadosPorIdService	
		 * @param  id
		 * @return ResponseMsg
		 **/  
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseMsg consultarEmpleadosPorIdService( Long id ){ 
			   LOGGER.info( "-----> Departamento 'consultarEmpleadosPorIdService': id={}", id );
			   
		       ResponseMsg objResponseMsg = new ResponseMsg(); 
			   
			   String vNombreServicio = this.constantes.nombreServicio; 
			   String vValor_01       = this.constantes.valor01; 
			   String vNombres        = this.objConfigurationData01.getNombres();
			   String vDni            = this.objConfigurationData01.getDni(); 		
			   LOGGER.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" ); 
			
			   Empleado   objEmpleado  = this.objRepositorio.consultarEmpleadosPorId( id );   
		       Auditoria  objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK );
						
			   List<Empleado> listaEmpleado = new ArrayList<Empleado>();
			   listaEmpleado.add( objEmpleado );
			
			   //Agregando: 
		       objResponseMsg.setListaEmpleados( listaEmpleado ); 
		       objResponseMsg.setAuditoria( objAuditoria );
		   
		       return objResponseMsg; 
		}	
		
	   /**
	    * consultarEmpleadosPorDepartamentoService	
	    * @param  organizationId
	    * @return ResponseMsg
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseMsg consultarEmpleadosPorDepartamentoService( Long departmentId ){
			   LOGGER.info( "-----> Departamento 'consultarEmpleadosPorDepartamentoService': departmentId={}", departmentId );
			   
		       ResponseMsg objResponseMsg = new ResponseMsg(); 
		    
			   String vNombreServicio = this.constantes.nombreServicio; 
			   String vValor_01       = this.constantes.valor01; 
			   String vNombres        = this.objConfigurationData01.getNombres();
			   String vDni            = this.objConfigurationData01.getDni(); 		
			   LOGGER.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" ); 
			   
			   List<Empleado> listaEmpleados = this.objRepositorio.consultarEmpleadosPorDepartamento( departmentId );
			   Auditoria      objAuditoria   = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK );
				
			   //Agregando: 
		       objResponseMsg.setListaEmpleados( listaEmpleados ); 
		       objResponseMsg.setAuditoria( objAuditoria );
		   
		       return objResponseMsg; 
		}	
 
 }
 
