package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemModelGen extends ItemModelProvider {

    public ItemModelGen(PackOutput output, String modid,
        ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        var overlay = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "item/liqueur_overlay");
        overlayItem(ModItems.WINE, overlay);
        overlayItem(ModItems.SPIRIT, overlay);
        overlayItem(ModItems.GRAPE_LIQUEUR, overlay);
        overlayItem(ModItems.LITCHI_LIQUEUR, overlay);
        overlayItem(ModItems.PEACH_LIQUEUR, overlay);
        overlayItem(ModItems.MANGO_LIQUEUR, overlay);
        overlayItem(ModItems.LEMON_LIQUEUR, overlay);
        overlayItem(ModItems.POMEGRANATE_LIQUEUR, overlay);
        overlayItem(ModItems.APPLE_LIQUEUR, overlay);
        overlayItem(ModItems.GOLDEN_GRAPE_LIQUEUR, overlay);
        overlayItem(ModItems.GOLDEN_APPLE_LIQUEUR, overlay);

        var knife = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "item/kitchen_knife");
        basicItem(ModItems.IRON_KNIFE, knife);
        basicItem(ModItems.GOLD_KNIFE, knife);
        basicItem(ModItems.DIAMOND_KNIFE, knife);
        basicItem(ModItems.EMERALD_KNIFE, knife);
    }

    public ItemModelBuilder basicItem(Holder<Item> item, ResourceLocation texture) {
        return getBuilder(item.getRegisteredName())
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", texture);
    }

    public ItemModelBuilder overlayItem(DeferredItem<Item> itemHolder, ResourceLocation overlay) {
        var item = BuiltInRegistries.ITEM.getKey(itemHolder.get());
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/liqueur"))
            .texture("layer1", overlay);
    }
}
