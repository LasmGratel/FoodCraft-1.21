package dev.lasm.foodcraft.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.darkhax.bookshelf.common.api.data.codecs.map.MapCodecs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ModCropBlock extends CropBlock {
    public final MapCodec<ModCropBlock> codec = RecordCodecBuilder.mapCodec(builder ->
        builder.group(propertiesCodec()).and(MapCodecs.ITEM.get("seedItem", ModCropBlock::getSeedItem))
            .apply(builder, ModCropBlock::new)
    );

    private final Holder<Item> seedItem;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
        Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
        Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
        Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
        Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
        Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
        Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
        Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
        Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
    };

    public ModCropBlock(Properties properties, Holder<Item> seedItem) {
        super(properties);
        this.seedItem = seedItem;
    }

    public Holder<Item> getSeedItem() {
        return seedItem;
    }

    @Override
    public @NotNull MapCodec<ModCropBlock> codec() {
        return codec;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return seedItem.value();
    }

    @Override
    protected @NotNull VoxelShape getShape(
        @NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }
}
