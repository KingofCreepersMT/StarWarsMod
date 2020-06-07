package com.multiteam.starwarsmod;

import com.multiteam.starwarsmod.proxy.ClientProxy;
import com.multiteam.starwarsmod.proxy.IProxy;
import com.multiteam.starwarsmod.proxy.ServerProxy;
import com.multiteam.starwarsmod.registry.BlockRegistry;
import com.multiteam.starwarsmod.registry.ContainerRegistry;
import com.multiteam.starwarsmod.registry.EntityRegistry;
import com.multiteam.starwarsmod.registry.RegistryHandler;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class StarWarsMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public StarWarsMod() {
        RegistryHandler.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {

    }

    private void setupClient(final FMLClientSetupEvent event) {
        BlockRegistry.fixRenders();
        EntityRegistry.registerRenderers();
        ContainerRegistry.registerScreens();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
