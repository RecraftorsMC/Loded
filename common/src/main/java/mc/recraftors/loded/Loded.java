package mc.recraftors.loded;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKeys;

public class Loded {
	public static final String MOD_ID = "loded";
	public static final TagKey<Item> COMPASS_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "compasses"));
}
