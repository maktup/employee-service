package pe.com.capacitacion.bean;

import java.io.Serializable; 
import com.fasterxml.jackson.annotation.JsonRootName; 

/**
 * Empleado
 * @author cguerra
 **/  
 @JsonRootName( value = "Empleado" ) 
 public class Empleado  implements Serializable{
 
        private static final long serialVersionUID = -7883259734962895249L;

		private Long   id;
		private String nombre;
		private int    edad;
		private String rol;		
		private Long   departmentId;  //REFERENCIA
		
		public Empleado(){
		}
	 
		public Empleado( Long id, String nombre, int edad, String rol, Long departmentId ){
			super(); 
			this.id           = id; 
			this.nombre       = nombre;
			this.edad         = edad;
			this.rol          = rol;
			this.departmentId = departmentId;
		}
 
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
 
		public Long getDepartmentId() {
			return departmentId;
		}
	
		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}
	
		public String getNombre() {
			return nombre;
		}
	
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public int getEdad() {
			return edad;
		}
	
		public void setEdad(int edad) {
			this.edad = edad;
		}
	
		public String getRol() {
			return rol;
		}
	
		public void setRol(String rol) {
			this.rol = rol;
		}
	
		@Override
		public String toString() {
			   return "Empleado [id=" + this.id + ", departmentId=" + this.departmentId + ", name=" + this.nombre + ", position=" + this.rol + ", departmentId=" + this.departmentId + "]";  
		} 

 }

 