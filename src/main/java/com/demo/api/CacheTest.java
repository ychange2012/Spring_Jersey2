package com.demo.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Path("cache")
@Controller
@Api(value = "/cache")
public class CacheTest {
	@Autowired
	private DemoService demoService;
	
	@Context
	HttpServletRequest request;
	
	@Path("query")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getName(@QueryParam("id") long id){
		demoService.query(id);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
	}
	
	@Path("query2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getName2(@QueryParam("id") long id){
		demoService.query2(id);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
	}
	
	@Path("del")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response delById(@QueryParam("id") long id){
		demoService.del(id);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
	}
	
	
	@Path("updatecache")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatecache(@ApiParam(value = "ID", required = true) @FormParam("id") long id,
			@ApiParam(value = "姓名", required = true)@FormParam("name") String name
			) {
		demoService.update(id, name);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
    }
	
	@Path("insertcache")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCache(@ApiParam(value = "ID", required = true) @FormParam("id") long id,
			@ApiParam(value = "姓名", required = true)@FormParam("name") String name
			) {
		demoService.insert(id, name);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
    }
	
	
}
