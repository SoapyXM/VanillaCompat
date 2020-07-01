package me.soapyxm.vanillacompat.gui;

import me.soapyxm.vanillacompat.VanillaCompat;
import me.soapyxm.vanillacompat.gui.component.GuiComponent;
import me.soapyxm.vanillacompat.gui.component.GuiInventorySlot;
import me.soapyxm.vanillacompat.gui.component.GuiSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.world.World;

import javax.swing.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GuiScreenHandler extends ScreenHandler {
    private GuiComponent[] components;
    private PlayerInventory playerInventory;
    private GuiScreenInventory inventory;
    private int rows;
    private int columns;

    private GuiScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, GuiScreenInventory inventory, int rows) {
        super(type, syncId);
        this.playerInventory = playerInventory;
        this.inventory = inventory;
        this.inventory.setScreenHandler(this);
        this.rows = rows;
        this.columns = inventory.size() / rows;
        components = new GuiComponent[inventory.size()];
        checkSize(inventory, rows * 9);
        this.inventory = inventory;
        this.rows = rows;
        inventory.onOpen(playerInventory.player);
    }

    public static GuiScreenHandler create(int syncId, PlayerInventory playerInventory) {
        GuiScreenInventory inventory = new GuiScreenInventory(new ItemStack(Items.LIGHT_GRAY_STAINED_GLASS_PANE), 9*3);
        return new GuiScreenHandler(ScreenHandlerType.GENERIC_9X3, syncId, playerInventory, inventory, 3);
    }

    public void initSlots() {
        int i = (this.rows - 4) * 18;
        for(int y=0; y < columns; y++) {
            for(int x=0; x < rows; x++) {
                int slotIndex = (y * rows) + x;
                if(components[slotIndex] instanceof GuiInventorySlot) {
                    GuiInventorySlot slot = (GuiInventorySlot)components[slotIndex];
                    this.addSlot(new Slot(slot.inventory, slot.slot, x, y));
                } else {
                    this.addSlot(new Slot(inventory, slotIndex, x, y));
                }
            }
        }

        for(int n = 0; n < 3; ++n) {
            for(int m = 0; m < 9; ++m) {
                this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 8 + m * 18, 103 + n * 18 + i));
            }
        }

        for(int n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, 8 + n * 18, 161 + i));
        }
    }

    public void addComponent(int x, int y, GuiComponent component) {
        components[y * columns + x] = component;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public ItemStack onSlotClick(int slot, int buttonID, SlotActionType actionType, PlayerEntity playerEntity) {
        if(slot >= inventory.size()) {
            return super.onSlotClick(slot, buttonID, actionType, playerEntity);
        }
        if(slot < 0) {
            return ItemStack.EMPTY;
        }
        if(components[slot] instanceof GuiInventorySlot) {
            return super.onSlotClick(slot, buttonID, actionType, playerEntity);
        }
        if(components[slot] != null) {
            ItemStack stack = components[slot].onClick(buttonID, actionType, playerEntity);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    public List<GuiComponent> getComponents() {
        return Arrays.asList(components);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        if(components[slot] != null) {
            components[slot].setStack(stack);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return super.transferSlot(player, index);
    }

    @Override
    protected void dropInventory(PlayerEntity player, World world, Inventory inventory) {
        super.dropInventory(player, world, inventory);
        for(GuiComponent component : components) {
            if(component instanceof GuiSlot)
                player.dropItem(component.getItem(), false);
        }
    }
}
