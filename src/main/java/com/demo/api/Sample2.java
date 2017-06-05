package com.demo.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

@Controller
@Path("test2")
@Api(value = "/test2")
public class Sample2 {
	@Context
	HttpServletRequest request;
	
	@Autowired
	private DemoService demoService;
	
	@Path("login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(
			@ApiParam(value = "账号", required = true) @FormParam("account") int account,
			@ApiParam(value = "密码", required = true)@FormParam("password") String password){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("account", account);
		result.put("deptId", password);
		return Response.ok(result).build();
		
	}
	
	
	
	/**
	 * data:JSON.stringify(param),
	 * type:'POST',
	 * contentType:'application/json'
	 * dataType:'json'
	 * 
	 * @param json
	 * @return
	 */
	
	@Path("save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String json) {
		if( json == null || "" .equals(json)) {
			System.out.println("缺少参数");
		}
		JSONObject o = JSONObject.fromObject(json);
		if(o.containsKey("account")){
			String user = o.getString("username");
			System.out.println("========:"+user);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
    }
	
	@Path("update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@ApiParam(value = "账号", required = true) @FormParam("id") long id,
			@ApiParam(value = "姓名", required = true)@FormParam("name") String name
			) {
		demoService.updateData(id, name);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("msg", "success");
		return Response.ok(result).build();
    }
	
	@Path("default")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response upload(@ApiParam(value="选择文件",required=true)
			@FormDataParam("uploadFile") InputStream fileInputStream,
			 @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition
			) {
		System.out.println("000000"+fileFormDataContentDisposition.getFileName());
		Map result = new HashMap();
		result.put("success", "true");
		return Response.ok(result).build();
//		return null;
	}
	


}
