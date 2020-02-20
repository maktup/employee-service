package pe.com.capacitacion.util;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Constantes
 * @author cguerra
 **/
 @Component
 public class Constantes{
 
	    @Value( "${ws.nombre.servicio}" )             //ACCESO: al valor REMOTO [ws.nombre.servicio]
	    public String nombreServicio;
	 
		@Value( "${propiedades.config.valor_01}" )    //ACCESO: [propiedades.config.valor_01}]
		public String valor01; 
	    
		
		//[DNS]
	    @Value( "${ingress.dns.employee}" )           //ACCESO: al valor REMOTO [ingress.dns.employee]
	    public String ingressEmployee;
		
	    @Value( "${ingress.dns.department}" )         //ACCESO: al valor REMOTO [ingress.dns.department]
	    public String ingressDepartment;
	    
	    @Value( "${ingress.dns.organization}" )       //ACCESO: al valor REMOTO [ingress.dns.organization]
	    public String ingressOrganization;
	   
	    @Value( "${ingress.dns.utlcapadb}" )          //ACCESO: al valor REMOTO [ingress.dns.utlcapadb]
	    public String ingressUtiCapadb;
	    
	    
	    //[JAEGER]
	    @Value( "${opentracing.jaeger.http-sender.url}" )    //ACCESO: al valor REMOTO [jeaguer.conexion.url.server]
	    public String jeagerUrlServer;
        
	    @Value( "${spring.application.name}" )               //ACCESO: al valor REMOTO [spring.application.name]
	    public String nombreMicroServicio;
 
	    
		public static String INSTANCIA_EUREKA_01 = "DEPARTMENT-SERVICE";  
		public static String INSTANCIA_EUREKA_02 = "EMPLOYEE-SERVICE"; 
		public static String INSTANCIA_EUREKA_03 = "ORGANIZATION-SERVICE";
		public static String INSTANCIA_EUREKA_04 = "UTL-CAPADB";
		
		public static String SERVICE_NAME_01 = "departmentservice";  
		public static String SERVICE_NAME_02 = "employeeservice"; 
		public static String SERVICE_NAME_03 = "organizationservice";
		public static String SERVICE_NAME_04 = "utlcapadb";
		
		public static String HTTP_METHOD_01 = "get";  
		public static String HTTP_METHOD_02 = "post"; 
		public static String HTTP_METHOD_03 = "delete";
 
		public static String IP_APP_OK      = "1.1.1.1";
		public static String USUARIO_APP_OK = "RGUERRA"; 
		
		public static String IP_APP_NOK      = "1.1.1.1";
		public static String NOMBRE_APP_NOK  = "IL";
		public static String USUARIO_APP_NOK = "RGUERRA";
		public static String COD_APP_NOK     = "-1";
		public static String COD_APP_OK      = "0";
		public static String MSJ_APP_NOK     = "ERROR EN EL PROCESO: ";
		public static String MSJ_APP_OK      = "PROCESO OK";
		
 }

 