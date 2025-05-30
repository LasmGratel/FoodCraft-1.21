package dev.lasm.foodcraft;

import com.mojang.logging.LogUtils;
import dev.lasm.foodcraft.client.screen.BeverageMakingScreen;
import dev.lasm.foodcraft.client.screen.BrewBarrelScreen;
import dev.lasm.foodcraft.client.screen.ChoppingBoardScreen;
import dev.lasm.foodcraft.client.screen.FryingPanScreen;
import dev.lasm.foodcraft.client.screen.MillScreen;
import dev.lasm.foodcraft.client.screen.PanScreen;
import dev.lasm.foodcraft.client.screen.PotScreen;
import dev.lasm.foodcraft.client.screen.PressureCookerScreen;
import dev.lasm.foodcraft.client.screen.StoveScreen;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModFeatures;
import dev.lasm.foodcraft.init.ModFluids;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModMenuTypes;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities.FluidHandler;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

@Mod(FoodCraft.MOD_ID)
public final class FoodCraft {
    public static final String MOD_ID = "foodcraft";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = TABS.register("main", () ->
            CreativeModeTab.builder().title(Component.translatable("itemGroup." + MOD_ID + ".main"))
                .displayItems(FoodCraft::addCreative)
                .icon(() -> new ItemStack(ModItems.STEAMED_FISH.get()))
                .build());

    public FoodCraft(IEventBus modEventBus, ModContainer modContainer) {
        NeoForgeMod.enableMilkFluid();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerScreens);
        modEventBus.addListener(this::registerFluidTextures);
        modEventBus.addListener(this::registerCapabilities);

        ModFluids.FLUID_TYPES.register(modEventBus);
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
        ModFeatures.FOLIAGE_PLACER_TYPES.register(modEventBus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        modContainer.registerConfig(Type.COMMON, Config.SPEC);
    }

    private static void addCreative(ItemDisplayParameters p, Output o) {
        o.acceptAll(ModItems.ITEMS.getEntries().stream().map(x -> new ItemStack(x.get()))
                .toList());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("");
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(FluidHandler.BLOCK, ModBlockEntityTypes.BEVERAGE_MAKING.get(), (be, side) -> be.fluidTank);
        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.BEVERAGE_MAKING.get(), (be, side) -> switch (side) {
            case DOWN -> new RangedWrapper(be.inventory, 4, 5);
            case UP -> new RangedWrapper(be.inventory, 0, 4);
            case null, default -> null;
        });

        event.registerBlockEntity(FluidHandler.BLOCK, ModBlockEntityTypes.BREW_BARREL.get(), (be, side) -> be.fluidTank);
        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.BREW_BARREL.get(), (be, side) -> switch (side) {
            case DOWN -> new RangedWrapper(be.inventory, 4, 5);
            case UP -> new RangedWrapper(be.inventory, 1, 4);
            case null, default -> null;
        });

        event.registerBlockEntity(FluidHandler.BLOCK, ModBlockEntityTypes.FRYING_PAN.get(), (be, side) -> be.fluidTank);
        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.FRYING_PAN.get(), (be, side) -> switch (side) {
            case DOWN -> new RangedWrapper(be.inventory, 3, 4);
            case UP -> new RangedWrapper(be.inventory, 2, 3);
            case null, default -> null;
        });

        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.MILL.get(), (be, side) -> switch (side) {
            case DOWN -> new RangedWrapper(be.inventory, 2, 3);
            case UP -> new RangedWrapper(be.inventory, 1, 2);
            case null -> null;
            default -> new RangedWrapper(be.inventory, 0, 1);
        });

        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.PAN.get(), (be, side) -> switch (side) {
            case DOWN -> new RangedWrapper(be.inventory, 2, 3);
            case UP -> new RangedWrapper(be.inventory, 0, 2);
            case null, default -> null;
        });

        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.POT.get(), (be, side) -> switch (side) {
            case DOWN -> new RangedWrapper(be.inventory, 12, 14);
            case UP -> new RangedWrapper(be.inventory, 0, 12);
            case null, default -> null;
        });

        event.registerBlockEntity(FluidHandler.BLOCK, ModBlockEntityTypes.PRESSURE_COOKER.get(), (be, side) -> be.fluidTank);
        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.PRESSURE_COOKER.get(), (be, side) -> switch (side) {
            case DOWN -> new RangedWrapper(be.inventory, 3, 4);
            case UP -> new RangedWrapper(be.inventory, 0, 3);
            case null, default -> null;
        });

        event.registerBlockEntity(ItemHandler.BLOCK, ModBlockEntityTypes.STOVE.get(), (be, side) -> be.inventory);
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.BREW_BARREL.get(), BrewBarrelScreen::new);
        event.register(ModMenuTypes.FRYING_PAN.get(), FryingPanScreen::new);
        event.register(ModMenuTypes.POT.get(), PotScreen::new);
        event.register(ModMenuTypes.PAN.get(), PanScreen::new);
        event.register(ModMenuTypes.CHOPPING_BOARD.get(), ChoppingBoardScreen::new);
        event.register(ModMenuTypes.MILL.get(), MillScreen::new);
        event.register(ModMenuTypes.PRESSURE_COOKER.get(), PressureCookerScreen::new);
        event.register(ModMenuTypes.STOVE.get(), StoveScreen::new);
        event.register(ModMenuTypes.BEVERAGE_MAKING.get(), BeverageMakingScreen::new);
    }

    private void registerFluidTextures(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/cooking_oil_still");
            private static final ResourceLocation FLOW = ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/cooking_oil_flow");

            @Override
            public ResourceLocation getStillTexture() {
                return STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return FLOW;
            }

            @Override
            public @Nullable ResourceLocation getOverlayTexture() {
                return STILL;
            }
        }, ModFluids.COOKING_OIL_TYPE.get());
    }

}
