package com.javbus.server;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	public static void main(String[] args) {
//		Thread thr1 = new Thread();
		Thread thr2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}, "线程1");
		FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
//				Thread.sleep(3000);
				// TODO Auto-generated method stub
				return String.valueOf(Thread.currentThread().getName());
			}
		});
		Thread thr4 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}, "线程4");
		Thread thr3 = new Thread(ft, "线程2");
//			thr1.start();
			thr2.start();
//			thr3.start();
//			thr3.yield(); // 线程礼让：暂停当前线程，运行其他线程
			thr4.start();
//			try {
//				thr2.join(); // 线程加入：等待该线程运行完毕再执行其他线程
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				System.out.println(ft.get());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("The main thread end....");
	}
}
