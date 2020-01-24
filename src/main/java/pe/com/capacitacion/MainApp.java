package pe.com.capacitacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean; 
import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.samplers.ProbabilisticSampler;
import io.opentracing.Tracer;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.repository.EmpleadoRepository;
import pe.com.capacitacion.util.Constantes;

/**
 * MainApp
 * @author cguerra
 **/
 @SpringBootApplication
 @EnableHystrix             //IMPORTANTE: 'HYSTRIX' 
 @EnableFeignClients        //IMPORTANTE: 'FEIGN CLIENT'
 @SuppressWarnings( "deprecation" )
 public class MainApp{
        
		private static final Logger LOGGER = LoggerFactory.getLogger( MainApp.class );
	 
	    @Autowired
	    private Constantes constantes; 
	    
	    
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
 
	   /**
	    * jaegerAlertTracer	
	    * @return Tracer
	    **/ 
		@Bean
	    public Tracer jaegerAlertTracer(){
	    	   LOGGER.info( "============>: [jaegerAlertTracer] " ); 
	    	   LOGGER.info( "- URL: [" + this.constantes.jeagerUrlServer + "]" ); 
	  
	           SamplerConfiguration   objSamplerConfig  = new SamplerConfiguration( ProbabilisticSampler.TYPE, 1);
	           SenderConfiguration    objSenderConfig   = SenderConfiguration.fromEnv().withEndpoint( this.constantes.jeagerUrlServer );
	           ReporterConfiguration  objReporterConfig = ReporterConfiguration.fromEnv().withLogSpans(true).withSender( objSenderConfig );
	           Configuration          objConfig         = new Configuration( this.constantes.nombreMicroServicio ).withSampler( objSamplerConfig ).withReporter( objReporterConfig );
	           Tracer                 objTracer         = objConfig.getTracer();
	           
	           return objTracer;
	    }
 
 }

 