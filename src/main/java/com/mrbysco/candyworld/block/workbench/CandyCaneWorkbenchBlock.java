package com.mrbysco.candyworld.block.workbench;

import com.mrbysco.candyworld.block.StackableBlock;
import com.mrbysco.candyworld.interfaces.IWorkbenchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class CandyCaneWorkbenchBlock extends StackableBlock implements IWorkbenchBlock {
    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.crafting");

    public CandyCaneWorkbenchBlock(Properties properties) {
        super(properties, false, false);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(worldIn, pos));
            player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return ActionResultType.CONSUME;
        }
    }

    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> {
            return new CustomWorkbenchContainer(id, inventory, IWorldPosCallable.create(worldIn, pos));
        }, CONTAINER_NAME);
    }
}
