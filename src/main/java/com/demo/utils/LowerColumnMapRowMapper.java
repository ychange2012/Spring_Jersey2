package com.demo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.jdbc.core.ColumnMapRowMapper;


public class LowerColumnMapRowMapper extends ColumnMapRowMapper {
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> temp =  super.mapRow(rs, rowNum);
		Map<String, Object> result = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : temp.entrySet()) {
			result.put(entry.getKey().toLowerCase(), entry.getValue());
		}
		/*
		 * for(String keyStr:temp.keySet())
			result.put(keyStr.toLowerCase(), temp.get(keyStr));
		 * 
		 */
		
		return result;
	}
	
	public Map<String, Object> mapRowAll(ResultSet rs) throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		ResultSetMetaData md = rs.getMetaData();
		while(rs.next()){
			int columCount = md.getColumnCount();
					for(int i=1;i<=columCount;i++){
						String key = md.getColumnLabel(i).toLowerCase();
						Object obj = rs.getObject(i);
						int type = md.getColumnType(i);
						int size = md.getScale(i);
						if(obj==null ||"".equals(obj)){
							obj="";
						}else if(type == Types.DATE || type == Types.TIME || type == Types.TIMESTAMP){
							String value = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(obj);
							if(value.endsWith("00:00:00")){
								value = value.substring(0, 10);
							}
							obj = value;
						}else if(type == Types.NUMERIC && obj instanceof BigDecimal){
							BigDecimal b = (BigDecimal)obj;
							b.setScale(size, RoundingMode.HALF_UP);
							obj = b.toString();
						}
						result.put(key, obj);
					}
		}
		return result;
		
	}
}
