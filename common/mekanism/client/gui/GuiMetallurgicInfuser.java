package mekanism.client.gui;

import java.util.ArrayList;
import java.util.List;

import mekanism.api.Coord4D;
import mekanism.api.ListUtils;
import mekanism.client.gui.GuiEnergyInfo.IInfoHandler;
import mekanism.common.PacketHandler;
import mekanism.common.PacketHandler.Transmission;
import mekanism.common.inventory.container.ContainerMetallurgicInfuser;
import mekanism.common.network.PacketTileEntity;
import mekanism.common.tile.TileEntityMetallurgicInfuser;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMetallurgicInfuser extends GuiMekanism
{
	public TileEntityMetallurgicInfuser tileEntity;
	
	public GuiMetallurgicInfuser(InventoryPlayer inventory, TileEntityMetallurgicInfuser tentity)
    {
        super(tentity, new ContainerMetallurgicInfuser(inventory, tentity));
        tileEntity = tentity;
        
        guiElements.add(new GuiRedstoneControl(this, tileEntity, MekanismUtils.getResource(ResourceType.GUI, "GuiMetallurgicInfuser.png")));
        guiElements.add(new GuiUpgradeManagement(this, tileEntity, MekanismUtils.getResource(ResourceType.GUI, "GuiMetallurgicInfuser.png")));
        guiElements.add(new GuiConfigurationTab(this, tileEntity, MekanismUtils.getResource(ResourceType.GUI, "GuiMetallurgicInfuser.png")));
        guiElements.add(new GuiEnergyInfo(new IInfoHandler() {
        	@Override
        	public List<String> getInfo()
        	{
        		String multiplier = MekanismUtils.getEnergyDisplay(MekanismUtils.getEnergyPerTick(tileEntity.getSpeedMultiplier(), tileEntity.getEnergyMultiplier(), tileEntity.ENERGY_PER_TICK));
        		return ListUtils.asList("Using: " + multiplier + "/t", "Needed: " + MekanismUtils.getEnergyDisplay(tileEntity.getMaxEnergy()-tileEntity.getEnergy()));
        	}
        }, this, tileEntity, MekanismUtils.getResource(ResourceType.GUI, "GuiMetallurgicInfuser.png")));
    }

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {		
		int xAxis = (mouseX - (width - xSize) / 2);
		int yAxis = (mouseY - (height - ySize) / 2);
		
        fontRenderer.drawString(tileEntity.getInvName(), 45, 6, 0x404040);
        fontRenderer.drawString(MekanismUtils.localize("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
        
		if(xAxis >= 165 && xAxis <= 169 && yAxis >= 17 && yAxis <= 69)
		{
			drawCreativeTabHoveringText(MekanismUtils.getEnergyDisplay(tileEntity.getEnergy()), xAxis, yAxis);
		}
		
		if(xAxis >= 7 && xAxis <= 11 && yAxis >= 17 && yAxis <= 69)
		{
			drawCreativeTabHoveringText(tileEntity.type != null ? tileEntity.type.getLocalizedName() + ": " + tileEntity.infuseStored : MekanismUtils.localize("gui.empty"), xAxis, yAxis);
		}
		
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
    {
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		
		mc.renderEngine.bindTexture(MekanismUtils.getResource(ResourceType.GUI, "GuiMetallurgicInfuser.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int guiWidth = (width - xSize) / 2;
        int guiHeight = (height - ySize) / 2;
        drawTexturedModalRect(guiWidth, guiHeight, 0, 0, xSize, ySize);
        
        int xAxis = mouseX - guiWidth;
 		int yAxis = mouseY - guiHeight;
        
        int displayInt;
        
        displayInt = tileEntity.getScaledProgress(32);
        drawTexturedModalRect(guiWidth + 72, guiHeight + 47, 176, 52, displayInt + 1, 8);
        
        displayInt = tileEntity.getScaledEnergyLevel(52);
        drawTexturedModalRect(guiWidth + 165, guiHeight + 17 + 52 - displayInt, 176, 52 - displayInt, 4, displayInt);
        
        if(tileEntity.type != null)
        {
	        displayInt = tileEntity.getScaledInfuseLevel(52);
	        mc.renderEngine.bindTexture(tileEntity.type.texture);
	        drawTexturedModalRect(guiWidth + 7, guiHeight + 17 + 52 - displayInt, tileEntity.type.texX, tileEntity.type.texY + 52 - displayInt, 4, displayInt);
        }
    }
	
	@Override
    protected void mouseClicked(int x, int y, int button)
    {
		super.mouseClicked(x, y, button);
		
		if(button == 0)
		{
			int xAxis = (x - (width - xSize) / 2);
			int yAxis = (y - (height - ySize) / 2);
			
			if(xAxis > 148 && xAxis < 168 && yAxis > 73 && yAxis < 82)
			{
				ArrayList data = new ArrayList();
				data.add(0);
				
				PacketHandler.sendPacket(Transmission.SERVER, new PacketTileEntity().setParams(Coord4D.get(tileEntity), data));
				mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			}
		}
    }
}
