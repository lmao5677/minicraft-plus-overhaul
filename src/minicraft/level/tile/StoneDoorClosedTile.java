package minicraft.level.tile;

import minicraft.Game;
import minicraft.entity.Entity;
import minicraft.entity.ItemEntity;
import minicraft.entity.Mob;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Screen;
import minicraft.item.Item;
import minicraft.item.ResourceItem;
import minicraft.item.ToolItem;
import minicraft.item.ToolType;
import minicraft.item.resource.Resource;
import minicraft.level.Level;
import minicraft.sound.Sound;

public class StoneDoorClosedTile extends Tile {
	public StoneDoorClosedTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int x, int y) {
		int col = Color.get(444, 333, 222, 333);
		
		screen.render(x * 16 + 0, y * 16 + 0, 2 + 24 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 3 + 24 * 32, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 2 + 25 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 3 + 25 * 32, col, 0);
	}

	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.Pickaxe) {
				if (player.payStamina(4 - tool.level)) {
					level.setTile(xt, yt, Tile.sbrick, 0);
					level.add(
							new ItemEntity(
									new ResourceItem(Resource.sdoor),
									xt * 16 + random.nextInt(10) + 3,
									yt * 16 + random.nextInt(10) + 3));
					Sound.monsterHurt.play();
					return true;
				}
			}
			/*if (tool.type == ToolType.pick) {
				if (player.payStamina(4 - tool.level)) {
					level.setTile(xt, yt, Tile.sbrick, 0);
					level.add(
							new ItemEntity(
									new ResourceItem(Resource.sdoor),
									xt * 16 + random.nextInt(10) + 3,
									yt * 16 + random.nextInt(10) + 3));
					Sound.monsterHurt.play();
					return true;
				}
			}*/
		}
		return false;
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		level.setTile(x, y, Tile.sdo, 0);
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
