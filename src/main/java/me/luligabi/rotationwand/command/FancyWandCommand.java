package me.luligabi.rotationwand.command;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.luligabi.rotationwand.RotationWand;
import me.luligabi.rotationwand.util.MessageUtil;
import me.luligabi.rotationwand.util.Permission;

public class FancyWandCommand implements CommandExecutor {
	
	FileConfiguration cfg = RotationWand.plugin.getConfig(); 
	String prefix = cfg.getString("prefix");
	
	public static ItemStack wand = new ItemStack(Material.BLAZE_ROD);
	ItemMeta wandmeta = wand.getItemMeta();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission(Permission.COMMAND_FANCY_WAND)) {
				wandmeta.setDisplayName(cfg.getString("fancyWandName").replace("&", "§"));
				wandmeta.addEnchant(Enchantment.LURE, 1, false);
				wandmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(cfg.getString("fancyWandLore").replace("&", "§"));
				wandmeta.setLore(lore);
				
				wand.setItemMeta(wandmeta);
				p.getInventory().addItem(wand);
				p.sendMessage(MessageUtil.sucessMessage(prefix, cfg.getString("fancyWandCommandSucess")));
			} else {
				p.sendMessage(MessageUtil.permissionMessage(Permission.COMMAND_FANCY_WAND));
			}
		} else {
			sender.sendMessage(MessageUtil.errorMessage(prefix, cfg.getString("mustBePlayer")));
		}
		return false;
	}
}
