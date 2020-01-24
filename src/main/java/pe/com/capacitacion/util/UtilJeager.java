package pe.com.capacitacion.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component; 
import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.samplers.ProbabilisticSampler;
import io.opentracing.Tracer;

/**
 * UtilJeager
 * @author cguerra
 **/
 @Component
 @SuppressWarnings( "deprecation" )
 public class UtilJeager{
	
	    private static final Logger LOGGER = LoggerFactory.getLogger( UtilJeager.class );
 
	    @Autowired
	    private Constantes constantes; 
	   
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

 