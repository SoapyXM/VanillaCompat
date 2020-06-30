package me.soapyxm.vanillacompat.fibs;

import dev.hephaestus.fiblib.blocks.BlockFib;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;

public class AlwaysBlockFib extends BlockFib {
    public AlwaysBlockFib(BlockState input, BlockState output) {
        super(input, output);
    }

    @Override
    protected boolean condition(ServerPlayerEntity serverPlayerEntity) {
        return true;
    }
}
