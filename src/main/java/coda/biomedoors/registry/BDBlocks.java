package coda.biomedoors.registry;

import coda.biomedoors.BiomeDoors;
import coda.biomedoors.common.blocks.DoorMachineBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BDBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BiomeDoors.MOD_ID);

    public static final RegistryObject<Block> DOOR_MACHINE = BLOCKS.register("door_machine", () -> new DoorMachineBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(2.5F)));
}
