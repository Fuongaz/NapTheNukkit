package phuongaz.NapThe.driver;

import com.google.gson.Gson;
import phuongaz.NapThe.card.Card;
import java.util.Map;
import java.util.HashMap;

public class Response {

	private PraseRes response;
	private Card card;
	private String request;

	public Response(String request, Card card) {
		this.card = card;
		this.request = request;
		parseResponse();
	}

	public void parseResponse() {
		Gson gson = new Gson();
		this.response = gson.fromJson(this.request, PraseRes.class);
	}

	public boolean isSucces() {
		if (this.response.status == 1) {
			return true;
		}
		return false;
	} 

	public boolean isPending() {
		if (this.response.status == 99) {
			return true;
		}
		return false;
	}

	public String errorToString() {
		int code = this.response.status;
		switch(code) {
			case 1:
				return "Thẻ thành công đúng mệnh giá";
			case 2:
				return "Thẻ thành công sai mệnh giá";
			case 3:
				return "Thẻ lỗi";
			case 4:
				return "Hệ thống bảo trì";
			case 99:
				return "Thẻ chờ xử lý vui lòng chờ 10 giây";
			case 100:
				return "Gửi thẻ thất bại: " + this.response.message;
			default:
				return "Lỗi: UNKNOWN";
		}
	}

	public Card getCard() {
		return this.card;
	}

	public class PraseRes {
		public int status;
		public String message;
	}
}
