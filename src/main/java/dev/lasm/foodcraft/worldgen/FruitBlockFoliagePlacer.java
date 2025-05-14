package dev.lasm.foodcraft.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public abstract class FruitBlockFoliagePlacer extends BlobFoliagePlacer {
    private final BlockState fruit;

    public FruitBlockFoliagePlacer(BlockState fruit) {
        super(ConstantInt.of(2), ConstantInt.of(0), 4);
        this.fruit = fruit;
    }

    @Override
    protected abstract FoliagePlacerType<?> type();

    protected void createFoliage(LevelSimulatedReader p_273066_, FoliagePlacer.FoliageSetter foliageSetter, RandomSource p_273178_, TreeConfiguration p_272850_, int p_273067_, FoliagePlacer.FoliageAttachment p_273711_, int p_273580_, int p_273511_, int p_273685_) {
        for(int i = p_273685_; i >= p_273685_ - p_273580_; --i) {
            int j = Math.max(p_273511_ + p_273711_.radiusOffset() - 1 - i / 2, 0);
            this.placeLeavesRow(p_273066_, foliageSetter, p_273178_, p_272850_, p_273711_.pos(), j - 1, i + 1, p_273711_.doubleTrunk());
            this.placeFruitRow(p_273066_, foliageSetter, p_273178_, p_272850_, p_273711_.pos(), 1, p_273685_ - 4, p_273711_.doubleTrunk());
        }

    }

    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return 4;
    }

    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }

    protected void placeFruitRow(LevelSimulatedReader pLevel, FoliagePlacer.FoliageSetter foliageSetter, RandomSource randomsource, TreeConfiguration treeconfig, BlockPos pos, int p_161443_, int p_161444_, boolean p_161445_) {
        int i = p_161445_ ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int j = -p_161443_; j <= p_161443_ + i; ++j) {
            for(int k = -p_161443_; k <= p_161443_ + i; ++k) {
                if (!this.shouldSkipLocationSigned(randomsource, j, p_161444_, k, p_161443_, p_161445_)) {
                    blockpos$mutableblockpos.setWithOffset(pos, j, p_161444_, k);
                    this.tryPlaceFruit(pLevel, foliageSetter, randomsource, treeconfig, blockpos$mutableblockpos.setWithOffset(pos, j, p_161444_, k));
                }
            }
        }

    }

    protected static boolean tryPlaceLeaf(LevelSimulatedReader pLevel, FoliagePlacer.FoliageSetter foliageSetter, RandomSource randomsource, TreeConfiguration treeconfig, BlockPos pos) {
        if (validTreePos(pLevel, pos)) {
            foliageSetter.set(pos, treeconfig.foliageProvider.getState(randomsource, pos));
            return true;
        } else {
            return false;
        }
    }

    protected void tryPlaceFruit(LevelSimulatedReader pLevel, FoliagePlacer.FoliageSetter foliageSetter, RandomSource randomsource, TreeConfiguration treeconfig, BlockPos pos) {
        if (validTreePos(pLevel, pos)) {
            foliageSetter.set(pos, fruit);
        }

    }

    public static boolean validTreePos(LevelSimulatedReader pLevel, BlockPos pos) {
        return pLevel.isStateAtPosition(pos, (state) -> {
            return !state.hasProperty(LeavesBlock.PERSISTENT) || !(Boolean)state.getValue(LeavesBlock.PERSISTENT);
        }) && TreeFeature.validTreePos(pLevel, pos);
    }
}
