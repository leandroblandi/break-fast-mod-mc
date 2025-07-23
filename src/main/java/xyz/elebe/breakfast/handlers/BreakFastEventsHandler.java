package xyz.elebe.breakfast.handlers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.elebe.breakfast.commands.BreakFastCommand;
import xyz.elebe.breakfast.config.BreakFastConfig;
import xyz.elebe.breakfast.helpers.BlockDestructionHelper;
import xyz.elebe.breakfast.helpers.GenericHelper;
import xyz.elebe.breakfast.helpers.PlayerActivationHelper;

@Mod(BreakFastEventsHandler.MOD_ID)
public class BreakFastEventsHandler {
    public static final String MOD_ID = "breakfastmod";

    public BreakFastEventsHandler() {
        setup();
    }

    @SubscribeEvent
    public void onCommandRegister(RegisterCommandsEvent event) {
        event.getDispatcher().register(BreakFastCommand.getCommand());
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerActivationHelper.deactivatePlayer(event.getEntity());
    }

    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerActivationHelper.deactivatePlayer(event.getEntity());
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (GenericHelper.isClientSide(event)) {
            return;
        }
        BlockDestructionHelper.handleBlockDestruction(event);
    }

    private void setup() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                BreakFastConfig.SPEC,
                "breakfastmod-common.toml"
        );
    }
}
