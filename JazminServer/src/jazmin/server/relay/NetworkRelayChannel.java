/**
 * 
 */
package jazmin.server.relay;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

/**
 * @author yama
 *
 */
public abstract  class NetworkRelayChannel extends RelayChannel{
	protected final String localHostAddress;
	protected final int localPort;
	protected InetSocketAddress remoteAddress;
	//
	protected Channel outboundChannel;
	protected final TransportType transportType;
	//
	public NetworkRelayChannel(TransportType type,String localAddress,  int localPort) {
		super();
		this.transportType=type;
		this.localHostAddress=localAddress;
		this.localPort=localPort;

	}

	/**
	 * @return the localHostAddress
	 */
	public String getLocalHostAddress() {
		return localHostAddress;
	}
	
	@Override
	public boolean isActive(){
		if(outboundChannel==null){
			return false;
		}
		return outboundChannel.isActive();
	}
	//
	@Override
	public void closeChannel()throws Exception{
		if(outboundChannel!=null){
			outboundChannel.close().sync();
		}
	}
	/**
	 * @return the localPort
	 */
	public int getLocalPort() {
		return localPort;
	}

	/**
	 * @return the remoteAddress
	 */
	public InetSocketAddress getRemoteAddress() {
		return remoteAddress;
	}
	
	
	/**
	 * @param remoteAddress the remoteAddress to set
	 */
	public void setRemoteAddress(InetSocketAddress remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	/**
	 * @return the transportType
	 */
	public TransportType getTransportType() {
		return transportType;
	}

	/* 	 */
	@Override
	public String getInfo() {
		String remoteAddressStr="";
		if(remoteAddress!=null){
			remoteAddressStr=remoteAddress.getAddress().getHostAddress()
					+":"+remoteAddress.getPort();
		}
		return transportType+"["+localHostAddress+":"+localPort+"<-->"+remoteAddressStr+"]";
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return getInfo();
	}
}