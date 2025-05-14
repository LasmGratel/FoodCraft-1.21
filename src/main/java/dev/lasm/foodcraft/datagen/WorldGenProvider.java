package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.init.ModFeatures;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER;

    public WorldGenProvider(
        PackOutput output, CompletableFuture<Provider> registries, Set<String> modIds) {
        super(output, registries, BUILDER, modIds);
    }

    static {
        BUILDER = (new RegistrySetBuilder()).add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrap).add(Keys.BIOME_MODIFIERS, BiomeModifiers::bootstrap);
    }
}
