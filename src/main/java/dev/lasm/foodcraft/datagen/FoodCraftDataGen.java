package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.FoodCraft;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = FoodCraft.MOD_ID, bus = Bus.MOD)
public final class FoodCraftDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        var blockTags = new BlockTags(output, lookupProvider, FoodCraft.MOD_ID, helper);
        event.getGenerator().addProvider(event.includeServer(), blockTags);
        event.getGenerator().addProvider(
            // Tell generator to run only when server data are generating
            event.includeServer(),
            // Extends net.minecraftforge.common.data.BlockTagsProvider
            new ItemTags(generator.getPackOutput(),
                event.getLookupProvider(),
                blockTags.contentsGetter(),
                FoodCraft.MOD_ID,
                helper
            )
        );
        var blockStateGen = new BlockStateGen(output, FoodCraft.MOD_ID, helper);
        event.getGenerator().addProvider(event.includeClient(), blockStateGen);
        event.getGenerator().addProvider(event.includeServer(), new WorldGenProvider(output, lookupProvider, Set.of("foodcraft")));
        event.getGenerator().addProvider(event.includeClient(), new ItemModelGen(output, FoodCraft.MOD_ID, helper));
        //event.getGenerator().addProvider(event.includeClient(), new LootTableProvider(output, lookupProvider));
    }
}
