package com.demo.utils;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.plugins.history.LoggingJobHistoryPlugin;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.demo.api.Sample;
import com.demo.filter.DefaultResponseFilter;

import io.swagger.config.ConfigFactory;
import io.swagger.config.ScannerFactory;
import io.swagger.config.SwaggerConfig;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.DefaultJaxrsScanner;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jersey.listing.ApiListingResourceJSON;


@ApplicationPath("/api")
public class MainConf extends ResourceConfig {
	 public MainConf(){
		 packages("com.demo.api");
//		 LogManager.getLogManager().reset();
//		 SLF4JBridgeHandler.install();
//		 Logger.getLogger("global").setLevel(Level.FINEST);
		 //register(LoggingFeature.class);
		 register(LoggingFilter.class);
		 
		 register(DefaultResponseFilter.class);
		 
		 register(JacksonFeature.class);
		 /*
		 register( ApiListingResource.class );
		 register( ApiDeclarationProvider.class );
		 register( ApiListingResourceJSON.class );
		 register( ResourceListingProvider.class );
		 
		 */
		 register(io.swagger.jaxrs.listing.ApiListingResource.class);
		 register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		 //register(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class); 
		 swaggerConfiguration( );
	 }
	 
	 private void swaggerConfiguration( )
	   {
		 /*
	      SwaggerConfig swaggerConfig = new SwaggerConfig( );
	      ConfigFactory.setConfig( swaggerConfig );
	      swaggerConfig.setApiVersion( "0.0.1" ); 
	      swaggerConfig.setBasePath( "http://192.168.200.89:8080/WebDemo/api" );
	      ScannerFactory.setScanner( new DefaultJaxrsScanner( ) );
	      ClassReaders.setReader( new DefaultJaxrsApiReader( ) );
	      */
		 	BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("1.0.0");
	        beanConfig.setSchemes(new String[]{"http"});
	        beanConfig.setHost("192.168.200.89:8080");
	        beanConfig.setBasePath("WebDemo/api");
	        beanConfig.setResourcePackage("com.demo.api");
	        beanConfig.setScan(true);
	   }
}
