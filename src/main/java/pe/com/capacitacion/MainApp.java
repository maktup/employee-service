package pe.com.capacitacion;

import io.opentracing.Tracer;
import io.jaegertracing.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.samplers.ConstSampler;
import pe.com.capacitacion.util.Constantes;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
 * MainApp
 * @author cguerra
 **/
 @SpringBootApplication
 @EnableHystrix             //IMPORTANTE: 'HYSTRIX' 
 @EnableFeignClients        //IMPORTANTE: 'FEIGN CLIENT'
 @EnableSwagger2            //IMPORTANTE: 'SWAGGER' 
 @EnableDiscoveryClient     //IMPORTANTE: Descubrimiento por 'KUBERNETES'. 
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
  
	    //---------------------------------------- [SWAGGER] ----------------------------------------// 
		public ApiInfo apiInfo(){
	           return new ApiInfoBuilder()
	                  .title( "CONTRATO/API PARA LA 'GESTION DEL EMPLEADOS'" )
	                  .description( "CONTRATO/API DEL MICROSERVICIO: employee-service" )
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
	    /*
		@Bean
	    public Tracer jaegerAlertTracer(){ 
	           SamplerConfiguration   objSamplerConfig  = new SamplerConfiguration().withType( ConstSampler.TYPE ).withParam( 1 ); 
	           SenderConfiguration    objSenderConfig   = SenderConfiguration.fromEnv().withEndpoint( this.constantes.jeagerUrlServer );
	           ReporterConfiguration  objReporterConfig = ReporterConfiguration.fromEnv().withLogSpans( false ).withSender( objSenderConfig );
	           Configuration          objConfig         = new Configuration( this.constantes.nombreMicroServicio ).withSampler( objSamplerConfig ).withReporter( objReporterConfig );
	           Tracer                 objTracer         = objConfig.getTracer();
	           
	           return objTracer;
	    }   */
	    //----------------------------------------- [JEAGER] ----------------------------------------// 
		
		
		@Bean
		public RestTemplate restTemplate(){
			   return new RestTemplate();
		}
		
 }

 