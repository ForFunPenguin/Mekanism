package mekanism.client.gui;

import mekanism.api.recipes.ItemStackGasToItemStackRecipe;
import mekanism.client.gui.element.GuiProgress.ProgressBar;
import mekanism.common.tile.prefab.TileEntityAdvancedElectricMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPurificationChamber extends GuiAdvancedElectricMachine<ItemStackGasToItemStackRecipe> {

    public GuiPurificationChamber(InventoryPlayer inventory, TileEntityAdvancedElectricMachine<ItemStackGasToItemStackRecipe> tile) {
        super(inventory, tile);
    }

    @Override
    public ProgressBar getProgressType() {
        return ProgressBar.RED;
    }
}