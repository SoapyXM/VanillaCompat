package me.soapyxm.vanillacompat.fibs;

import dev.hephaestus.fiblib.items.TemplateItemFib;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class AlwaysTemplateItemFib extends TemplateItemFib {
    public AlwaysTemplateItemFib(ItemStack inputTemplate, ItemStack outputTemplate) {
        super(inputTemplate, outputTemplate);
    }

    @Override
    public boolean condition(ServerPlayerEntity serverPlayerEntity) {
        return true;
    }
}
