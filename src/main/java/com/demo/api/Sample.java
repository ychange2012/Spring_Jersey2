package com.demo.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.pojo.Account;
import com.demo.pojo.ErrorRep;
import com.demo.service.DemoService;

import io.swagger.annotations.*;
@SwaggerDefinition(
        info = @Info(
                description = "Gets the weather",
                version = "V1.0.0",
                title = "The Weather API"
                
        ))

@Path("test")
@Controller
@Api(value = "/test" ,description = "Operations about user")

public class Sample {
	
	@Autowired
	private DemoService demoService;
	
	@Context
	HttpServletRequest request;
	
	@Path("dashboard")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDashboard(@QueryParam("account") String account,@QueryParam("deptId") int deptId){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("account", account);
		result.put("deptId", deptId);
		return Response.ok(result).build();
		
	}
	
	@GET
	@Path("getMsg")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = {@ApiResponse( code = 503, message = "Unexpected error", response = ErrorRep.class) })
	@ApiOperation(value = "获取账号信息接口",notes = "接口详细信息描述",response = Account.class, httpMethod = "GET")
	public Response deleteUser(
	      @ApiParam(value = "账号", required = true) @QueryParam("account") String account,
	      @ApiParam(value = "部门ID", required = true) @QueryParam("deptId") int deptId,
	      @ApiParam(value = "类型", required = true) @QueryParam("num") double num) {
		System.out.println("=============success=============="+num);
		if ("admin".equals(account)) {
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("account", account);
			result.put("deptId", deptId);
			result.put("num", num);
			return Response.ok(result).build();
	    } else {
	      return Response.status(Response.Status.NOT_FOUND).build();
	    }
	  }
	
	@Path("queryTest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@QueryParam("id") long id,@QueryParam("name") String name){
		Map<String,Object> result = new HashMap<String,Object>();
		result = demoService.queryById(id);
		return Response.ok(result).build();
		
	}
}
