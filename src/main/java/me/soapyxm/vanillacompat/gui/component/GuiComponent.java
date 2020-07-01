package me.soapyxm.vanillacompat.gui.component;

import me.soapyxm.vanillacompat.gui.GuiScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;

public abstract class GuiComponent {
    private GuiScreenHandler screenHandler;

    public GuiComponent(GuiScreenHandler screenHandler) {
        this.screenHandler = screenHandler;
    }

    public abstract ItemStack getItem();
    public abstract ItemStack onClick(int buttonID, SlotActionType actionType, PlayerEntity playerEntity);
    public abstract void setStack(ItemStack stack);
}
