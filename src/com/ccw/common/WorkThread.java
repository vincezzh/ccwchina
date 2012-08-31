package com.ccw.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.vincezzh.meitounao.MeiTouNaoCenter;

class MyTimer extends TimerTask {
	private WorkCenter wc;
	private Date today;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public MyTimer(WorkCenter wc) {
		this.wc = wc;
		today = new Date();
	}

	@Override
	public void run() {
		String todayDate = sdf.format(today);
		Date now = new Date();
		String nowDate = sdf.format(now);
		if(!nowDate.equals(todayDate)) {
			System.out.println("~~~~~~~~~~Send Reminder start~~~~~~~~~~~");
			wc.sendCoursesReminder();
			today = now;
			System.out.println("~~~~~~~~~~Send Reminder end~~~~~~~~~~~");
		}
	}
}

class MeiTouNaoTimer extends TimerTask {
	private MeiTouNaoCenter mtnc;
	
	public MeiTouNaoTimer(MeiTouNaoCenter mtnc) {
		this.mtnc = mtnc;
	}

	@Override
	public void run() {
		System.out.println("~~~~~~~~~~Send MeiTouNao start~~~~~~~~~~~");
		mtnc.sendNotifications();
		System.out.println("~~~~~~~~~~Send MeiTouNao end~~~~~~~~~~~");
	}
}

public class WorkThread {
	public void startThread(WorkCenter wc, MeiTouNaoCenter mtnc) {
		Timer timer = new Timer();
		TimerTask task = new MyTimer(wc);
		timer.schedule(task, 10*60*1000, 30*60*1000);
		
		Timer timer2 = new Timer();
		TimerTask task2 = new MeiTouNaoTimer(mtnc);
		timer2.schedule(task2, 3*60*1000, 10*60*1000);
	}
}
