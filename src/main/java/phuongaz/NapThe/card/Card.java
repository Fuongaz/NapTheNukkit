package phuongaz.NapThe.card;

import phuongaz.NapThe.utils.QueryString;
import phuongaz.NapThe.Loader;
import java.util.HashMap; 
import java.util.Map; 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Card {

	private String seri;
	private String pin;
	private String amount;
	private String telco;
	private String Command;
	private String id;
	private Loader loader = new Loader();

	public Card(Map datacard) {
		this.seri = datacard.get("seri").toString();
		this.pin = datacard.get("pin").toString();
		this.amount = datacard.get("amount").toString();
		this.telco = datacard.get("telco").toString();
		this.id = datacard.get("request_id").toString();
	}

	public String getSerial() {
		return this.seri;
	}

	public String getPin() {
		return this.pin;
	}

	public String getAmount() {
		return this.amount;
	}

	public String getTelco() {
		return this.telco;
	}

	public String getRequestId() {
		return this.id;
	}

	public String getPartnerKey() {
		return this.loader.getPartnerKey();
	}

	public String getPartnerId() {
		return this.loader.getPartnerId();
	}

	public String makeQuery(String command) {
		QueryString qs = new QueryString();
		qs.add("request_id", getRequestId());
		qs.add("partner_id", getPartnerId());
		qs.add("serial", getSerial());
		qs.add("code", getPin());
		qs.add("telco", getTelco());
		qs.add("amount", getAmount());
		qs.add("command", command);
		qs.add("sign", createSign());
		return qs.getQuery();
	}

	public String createSign() {
		String sign = getPartnerKey() + getPin() + getSerial();
	 	try {
	    	MessageDigest md = MessageDigest.getInstance("MD5");
	    	byte[] messageDigest = md.digest(sign.getBytes());
			return convertByteToHex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
  public static String convertByteToHex(byte[] data) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < data.length; i++) {
      sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }
}