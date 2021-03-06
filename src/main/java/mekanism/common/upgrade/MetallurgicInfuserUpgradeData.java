package mekanism.common.upgrade;

import java.util.List;
import mekanism.api.infuse.InfusionStack;
import mekanism.api.inventory.slot.IInventorySlot;
import mekanism.common.base.IRedstoneControl.RedstoneControl;
import mekanism.common.base.ITileComponent;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.InfusionInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import net.minecraft.item.ItemStack;

public class MetallurgicInfuserUpgradeData extends MachineUpgradeData {

    public final InfusionStack stored;
    public final InfusionInventorySlot infusionSlot;

    //Metallurgic Infuser Constructor
    public MetallurgicInfuserUpgradeData(boolean redstone, RedstoneControl controlType, double electricityStored, int operatingTicks, InfusionStack stored,
          InfusionInventorySlot infusionSlot, EnergyInventorySlot energySlot, InputInventorySlot inputSlot, OutputInventorySlot outputSlot, List<ITileComponent> components) {
        super(redstone, controlType, electricityStored, operatingTicks, energySlot, inputSlot, outputSlot, components);
        this.stored = stored;
        this.infusionSlot = infusionSlot;
    }

    //Infusing Factory Constructor
    public MetallurgicInfuserUpgradeData(boolean redstone, RedstoneControl controlType, double electricityStored, int[] progress, InfusionStack stored,
          InfusionInventorySlot infusionSlot, EnergyInventorySlot energySlot, List<IInventorySlot> inputSlots, List<IInventorySlot> outputSlots, boolean sorting,
          ItemStack typeInputStack, ItemStack typeOutputStack, List<ITileComponent> components) {
        super(redstone, controlType, electricityStored, progress, energySlot, inputSlots, outputSlots, sorting, typeInputStack, typeOutputStack, components);
        this.stored = stored;
        this.infusionSlot = infusionSlot;
    }
}