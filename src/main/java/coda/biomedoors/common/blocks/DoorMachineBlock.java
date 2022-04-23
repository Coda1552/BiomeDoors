package coda.biomedoors.common.blocks;

import coda.biomedoors.BiomeDoors;
import coda.biomedoors.common.BiomeListSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DoorMachineBlock extends Block {

    public DoorMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (!level.isClientSide()) {

            BlockState door = level.getBlockState(pos.above());

            if (door.is(BlockTags.DOORS)) {
                BiomeListSavedData data = ((ServerLevel) level).getDataStorage().computeIfAbsent(BiomeListSavedData::new, () -> new BiomeListSavedData(level), BiomeDoors.MOD_ID + ".savedata");

                ResourceKey<Biome> biome = data.getBiome(door);

                var pair = ((ServerLevel) level).findNearestBiome(b -> b.is(biome), pos, 6400, 8);

                if (pair == null) return;

                entity.teleportToWithTicket(pair.getFirst().getX() + 0.5, pair.getFirst().getY(), pair.getFirst().getZ() + 0.5);
             }
        }
    }
}
