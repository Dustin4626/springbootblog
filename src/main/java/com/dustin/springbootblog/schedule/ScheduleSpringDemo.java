package com.dustin.springbootblog.schedule;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring 自訂定時任務設定
 * org.springframework.scheduling 
 * 
 * 1.SpringbootblogApplication.java add annotation @EnableScheduling
 * 
 * 2.ScheduleController add annotation @Component
 * 
 * 3.method add annotation @Scheduled
 *
 */
@Component
public class ScheduleSpringDemo {

	/**
	 * google "cron 表達式" 查詢範例 
	 *   
	 * "*" 表任意的
	 * 
	 * 1. Seconds (秒) ：可以用數字0－59 表示，
	 * 2. Minutes(分) ：可以用數字0－59 表示
	 * 3. Hours(時) ：可以用數字0-23表示
	 * 4. Day-of-Month(天) ：可以用數字1-31 中的任一一個值，但要注意一些特別的月份
	 * 5. Month(月) ：可以用0-11 或用字符串 「JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV and DEC」 表示
	 * 6. Day-of-Week(每周)：可以用數字1-7表示（1 ＝ 星期日）或用字符口串「SUN, MON, TUE, WED, THU, FRI and SAT」表示
	 * 
	 * 	每兩秒執行一次 
	 */
//	@Scheduled(cron = "0/2 * * * * ?")
	public void outDemo() {
		System.out.println("outDemo " + new Date());
	}
	
	
	
}
