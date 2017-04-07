package com.demo.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class CreateCsvBySQL {
	private static Log log = LogFactory.getLog(CreateCsvBySQL.class.getName());
	
	public static boolean create (String fileName,String fileTitle,String sql){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData md = null;
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		String basePath = "d:/csv_temp/";
	    File baseDir = new File(basePath);
		try {
			Resource resource = new ClassPathResource("/jdbc.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			Class.forName(props.getProperty("mainDataSource.driverClassName").trim());
			con = DriverManager.getConnection(props.getProperty("mainDataSource.driverClassName").trim(), props.getProperty("mainDataSource.username").trim(), props.getProperty("mainDataSource.password").trim());
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			md = rs.getMetaData();
			csvFile = new File(basePath+"/"+ fileName);
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(  
			        new FileOutputStream(csvFile), "gbk"), 1024);
			csvFileOutputStream.write(fileTitle);
			csvFileOutputStream.newLine();
			while(rs.next()) {
				int columnCount = md.getColumnCount();
				String tempStr = "";
				for (int j = 1; j <= columnCount; j++) {
					Object obj = rs.getObject(j);
					int type = md.getColumnType(j);
					int size = md.getScale(j);
					String columnStr = TableOperHelper.formatObject(obj, type, size);
					tempStr  = tempStr +columnStr+",";
				}
				int strLen = tempStr.length();
				tempStr = tempStr.substring(0,strLen-1);
				csvFileOutputStream.write(tempStr);
				csvFileOutputStream.newLine();
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			DbUtils.closeQuietly(con,ps,rs);
			try {
				if(csvFileOutputStream!=null)
					csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		return false;
	}

}
