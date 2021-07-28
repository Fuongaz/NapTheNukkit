package phuongaz.NapThe.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.Task;
import phuongaz.NapThe.card.Card;
import phuongaz.NapThe.driver.Driver;
import phuongaz.NapThe.driver.Response;

public class PendingCardTask extends Task{

	private Driver driver = new Driver();
	private Card card;

	public PendingCardTask(Card card) {
		this.card = card;
	}

	public void onRun(int i) {
		Response response = this.driver.getCard(card, "check");
		Player player = (Player) Server.getInstance().getPlayer(response.getCard().getRequestId().split("|")[0]);
		if (response.isSucces()) {
			player.sendMessage("Donate succesfully " + this.card.getTelco() + " | " + this.card.getAmount() + "VND");
			this.getHandler().cancel();
		} else if (response.isPending()) {
			player.sendMessage(response.errorToString());
		} else {
			player.sendMessage(response.errorToString());
			this.getHandler().cancel();
		}
	}
}