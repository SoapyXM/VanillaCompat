package me.soapyxm.vanillacompat.mixin.autocompat;

import dev.hephaestus.fiblib.FibLib;
import me.soapyxm.vanillacompat.fibs.AlwaysBlockFib;
import me.soapyxm.vanillacompat.fibs.AlwaysTemplateItemFib;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public class RegistryMixin<T, V> {
    @Inject(at = @At("TAIL"), method = "add(Lnet/minecraft/util/registry/RegistryKey;Ljava/lang/Object;)Ljava/lang/Object;")
    public void registrySetTail(RegistryKey<T> key, V entry, CallbackInfoReturnable<V> cir) {
        Identifier id = key.getValue();
        if(entry instanceof Item) {
            Item item = (Item)entry;
            if(!id.getNamespace().equals("minecraft")) {
                FibLib.log("Registering AutoCompat item fib for " + id.toString());
                FibLib.Items.register(new AlwaysTemplateItemFib(new ItemStack(item), getStackForFib(id, item)));
            }
        }
        if(entry instanceof Block) {
            Block block = (Block)entry;
            if(!id.getNamespace().equals("minecraft")) {
                for(BlockState state : block.getStateManager().getStates()) {
                    FibLib.log("Registering AutoCompat block fib for " + id.toString());
                    FibLib.Blocks.register(new AlwaysBlockFib(state, getStateForFib(id, state)));
                }
            }
        }
    }

    private ItemStack getStackForFib(Identifier id, Item item) {
        if(item instanceof BlockItem) {
            BlockItem blockItem = (BlockItem)item;
            Block fibbedBlock = getStateForFib(id, blockItem.getBlock().getDefaultState()).getBlock();
            return new ItemStack(fibbedBlock);
        } else {
            if(id.getPath().contains("ingot")) {
                return new ItemStack(Items.IRON_INGOT);
            } else if(id.getPath().contains("nugget")) {
                return new ItemStack(Items.IRON_NUGGET);
            }
            return new ItemStack(Items.NETHERITE_SCRAP);
        }
    }

    private BlockState getStateForFib(Identifier id, BlockState state) {
        if(id.getPath().contains("ore")) {
            return Blocks.IRON_ORE.getDefaultState();
        } else if(id.getPath().contains("block")) {
            return Blocks.IRON_BLOCK.getDefaultState();
        }
        return Blocks.RED_CONCRETE.getDefaultState();
    }
}
