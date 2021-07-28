package phuongaz.NapThe;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import phuongaz.NapThe.card.Card;
import phuongaz.NapThe.driver.Driver;
import phuongaz.NapThe.driver.Response;
import phuongaz.NapThe.task.PendingCardTask;

public class NapTheCommand extends Command {

  private Random rd = new Random();

	public NapTheCommand(String name) {
	    super(name);
	    this.setDescription("Donate Command.");
  	}

  @Override
  public boolean execute(CommandSender sender, String commandLabel, String[] args) {
/*  	if (!(sender instanceof Player)) {
	     sender.sendMessage("You can only run this command in game.");
	     return false;
  	}*/
  	if (args.length != 4) {
  		sender.sendMessage("/napthe <amount> <seri> <pin> <card type>");
  		return false;
  	}
    Map<String, String> data = new HashMap();
    data.put("amount", args[0]);
    data.put("seri", args[1]);
    data.put("pin", args[2]);
    data.put("telco", args[3]);
    data.put("request_id", sender.getName() + "|" + this.rd.nextInt(1000000));
    Card card = new Card(data);
    Driver driver = new Driver();
    Response response = driver.getCard(card, "charging");
    if (response.isSucces()) {
      sender.sendMessage("Donate successfully " + args[3] + " | " + args[0] + "VND");
    } else if (response.isPending()){
      PendingCardTask task = new PendingCardTask(card);
      Loader.getInstance().getServer().getScheduler().scheduleDelayedTask(task, 20*10);
      sender.sendMessage(response.errorToString());
    } else {
      sender.sendMessage(response.errorToString());
    }
    return true;    
  }
}