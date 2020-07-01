package me.soapyxm.vanillacompat.gui.component;

import me.soapyxm.vanillacompat.gui.GuiScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public abstract class GuiButton extends GuiComponent {
    private final ItemStack item;

    public GuiButton(GuiScreenHandler screenHandler, ItemStack item) {
        super(screenHandler);
        this.item = item;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    public abstract void click(PlayerEntity player);

    @Override
    public ItemStack onClick(int buttonID, SlotActionType actionType, PlayerEntity playerEntity) {
        click(playerEntity);
        return ItemStack.EMPTY;
    }

    @Override
    public void setStack(ItemStack stack) {

    }
}
