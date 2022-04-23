package coda.biomedoors;

import coda.biomedoors.registry.BDBlocks;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BiomeDoors.MOD_ID)
public class BiomeDoors {
    public static final String MOD_ID = "biomedoors";

    public BiomeDoors() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BDBlocks.BLOCKS.register(bus);

        bus.addListener(this::registerCapabilities);
    }


    private void registerCapabilities(RegisterCapabilitiesEvent event) {

    }
}
