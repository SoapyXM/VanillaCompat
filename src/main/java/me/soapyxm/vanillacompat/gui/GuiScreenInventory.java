package me.soapyxm.vanillacompat.gui;

import me.soapyxm.vanillacompat.gui.component.GuiComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;

public class GuiScreenInventory implements Inventory {
    private ItemStack bg;
    private final int size;
    private GuiScreenHandler screenHandler;

    public GuiScreenInventory(ItemStack bg, int size) {
        this.bg = bg;
        this.bg.setCustomName(new LiteralText(""));
        this.size = size;
    }

    public void setScreenHandler(GuiScreenHandler handler) {
        this.screenHandler = handler;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStack(int slot) {
        GuiComponent component = screenHandler.getComponents().get(slot);
        if(component != null)
            return component.getItem();
        return bg;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        throw new RuntimeException("Tried to remove a stack from a GUI screen inventory.");
    }

    @Override
    public ItemStack removeStack(int slot) {
        throw new RuntimeException("Tried to remove a stack from a GUI screen inventory.");
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        screenHandler.getComponents().get(slot).setStack(stack);
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        throw new RuntimeException("Tried to clear a GUI screen inventory.");
    }
}
