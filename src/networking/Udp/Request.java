package networking.Udp;

import projekt.Packet;

public class Request extends Packet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7048277701330425079L;
	protected String type;
	protected String value;
	
	public Request()
	{
		super();
		type = "empty";
		value = "No device";
	}

	public Request(String typ, String val)
	{
		super();
		type = typ;
		value = val;
	}
}
