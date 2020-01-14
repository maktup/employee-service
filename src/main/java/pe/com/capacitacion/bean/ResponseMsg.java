package pe.com.capacitacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 
import com.fasterxml.jackson.annotation.JsonRootName; 

/**
 * ResponseMsg
 * @author cguerra
 **/  
 @JsonRootName( value = "ResponseMsg" ) 
 public class ResponseMsg implements Serializable{
 
        private static final long serialVersionUID = -7883259734965595249L;

        private Auditoria      auditoria;
		private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
  
		public ResponseMsg(){
		}

		public ResponseMsg( Auditoria auditoria, List<Empleado> listaEmpleado ){
			   super();
			   this.auditoria = auditoria;
			   this.listaEmpleados  = listaEmpleado;
		}
 
		public List<Empleado> getListaEmpleados() {
			   return listaEmpleados;
		}
 
		public void setListaEmpleados(List<Empleado> listaEmpleados ) {
			   this.listaEmpleados = listaEmpleados;
		}

		public static long getSerialversionuid() {
			   return serialVersionUID;
		}

		public Auditoria getAuditoria() {
			   return auditoria;
		}

		public void setAuditoria(Auditoria auditoria) {
			   this.auditoria = auditoria;
		}

		@Override
		public String toString() {
			   return "ResponseMsg [auditoria=" + this.auditoria + ", listaEmpleado=" + this.listaEmpleados + "]";
		}
 
 }

 