package com.mrbysco.candyworld.block.workbench;

import com.mrbysco.candyworld.interfaces.IWorkbenchBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

import javax.annotation.ParametersAreNonnullByDefault;

public class CustomWorkbenchContainer extends CraftingMenu {

    public CustomWorkbenchContainer(int id, Inventory playerInventory, ContainerLevelAccess worldPosCallable) {
        super(id, playerInventory, worldPosCallable);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean stillValid(Player playerIn) {
        return isWithinUsableDistance(this.access, playerIn);
    }

    protected boolean isWithinUsableDistance(ContainerLevelAccess worldPos, Player playerIn) {
        return worldPos.evaluate((world, pos) -> {
            return !(world.getBlockState(pos).getBlock() instanceof IWorkbenchBlock) ? false : playerIn.distanceToSqr((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }
}
