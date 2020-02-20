package pe.com.capacitacion;

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
import io.jaegertracing.samplers.ConstSampler;
import io.opentracing.Tracer;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.repository.EmpleadoRepository;
import pe.com.capacitacion.util.Constantes;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
 
/**
 * MainApp
 * @author cguerra
 **/
 @SpringBootApplication
 @EnableHystrix             //IMPORTANTE: 'HYSTRIX' 
 @EnableFeignClients        //IMPORTANTE: 'FEIGN CLIENT' 
 public class MainApp{
 
		@Autowired
		private Constantes constantes; 
		
	    public static final String PAQUETE_SWAGGER_SCAN = "pe.com.capacitacion.controller";
		 
	   /**
	    * main 
	    * @param argumentos
	    **/
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
 
	    //---------------------------------------- [SWAGGER] ----------------------------------------// 
		public ApiInfo apiInfo(){
	           return new ApiInfoBuilder()
	                  .title( "CONTRATO/API PARA LA GESTION 'UTILITARIA' DE LA BD: 'CAPADB'" )
	                  .description( "CONTRATO/API DEL MICROSERVICIO: utl-capadb" )
	                  .license( "Apache 2.0" )
	                  .licenseUrl( "http://www.apache.org/licenses/LICENSE-2.0.html" )
	                  .termsOfServiceUrl( "" )
	                  .version( "1.0" )
	                  .contact( new Contact( "", "", "cesarricardo_guerra19@hotmail.com" ) )
	                  .build();
	    }

	    @Bean
	    public Docket customImplementation(){
	           return new Docket( DocumentationType.SWAGGER_2 )
		                  .select()
		                  .apis( RequestHandlerSelectors.basePackage( PAQUETE_SWAGGER_SCAN ) )
		                  .paths( PathSelectors.any() ) 
	                      .build() 
	                      .apiInfo( this.apiInfo() );
	    }
	    //---------------------------------------- [SWAGGER] ----------------------------------------//
		
	    //----------------------------------------- [JEAGER] ----------------------------------------//   
		@Bean
	    public Tracer jaegerAlertTracer(){ 
	           SamplerConfiguration   objSamplerConfig  = new SamplerConfiguration().withType( ConstSampler.TYPE ).withParam( 1 ); 
	           SenderConfiguration    objSenderConfig   = SenderConfiguration.fromEnv().withEndpoint( this.constantes.jeagerUrlServer );
	           ReporterConfiguration  objReporterConfig = ReporterConfiguration.fromEnv().withLogSpans( false ).withSender( objSenderConfig );
	           Configuration          objConfig         = new Configuration( this.constantes.nombreMicroServicio ).withSampler( objSamplerConfig ).withReporter( objReporterConfig );
	           Tracer                 objTracer         = objConfig.getTracer();
	           
	           return objTracer;
	    }   
	    //----------------------------------------- [JEAGER] ----------------------------------------// 
		
 }

 