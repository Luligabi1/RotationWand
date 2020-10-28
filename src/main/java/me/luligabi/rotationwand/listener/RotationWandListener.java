package me.luligabi.rotationwand.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.luligabi.rotationwand.RotationWand;
import me.luligabi.rotationwand.util.MessageUtil;
import me.luligabi.rotationwand.util.Permission;

public class RotationWandListener implements Listener {
	
	FileConfiguration cfg = RotationWand.plugin.getConfig(); 
	String prefix = cfg.getString("prefix");
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		
		if(p.hasPermission(Permission.USE_ROTATION_WAND)) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK && p.isSneaking()) {
				if(p.getItemInHand().getType() == Material.STICK && p.getItemInHand().getItemMeta().getDisplayName().equals(cfg.getString("rotationWandName").replace("&", "§"))) {	
					switch(b.getType()) {
						case ACACIA_STAIRS:
						case BIRCH_WOOD_STAIRS:
						case BRICK_STAIRS:
						case COBBLESTONE_STAIRS:
						case DARK_OAK_STAIRS:
						case JUNGLE_WOOD_STAIRS:
						case NETHER_BRICK_STAIRS:
						case QUARTZ_STAIRS:
						case RED_SANDSTONE_STAIRS:
						case SANDSTONE_STAIRS:
						case SMOOTH_STAIRS:
						case SPRUCE_WOOD_STAIRS:
						case WOOD_STAIRS:
							if(b.getData() == (byte) 3) { //stairs (upright) north -> east
								b.setData((byte) 0, false);
							} else if(b.getData() == (byte) 0) { //stairs (upright) east -> south
								b.setData((byte) 2, false);
							} else if(b.getData() == (byte) 2) { //stairs (upright)  south -> west
								b.setData((byte) 1, false);
							} else if(b.getData() == (byte) 1) { //stairs (upright) west -> north
								b.setData((byte) 3, false);
							} else if(b.getData() == (byte) 6) { //stairs (upside down) north -> east
								b.setData((byte) 5, false);
							} else if(b.getData() == (byte) 5) { //stairs (upside down) east -> south
								b.setData((byte) 7, false);
							} else if(b.getData() == (byte) 7) { //stairs (upside down) south -> west
								b.setData((byte) 4, false);
							} else if(b.getData() == (byte) 4) { //stairs (upside down) west -> north
							b.setData((byte) 6, false);
							}
							break;
						case ACTIVATOR_RAIL: 
						case DETECTOR_RAIL: 
						case POWERED_RAIL:
							if(b.getData() == (byte) 0) { //Z axis to X axis
								b.setData((byte) 1, false);
							} else if(b.getData() == (byte) 1) { //X axis to Z axis
								b.setData((byte) 0, false);
							} else if(b.getData() == (byte) 4) { //north -> east
								b.setData((byte) 2, false);
							} else if(b.getData() == (byte) 2) { //east -> south
								b.setData((byte) 5, false);
							} else if(b.getData() == (byte) 5) { //south -> west
								b.setData((byte) 3, false);
							} else if(b.getData() == (byte) 3) { //west -> north
								b.setData((byte) 4, false);
							}
							break;
						case ANVIL:
							if(b.getData() == (byte) 0) { //anvil (regular) Z axis to X axis
								b.setData((byte) 1, false);
							} else if(b.getData() == (byte) 1) { //anvil (regular) Y axis to X axis
								b.setData((byte) 0, false);
							} else if(b.getData() == (byte) 4) { //anvil (slightly damaged) Z axis to X axis
								b.setData((byte) 5);
							} else if(b.getData() == (byte) 5) { //anvil (slightly damaged) X axis to Z axis
								b.setData((byte) 4);
							} else if(b.getData() == (byte) 8) { //anvil (very damaged) Z axis to X axis
								b.setData((byte) 9);
							} else if(b.getData() == (byte) 9) { //anvil (very damaged) X axis to Z axis
								b.setData((byte) 8);
							}
							break;
						case CHEST:
						case ENDER_CHEST: 
						case TRAPPED_CHEST:
						case LADDER: 
						case TRIPWIRE_HOOK: 
						case WALL_BANNER: 
						case WALL_SIGN:
							if(b.getData() == (byte) 2) { // north -> east
								b.setData((byte) 5, false);
							} else if(b.getData() == (byte) 5) { //east -> south
								b.setData((byte) 3, false);
							} else if(b.getData() == (byte) 3) { //south -> west
								b.setData((byte) 4, false);
							} else if(b.getData() == (byte) 4) { //west -> north
								b.setData((byte) 2, false);
							} else {
								p.sendMessage(MessageUtil.errorMessage(prefix, cfg.getString("rotationInvalidBlockState")));
							}
							break;
						case FENCE_GATE:
							break;
						case IRON_TRAPDOOR:
						case TRAP_DOOR:
							break;
						case LEVER:
							break;
						case RAILS:
							break;
						case SIGN_POST: 
						case STANDING_BANNER:
							byte currentData = b.getData();
							b.setData(currentData == 0 ? (byte) 15 : (byte) (currentData-1), false);
							break;
						case SKULL:
							Skull skull = (Skull) b.getState();
							if(skull.getRotation() == BlockFace.NORTH) {
								skull.setRotation(BlockFace.NORTH_EAST);
							} else if(skull.getRotation() == BlockFace.EAST) {
								skull.setRotation(BlockFace.SOUTH_EAST);
							} else if(skull.getRotation() == BlockFace.SOUTH) {
								skull.setRotation(BlockFace.SOUTH_WEST);
							} else if(skull.getRotation() == BlockFace.WEST) {
								skull.setRotation(BlockFace.NORTH_WEST);
							}
							break;
						case TORCH: 
						case REDSTONE_TORCH_ON: 
						case REDSTONE_TORCH_OFF:
							if(b.getData() == (byte) 3) { //north -> east
								b.setData((byte) 2, false);
							} else if(b.getData() == (byte) 2) { //east -> south
								b.setData((byte) 4, false);
							} else if(b.getData() == (byte) 4) { //south -> west
								b.setData((byte) 1, false);
							} else if(b.getData() == (byte) 1) { //west -> north
								b.setData((byte) 3, false);
							} else {
								return;
							}
							break;
						default:
							p.sendMessage(MessageUtil.errorMessage(prefix, cfg.getString("rotationInvalidBlock")));
							break;
					}				
				}
			}
		} else {
			p.sendMessage(MessageUtil.permissionMessage(Permission.USE_ROTATION_WAND));
		}
	}
}
