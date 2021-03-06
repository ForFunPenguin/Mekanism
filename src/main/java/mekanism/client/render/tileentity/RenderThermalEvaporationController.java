package mekanism.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import javax.annotation.Nonnull;
import mekanism.client.render.FluidRenderer;
import mekanism.client.render.FluidRenderer.RenderData;
import mekanism.client.render.MekanismRenderType;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.MekanismRenderer.GlowInfo;
import mekanism.client.render.MekanismRenderer.Model3D;
import mekanism.common.tile.TileEntityThermalEvaporationController;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;

public class RenderThermalEvaporationController extends TileEntityRenderer<TileEntityThermalEvaporationController> {

    public RenderThermalEvaporationController(TileEntityRendererDispatcher renderer) {
        super(renderer);
    }

    @Override
    public void render(@Nonnull TileEntityThermalEvaporationController tile, float partialTick, @Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer,
          int light, int overlayLight) {
        if (tile.structured && tile.height - 2 >= 1 && !tile.inputTank.isEmpty()) {
            RenderData data = new RenderData();
            data.location = tile.getRenderLocation();
            data.height = tile.height - 2;
            //TODO: If we ever allow different width for the evap controller then update this length and width
            data.length = 2;
            data.width = 2;
            data.fluidType = tile.inputTank.getFluid();
            renderDispatcher.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            matrix.push();
            BlockPos pos = tile.getPos();
            matrix.translate(data.location.x - pos.getX(), data.location.y - pos.getY(), data.location.z - pos.getZ());
            float fluidScale = (float) tile.inputTank.getFluidAmount() / (float) tile.getMaxFluid();
            GlowInfo glowInfo = MekanismRenderer.enableGlow(data.fluidType);
            //Render the proper height
            Model3D fluidModel = FluidRenderer.getFluidModel(data, Math.min(1, fluidScale));
            MekanismRenderer.renderObject(fluidModel, matrix, renderer, MekanismRenderType.renderFluidState(AtlasTexture.LOCATION_BLOCKS_TEXTURE),
                  MekanismRenderer.getColorARGB(data.fluidType, fluidScale));
            MekanismRenderer.disableGlow(glowInfo);
            matrix.pop();
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityThermalEvaporationController tile) {
        return tile.structured && tile.height - 2 >= 1 && !tile.inputTank.isEmpty();
    }
}