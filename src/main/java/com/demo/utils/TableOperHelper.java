package com.demo.utils;

import java.sql.Types;
import java.text.NumberFormat;

import org.apache.commons.lang.time.FastDateFormat;

public class TableOperHelper {
	
	  private static final FastDateFormat dateTimeFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	  private static NumberFormat nf2 = NumberFormat.getNumberInstance();

	  private static NumberFormat nf4 = NumberFormat.getNumberInstance();
	  
	  static {
		    nf2.setMinimumFractionDigits(2);
		    nf2.setMaximumFractionDigits(2);
		    nf2.setMinimumIntegerDigits(1);
		    nf2.setGroupingUsed(false);
		    nf4.setMinimumFractionDigits(4);
		    nf4.setMaximumFractionDigits(4);
		    nf4.setMinimumIntegerDigits(1);
		    nf4.setGroupingUsed(false);
		  }

	  
	/**
	   * 格式化对象，如类型为日期型，则格式化成日期形式，否则调用toString
	   * @param obj 对象
	   * @param type java.sql.Types类型
	   * @param size 精度
	   * @return
	   */
	  public static String formatObject(Object obj, int type, int size) {

	    if (obj == null || "".equals(obj)) {
	      return "";
	    }

	    if (type == Types.DATE || type == Types.TIME || type == Types.TIMESTAMP) {
	      String value = dateTimeFormat.format(obj);
	      if (value.endsWith("00:00:00")) {
	        value = value.substring(0, 10);
	      }
	      return value;
	    }

	    if (size == 2 && type == Types.NUMERIC) {
	      return nf2.format(obj);
	    }
	    if (size == 4 && type == Types.NUMERIC) {
	      return nf4.format(obj);
	    }
	    return obj.toString();
	  }
}
