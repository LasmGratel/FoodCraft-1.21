package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.block.ModCropBlock;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

public class BlockTags extends BlockTagsProvider {

    public BlockTags(PackOutput output,
        CompletableFuture<Provider> lookupProvider, String modId,
        @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        tag(net.minecraft.tags.BlockTags.SAPLINGS).replace(false).add(
            ModBlocks.COCONUT_SAPLING.getKey(),
            ModBlocks.GRAPEFRUIT_SAPLING.getKey(),
            ModBlocks.CHERRY_SAPLING.getKey(),
            ModBlocks.BANANA_SAPLING.getKey(),
            ModBlocks.PEACH_SAPLING.getKey(),
            ModBlocks.PERSIMMON_SAPLING.getKey(),
            ModBlocks.POMEGRANATE_SAPLING.getKey(),
            ModBlocks.HAWTHORN_SAPLING.getKey(),
            ModBlocks.LOQUAT_SAPLING.getKey(),
            ModBlocks.LEMON_SAPLING.getKey(),
            ModBlocks.PAPAYA_SAPLING.getKey(),
            ModBlocks.LONGAN_SAPLING.getKey(),
            ModBlocks.MANGO_SAPLING.getKey(),
            ModBlocks.LITCHI_SAPLING.getKey(),
            ModBlocks.PEAR_SAPLING.getKey(),
            ModBlocks.ORANGE_SAPLING.getKey(),
            ModBlocks.DATE_SAPLING.getKey()
        );

        tag(net.minecraft.tags.BlockTags.CROPS).replace(false).add(
            ModBlocks.ADZUKI_BEAN.getKey(),
            ModBlocks.SOYBEAN.getKey(),
            ModBlocks.MUNG_BEAN.getKey(),
            ModBlocks.CORN.getKey(),
            ModBlocks.CUCUMBER.getKey(),
            ModBlocks.EGGPLANT.getKey(),
            ModBlocks.FACING_HEAVEN_PEPPER.getKey(),
            ModBlocks.GREEN_PEPPER.getKey(),
            ModBlocks.PEANUT.getKey(),
            ModBlocks.RICE.getKey(),
            ModBlocks.STICKY_RICE.getKey(),
            ModBlocks.SWEET_POTATO.getKey(),
            ModBlocks.TOMATO.getKey(),
            ModBlocks.WHITE_RADISH.getKey(),
            ModBlocks.CABBAGE.getKey(),
            ModBlocks.STRAWBERRY.getKey()
        );
    }
}
