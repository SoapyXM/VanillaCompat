package me.soapyxm.vanillacompat;

import me.soapyxm.vanillacompat.gui.GuiScreenHandlerFactory;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VanillaCompat implements ModInitializer {
    public static final String MODID = "vanillacompat";
    private static final Logger LOGGER = LogManager.getLogger(MODID);

    public static void log(String msg) {
        LOGGER.info(msg);
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    @Override
    public void onInitialize() {
        log("Initialized.");
    }
}
