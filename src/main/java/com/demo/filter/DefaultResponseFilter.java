package com.demo.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;

public class DefaultResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext resContext, ContainerResponseContext repContext) throws IOException {
		MediaType type = repContext.getMediaType();
        if (type != null) {
            if (  (type.equals(MediaType.APPLICATION_JSON_TYPE) 
            		|| type.equals(MediaType.APPLICATION_XML_TYPE)
            		||type.equals(MediaType.TEXT_HTML_TYPE)
            		||type.equals(MediaType.TEXT_PLAIN_TYPE)
            		||type.equals(MediaType.TEXT_XML_TYPE)) 
            		&& !type.getParameters().containsKey(MediaType.CHARSET_PARAMETER)) {
                MediaType typeWithCharset = type.withCharset("utf-8");
                repContext.getHeaders().putSingle("Content-Type", typeWithCharset);
            }
            repContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            repContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,application/json");

        }
		
	}

}
