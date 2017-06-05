package com.demo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SystemInit implements ApplicationListener<ContextRefreshedEvent> {
	private Log log = LogFactory.getLog(SystemInit.class.getName());
	private String [] initList;

	public String[] getInitList() {
		return initList;
	}

	public void setInitList(String[] initList) {
		this.initList = initList;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			for(int i=0;i<initList.length;i++){
				System.out.println("系统初始化："+initList[i]);
				//log.info("系统初始化："+initList[i]);
			}
		}
		
	}

	

}
