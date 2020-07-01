package me.soapyxm.vanillacompat;

import dev.hephaestus.fiblib.FibLib;
import me.soapyxm.vanillacompat.fibs.AlwaysBlockFib;
import me.soapyxm.vanillacompat.fibs.AlwaysTemplateItemFib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompatRegistry {
    private static final Set<Identifier> compatIDs = new HashSet<>();

    public static void registerTranslation(Identifier id, ItemStack stack, ItemStack replaceWith) {
        compatIDs.add(id);
        FibLib.Items.register(new AlwaysTemplateItemFib(stack, replaceWith));
    }

    public static void registerTranslation(Identifier id, Item item, ItemStack replaceWith) {
        registerTranslation(id, new ItemStack(item), replaceWith);
    }

    public static void registerTranslation(Identifier id, BlockState state, BlockState replaceWith) {
        compatIDs.add(id);
        FibLib.Blocks.register(new AlwaysBlockFib(state, replaceWith));
    }

    public static void registerTranslation(Identifier id, Block block, BlockState replaceWith, boolean registerItemBlock) {
        for(BlockState state : block.getStateManager().getStates()) {
            registerTranslation(id, state, replaceWith);
        }
        if(registerItemBlock) {
            registerTranslation(id, new ItemStack(block), new ItemStack(replaceWith.getBlock()));
        }
    }

    public static void registerTranslation(Identifier id, Block block, BlockState replaceWith) {
        registerTranslation(id, block, replaceWith, false);
    }

    public static boolean hasBeenRegistered(Identifier id) {
        return compatIDs.contains(id);
    }
}
