package coda.biomedoors.common;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BiomeListSavedData extends SavedData {
    private Map<Block, ResourceKey<Biome>> map;

    public BiomeListSavedData(Level level) {
        var biomeRegistry = level.getServer().registryAccess().ownedRegistry(Registry.BIOME_REGISTRY);

        biomeRegistry.ifPresent(a -> {
            List<ResourceKey<Biome>> biomeList = new ArrayList<>();

            for (var biome : a) {
                var biomeKey = a.getResourceKey(biome).get();

                if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OVERWORLD)) {
                    biomeList.add(biomeKey);
                }
            }

            map = ForgeRegistries.BLOCKS.getEntries().stream()
                    .filter(b -> b.getValue().builtInRegistryHolder().is(BlockTags.DOORS))
                    .collect(Collectors.toMap(Map.Entry::getValue, d -> biomeList.remove(level.random.nextInt(biomeList.size()))));
        });
    }

    public BiomeListSavedData(CompoundTag tag) {
        if (tag.isEmpty()) return;

        map = new HashMap<>();

        for (String key : tag.getAllKeys()) {
            map.put(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(key)), ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(tag.getString(key))));
        }
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        if (map != null) {
            for (var entry : map.entrySet()) {
                tag.putString(entry.getKey().getRegistryName().toString(), entry.getValue().getRegistryName().toString());
            }
        }

        return tag;
    }

    public ResourceKey<Biome> getBiome(BlockState door) {
        return map == null ? null : map.get(door.getBlock());
    }
}
