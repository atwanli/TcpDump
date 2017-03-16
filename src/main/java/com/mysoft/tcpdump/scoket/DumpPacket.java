package com.mysoft.tcpdump.scoket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysoft.tcpdump.bean.WebPacket;

import jpcap.PacketReceiver;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

/**
 * @Title: DumpPacket.java
 * @Package com.mysoft.tcpdump.scoket
 * @Description: TODO()
 * @author wwl
 * @date 2016年11月21日 上午10:05:14
 * @version V1.0
 */
public class DumpPacket implements PacketReceiver {
	final private static Logger logger = LoggerFactory.getLogger(DumpPacket.class);
	private String addresses;
	private String[] ports;// 监控固定端口
	private String[] sourceIps;// 请求ip
	private String[] destIps;// 目标ip

	public DumpPacket(String ip) {
		this.addresses = ip;
		init();
	}

	/**
	 * 初始化数据
	 */
	public void init() {
		Properties p = new Properties();
		InputStream is;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			p.load(is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String port = p.getProperty("data.ports");
		String sourceIp = p.getProperty("data.sourceIps");
		String destIp = p.getProperty("data.destIps");
		
		logger.info("=======================================");
		logger.info(this.addresses + " " + port + " " + sourceIp + " " + destIp);
		this.ports = port.split(",");
		this.sourceIps = sourceIp.split(",");
		this.destIps = destIp.split(",");
		logger.info("请求IP" + Arrays.toString(this.sourceIps));
		logger.info("目标IP" + Arrays.toString(this.destIps));
		logger.info("端口" + Arrays.toString(this.ports));
		logger.info("=======================================");
	}

	/**
	 * 实时处理数据包
	 * 
	 * @param packet
	 */
	@Override
	public void receivePacket(Packet packet) {
		WebPacket webPacket = putPacketData(packet);
		checkAvailable(webPacket);
	}

	/**
	 * 筛选可需数据
	 * 
	 * @param webPacket
	 */
	private void checkAvailable(WebPacket webPacket) {
		for (int i = 0; i < ports.length; i++) {
//			if (webPacket.getPacket_data().length > 0) {
				if ((ports[i].equals(String.valueOf(webPacket.getDst_port())))
						&& (destIps[i].equals(webPacket.getDest_ip()))
						&& (sourceIps[i].equals(webPacket.getSource_ip()))) {
//					logger.info("请求webpacket:" + webPacket);	
					handle(webPacket,Charset.forName("UTF-8"));
					handle(webPacket,Charset.forName("GBK"));
					handle(webPacket,Charset.forName("GB2312"));
				}
				if (((ports[i].equals(String.valueOf(webPacket.getSrc_port())))
						&& (destIps[i].equals(webPacket.getSource_ip()))
						&& (sourceIps[i].equals(webPacket.getDest_ip())))) {
//					logger.info("响应webpacket:" + webPacket);
					handle(webPacket,Charset.forName("GBK"));
				}
//			}
		}
	}

	
	/**
	 * 数据分析
	 * @param webPacket
	 */
	public void handle(WebPacket webPacket,Charset paramCharset){
		String responseXml = null;
		responseXml = new String(webPacket.getPacket_data(),paramCharset);
		String  plainXml=responseXml.substring(responseXml.indexOf("<Plain"),responseXml.indexOf("<ds:Signature")).replaceAll("\n","");
		logger.info(webPacket.getReceive_date()+"   "+plainXml);
	}
	/**
	 * 封装抓取数据
	 * 
	 * @param webPacket
	 * @param packet
	 * @return
	 */
	private WebPacket putPacketData(Packet packet) {
		WebPacket webPacket = new WebPacket();
		webPacket.setAddresses(this.addresses);// 监听网卡地址
		EthernetPacket ethernetPacket = (EthernetPacket) packet.datalink;
		webPacket.setSource_mac(ethernetPacket.getSourceAddress());// 请求硬件地址
		webPacket.setDest_mac(ethernetPacket.getDestinationAddress());// 目标硬件地址
		IPPacket ippacket = (IPPacket) packet;
		webPacket.setSource_ip(ippacket.src_ip.getHostAddress());// 请求IP地址
		webPacket.setDest_ip(ippacket.dst_ip.getHostAddress());// 目标IP地址
		webPacket.setProtocol(ippacket.protocol);// 网络协议
		webPacket.setPriority(ippacket.priority);// 优先级
		webPacket.setVersion(ippacket.version);// 版本
		webPacket.setHop_limit(ippacket.hop_limit);// 生存时间
		webPacket.setIdent(ippacket.ident);// 分组标识
		webPacket.setCaplen(ippacket.len);// 网络包长度
		webPacket.setDatalen(ippacket.length);// 数据包长度
		webPacket.setHeader_data(ippacket.header);// 报文头部信息
		webPacket.setPacket_data(ippacket.data);// 报文数据
		webPacket.setReceive_date(new Timestamp(ippacket.sec * 1000 + ippacket.usec / 1000));// 收报时间
		if (ippacket.protocol == 6) {
			TCPPacket tcppacket = (TCPPacket) ippacket;
			webPacket.setSrc_port(tcppacket.src_port);// 源端口
			webPacket.setDst_port(tcppacket.dst_port);// 目标端口
		}
		if (ippacket.protocol == 17) {
			UDPPacket udppacket = (UDPPacket) ippacket;
			webPacket.setSrc_port(udppacket.src_port);// 源端口
			webPacket.setDst_port(udppacket.dst_port);// 目标端口
		}
		return webPacket;
	}

	public String[] getPorts() {
		return ports;
	}

	public void setPorts(String[] ports) {
		this.ports = ports;
	}

	public String[] getSourceIps() {
		return sourceIps;
	}

	public void setSourceIps(String[] sourceIps) {
		this.sourceIps = sourceIps;
	}

	public String[] getDestIps() {
		return destIps;
	}

	public void setDestIps(String[] destIps) {
		this.destIps = destIps;
	}

}
