package xyz.elebe.breakfast.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import xyz.elebe.breakfast.constants.CommandConstants;
import xyz.elebe.breakfast.helpers.PlayerActivationHelper;

public class BreakFastCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(CommandConstants.COMMAND_NAME)
                .requires(s -> s.hasPermission(0))
                .then(Commands.literal(CommandConstants.COMMAND_ACTIVATE_LITERAL)
                        .executes(ctx -> PlayerActivationHelper.togglePlayerActivation(ctx.getSource().getPlayerOrException())))
                .then(Commands.literal(CommandConstants.COMMAND_DEACTIVATE_LITERAL)
                        .executes(ctx -> PlayerActivationHelper.togglePlayerActivation(ctx.getSource().getPlayerOrException())));
    }

}
