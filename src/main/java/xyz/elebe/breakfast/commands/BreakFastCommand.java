package xyz.elebe.breakfast.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import xyz.elebe.breakfast.helpers.PlayerActivationHelper;

public class BreakFastCommand {

    // Command name, and literals: change this if you want to customize it
    private static final String COMMAND_NAME = "bf";
    private static final String COMMAND_ACTIVATE_LITERAL = "enable";
    private static final String COMMAND_DEACTIVATE_LITERAL = "disable";

    // Messages sent by server: change this if you want to modify the message
    public static final Component COMMAND_ACTIVATION_MESSAGE = Component.literal("🟢 BreakFast mode ACTIVATED");
    public static final Component COMMAND_DEACTIVATION_MESSAGE = Component.literal("🔴 BreakFast mode DEACTIVATED");

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(COMMAND_NAME)
                .requires(s -> s.hasPermission(0))
                .then(Commands.literal(COMMAND_ACTIVATE_LITERAL)
                        .executes(ctx -> PlayerActivationHelper.togglePlayerActivation(ctx.getSource().getPlayerOrException())))
                .then(Commands.literal(COMMAND_DEACTIVATE_LITERAL)
                        .executes(ctx -> PlayerActivationHelper.togglePlayerActivation(ctx.getSource().getPlayerOrException())));
    }
}
