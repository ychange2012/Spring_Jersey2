package com.demo.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public List<String> getColName(long gridId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData md = null;
		List<String> result = new ArrayList<String>();
		try {
			String sql = "select t.grid_sql from test_grid t WHERE t.grid_id = ?";
			con = defultDao.getDataSource().getConnection();
			Map temp = defultDao.queryObjectSQL(sql, gridId);
			String sqlStr = temp==null ? null:temp.get("grid_sql").toString();
			ps = con.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			md = rs.getMetaData();
			int numberOfColumns = md.getColumnCount();
			for(int i=1;i<=numberOfColumns;i++){
				result.add(getColCN(md.getColumnLabel(i),gridId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			DbUtils.closeQuietly(con,ps,rs);
		}
		return result;
	}
	
	public String getColCN(String col,long gridId) throws SQLException{
		String sql = "select t.grid_col_name from test_grid_conf t WHERE t.grid_id = ?  and t.grid_col = ?";
		Map result = defultDao.queryObjectSQL(sql, gridId,col);
		if(result == null ){
			return col;
		}else{
			return (String)result.get("grid_col_name");
		}
	}

}
