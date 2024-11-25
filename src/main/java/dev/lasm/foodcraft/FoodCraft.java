package dev.lasm.foodcraft;

import com.mojang.logging.LogUtils;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import java.util.Objects;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(FoodCraft.MOD_ID)
public final class FoodCraft {
    public static final String MOD_ID = "foodcraft";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = TABS.register("main", () ->
            CreativeModeTab.builder().title(Component.translatable("itemGroup." + MOD_ID + ".main"))
                .displayItems((p, o) -> o.accept(ModItems.RICE_PORRIDGE.get()))
                .build());

    public FoodCraft() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        TABS.register(modEventBus);

        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (Objects.equals(event.getTabKey(), CREATIVE_TAB.getKey())) {
            event.acceptAll(ModItems.ITEMS.getEntries().stream().map(x -> new ItemStack(x.get()))
                .toList());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("");
    }


}
