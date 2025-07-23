package xyz.elebe.breakfast.helpers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;
import xyz.elebe.breakfast.config.BreakFastConfig;

public class BlockDestructionHelper {

    public static void handleBlockDestruction(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        if (!PlayerActivationHelper.isPlayerActivated(player)) {
            return;
        }

        ItemStack heldItem = player.getMainHandItem();

        if (BreakFastConfig.ONLY_WITH_TOOLS.get() &&
                !(heldItem.canPerformAction(ToolActions.PICKAXE_DIG) || heldItem.canPerformAction(ToolActions.SHOVEL_DIG))) {
            return;
        }

        Level level = player.level;
        BlockPos center = event.getPos();
        int maxBlocks = BreakFastConfig.MAX_BLOCKS.get();

        // We control unwanted behaviors with this default fallback
        if (maxBlocks < 1 || maxBlocks > 10) {
            maxBlocks = 3;
        }

        int halfRange = maxBlocks / 2;

        for (int dx = -halfRange; dx <= halfRange; dx++) {
            for (int dz = -halfRange; dz <= halfRange; dz++) {
                BlockPos newPos = center.offset(dx, 0, dz);
                destroyBlock(newPos, center, level);
            }
        }
    }

    private static void destroyBlock(BlockPos newPos, BlockPos center, Level level) {
        if (newPos.equals(center)) {
            return;
        }

        BlockState state = level.getBlockState(newPos);

        if (state.isAir() || state.getDestroySpeed(level, newPos) < 0) {
            return;
        }

        level.destroyBlock(newPos, true);
    }

}
