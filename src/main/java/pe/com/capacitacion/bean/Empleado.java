package pe.com.capacitacion.bean;

import java.io.Serializable; 
import javax.xml.bind.annotation.XmlRootElement; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Empleado
 * @author cguerra
 **/
 //@Getter            //Autogenerar GETTERs. 
 //@Setter            //Autogenerar SETTERs. 
 @NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
 @AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
 @Builder             //Autogenerar BUILDER. 
 @Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros. 
 @XmlRootElement( name = "empleado" ) 
 public class Empleado implements Serializable{
 
	    private static final long serialVersionUID = -2569051539525119808L;
	
		private Long   id;
		private String nombre; 
		private int    edad; 
		private String rol; 		
		private Long   idDep;  //REFERENCIA
 
 }
 
