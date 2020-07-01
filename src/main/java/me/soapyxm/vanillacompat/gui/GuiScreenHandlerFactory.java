package me.soapyxm.vanillacompat.gui;

import me.soapyxm.vanillacompat.VanillaCompat;
import me.soapyxm.vanillacompat.gui.component.GuiButton;
import me.soapyxm.vanillacompat.gui.component.GuiInventorySlot;
import me.soapyxm.vanillacompat.gui.component.GuiLabel;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class GuiScreenHandlerFactory implements NamedScreenHandlerFactory {
    private BarrelBlockEntity be;

    public GuiScreenHandlerFactory(BarrelBlockEntity be) {
        this.be = be;
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("DEBUG TEST NAME");
    }

    @Override
    public ScreenHandler createMenu(int syncID, PlayerInventory inv, PlayerEntity player) {
        GuiScreenHandler screenHandler = GuiScreenHandler.create(syncID, inv);
        screenHandler.addComponent(1, 1, new GuiButton(screenHandler, new ItemStack(Items.GREEN_WOOL)) {
            @Override
            public void click(PlayerEntity player) {
                VanillaCompat.log("Button clicked!");
                player.sendMessage(new LiteralText("Clicked a button."), false);
                screenHandler.close(player);
            }
        });
        screenHandler.addComponent(1, 0, new GuiLabel(screenHandler, new ItemStack(Items.BIRCH_SIGN), new LiteralText("This is a label!")));
        screenHandler.addComponent(2, 1, new GuiInventorySlot(screenHandler, 11, be, 0));
        screenHandler.initSlots();
        return screenHandler;
    }
}
