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

    /**
     * Called when commands are registered.
     * <p>
     * Registers the mod's command using the given command dispatcher.
     *
     * @param event the event fired when commands are registered
     */
    @SubscribeEvent
    public void onCommandRegister(RegisterCommandsEvent event) {
        event.getDispatcher().register(BreakFastCommand.getCommand());
    }

    /**
     * Called when a player logs in.
     * <p>
     * Deactivates Ultra Mining mode for the player.
     *
     * @param event the event fired when a player logs in
     */
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerActivationHelper.deactivatePlayer(event.getEntity());
    }

    /**
     * Called when a player logs out.
     * <p>
     * Deactivates Ultra Mining mode for the player.
     *
     * @param event the event fired when a player logs out
     */
    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerActivationHelper.deactivatePlayer(event.getEntity());
    }

    /**
     * Called when a player breaks a block.
     * <p>
     * If the player has Ultra Mining enabled, this method will destroy all blocks
     * in a 3x3 area centered at the block that was broken.
     *
     * @param event the event fired when a player breaks a block
     */
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockDestructionHelper.handleBlockDestruction(event);
    }

    /**
     * Registers the event bus for the mod and configures the common TOML
     * configuration file.
     */
    private void setup() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                BreakFastConfig.SPEC,
                "breakfastmod-common.toml"
        );
    }
}
