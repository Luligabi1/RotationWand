package me.luligabi.rotationwand.util;

import me.luligabi.rotationwand.RotationWand;

public class MessageUtil {
	
	public static String sucessMessage(String prefix, String message) {
		return RotationWand.plugin.getConfig().getString("sucessMessage")
				.replace("&", "§")
				.replace("%prefix%", prefix)
				.replace("%message%", message);
	}
	public static String errorMessage(String prefix, String message) {
		return RotationWand.plugin.getConfig().getString("errorMessage")
				.replace("&", "§")
				.replace("%prefix%", prefix)
				.replace("%message%", message);
	}
	public static String infoMessage(String prefix, String message) {
		return RotationWand.plugin.getConfig().getString("infoMessage")
				.replace("&", "§")
				.replace("%prefix%", prefix)
				.replace("%message%", message);
	}
	public static String permissionMessage(String permission) {
		return RotationWand.plugin.getConfig().getString("permissionMessage")
				.replace("&", "§")
				.replace("%permission%", permission);
	}
}