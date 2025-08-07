package xyz.elebe.breakfast.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import xyz.elebe.breakfast.constants.CommandConstants;
import xyz.elebe.breakfast.helpers.PlayerActivationHelper;

public class BreakFastCommand {

    /**
     * Registers the BreakFast command.
     * <p>
     * The command is registered under the name {@value CommandConstants#COMMAND_NAME}.
     * <p>
     * The command has two subcommands: {@value CommandConstants#COMMAND_ACTIVATE_LITERAL} and
     * {@value CommandConstants#COMMAND_DEACTIVATE_LITERAL}. The first subcommand enables Ultra Mining mode, and the second
     * disables it.
     * <p>
     * The command also requires the sender to have at least level 0 permission.
     *
     * @return a {@link LiteralArgumentBuilder} that can be used to register the command
     */
    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(CommandConstants.COMMAND_NAME)
                .requires(s -> s.hasPermission(CommandConstants.PERMISSION_LEVEL_TO_EXECUTE))
                .then(Commands.literal(CommandConstants.COMMAND_ACTIVATE_LITERAL)
                        .executes(ctx -> PlayerActivationHelper.activatePlayer(ctx.getSource().getPlayerOrException())))
                .then(Commands.literal(CommandConstants.COMMAND_DEACTIVATE_LITERAL)
                        .executes(ctx -> PlayerActivationHelper.deactivatePlayer(ctx.getSource().getPlayerOrException())));
    }

}
