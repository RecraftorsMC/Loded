package mc.recraftors.loded.mixin;

import mc.recraftors.loded.Loded;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.CompassItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow @Final private Property levelCost;

    @Shadow private String newItemName;

    AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void onUpdateLodeCompassCopyHeadInjector(CallbackInfo ci) {
        ItemStack i1 = this.input.getStack(0);
        if (i1.getRegistryEntry().isIn(Loded.COMPASS_TAG) && CompassItem.hasLodestone(i1)) {
            ItemStack i2 = this.input.getStack(1);
            if (i2.isOf(i1.getItem())) {
                ItemStack i3 = i1.copy();
                i3.setCount(2);
                int c = 2;
                if (!this.newItemName.isBlank() && !this.newItemName.equals(i1.getName().getString())) {
                    c += 1;
                    i3.setCustomName(Text.literal(this.newItemName));
                }
                this.output.setStack(0, i3);
                this.levelCost.set(c + i1.getRepairCost());
                this.sendContentUpdates();
                ci.cancel();
            }
        }
    }
}
