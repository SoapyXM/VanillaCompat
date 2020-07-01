package me.soapyxm.vanillacompat.gui.component;

import me.soapyxm.vanillacompat.gui.GuiScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;

public class GuiLabel extends GuiComponent {
    private Text text;
    private ItemStack stackTemplate;

    public GuiLabel(GuiScreenHandler screenHandler, ItemStack stack, Text initial) {
        super(screenHandler);
        this.stackTemplate = stack;
        this.text = initial;
    }

    @Override
    public ItemStack getItem() {
        ItemStack newStack = stackTemplate.copy();
        newStack.setCustomName(text);
        return newStack;
    }

    @Override
    public ItemStack onClick(int buttonID, SlotActionType actionType, PlayerEntity playerEntity) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setStack(ItemStack stack) {

    }
}
