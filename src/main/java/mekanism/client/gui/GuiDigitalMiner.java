package mekanism.client.gui;

import java.util.ArrayList;
import mekanism.api.TileNetworkList;
import mekanism.api.text.EnumColor;
import mekanism.client.gui.button.MekanismButton;
import mekanism.client.gui.button.MekanismImageButton;
import mekanism.client.gui.button.TranslationButton;
import mekanism.client.gui.element.GuiEnergyInfo;
import mekanism.client.gui.element.GuiRedstoneControl;
import mekanism.client.gui.element.GuiSlot;
import mekanism.client.gui.element.GuiSlot.SlotOverlay;
import mekanism.client.gui.element.GuiSlot.SlotType;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.tab.GuiSecurityTab;
import mekanism.client.gui.element.tab.GuiUpgradeTab;
import mekanism.client.gui.element.tab.GuiVisualsTab;
import mekanism.common.Mekanism;
import mekanism.common.content.miner.ThreadMinerSearch.State;
import mekanism.common.inventory.container.tile.DigitalMinerContainer;
import mekanism.common.network.PacketGuiButtonPress;
import mekanism.common.network.PacketGuiButtonPress.ClickedTileButton;
import mekanism.common.network.PacketTileEntity;
import mekanism.common.tile.TileEntityDigitalMiner;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import mekanism.common.util.text.BooleanStateDisplay.OnOff;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.common.util.text.TextComponentUtil;
import mekanism.common.util.text.Translation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class GuiDigitalMiner extends GuiMekanismTile<TileEntityDigitalMiner, DigitalMinerContainer> {

    private MekanismButton startButton;
    private MekanismButton stopButton;
    private MekanismButton configButton;

    public GuiDigitalMiner(DigitalMinerContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        ySize += 64;
    }

    @Override
    public void init() {
        super.init();
        ResourceLocation resource = getGuiLocation();
        addButton(new GuiRedstoneControl(this, tile, resource));
        addButton(new GuiSecurityTab<>(this, tile, resource));
        addButton(new GuiUpgradeTab(this, tile, resource));
        addButton(new GuiVerticalPowerBar(this, tile, resource, 163, 23));
        addButton(new GuiVisualsTab(this, tile, resource));
        addButton(new GuiEnergyInfo(() -> {
            double perTick = tile.getPerTick();
            ArrayList<ITextComponent> ret = new ArrayList<>(4);
            ret.add(TextComponentUtil.build(Translation.of("gui.mekanism.digitalMiner.capacity"), ": ", EnergyDisplay.of(tile.getMaxEnergy())));
            ret.add(TextComponentUtil.build(Translation.of("gui.mekanism.needed"), ": ", EnergyDisplay.of(perTick), "/t"));
            if (perTick > tile.getMaxEnergy()) {
                ret.add(TextComponentUtil.build(TextFormatting.RED, Translation.of("gui.mekanism.insufficientbuffer")));
            }
            ret.add(TextComponentUtil.build(Translation.of("gui.mekanism.bufferfree"), ": ", EnergyDisplay.of(tile.getNeededEnergy())));
            return ret;
        }, this, resource));
        addButton(new GuiSlot(SlotType.NORMAL, this, resource, 151, 5).with(SlotOverlay.POWER));
        addButton(new GuiSlot(SlotType.NORMAL, this, resource, 143, 26));

        addButton(startButton = new TranslationButton(this, guiLeft + 69, guiTop + 17, 60, 20, "gui.mekanism.start",
              () -> Mekanism.packetHandler.sendToServer(new PacketTileEntity(tile, TileNetworkList.withContents(3)))));
        addButton(stopButton = new TranslationButton(this, guiLeft + 69, guiTop + 37, 60, 20, "gui.mekanism.stop",
              () -> Mekanism.packetHandler.sendToServer(new PacketTileEntity(tile, TileNetworkList.withContents(4)))));
        addButton(configButton = new TranslationButton(this, guiLeft + 69, guiTop + 57, 60, 20, "gui.mekanism.config",
              () -> Mekanism.packetHandler.sendToServer(new PacketGuiButtonPress(ClickedTileButton.DIGITAL_MINER_CONFIG, tile.getPos()))));
        addButton(new MekanismImageButton(this, guiLeft + 131, guiTop + 47, 14, getButtonLocation("reset"),
              () -> Mekanism.packetHandler.sendToServer(new PacketTileEntity(tile, TileNetworkList.withContents(5))),
              getOnHover("gui.mekanism.digitalMiner.reset")));
        addButton(new MekanismImageButton(this, guiLeft + 131, guiTop + 63, 14, getButtonLocation("silk_touch"),
              () -> Mekanism.packetHandler.sendToServer(new PacketTileEntity(tile, TileNetworkList.withContents(9))),
              getOnHover("gui.mekanism.digitalMiner.silkTouch")));
        addButton(new MekanismImageButton(this, guiLeft + 147, guiTop + 47, 14, getButtonLocation("auto_eject"),
              () -> Mekanism.packetHandler.sendToServer(new PacketTileEntity(tile, TileNetworkList.withContents(0))),
              getOnHover("gui.mekanism.autoEject")));
        addButton(new MekanismImageButton(this, guiLeft + 147, guiTop + 63, 14, getButtonLocation("auto_pull"),
              () -> Mekanism.packetHandler.sendToServer(new PacketTileEntity(tile, TileNetworkList.withContents(1))),
              getOnHover("gui.mekanism.digitalMiner.autoPull")));
        updateEnabledButtons();
    }

    @Override
    public void tick() {
        super.tick();
        updateEnabledButtons();
    }

    private void updateEnabledButtons() {
        startButton.active = tile.searcher.state == State.IDLE || !tile.running;
        stopButton.active = tile.searcher.state != State.IDLE && tile.running;
        configButton.active = tile.searcher.state == State.IDLE;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawString(tile.getName(), 69, 6, 0x404040);
        drawString(TextComponentUtil.translate("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
        ITextComponent runningType;
        if (tile.getPerTick() > tile.getMaxEnergy()) {
            runningType = TextComponentUtil.translate("gui.mekanism.digitalMiner.lowPower");
        } else if (tile.running) {
            runningType = TextComponentUtil.translate("gui.mekanism.digitalMiner.running");
        } else {
            runningType = TextComponentUtil.translate("gui.mekanism.idle");
        }
        drawString(runningType, 9, 10, 0x00CD00);
        drawString(tile.searcher.state.desc, 9, 19, 0x00CD00);

        drawString(TextComponentUtil.build(Translation.of("gui.mekanism.eject"), ": ", OnOff.of(tile.doEject)), 9, 30, 0x00CD00);
        drawString(TextComponentUtil.build(Translation.of("gui.mekanism.digitalMiner.pull"), ": ", OnOff.of(tile.doPull)), 9, 39, 0x00CD00);
        drawString(TextComponentUtil.build(Translation.of("gui.mekanism.digitalMiner.silk"), ": ", OnOff.of(tile.silkTouch)), 9, 48, 0x00CD00);
        drawString(TextComponentUtil.build(Translation.of("gui.mekanism.digitalMiner.toMine"), ":"), 9, 59, 0x00CD00);
        //TODO: Can this be combined with the one above
        drawString("" + tile.clientToMine, 9, 68, 0x00CD00);

        if (!tile.missingStack.isEmpty()) {
            drawColorIcon(144, 27, EnumColor.DARK_RED, 0.8F);
            renderItem(tile.missingStack, 144, 27);
        } else {
            minecraft.getTextureManager().bindTexture(MekanismUtils.getResource(ResourceType.GUI_ELEMENT, "slot.png"));
            drawTexturedRect(143, 26, SlotOverlay.CHECK.textureX, SlotOverlay.CHECK.textureY, 18, 18);
        }

        int xAxis = mouseX - guiLeft;
        int yAxis = mouseY - guiTop;
        if (xAxis >= 164 && xAxis <= 168 && yAxis >= 25 && yAxis <= 77) {
            displayTooltip(EnergyDisplay.of(tile.getEnergy(), tile.getMaxEnergy()).getTextComponent(), xAxis, yAxis);
        } else if (xAxis >= 144 && xAxis <= 160 && yAxis >= 27 && yAxis <= 43) {
            if (!tile.missingStack.isEmpty()) {
                displayTooltip(TextComponentUtil.translate("gui.mekanism.digitalMiner.missingBlock"), xAxis, yAxis);
            } else {
                displayTooltip(TextComponentUtil.translate("gui.mekanism.well"), xAxis, yAxis);
            }
        }
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(int xAxis, int yAxis) {
        super.drawGuiContainerBackgroundLayer(xAxis, yAxis);
        int displayInt = tile.getScaledEnergyLevel(52);
        drawTexturedRect(guiLeft + 164, guiTop + 25 + 52 - displayInt, 176, 52 - displayInt, 4, displayInt);
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return MekanismUtils.getResource(ResourceType.GUI, "digital_miner.png");
    }
}