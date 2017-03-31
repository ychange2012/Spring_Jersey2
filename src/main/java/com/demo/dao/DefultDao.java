package com.demo.dao;

public interface DefultDao extends CommonDao{
		public String query(long id);
		public String update(long id ,String name);
		public void del(long id);
		public String insert(long id,String name);
}
