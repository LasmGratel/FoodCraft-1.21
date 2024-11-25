package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ForgeTags;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(PackOutput pOutput, CompletableFuture<Provider> pLookupProvider,
        CompletableFuture<TagLookup<Block>> pBlockTags, String modId,
        @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull Provider provider) {
        tag(ForgeTags.CROPS).replace(false).add(
            // Vegetables
            ModItems.CORN.getKey(),
            ModItems.CUCUMBER.getKey(),
            ModItems.EGGPLANT.getKey(),
            ModItems.FACING_HEAVEN_PEPPER.getKey(),
            ModItems.GREEN_PEPPER.getKey(),
            ModItems.PEANUT.getKey(),
            ModItems.RICE.getKey(),
            ModItems.STICKY_RICE.getKey(),
            ModItems.SWEET_POTATO.getKey(),
            ModItems.TOMATO.getKey(),
            ModItems.WHITE_RADISH.getKey(),
            ModItems.CABBAGE.getKey(),
            ModItems.GREEN_ONION.getKey(),

            // Fruits
            ModItems.STRAWBERRY.getKey(),
            ModItems.GRAPEFRUIT.getKey(),
            ModItems.CHERRY.getKey(),
            ModItems.COCONUT.getKey(),
            ModItems.BANANA.getKey(),
            ModItems.PEACH.getKey(),
            ModItems.PERSIMMON.getKey(),
            ModItems.POMEGRANATE.getKey(),
            ModItems.HAWTHORN.getKey(),
            ModItems.LOQUAT.getKey(),
            ModItems.LEMON.getKey(),
            ModItems.PAPAYA.getKey(),
            ModItems.LONGAN.getKey(),
            ModItems.MANGO.getKey(),
            ModItems.LITCHI.getKey(),
            ModItems.PEAR.getKey(),
            ModItems.ORANGE.getKey(),
            ModItems.DATE.getKey()
        );

        tag(ForgeTags.VEGETABLES).replace(false).add(
            ModItems.CORN.getKey(),
            ModItems.CUCUMBER.getKey(),
            ModItems.EGGPLANT.getKey(),
            ModItems.FACING_HEAVEN_PEPPER.getKey(),
            ModItems.GREEN_PEPPER.getKey(),
            ModItems.PEANUT.getKey(),
            ModItems.RICE.getKey(),
            ModItems.STICKY_RICE.getKey(),
            ModItems.SWEET_POTATO.getKey(),
            ModItems.TOMATO.getKey(),
            ModItems.WHITE_RADISH.getKey(),
            ModItems.CABBAGE.getKey(),
            ModItems.GREEN_ONION.getKey()
        );

        tag(ModTags.FRUITS).replace(false).add(
            ModItems.STRAWBERRY.getKey(),
            ModItems.GRAPEFRUIT.getKey(),
            ModItems.CHERRY.getKey(),
            ModItems.COCONUT.getKey(),
            ModItems.BANANA.getKey(),
            ModItems.PEACH.getKey(),
            ModItems.PERSIMMON.getKey(),
            ModItems.POMEGRANATE.getKey(),
            ModItems.HAWTHORN.getKey(),
            ModItems.LOQUAT.getKey(),
            ModItems.LEMON.getKey(),
            ModItems.PAPAYA.getKey(),
            ModItems.LONGAN.getKey(),
            ModItems.MANGO.getKey(),
            ModItems.LITCHI.getKey(),
            ModItems.PEAR.getKey(),
            ModItems.ORANGE.getKey(),
            ModItems.DATE.getKey()
        );

        tag(ModTags.CORN).replace(false).add(ModItems.CORN.getKey());
        tag(ModTags.CUCUMBER).replace(false).add(ModItems.CUCUMBER.getKey());
        tag(ModTags.EGGPLANT).replace(false).add(ModItems.EGGPLANT.getKey());
        tag(ModTags.FACING_HEAVEN_PEPPER).replace(false).add(ModItems.FACING_HEAVEN_PEPPER.getKey());
        tag(ModTags.GREEN_PEPPER).replace(false).add(ModItems.GREEN_PEPPER.getKey());
        tag(ModTags.PEANUT).replace(false).add(ModItems.PEANUT.getKey());
        tag(ModTags.RICE).replace(false).add(ModItems.RICE.getKey());
        tag(ModTags.STICKY_RICE).replace(false).add(ModItems.STICKY_RICE.getKey());
        tag(ModTags.SWEET_POTATO).replace(false).add(ModItems.SWEET_POTATO.getKey());
        tag(ModTags.TOMATO).replace(false).add(ModItems.TOMATO.getKey());
        tag(ModTags.WHITE_RADISH).replace(false).add(ModItems.WHITE_RADISH.getKey());
        tag(ModTags.CABBAGE).replace(false).add(ModItems.CABBAGE.getKey());
        tag(ModTags.GREEN_ONION).replace(false).add(ModItems.GREEN_ONION.getKey());
        tag(ModTags.STRAWBERRY).replace(false).add(ModItems.STRAWBERRY.getKey());
        tag(ModTags.GRAPEFRUIT).replace(false).add(ModItems.GRAPEFRUIT.getKey());
        tag(ModTags.CHERRY).replace(false).add(ModItems.CHERRY.getKey());
        tag(ModTags.COCONUT).replace(false).add(ModItems.COCONUT.getKey());
        tag(ModTags.BANANA).replace(false).add(ModItems.BANANA.getKey());
        tag(ModTags.PEACH).replace(false).add(ModItems.PEACH.getKey());
        tag(ModTags.PERSIMMON).replace(false).add(ModItems.PERSIMMON.getKey());
        tag(ModTags.POMEGRANATE).replace(false).add(ModItems.POMEGRANATE.getKey());
        tag(ModTags.HAWTHORN).replace(false).add(ModItems.HAWTHORN.getKey());
        tag(ModTags.LOQUAT).replace(false).add(ModItems.LOQUAT.getKey());
        tag(ModTags.LEMON).replace(false).add(ModItems.LEMON.getKey());
        tag(ModTags.PAPAYA).replace(false).add(ModItems.PAPAYA.getKey());
        tag(ModTags.LONGAN).replace(false).add(ModItems.LONGAN.getKey());
        tag(ModTags.MANGO).replace(false).add(ModItems.MANGO.getKey());
        tag(ModTags.LITCHI).replace(false).add(ModItems.LITCHI.getKey());
        tag(ModTags.PEAR).replace(false).add(ModItems.PEAR.getKey());
        tag(ModTags.ORANGE).replace(false).add(ModItems.ORANGE.getKey());
        tag(ModTags.DATE).replace(false).add(ModItems.DATE.getKey());

        tag(ForgeTags.BERRIES).replace(false).add(ModItems.STRAWBERRY.getKey());

        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/corn"))).replace(false).add(ModItems.CORN.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/cucumber"))).replace(false).add(ModItems.CUCUMBER.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/eggplant"))).replace(false).add(ModItems.EGGPLANT.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/facing_heaven_pepper"))).replace(false).add(ModItems.FACING_HEAVEN_PEPPER.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/green_pepper"))).replace(false).add(ModItems.GREEN_PEPPER.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/peanut"))).replace(false).add(ModItems.PEANUT.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/rice"))).replace(false).add(ModItems.RICE.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/sticky_rice"))).replace(false).add(ModItems.STICKY_RICE.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/sweet_potato"))).replace(false).add(ModItems.SWEET_POTATO.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/tomato"))).replace(false).add(ModItems.TOMATO.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/white_radish"))).replace(false).add(ModItems.WHITE_RADISH.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/cabbage"))).replace(false).add(ModItems.CABBAGE.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/green_onion"))).replace(false).add(ModItems.GREEN_ONION.getKey());

        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/strawberry"))).replace(false).add(ModItems.STRAWBERRY.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/grapefruit"))).replace(false).add(ModItems.GRAPEFRUIT.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/cherry"))).replace(false).add(ModItems.CHERRY.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/coconut"))).replace(false).add(ModItems.COCONUT.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/banana"))).replace(false).add(ModItems.BANANA.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/peach"))).replace(false).add(ModItems.PEACH.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/persimmon"))).replace(false).add(ModItems.PERSIMMON.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/pomegranate"))).replace(false).add(ModItems.POMEGRANATE.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/hawthorn"))).replace(false).add(ModItems.HAWTHORN.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/loquat"))).replace(false).add(ModItems.LOQUAT.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/lemon"))).replace(false).add(ModItems.LEMON.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/papaya"))).replace(false).add(ModItems.PAPAYA.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/longan"))).replace(false).add(ModItems.LONGAN.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/mango"))).replace(false).add(ModItems.MANGO.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/litchi"))).replace(false).add(ModItems.LITCHI.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/pear"))).replace(false).add(ModItems.PEAR.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/orange"))).replace(false).add(ModItems.ORANGE.getKey());
        tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "crops/date"))).replace(false).add(ModItems.DATE.getKey());

    }
}
