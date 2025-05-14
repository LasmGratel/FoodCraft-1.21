package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class BlockStateGen extends BlockStateProvider {

    public BlockStateGen(PackOutput output, String modid,
        ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cross(ModBlocks.GRAPEFRUIT);
        cross(ModBlocks.CHERRY);
        cross(ModBlocks.COCONUT);
        cross(ModBlocks.BANANA);
        cross(ModBlocks.PEACH);
        cross(ModBlocks.PERSIMMON);
        cross(ModBlocks.POMEGRANATE);
        cross(ModBlocks.HAWTHORN);
        cross(ModBlocks.LOQUAT);
        cross(ModBlocks.LEMON);
        cross(ModBlocks.PAPAYA);
        cross(ModBlocks.LONGAN);
        cross(ModBlocks.MANGO);
        cross(ModBlocks.LITCHI);
        cross(ModBlocks.PEAR);
        cross(ModBlocks.ORANGE);
        cross(ModBlocks.DATE);

        sapling(ModBlocks.GRAPEFRUIT_SAPLING);
        sapling(ModBlocks.CHERRY_SAPLING);
        sapling(ModBlocks.COCONUT_SAPLING);
        sapling(ModBlocks.BANANA_SAPLING);
        sapling(ModBlocks.PEACH_SAPLING);
        sapling(ModBlocks.PERSIMMON_SAPLING);
        sapling(ModBlocks.POMEGRANATE_SAPLING);
        sapling(ModBlocks.HAWTHORN_SAPLING);
        sapling(ModBlocks.LOQUAT_SAPLING);
        sapling(ModBlocks.LEMON_SAPLING);
        sapling(ModBlocks.PAPAYA_SAPLING);
        sapling(ModBlocks.LONGAN_SAPLING);
        sapling(ModBlocks.MANGO_SAPLING);
        sapling(ModBlocks.LITCHI_SAPLING);
        sapling(ModBlocks.PEAR_SAPLING);
        sapling(ModBlocks.ORANGE_SAPLING);
        sapling(ModBlocks.DATE_SAPLING);


    }

    public void saplingBlock(Block block, ModelFile modelFile) {
        this.getVariantBuilder(block).forAllStates((state) -> {
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }

    private void sapling(DeferredBlock<Block> block) {
        //saplingBlock(block.get(), models().cross("block/" + block.getId().getPath(), this.blockTexture(block.get())).renderType("cutout"));
        simpleBlock(block.get(), models().cross("block/" + block.getId().getPath(), this.blockTexture(block.get())).renderType("cutout_mipped"));

        itemModels().getBuilder(block.getId().toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(
            FoodCraft.MOD_ID, "block/" + block.getId().getPath()));
    }

    private void cross(DeferredBlock<Block> block) {
        simpleBlock(block.get(), models().cross("block/" + block.getId().getPath(), this.blockTexture(block.get())).renderType("cutout_mipped"));
    }
}
