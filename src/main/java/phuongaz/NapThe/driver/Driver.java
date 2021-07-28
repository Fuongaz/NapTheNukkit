package phuongaz.NapThe.driver;

import java.util.HashMap;
import phuongaz.NapThe.utils.RequestUtils;
import phuongaz.NapThe.utils.QueryString;
import phuongaz.NapThe.card.Card;

public class Driver {

	public Response getCard(Card card, String command) {
		String qs = card.makeQuery(command);
		String request = RequestUtils.get("http://thesieure.com/chargingws/v2?" + qs);
		return new Response(request, card);
	}
}