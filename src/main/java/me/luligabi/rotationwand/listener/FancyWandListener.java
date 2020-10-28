package me.luligabi.rotationwand.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.luligabi.rotationwand.RotationWand;
import me.luligabi.rotationwand.util.MessageUtil;
import me.luligabi.rotationwand.util.Permission;

public class FancyWandListener implements Listener {
	
	FileConfiguration cfg = RotationWand.plugin.getConfig(); 
	String prefix = cfg.getString("prefix");
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		
		if(p.hasPermission(Permission.USE_FANCY_WAND)) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK && p.isSneaking()) {
				if(p.getItemInHand().getType() == Material.BLAZE_ROD && p.getItemInHand().getItemMeta().getDisplayName().equals(cfg.getString("fancyWandName").replace("&", "§"))) {	
					switch(b.getType()) {
						case LOG:
							if(b.getData() == 0 || b.getData() == 4 || b.getData() == 8) { //Oak Wood
								b.setData((byte) 12);
							} else if(b.getData() == 1 || b.getData() == 5 || b.getData() == 9) { //Spruce Wood
								b.setData((byte) 13);
							} else if(b.getData() == 2 || b.getData() == 6 || b.getData() == 10) { //Birch Wood
								b.setData((byte) 14);
							} else if(b.getData() == 3 || b.getData() == 7 || b.getData() == 11) { //Jungle Wood
								b.setData((byte) 15);
							} else {
								p.sendMessage(MessageUtil.errorMessage(prefix, cfg.getString("fancyInvalidBlockState")));
							}
							break;
						case LOG_2:
							if(b.getData() == 0 || b.getData() == 4 || b.getData() == 8) { //Acacia Wood
								b.setData((byte) 12);
							} else if(b.getData() == 1 || b.getData() == 5 || b.getData() == 9) { //Dark Oak Wood
								b.setData((byte) 13);
							} else {
								p.sendMessage(MessageUtil.errorMessage(prefix, cfg.getString("fancyInvalidBlockState")));
							}
							break;
						case SMOOTH_BRICK:
							b.setType(Material.DOUBLE_STEP);
							b.setData((byte) 8);
							break;
						case SANDSTONE:
							b.setType(Material.DOUBLE_STEP);
							b.setData((byte) 9);
							break;
						case RED_SANDSTONE:
							b.setType(Material.DOUBLE_STONE_SLAB2);
							b.setData((byte) 8);
						default:
							p.sendMessage(MessageUtil.errorMessage(prefix, cfg.getString("fancyInvalidBlock")));
							break;
					}				
				}
			}
		} else {
			p.sendMessage(MessageUtil.permissionMessage(Permission.USE_FANCY_WAND));
		}
	}
}