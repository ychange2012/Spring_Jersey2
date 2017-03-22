package com.demo.utils;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

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
