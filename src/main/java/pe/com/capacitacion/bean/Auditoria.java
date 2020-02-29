package pe.com.capacitacion.bean;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Auditoria
* @author cguerra
**/
//@Getter            //Autogenerar GETTERs. 
//@Setter            //Autogenerar SETTERs. 
@NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
@AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
@Builder             //Autogenerar BUILDER. 
@Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros. 
public class Auditoria{
	
	    private String ipApp;
	    private String nombreApp; 
	    private String usuarioApp; 
	    private String codigoApp;
	    private String codigoHttp;
	    private String mensajeApp;
 
}
