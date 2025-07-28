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

    /**
     * Handles block destruction events. If the player is activated, and the event is on the server side,
     * it will destroy all blocks in a 3x3 area around the original block broken, if the player is holding a pickaxe or shovel.
     * If the config option is set, it will only destroy blocks if the player is holding one of these tools.
     * The area destroyed is determined by the config option, with a maximum of 10 blocks and a minimum of 1.
     *
     * @param event the block destruction event
     *
     */
    public static void handleBlockDestruction(BlockEvent.BreakEvent event) {

        if (GenericHelper.isClientSide(event)) {
            return;
        }

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


    /**
     * Given a center position and an offset in the x, y, and z directions,
     * returns the position that is offset from the center by the given
     * amounts in the direction specified by the given direction.
     *
     * @param center the center position
     * @param dx the offset in the x direction
     * @param dy the offset in the y direction
     * @param dz the offset in the z direction
     * @param direction the direction in which to offset
     * @return the offset position
     */
    private static BlockPos getOffsetPos(BlockPos center, int dx, int dy, int dz, Direction direction) {
        return switch (direction.getAxis()) {
            case Y -> center.offset(dx, 0, dz); // XZ
            case Z -> center.offset(dx, dy, 0); // XY
            case X -> center.offset(0, dy, dz); // YZ
            default -> center;
        };
    }

    /**
     * Destroys the block at the given position in the given level.
     *
     * If the block is air or unbreakable, does nothing.
     *
     * @param pos the position of the block to destroy
     * @param level the level in which the block is located
     */
    private static void destroyBlock(BlockPos pos, Level level) {
        BlockState state = level.getBlockState(pos);

        if (state.isAir() || state.getDestroySpeed(level, pos) < 0) {
            return;
        }

        level.destroyBlock(pos, true);
    }
}