package mc.recraftors.loded;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Loded {
	public static final String MOD_ID = "loded";
	public static final TagKey<Item> COMPASS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "compasses"));
}
