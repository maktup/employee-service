package pe.com.capacitacion.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter            //Autogenerar GETTERs. 
//@Setter            //Autogenerar SETTERs. 
@NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
@AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
@Builder             //Autogenerar BUILDER. 
@Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros. 
@Component
@ConfigurationProperties( "grupoconfig01" ) 
public class ConfigurationData_01{
 
		private String nombres;	
		private String dni;
 
}
