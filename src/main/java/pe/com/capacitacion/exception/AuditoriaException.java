package pe.com.capacitacion.exception;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pe.com.capacitacion.bean.Auditoria;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.bean.ResponseMsg;
 
/**
 * AuditoriaException IMPORTANTE: los parametros deben ser EXACTAMENTE a los mismos del metodo que lo llama. 
 * @cguerra
 **/
 @Component
 public class AuditoriaException{
	
	    private static final Logger LOGGER = LoggerFactory.getLogger( AuditoriaException.class );
 
	   /**
	    * lanzarExceptionWS 
	    * @param  objetoParam
	    * @param  e
	    * @return ResponseMsg
	    **/
	    public ResponseMsg lanzarExceptionWS( Empleado objetoParam, Throwable e ){
		       LOGGER.error( "------> 'lanzarExceptionWS' " );
		       
		       ResponseMsg objResponseMsg = new ResponseMsg();	 
		       Auditoria   objAuditoria   = this.cargarDatosAuditoriaException( e ); 
		       
			   //Agregando:  			   
			   objResponseMsg.setAuditoria( objAuditoria );  
			   return objResponseMsg;
	    } 
    
	   /**
	    * lanzarExceptionWS 
	    * @param  id
	    * @param  e
	    * @return ResponseMsg
	    **/
	    public ResponseMsg lanzarExceptionWS( Long id, Throwable e ){
		       LOGGER.error( "------> 'lanzarExceptionWS' " );
 
		       ResponseMsg objResponseMsg = new ResponseMsg();	 
		       Auditoria   objAuditoria   = this.cargarDatosAuditoriaException( e ); 
		       
			   //Agregando:  			   
			   objResponseMsg.setAuditoria( objAuditoria );  
			   return objResponseMsg;
	    } 
	    
	   /**
	    * lanzarListaExceptionWS 
	    * @param  e
	    * @return ResponseMsg
	    **/
	    public ResponseMsg lanzarListaExceptionWS( Throwable e ){
		       LOGGER.error( "------> 'lanzarListaExceptionWS' " );
 
		       ResponseMsg objResponseMsg = new ResponseMsg();	 
		       Auditoria   objAuditoria    = this.cargarDatosAuditoriaException( e );
		  
			   //Agregando:  			   
			   objResponseMsg.setAuditoria( objAuditoria );  
			   return objResponseMsg;
	    }  
   
	   /**
	    * lanzarListaExceptionWS 
	    * @param  organizationId
        * @param  e
	    * @return ResponseMsg
	    **/
	    public ResponseMsg lanzarListaExceptionWS( Long organizationId, Throwable e ){
		       LOGGER.error( "------> 'lanzarListaExceptionWS' " );
 
		       ResponseMsg objResponseMsg = new ResponseMsg();	 
		       Auditoria   objAuditoria   = this.cargarDatosAuditoriaException( e );
		  
			   //Agregando:  			   
			   objResponseMsg.setAuditoria( objAuditoria );  
			   return objResponseMsg;
	    } 
	    
	   /**
	    * cargarDatosAuditoriaException 
	    * @param  e
	    * @return Auditoria
	    **/
	    private Auditoria cargarDatosAuditoriaException( Throwable e ){ 
		        LOGGER.error( "------> 'cargarDatosAuditoriaException' " );
 
		        Auditoria objAuditoria = new Auditoria();
		       
		        String vIp         = "1.1.1.1";
		        String vNombreApp  = "XXX";
		        String vUsuarioApp = "rguerra"; 
		        String vMensajeApp = "EXCEPTION encontrada: \n" + e.getMessage(); 
	  
		        objAuditoria.setIpApp( vIp );
		        objAuditoria.setNombreApp( vNombreApp );
		        objAuditoria.setUsuarioApp( vUsuarioApp );
		        objAuditoria.setMensajeApp( vMensajeApp ); 
        
			    return objAuditoria;
	    } 
	    
	   /**
	    * cargarDatosAuditoria 
	    * @param  vIpApp
	    * @param  vNombreApp
	    * @param  vUsuarioApp
	    * @param  vMensajeApp
	    * @return Auditoria
	    **/
	    public Auditoria cargarDatosAuditoria( String vIpApp, String vNombreApp, String vUsuarioApp, String vMensajeApp ){ 
		       LOGGER.error( "------> 'cargarDatosAuditoria' " );
 
		       Auditoria objAuditoria = new Auditoria();
 
		       objAuditoria.setIpApp( vIpApp );
		       objAuditoria.setNombreApp( vNombreApp );
		       objAuditoria.setUsuarioApp( vUsuarioApp );
		       objAuditoria.setMensajeApp( vMensajeApp ); 
        
			   return objAuditoria;
	    } 
	    
 }
 
