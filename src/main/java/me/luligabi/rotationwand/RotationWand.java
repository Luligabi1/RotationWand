package me.luligabi.rotationwand;

import org.bukkit.plugin.java.JavaPlugin;

import me.luligabi.rotationwand.command.FancyWandCommand;
import me.luligabi.rotationwand.command.RotationWandCommand;
import me.luligabi.rotationwand.listener.FancyWandListener;
import me.luligabi.rotationwand.listener.RotationWandListener;

public class RotationWand extends JavaPlugin {
	
	public static RotationWand plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		this.getCommand("rotationwand").setExecutor(new RotationWandCommand());
		this.getCommand("fancywand").setExecutor(new FancyWandCommand());
		getServer().getPluginManager().registerEvents(new RotationWandListener(), this);
		getServer().getPluginManager().registerEvents(new FancyWandListener(), this);
	}
	@Override
	public void onDisable() {
		
	}

}
