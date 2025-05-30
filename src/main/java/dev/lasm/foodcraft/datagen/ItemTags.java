package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.Tags.Items;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(PackOutput pOutput, CompletableFuture<Provider> pLookupProvider,
        CompletableFuture<TagLookup<Block>> pBlockTags, String modId,
        @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull Provider provider) {
        tag(Tags.Items.CROPS).replace(false).add(
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
            ModItems.ADZUKI_BEAN.getKey(),
            ModItems.SOYBEAN.getKey(),
            ModItems.MUNG_BEAN.getKey(),

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
            ModItems.GRAPE.getKey(),
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

        tag(ModTags.VEGETABLES).replace(false).add(
            ModItems.CORN.getKey(),
            ModItems.CUCUMBER.getKey(),
            ModItems.EGGPLANT.getKey(),
            ModItems.FACING_HEAVEN_PEPPER.getKey(),
            ModItems.GREEN_PEPPER.getKey(),
            ModItems.PEANUT.getKey(),
            ModItems.RICE.getKey(),
            ModItems.SWEET_POTATO.getKey(),
            ModItems.TOMATO.getKey(),
            ModItems.WHITE_RADISH.getKey(),
            ModItems.CABBAGE.getKey(),
            ModItems.GREEN_ONION.getKey()
        );

        tag(ModTags.FRUITS).replace(false).add(
            ModItems.STRAWBERRY.getKey(),
            ModItems.GRAPEFRUIT.getKey(),
            ModItems.GRAPE.getKey(),
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

        tag(ModTags.JUICES).replace(false).add(
            ModItems.STRAWBERRY_JUICE.getKey(),
            ModItems.GRAPEFRUIT_JUICE.getKey(),
            ModItems.GRAPE_JUICE.getKey(),
            ModItems.CHERRY_JUICE.getKey(),
            ModItems.COCONUT_JUICE.getKey(),
            ModItems.BANANA_JUICE.getKey(),
            ModItems.PEACH_JUICE.getKey(),
            ModItems.PERSIMMON_JUICE.getKey(),
            ModItems.POMEGRANATE_JUICE.getKey(),
            ModItems.HAWTHORN_JUICE.getKey(),
            ModItems.LOQUAT_JUICE.getKey(),
            ModItems.LEMON_JUICE.getKey(),
            ModItems.PAPAYA_JUICE.getKey(),
            ModItems.LONGAN_JUICE.getKey(),
            ModItems.MANGO_JUICE.getKey(),
            ModItems.LITCHI_JUICE.getKey(),
            ModItems.PEAR_JUICE.getKey(),
            ModItems.ORANGE_JUICE.getKey(),
            ModItems.DATE_JUICE.getKey()
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
        tag(ModTags.ADZUKI_BEAN).replace(false).add(ModItems.ADZUKI_BEAN.getKey());
        tag(ModTags.SOYBEAN).replace(false).add(ModItems.SOYBEAN.getKey());
        tag(ModTags.MUNG_BEAN).replace(false).add(ModItems.MUNG_BEAN.getKey());

        tag(ModTags.STRAWBERRY).replace(false).add(ModItems.STRAWBERRY.getKey());
        tag(ModTags.GRAPEFRUIT).replace(false).add(ModItems.GRAPEFRUIT.getKey());
        tag(ModTags.GRAPE).replace(false).add(ModItems.GRAPE.getKey());
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

        tag(Items.FOODS_BERRY).replace(false).add(ModItems.STRAWBERRY.getKey());

        tag(ModTags.commonItemTag("crops/corn")).replace(false).add(ModItems.CORN.getKey());
        tag(ModTags.commonItemTag("crops/cucumber")).replace(false).add(ModItems.CUCUMBER.getKey());
        tag(ModTags.commonItemTag("crops/eggplant")).replace(false).add(ModItems.EGGPLANT.getKey());
        tag(ModTags.commonItemTag("crops/facing_heaven_pepper")).replace(false).add(ModItems.FACING_HEAVEN_PEPPER.getKey());
        tag(ModTags.commonItemTag("crops/green_pepper")).replace(false).add(ModItems.GREEN_PEPPER.getKey());
        tag(ModTags.commonItemTag("crops/peanut")).replace(false).add(ModItems.PEANUT.getKey());
        tag(ModTags.commonItemTag("crops/rice")).replace(false).add(ModItems.RICE.getKey());
        tag(ModTags.commonItemTag("crops/sticky_rice")).replace(false).add(ModItems.STICKY_RICE.getKey());
        tag(ModTags.commonItemTag("crops/sweet_potato")).replace(false).add(ModItems.SWEET_POTATO.getKey());
        tag(ModTags.commonItemTag("crops/tomato")).replace(false).add(ModItems.TOMATO.getKey());
        tag(ModTags.commonItemTag("crops/white_radish")).replace(false).add(ModItems.WHITE_RADISH.getKey());
        tag(ModTags.commonItemTag("crops/cabbage")).replace(false).add(ModItems.CABBAGE.getKey());
        tag(ModTags.commonItemTag("crops/green_onion")).replace(false).add(ModItems.GREEN_ONION.getKey());

        tag(ModTags.commonItemTag("crops/strawberry")).replace(false).add(ModItems.STRAWBERRY.getKey());
        tag(ModTags.commonItemTag("crops/grapefruit")).replace(false).add(ModItems.GRAPEFRUIT.getKey());
        tag(ModTags.commonItemTag("crops/cherry")).replace(false).add(ModItems.CHERRY.getKey());
        tag(ModTags.commonItemTag("crops/coconut")).replace(false).add(ModItems.COCONUT.getKey());
        tag(ModTags.commonItemTag("crops/banana")).replace(false).add(ModItems.BANANA.getKey());
        tag(ModTags.commonItemTag("crops/peach")).replace(false).add(ModItems.PEACH.getKey());
        tag(ModTags.commonItemTag("crops/persimmon")).replace(false).add(ModItems.PERSIMMON.getKey());
        tag(ModTags.commonItemTag("crops/pomegranate")).replace(false).add(ModItems.POMEGRANATE.getKey());
        tag(ModTags.commonItemTag("crops/hawthorn")).replace(false).add(ModItems.HAWTHORN.getKey());
        tag(ModTags.commonItemTag("crops/loquat")).replace(false).add(ModItems.LOQUAT.getKey());
        tag(ModTags.commonItemTag("crops/lemon")).replace(false).add(ModItems.LEMON.getKey());
        tag(ModTags.commonItemTag("crops/papaya")).replace(false).add(ModItems.PAPAYA.getKey());
        tag(ModTags.commonItemTag("crops/longan")).replace(false).add(ModItems.LONGAN.getKey());
        tag(ModTags.commonItemTag("crops/mango")).replace(false).add(ModItems.MANGO.getKey());
        tag(ModTags.commonItemTag("crops/litchi")).replace(false).add(ModItems.LITCHI.getKey());
        tag(ModTags.commonItemTag("crops/pear")).replace(false).add(ModItems.PEAR.getKey());
        tag(ModTags.commonItemTag("crops/orange")).replace(false).add(ModItems.ORANGE.getKey());
        tag(ModTags.commonItemTag("crops/date")).replace(false).add(ModItems.DATE.getKey());
        tag(ModTags.commonItemTag("crops/grape")).replace(false).add(ModItems.GRAPE.getKey());

        // 为 STRAWBERRY_JUICE 添加 Tag (strawberry_juice -> strawberryjuice)
        tag(ModTags.commonItemTag("juices/strawberryjuice")).replace(false).add(ModItems.STRAWBERRY_JUICE.getKey());

// 为 GRAPEFRUIT_JUICE 添加 Tag (grapefruit_juice -> grapefruitjuice)
        tag(ModTags.commonItemTag("juices/grapefruitjuice")).replace(false).add(ModItems.GRAPEFRUIT_JUICE.getKey());

// 为 GRAPE_JUICE 添加 Tag (grape_juice -> grapejuice)
        tag(ModTags.commonItemTag("juices/grapejuice")).replace(false).add(ModItems.GRAPE_JUICE.getKey());

// 为 CHERRY_JUICE 添加 Tag (cherry_juice -> cherryjuice)
        tag(ModTags.commonItemTag("juices/cherryjuice")).replace(false).add(ModItems.CHERRY_JUICE.getKey());

// 为 COCONUT_JUICE 添加 Tag (coconut_juice -> coconutjuice)
        tag(ModTags.commonItemTag("juices/coconutjuice")).replace(false).add(ModItems.COCONUT_JUICE.getKey());

// 为 BANANA_JUICE 添加 Tag (banana_juice -> bananajuice)
        tag(ModTags.commonItemTag("juices/bananajuice")).replace(false).add(ModItems.BANANA_JUICE.getKey());

// 为 PEACH_JUICE 添加 Tag (peach_juice -> peachjuice)
        tag(ModTags.commonItemTag("juices/peachjuice")).replace(false).add(ModItems.PEACH_JUICE.getKey());

// 为 PERSIMMON_JUICE 添加 Tag (persimmon_juice -> persimmonjuice)
        tag(ModTags.commonItemTag("juices/persimmonjuice")).replace(false).add(ModItems.PERSIMMON_JUICE.getKey());

// 为 POMEGRANATE_JUICE 添加 Tag (pomegranate_juice -> pomegranatejuice)
        tag(ModTags.commonItemTag("juices/pomegranatejuice")).replace(false).add(ModItems.POMEGRANATE_JUICE.getKey());

// 为 HAWTHORN_JUICE 添加 Tag (hawthorn_juice -> hawthornjuice)
        tag(ModTags.commonItemTag("juices/hawthornjuice")).replace(false).add(ModItems.HAWTHORN_JUICE.getKey());

// 为 LOQUAT_JUICE 添加 Tag (loquat_juice -> loquatjuice)
        tag(ModTags.commonItemTag("juices/loquatjuice")).replace(false).add(ModItems.LOQUAT_JUICE.getKey());

// 为 LEMON_JUICE 添加 Tag (lemon_juice -> lemonjuice)
        tag(ModTags.commonItemTag("juices/lemonjuice")).replace(false).add(ModItems.LEMON_JUICE.getKey());

// 为 PAPAYA_JUICE 添加 Tag (papaya_juice -> papayajuice)
        tag(ModTags.commonItemTag("juices/papayajuice")).replace(false).add(ModItems.PAPAYA_JUICE.getKey());

// 为 LONGAN_JUICE 添加 Tag (longan_juice -> longanjuice)
        tag(ModTags.commonItemTag("juices/longanjuice")).replace(false).add(ModItems.LONGAN_JUICE.getKey()); // 修正：这里应该是 LONGAN_JUICE

// 为 MANGO_JUICE 添加 Tag (mango_juice -> mangojuice)
        tag(ModTags.commonItemTag("juices/mangojuice")).replace(false).add(ModItems.MANGO_JUICE.getKey());

// 为 LITCHI_JUICE 添加 Tag (litchi_juice -> litchijuice)
        tag(ModTags.commonItemTag("juices/litchijuice")).replace(false).add(ModItems.LITCHI_JUICE.getKey());

// 为 PEAR_JUICE 添加 Tag (pear_juice -> pearjuice)
        tag(ModTags.commonItemTag("juices/pearjuice")).replace(false).add(ModItems.PEAR_JUICE.getKey());

// 为 ORANGE_JUICE 添加 Tag (orange_juice -> orangejuice)
        tag(ModTags.commonItemTag("juices/orangejuice")).replace(false).add(ModItems.ORANGE_JUICE.getKey());

// 为 DATE_JUICE 添加 Tag (date_juice -> datejuice)
        tag(ModTags.commonItemTag("juices/datejuice")).replace(false).add(ModItems.DATE_JUICE.getKey());

        tag(net.minecraft.tags.ItemTags.SAPLINGS).replace(false).add(
            ModItems.COCONUT_SAPLING.getKey(),
            ModItems.GRAPEFRUIT_SAPLING.getKey(),
            ModItems.CHERRY_SAPLING.getKey(),
            ModItems.BANANA_SAPLING.getKey(),
            ModItems.PEACH_SAPLING.getKey(),
            ModItems.PERSIMMON_SAPLING.getKey(),
            ModItems.POMEGRANATE_SAPLING.getKey(),
            ModItems.HAWTHORN_SAPLING.getKey(),
            ModItems.LOQUAT_SAPLING.getKey(),
            ModItems.LEMON_SAPLING.getKey(),
            ModItems.PAPAYA_SAPLING.getKey(),
            ModItems.LONGAN_SAPLING.getKey(),
            ModItems.MANGO_SAPLING.getKey(),
            ModItems.LITCHI_SAPLING.getKey(),
            ModItems.PEAR_SAPLING.getKey(),
            ModItems.ORANGE_SAPLING.getKey(),
            ModItems.DATE_SAPLING.getKey()
        );
    }
}
