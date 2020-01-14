package pe.com.capacitacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.repository.EmpleadoRepository;

/**
 * MainApp
 * @author cguerra
 **/
 @SpringBootApplication
 @EnableDiscoveryClient     //IMPORTANTE: 'EUREKA CLIENT' + KUBERNETES
 @EnableHystrix             //IMPORTANTE: 'HYSTRIX' 
 @EnableFeignClients        //IMPORTANTE: 'FEIGN CLIENT'
 public class MainApp{
	
	    public static void main( String[] argumentos ){
		 	   SpringApplication.run( MainApp.class, argumentos );
	    }
	    
	   /**
	    * repository  
	    * @return EmpleadoRepository 
	    **/   
		@Bean
		public EmpleadoRepository repository(){
				EmpleadoRepository objRepository = new EmpleadoRepository(); 
				
				objRepository.agregarEmpleado( new Empleado( 1L, "John Smith",      34, "Analyst",   1L ) );
				objRepository.agregarEmpleado( new Empleado( 1L, "Darren Hamilton", 37, "Manager",   1L ) );
				objRepository.agregarEmpleado( new Empleado( 1L, "Tom Scott",       26, "Developer", 1L ) );
				objRepository.agregarEmpleado( new Empleado( 1L, "Anna London",     39, "Analyst",   2L ) );		
				objRepository.agregarEmpleado( new Empleado( 1L, "Patrick Dempsey", 27, "Developer", 2L ) );
				objRepository.agregarEmpleado( new Empleado( 2L, "Kevin Price",     38, "Developer", 3L ) );		
				objRepository.agregarEmpleado( new Empleado( 2L, "Ian Scott",       34, "Developer", 3L ) );
				objRepository.agregarEmpleado( new Empleado( 2L, "Andrew Campton",  30, "Manager",   3L ) );
				objRepository.agregarEmpleado( new Empleado( 2L, "Steve Franklin",  25, "Developer", 4L ) );
				objRepository.agregarEmpleado( new Empleado( 2L, "Elisabeth Smith", 30, "Developer", 4L ) );
				
				return objRepository;
		}	
		
 }
