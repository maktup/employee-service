package pe.com.capacitacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean; 
import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import io.opentracing.Tracer;
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
  
		/* Configure sender host and port details */
	    private static final int    JAEGER_PORT = 32326;
	    private static final String JAEGER_HOST = "http://capacitacion.microservicios.prometheus-server"; 
	 
	    
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

	
	    /* End */
	    @Bean
	    public Tracer getTracer() {
	
	        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
	                .withType(ProbabilisticSampler.TYPE).withParam(1);
	
	            /* Update default sender configuration with custom host and port */
	            SenderConfiguration senderConfig = Configuration.SenderConfiguration.fromEnv()
	                    .withAgentHost( JAEGER_HOST ) 
	                    .withAgentPort( JAEGER_PORT );
	        /* End */ 
	        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
	                .withLogSpans(true)
	                .withSender(senderConfig);
	
	        Configuration config = new Configuration( "Service_Name" ).withSampler( samplerConfig )
	                .withReporter(reporterConfig);
	
	        return config.getTracer();
	    }
 }

 