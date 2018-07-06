package com.yedam.web;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	@Scheduled(cron = "0/5 * 11 * * *")
	public void cronTest() {
		System.out.println("오전 11시에 5초간격으로 실행됩니다.");
	}
	
}
