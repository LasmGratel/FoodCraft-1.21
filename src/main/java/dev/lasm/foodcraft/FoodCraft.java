package dev.lasm.foodcraft;

import com.mojang.logging.LogUtils;
import dev.lasm.foodcraft.client.screen.BrewBarrelScreen;
import dev.lasm.foodcraft.client.screen.FryingPanScreen;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModFluids;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModMenuTypes;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(FoodCraft.MOD_ID)
public final class FoodCraft {
    public static final String MOD_ID = "foodcraft";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = TABS.register("main", () ->
            CreativeModeTab.builder().title(Component.translatable("itemGroup." + MOD_ID + ".main"))
                .displayItems(FoodCraft::addCreative)
                .build());

    public FoodCraft(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerScreens);

        ModFluids.FLUIDS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        TABS.register(modEventBus);

//        NeoForge.EVENT_BUS.register(this);

        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private static void addCreative(ItemDisplayParameters p, Output o) {
        o.acceptAll(ModItems.ITEMS.getEntries().stream().map(x -> new ItemStack(x.get()))
                .toList());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("");
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.BREW_BARREL.get(), BrewBarrelScreen::new);
        event.register(ModMenuTypes.FRYING_PAN.get(), FryingPanScreen::new);
    }


}
