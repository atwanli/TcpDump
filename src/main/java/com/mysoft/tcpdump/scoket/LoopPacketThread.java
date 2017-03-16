package com.mysoft.tcpdump.scoket;

import jpcap.JpcapCaptor;

/**
 * @Title: LoopPacketThread.java
 * @Package com.mysoft.tcpdump.scoket
 * @Description: TODO()
 * @author wwl
 * @date 2016年11月21日 上午10:04:45
 * @version V1.0
 */
public class LoopPacketThread extends Thread {
	private JpcapCaptor jpcap;
	private String ip;

	public LoopPacketThread(JpcapCaptor jpcap,String ip) {
		this.jpcap = jpcap;
		this.ip=ip;
	}

	public void run() {
		this.jpcap.loopPacket(-1, new DumpPacket(ip));
	}
}
