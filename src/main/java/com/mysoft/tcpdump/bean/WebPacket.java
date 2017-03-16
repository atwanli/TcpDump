package com.mysoft.tcpdump.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * @Title: WebPacket.java
 * @Package com.mysoft.tcpdump.bean
 * @Description: TODO(数据包对象)
 * @author wwl
 * @date 2016年11月21日 上午10:01:05
 * @version V1.0
 */
public class WebPacket implements Serializable {
	private static final long serialVersionUID = -306139042821180470L;
	private String addresses;
	private short protocol;
	private byte version;
	private short hop_limit;
	private byte priority;
	private int ident;
	private int src_port;
	private int dst_port;
	private String source_ip;
	private String source_mac;
	private String dest_ip;
	private String dest_mac;
	private int caplen;
	private short datalen;
	private byte[] header_data;
	private byte[] packet_data;
	private Timestamp receive_date;

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public short getProtocol() {
		return protocol;
	}

	public void setProtocol(short protocol) {
		this.protocol = protocol;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public short getHop_limit() {
		return hop_limit;
	}

	public void setHop_limit(short hop_limit) {
		this.hop_limit = hop_limit;
	}

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public int getSrc_port() {
		return src_port;
	}

	public void setSrc_port(int src_port) {
		this.src_port = src_port;
	}

	public int getDst_port() {
		return dst_port;
	}

	public void setDst_port(int dst_port) {
		this.dst_port = dst_port;
	}

	public String getSource_ip() {
		return source_ip;
	}

	public void setSource_ip(String source_ip) {
		this.source_ip = source_ip;
	}

	public String getSource_mac() {
		return source_mac;
	}

	public void setSource_mac(String source_mac) {
		this.source_mac = source_mac;
	}

	public String getDest_ip() {
		return dest_ip;
	}

	public void setDest_ip(String dest_ip) {
		this.dest_ip = dest_ip;
	}

	public String getDest_mac() {
		return dest_mac;
	}

	public void setDest_mac(String dest_mac) {
		this.dest_mac = dest_mac;
	}

	public int getCaplen() {
		return caplen;
	}

	public void setCaplen(int caplen) {
		this.caplen = caplen;
	}

	public short getDatalen() {
		return datalen;
	}

	public void setDatalen(short datalen) {
		this.datalen = datalen;
	}

	public byte[] getHeader_data() {
		return header_data;
	}

	public void setHeader_data(byte[] header_data) {
		this.header_data = header_data;
	}

	public byte[] getPacket_data() {
		return packet_data;
	}

	public void setPacket_data(byte[] packet_data) {
		this.packet_data = packet_data;
	}

	public Timestamp getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(Timestamp receive_date) {
		this.receive_date = receive_date;
	}

	@Override
	public String toString() {
		try {
			return "WebPacket [addresses=" + addresses + ", protocol=" + protocol + ", version=" + version + ", hop_limit="
					+ hop_limit + ", priority=" + priority + ", ident=" + ident + ", src_port=" + src_port + ", dst_port="
					+ dst_port + ", source_ip=" + source_ip + ", source_mac=" + source_mac + ", dest_ip=" + dest_ip
					+ ", dest_mac=" + dest_mac + ", caplen=" + caplen + ", datalen=" + datalen + ", header_data="
					+ new String(header_data,"ISO8859_1") + ", packet_data=" + new String(packet_data,"ISO8859_1") + ", receive_date="
					+ receive_date + "]";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
