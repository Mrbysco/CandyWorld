package com.mrbysco.candyworld.block.workbench;

import com.mrbysco.candyworld.interfaces.IWorkbenchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.ParametersAreNonnullByDefault;

public class CustomWorkbenchContainer extends WorkbenchContainer {

	public CustomWorkbenchContainer(int id, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
		super(id, playerInventory, worldPosCallable);
	}

	@Override
	@ParametersAreNonnullByDefault
	public boolean stillValid(PlayerEntity playerIn) {
		return isWithinUsableDistance(this.access, playerIn);
	}

	protected boolean isWithinUsableDistance(IWorldPosCallable worldPos, PlayerEntity playerIn) {
		return worldPos.evaluate((world, pos) -> {
			return !(world.getBlockState(pos).getBlock() instanceof IWorkbenchBlock) ? false : playerIn.distanceToSqr((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D;
		}, true);
	}
}
