package com.demo.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.service.SysService;

import io.swagger.annotations.Api;

@Path("sysapi")
@Controller
@Api(value = "/sysapi")
public class SysConfApi {
	@Context
	HttpServletRequest request;
	
	@Autowired
	private SysService sysService;
	
	@Path("query")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConf(@QueryParam("id") long id){
		sysService.getColName("SELECT t.id,t.last_name,t.start_date FROM test_col t");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
	}
}
