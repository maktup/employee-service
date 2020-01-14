package pe.com.capacitacion.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties( "grupoconfig01" ) 
public class ConfigurationData_01{
 
		private String nombres;	
		private String dni;
 
 
		public String getNombres() {
			   return nombres;
		}
	
		public void setNombres(String nombres) {
			   this.nombres = nombres;
		}

		public String getDni() {
			   return dni;
		}

		public void setDni(String dni) {
			   this.dni = dni;
		}
  
}
