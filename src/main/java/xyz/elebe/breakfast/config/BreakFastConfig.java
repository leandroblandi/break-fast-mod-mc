package xyz.elebe.breakfast.config;

import net.minecraftforge.common.ForgeConfigSpec;
import xyz.elebe.breakfast.constants.ConfigConstants;

public class BreakFastConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ONLY_WITH_TOOLS;
    public static final ForgeConfigSpec.IntValue MAX_BLOCKS;

    static {
        // Under general configuration
        BUILDER.push("general");

        // Config option to allow player break blocks with tools or not
        ONLY_WITH_TOOLS = BUILDER
                .comment("Allow player break 3x3 block only with tools (shovel, pickaxe)")
                .define("onlyWithTools", ConfigConstants.BREAK_BLOCKS_WITH_TOOLS_DEFAULT_VALUE);

        // Config option to parametrize the maximum number of blocks to break
        MAX_BLOCKS = BUILDER
                .comment("Max range block player can break")
                .defineInRange("maxBlocks",
                        ConfigConstants.BREAK_BLOCK_DEFAULT_VALUE,
                        ConfigConstants.BREAK_BLOCK_MIN_VALUE,
                        ConfigConstants.BREAK_BLOCK_MAX_VALUE);

        // Build the config
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private BreakFastConfig() {

    }
}
