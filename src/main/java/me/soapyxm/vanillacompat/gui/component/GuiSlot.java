package me.soapyxm.vanillacompat.gui.component;

import me.soapyxm.vanillacompat.gui.GuiScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class GuiSlot extends GuiComponent {
    private ItemStack stack = ItemStack.EMPTY;

    public GuiSlot(GuiScreenHandler screenHandler) {
        super(screenHandler);
    }

    @Override
    public ItemStack getItem() {
        return stack;
    }

    @Override
    public ItemStack onClick(int buttonID, SlotActionType actionType, PlayerEntity playerEntity) {
        return stack;
    }

    @Override
    public void setStack(ItemStack stack) {
        this.stack = stack;
    }
}
