package networking.Udp;

import projekt.Packet;

public class Request extends Packet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String type;
	protected String value;
	
	public Request()
	{
		super();
		type = "empty";
		value = "no_device";
		
	}
	
	public Request(String typ, String val)
	{
		super();
		type = typ;
		value = val;
		
	}
	
	public Request(String typ, String val, String dev, String desc, long dat)
	{
		super(dev,desc,dat);
		type = typ;
		value = val;
	}
	
	public String toString()
	{
		return "Type: " + type + " value: " + value + super.toString();
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getValue()
	{
		return value;
	}

}
