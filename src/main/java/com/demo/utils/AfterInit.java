package com.demo.utils;

import org.springframework.stereotype.Component;
@Component
public class AfterInit {
	public void execute(){
		try {
			Thread.sleep(1000*60);
			System.out.println("66565666");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
