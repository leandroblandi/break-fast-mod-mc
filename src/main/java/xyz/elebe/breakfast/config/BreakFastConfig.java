package xyz.elebe.breakfast.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BreakFastConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ONLY_WITH_TOOLS;
    public static final ForgeConfigSpec.IntValue MAX_BLOCKS;

    static {
        BUILDER.push("general");

        ONLY_WITH_TOOLS = BUILDER
                .comment("Allow player break 3x3 block only with tools (shovel, pickaxe)")
                .define("onlyWithTools", true);

        MAX_BLOCKS = BUILDER
                .comment("Max range block player can break")
                .defineInRange("maxBlocks", 3, 1, 10);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
