package pe.com.capacitacion.service;
 
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.bean.Auditoria;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import org.springframework.web.client.RestTemplate; 
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; 
import brave.Tracer;
import brave.propagation.TraceContextOrSamplingFlags;
import brave.propagation.TraceIdContext;
import pe.com.capacitacion.dto.ResponseEmplMsg;
import pe.com.capacitacion.exception.AuditoriaException;
import pe.com.capacitacion.properties.ConfigurationData_01;
import pe.com.capacitacion.util.Constantes;
import zipkin2.Span;

/**
 * EmpleadoService
 * @author cguerra
 **/
 @Slf4j      //Autogenerar LOG4J. 
 @Service
 public class EmpleadoService extends AuditoriaException{
	 
		@Autowired
		private Constantes constantes; 
 
		@Autowired
		private RestTemplateBuilder objTemplate; 
		
        @Autowired
        private ConfigurationData_01 objConfigurationData01;   //ACCESO: inicia con [grupoconfig01]  
 
        @Autowired
        private DiscoveryClient discoveryClient;
        
        @Autowired
    	private Environment objVariablesEntorno;
 
        
        @Autowired
        private Tracer tracer;
        
 
		public String saludar() throws InterruptedException{ 
			log.info("saludar2");
			
			brave.Span span = this.tracer.nextSpan( TraceContextOrSamplingFlags.newBuilder().traceIdContext( TraceIdContext.newBuilder().traceId(123L).build() ).build() ).name( "employee-service" ).start();
			log.info( "==================>:" + span);
			
		    //Obtener el HOST del POD donde está ubicado el 'MICROSERVICIO'. 
		    ServiceInstance objServiceInstance = this.discoveryClient.getInstances( Constantes.INSTANCIA_KUBERNETES_02 ).get( 0 );
		    String vHostKubernetes = objServiceInstance.getUri() + ""; 
		    
			RestTemplate objRspTmp = this.objTemplate.build();  
			String s = objRspTmp.getForObject( vHostKubernetes + "/" + Constantes.SERVICE_NAME_02 + "/hi", String.class );
			return "hi/" + s;
		}

 
		public String hola() throws InterruptedException {
			   log.info("hi"); 
			   //this.tracer.addTag("random-sleep-millis", String.valueOf(millis) );
			   return "hello";
		} 
        
        
 	   /**	
 	    * agregarEmpleadoService	
 	    * @param  empleado
 	    * @return ResponseEntity<ResponseEmplMsg>
 	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseEmplMsg> agregarEmpleadoService( Empleado empleado ){
			   log.info( "-----> Empleado 'agregarEmpleadoService': {}", empleado );
				 
			   Gson         objGson   = new Gson();
			   String       vURI      = "/empleados";
			   RestTemplate objRspTmp = this.objTemplate.build(); 
			   
			   //Variables de Entorno: 
			   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
			  			   
			   //Obtener el HOST del POD donde está ubicado el 'MICROSERVICIO'. 
			   ServiceInstance objServiceInstance = this.discoveryClient.getInstances( Constantes.INSTANCIA_KUBERNETES_04 ).get( 0 );
			    
			   String vHostKubernetes = objServiceInstance.getUri() + ""; 
			   log.info( "-----> vHostKubernetes: [" + objServiceInstance.getUri() + "]" );
 
			   //Armando URI: 
			   String vURL = (vHostKubernetes + "/" + Constantes.SERVICE_NAME_04 + "/" + Constantes.HTTP_METHOD_02 + vURI); 
			   log.info( "========>: vURL [" + vURL + "]" ); 
			   
			   //Transformar de OBJETO a JSON:                                         
			   String vParamRequestJSON = objGson.toJson( empleado );
			   log.info( "========>: vParamRequestJSON: " + vParamRequestJSON ); 
			   	       
			   //Definiendo Entity: 
			   HttpHeaders objHeader = new HttpHeaders(); 
			   objHeader.setContentType( MediaType.APPLICATION_JSON );		 
			   HttpEntity<Object> objEntityRequest = new HttpEntity<Object>( empleado, objHeader ); 
			   
			   //Enviar mensaje POST: 
			   ResponseEntity<String> vCadenaJSON_01 = objRspTmp.postForEntity( vURL, objEntityRequest, String.class );
			   log.info( "========>: vCadenaJSON_01 [" + vCadenaJSON_01.getBody() + "]" );
			   
			   //Transformar de JSON a OBJETO:    		
			   pe.com.capacitacion.dto.ResponseEmplMsg objResponseMsg = objGson.fromJson( vCadenaJSON_01.getBody(), pe.com.capacitacion.dto.ResponseEmplMsg.class );
			   log.info( "========>: objResponseMsg: " + objResponseMsg ); 

			   //Objeto Return: 
			   ResponseEntity<ResponseEmplMsg> objRetorno = new ResponseEntity<ResponseEmplMsg>( objResponseMsg, HttpStatus.OK ); 
			   return objRetorno;
		}
		
		/**	
		* eliminarEmpleadoService	
		* @param  id
		* @return ResponseEmplMsg
		**/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseEmplMsg> eliminarEmpleadoService( Long id ){
			   log.info( "-----> Empleado 'eliminarEmpleadoService': {}", id );
		
			   String       vURI      = "/empleados/";
			   RestTemplate objRspTmp = this.objTemplate.build(); 
			   
			   //Variables de Entorno: 
			   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
			  			   
			   //Obtener el HOST del POD donde está ubicado el 'MICROSERVICIO'. 
			   ServiceInstance objServiceInstance = this.discoveryClient.getInstances( Constantes.INSTANCIA_KUBERNETES_04 ).get( 0 );
			   
			   String vHostKubernetes = objServiceInstance.getUri() + ""; 
			   log.info( "-----> vHostKubernetes: [" + objServiceInstance.getUri() + "]" );
	 
			   //Armando URI: 
			   String vURL = (vHostKubernetes + "/" + Constantes.SERVICE_NAME_04 + "/" + Constantes.HTTP_METHOD_03 + vURI + id); 
			   log.info( "========>: vURL [" + vURL + "]" ); 
			   
			   //Enviar mensaje DELETE: 
			   objRspTmp.delete( vURL );  //Es VOID. 
		       
			   //Armando estructura RESPONSE: 
			   Auditoria       objAuditoria   = super.cargarDatosAuditoria( Constantes.IP_APP_NOK, Constantes.MSJ_APP_OK, Constantes.USUARIO_APP_NOK, Constantes.MSJ_APP_OK ); 
			   ResponseEmplMsg objResponseMsg = new ResponseEmplMsg();
			   objResponseMsg.setAuditoria( objAuditoria );
			   
			   //Objeto Return: 
			   ResponseEntity<ResponseEmplMsg> objRetorno = new ResponseEntity<ResponseEmplMsg>( objResponseMsg, HttpStatus.OK ); 
			   return objRetorno;
		}
		
	   /**
		* consultarEmpleadosAllService	
		* @return ResponseEmplMsg
		**/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseEntity<ResponseEmplMsg> consultarEmpleadosAllService(){ 
			   log.info( "-----> Empleado 'consultarEmpleadosAllService'" );
  
			   Gson         objGson   = new Gson();
			   String       vURI      = "/empleados"; 
			   RestTemplate objRspTmp = this.objTemplate.build(); 
			   
			   //Variables de Entorno: 
			   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
			  			   
			   //Obtener el HOST del POD donde está ubicado el 'MICROSERVICIO'. 
			   ServiceInstance objServiceInstance = this.discoveryClient.getInstances( Constantes.INSTANCIA_KUBERNETES_04 ).get( 0 ); 
			   
			   String vHostKubernetes = objServiceInstance.getUri() + ""; 
			   log.info( "-----> vHostKubernetes: [" + objServiceInstance.getUri() + "]" );
 
			   //Armando URI: 
			   String vURL = (vHostKubernetes + "/" + Constantes.SERVICE_NAME_04 + "/" + Constantes.HTTP_METHOD_01 + vURI); 
			   log.info( "========>: vURL [" + vURL + "]" );
			    
			   //Enviar mensaje GET: 
			   String vCadenaJSON_01 = objRspTmp.getForObject( vURL, String.class );
			   log.info( "========>: vCadenaJSON_01 [" + vCadenaJSON_01 + "]" ); 
			   
			   //Transformar de JSON a OBJETO:   
			   pe.com.capacitacion.dto.ResponseEmplMsg objResponseMsg = objGson.fromJson( vCadenaJSON_01, pe.com.capacitacion.dto.ResponseEmplMsg.class );
			   log.info( "========>: objResponseMsg: " + objResponseMsg ); 
 
			   //Objeto Return: 
			   ResponseEntity<ResponseEmplMsg> objRetorno = new ResponseEntity<ResponseEmplMsg>( objResponseMsg, HttpStatus.OK ); 
			   return objRetorno;
		}
	 	 	 	
	   /**
		* consultarEmpleadosPorIdService	
		* @param  id
		* @return ResponseEmplMsg
		**/  
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseEmplMsg> consultarEmpleadosPorIdService( Long id ){ 
			   log.info( "-----> Empleado 'consultarEmpleadosPorIdService': id={}", id ); 
				 
			   Gson         objGson   = new Gson();
			   String       vURI      = "/empleados/";
			   RestTemplate objRspTmp = this.objTemplate.build();  
			   
			   //Variables de Entorno: 
			   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
			  			   
			   //Obtener el HOST del POD donde está ubicado el 'MICROSERVICIO'. 
			   ServiceInstance objServiceInstance = this.discoveryClient.getInstances( Constantes.INSTANCIA_KUBERNETES_04 ).get( 0 );
			   
			   String vHostKubernetes = objServiceInstance.getUri() + ""; 
			   log.info( "-----> vHostKubernetes: [" + objServiceInstance.getUri() + "]" );
			  	 
			   //Armando URI: 
			   String vURL = (vHostKubernetes + "/" + Constantes.SERVICE_NAME_04 + "/" + Constantes.HTTP_METHOD_01 + vURI + id); 
			   log.info( "========>: vURL [" + vURL + "]" );
			   
			   //Enviar mensaje GET: 
			   String vCadenaJSON_01 = objRspTmp.getForObject( vURL, String.class );
			   log.info( "========>: vCadenaJSON_01 [" + vCadenaJSON_01 + "]" ); 
			   
			   //Transformar de JSON a OBJETO:   
			   pe.com.capacitacion.dto.ResponseEmplMsg objResponseMsg = objGson.fromJson( vCadenaJSON_01, pe.com.capacitacion.dto.ResponseEmplMsg.class );
			   log.info( "========>: objResponseMsg: " + objResponseMsg ); 
 
			   //Objeto Return: 
			   ResponseEntity<ResponseEmplMsg> objRetorno = new ResponseEntity<ResponseEmplMsg>( objResponseMsg, HttpStatus.OK ); 
			   return objRetorno; 
		}	
		
	   /**
	    * consultarEmpleadosPorDepartamentoService	
	    * @param  idDep
	    * @return ResponseEmplMsg
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseEntity<ResponseEmplMsg> consultarEmpleadosPorDepartamentoService( Long idDep ){
			   log.info( "-----> Departamento 'consultarEmpleadosPorDepartamentoService': idDep={}", idDep );
			   
			   Gson         objGson   = new Gson();
			   String       vURI      = "/empleados-departamento/";
			   RestTemplate objRspTmp = this.objTemplate.build(); 
			   
			   //Variables de Entorno: 
			   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
			  			   
			   //Obtener el HOST del POD donde está ubicado el 'MICROSERVICIO'. 
			   ServiceInstance objServiceInstance = this.discoveryClient.getInstances( Constantes.INSTANCIA_KUBERNETES_04 ).get( 0 );
			   
			   String vHostKubernetes = objServiceInstance.getUri() + ""; 
			   log.info( "-----> vHostKubernetes: [" + objServiceInstance.getUri() + "]" );
			    
			   //Armando URI: 
			   String vURL = (vHostKubernetes + "/" + Constantes.SERVICE_NAME_04 + "/" + Constantes.HTTP_METHOD_01 + vURI + idDep); 
			   log.info( "========>: vURL [" + vURL + "]" );
			   
			   //Enviar mensaje GET: 
			   String vCadenaJSON_01 = objRspTmp.getForObject( vURL, String.class );
			   log.info( "========>: vCadenaJSON_01 [" + vCadenaJSON_01 + "]" ); 
			   
			   //Transformar de JSON a OBJETO:   
			   pe.com.capacitacion.dto.ResponseEmplMsg objResponseMsg = objGson.fromJson( vCadenaJSON_01, pe.com.capacitacion.dto.ResponseEmplMsg.class );
			   log.info( "========>: objResponseMsg: " + objResponseMsg ); 
 
			   //Objeto Return: 
			   ResponseEntity<ResponseEmplMsg> objRetorno = new ResponseEntity<ResponseEmplMsg>( objResponseMsg, HttpStatus.OK ); 
			   return objRetorno; 
		}	
	  
	   /**
	    * mostrarVariablesEntorno
	    * @param constantesParam
	    * @param objConfigurationData01Param
	    * @param objConfigurationData02Param
	    **/
        private void mostrarVariablesEntorno( Constantes constantesParam, ConfigurationData_01 objConfigurationData01Param ){ 
        	    log.info( "-----> Empleado 'mostrarVariablesEntorno'" );
        	    
			    String vNombreServicio  = constantesParam.nombreServicio; 
			    String vValor_01        = constantesParam.valor01; 
			    String vNombres         = objConfigurationData01Param.getNombres();
			    String vDni             = objConfigurationData01Param.getDni(); 		
			   
			    log.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" );  
			    log.info( "BOOTADMIN_USUARIO: [" + this.objVariablesEntorno.getProperty( "BOOTADMIN_USUARIO" ) + "], BOOTADMIN_PASSWORD: [" + this.objVariablesEntorno.getProperty( "BOOTADMIN_PASSWORD" ) + "]" );  
        }
		
 }
 
