package pe.com.capacitacion.properties;

import org.springframework.boot.context.properties.ConfigurationProperties; 
import org.springframework.stereotype.Component; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ConfigurationData_02
 * @author cguerra
 **/
//@Getter            //Autogenerar GETTERs. 
//@Setter            //Autogenerar SETTERs. 
 @NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
 @AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
 @Builder             //Autogenerar BUILDER. 
 @Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros. 
 @Component
 @ConfigurationProperties( "ingress.dns" )
 public class ConfigurationData_02{
	
	    private String employee;
	    private String department;
	    private String organization;
	 
 }

 