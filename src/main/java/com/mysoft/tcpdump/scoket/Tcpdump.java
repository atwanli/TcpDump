package com.mysoft.tcpdump.scoket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

/**
 * @Title: Tcpdump.java
 * @Package com.mysoft.tcpdump.scoket
 * @Description: TODO()
 * @author wwl
 * @date 2016年11月21日 上午10:03:26
 * @version V1.0
 */
public class Tcpdump {
	final private static Logger logger = LoggerFactory.getLogger(Tcpdump.class);
	@Value("${data.networkCardNumeber}")
	private int NetworkCardNumeber=0;

	/**
	 * 根据网卡个数，启动统计线程
	 */
	public void init() {
		try {
			// 获取本机上的网络接口对象
			final NetworkInterface[] devices = JpcapCaptor.getDeviceList();
			for (int i = 0; i < devices.length; i++) {
				NetworkInterface nc = devices[i];
				String ip = nc.addresses[i].address.toString();
				logger.info("======第" + (i + 1) + "个网卡ip[" + ip + "]======");
				// 大与零则为有效网卡,不抓本机地址.
			}
			NetworkInterface nc = devices[NetworkCardNumeber];
			if (nc.addresses.length > 0) {
				// 一个网卡可能有多个地址,只取第一个地址
				String addr = nc.addresses[0].address.toString();
				// 创建某个卡口上的抓取对象,
				JpcapCaptor jpcap = JpcapCaptor.openDevice(nc, 2000, true, 100);
				// 创建对应的抓取线程并启动
				LoopPacketThread lt = new LoopPacketThread(jpcap,addr);
				lt.run();
				logger.info("======网卡[" + addr + "]上的采集线程己启动======");
			}
		} catch (Exception ef) {
			ef.printStackTrace();
			logger.error("start caputerThread error ! ! ! !" + ef);
		}
	}

	public int getNetworkCardNumeber() {
		return NetworkCardNumeber;
	}

	public void setNetworkCardNumeber(int networkCardNumeber) {
		NetworkCardNumeber = networkCardNumeber;
	}
	
}