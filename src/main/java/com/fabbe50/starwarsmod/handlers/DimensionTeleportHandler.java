package com.fabbe50.starwarsmod.handlers;

import com.fabbe50.starwarsmod.helpers.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/**
 * Created by fabbe on 09/12/2017 - 3:31 AM.
 */
public class DimensionTeleportHandler {
    @SubscribeEvent
    public void dimensionTeleportEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;

        try {
            if (player.dimension == 0) {
                if (player.posY > 300) {
                    FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, 5381, new WorldTeleporter(player.world.getMinecraftServer().getWorld(5381), player.getPosition()));
                }
            } else if (player.dimension == 5381) {
                if (player.posY < -20) {
                    FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, 0, new WorldTeleporter(player.world.getMinecraftServer().getWorld(0), player.getPosition()));
                }
            }
        } catch (Exception e) {
            List<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
            for (EntityPlayer player1 : players) {
                LogHelper.debug(player1.dimension);
            }
        }
    }
}
