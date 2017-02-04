package beans;

import java.util.HashMap;
import java.util.Map;

public class SMSContacts
{
	private Map<String,String> contacts = new HashMap<String,String>();
	
	public Map<String, String> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}

	public SMSContacts(Map<String,String> records)
	{
		this.contacts =records;
	}
}
