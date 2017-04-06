package com.demo.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.impl.DefultDaoImpl;
import com.demo.service.SysService;

@Service
public class SysServiceImpl implements SysService {
	private Log log = LogFactory.getLog(SysServiceImpl.class.getName());
	@Autowired
	DefultDaoImpl defultDao;
	
	@Override
	public List<String> getColName(String sql) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData md = null;
		
		try {
			con = defultDao.getDataSource().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			md = rs.getMetaData();
			int numberOfColumns = md.getColumnCount();
			
			for(int i=1;i<=numberOfColumns;i++){
				System.out.println(md.getColumnLabel(i));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			DbUtils.closeQuietly(con,ps,rs);
		}
		
		return null;
	}

}
