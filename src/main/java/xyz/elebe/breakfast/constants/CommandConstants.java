package xyz.elebe.breakfast.constants;

public class CommandConstants {

    // Command name
    public static final String COMMAND_NAME = "bf";

    // Command literals (options, like true or false)
    public static final String COMMAND_ACTIVATE_LITERAL = "enable";
    public static final String COMMAND_DEACTIVATE_LITERAL = "disable";

    // Chat messages system send player when command is activated
    public final static String PLAYER_OUTPUT_COMMAND_ACTIVATION = "[BreakFast] Ultra mining mode enabled";
    public final static String PLAYER_OUTPUT_COMMAND_DEACTIVATION = "[BreakFast] Ultra mining mode disabled";

    // Determine the permission level required to execute the command
    // Change this to your desired level (i.e. 0-4)
    public static final int PERMISSION_LEVEL_TO_EXECUTE = 0;


    private CommandConstants() {
    }
}
