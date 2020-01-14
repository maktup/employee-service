package pe.com.capacitacion.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pe.com.capacitacion.bean.Empleado;

/**
 * EmpleadoRepository
 * @author cguerra
 **/
 public class EmpleadoRepository{

		private List<Empleado> listaEmpleados = new ArrayList<Empleado>(); 
		
	   /**
	    * agregarEmpleado	
	    * @param  empleado 
	    **/
		public void agregarEmpleado( Empleado empleado ){
			   empleado.setId( (long)(this.listaEmpleados.size() + 1 ) );
			   this.listaEmpleados.add( empleado ); 
		}
		
	   /**
	    * consultarEmpleadosAll	
	    * @return List<Empleado>
	    **/
		public List<Empleado> consultarEmpleadosAll(){ 
			   return this.listaEmpleados;
		}
		
	   /**
	    * consultarEmpleadosPorId	
	    * @param  id
	    * @return Empleado
	    **/
		public Empleado consultarEmpleadosPorId( Long id ){
			   Optional<Empleado> objOptional = this.listaEmpleados.stream().filter( a -> a.getId().equals( id ) ).findFirst(); 
			   Empleado           objEmpleado = null; 
				
			   if( objOptional.isPresent() ){
				   objEmpleado = objOptional.get();
			   }
			   else{
					objEmpleado = null;
			   }
				
			   return objEmpleado; 
		}
		
	   /**
	    * consultarEmpleadosPorDepartamento	
	    * @param  departmentId
	    * @return List<Empleado>
	    **/
		public List<Empleado> consultarEmpleadosPorDepartamento( Long departmentId ){
			   return listaEmpleados.stream().filter(a -> a.getDepartmentId().equals( departmentId ) ).collect( Collectors.toList() );
		}
 
 }
 
