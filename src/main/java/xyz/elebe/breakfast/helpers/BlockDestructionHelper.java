package xyz.elebe.breakfast.helpers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
        Direction direction = player.getDirection();

        int maxBlocks = BreakFastConfig.MAX_BLOCKS.get();
        if (maxBlocks < 1 || maxBlocks > 10) {
            maxBlocks = 3;
        }

        int half = maxBlocks / 2;

        for (int dx = -half; dx <= half; dx++) {
            for (int dy = -half; dy <= half; dy++) {
                for (int dz = -half; dz <= half; dz++) {
                    BlockPos offsetPos = getOffsetPos(center, dx, dy, dz, direction);
                    if (!offsetPos.equals(center)) {
                        destroyBlock(offsetPos, level);
                    }
                }
            }
        }
    }

    private static BlockPos getOffsetPos(BlockPos center, int dx, int dy, int dz, Direction direction) {
        return switch (direction.getAxis()) {
            case Y -> center.offset(dx, 0, dz); // XZ
            case Z -> center.offset(dx, dy, 0); // XY
            case X -> center.offset(0, dy, dz); // YZ
            default -> center;
        };
    }

    private static void destroyBlock(BlockPos pos, Level level) {
        BlockState state = level.getBlockState(pos);

        if (state.isAir() || state.getDestroySpeed(level, pos) < 0) {
            return;
        }

        level.destroyBlock(pos, true);
    }
}