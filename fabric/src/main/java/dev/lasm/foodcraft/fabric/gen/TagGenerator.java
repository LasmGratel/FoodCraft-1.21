package dev.lasm.foodcraft.fabric.gen;

import dev.lasm.foodcraft.init.ModItems;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagGenerator extends ItemTagProvider {
    private static final TagKey<Item> DUST_CHOCOLATE = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "dusts/chocolate"));
    private static final TagKey<Item> DUST_SALT = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "dusts/salt"));

    public TagGenerator(FabricDataOutput output,
        CompletableFuture<Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override

    protected void addTags(Provider arg) {
        getOrCreateTagBuilder(DUST_CHOCOLATE).add(ModItems.CHOCOLATE_DUST.get());
    }
}
