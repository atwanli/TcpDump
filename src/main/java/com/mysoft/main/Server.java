package com.mysoft.main;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysoft.tcpdump.scoket.Tcpdump;

/**
 * 程序的主入口
 * 
 *
 */
public class Server {
	final private static Logger logger = LoggerFactory.getLogger(Server.class);
	public static ApplicationContext ctx;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws SchedulerException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		logger.info("==============start server==============");
		ctx = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
		logger.info("==============start server ok==============");
		Tcpdump tcpdump=ctx.getBean("tcpdump",Tcpdump.class);
		tcpdump.init();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				((ClassPathXmlApplicationContext) ctx).destroy();
			}
		}));
	}

}
