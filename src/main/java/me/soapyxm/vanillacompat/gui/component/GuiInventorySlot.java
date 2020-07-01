package me.soapyxm.vanillacompat.gui.component;

import me.soapyxm.vanillacompat.gui.GuiScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;

public class GuiInventorySlot extends GuiComponent {
    public final Inventory inventory;
    public final int slot;

    public GuiInventorySlot(GuiScreenHandler screenHandler, int guiIndex, Inventory inventory, int slot) {
        super(screenHandler);
        this.inventory = inventory;
        this.slot = slot;
    }

    @Override
    public ItemStack getItem() {
        return inventory.getStack(slot);
    }

    @Override
    public ItemStack onClick(int buttonID, SlotActionType actionType, PlayerEntity playerEntity) {
        return inventory.removeStack(slot);
    }

    @Override
    public void setStack(ItemStack stack) {
        inventory.setStack(slot, stack);
    }
}
