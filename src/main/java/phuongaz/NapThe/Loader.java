package phuongaz.NapThe;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Loader extends PluginBase{

    private static Loader instance;

    public static Loader getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.getServer().getCommandMap().register("NapThe", new NapTheCommand("napthe"));
	}

	public String getPartnerKey() {
		return "";
	}

	public String getPartnerId() {
		return "";
	}
}