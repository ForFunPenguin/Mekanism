package mekanism.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import javax.annotation.Nonnull;
import mekanism.client.render.MekanismRenderType;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class ModelChemicalDissolutionChamber extends Model {

    private static final ResourceLocation DISSOLUTION_TEXTURE = MekanismUtils.getResource(ResourceType.RENDER, "chemical_dissolution_chamber.png");
    private final RenderType RENDER_TYPE = func_228282_a_(DISSOLUTION_TEXTURE);
    private final RenderType GLASS_RENDER_TYPE = MekanismRenderType.mekStandard(DISSOLUTION_TEXTURE);

    private final ModelRenderer support2;
    private final ModelRenderer vat5;
    private final ModelRenderer top2;
    private final ModelRenderer top;
    private final ModelRenderer base;
    private final ModelRenderer vat2;
    private final ModelRenderer vat3;
    private final ModelRenderer vat6;
    private final ModelRenderer vat9;
    private final ModelRenderer vat8;
    private final ModelRenderer vat7;
    private final ModelRenderer vat4;
    private final ModelRenderer backEdge2;
    private final ModelRenderer back;
    private final ModelRenderer backEdge1;
    private final ModelRenderer vents;
    private final ModelRenderer support1;
    private final ModelRenderer vat1;
    private final ModelRenderer nozzle8;
    private final ModelRenderer nozzle5;
    private final ModelRenderer nozzle7;
    private final ModelRenderer nozzle4;
    private final ModelRenderer nozzle9;
    private final ModelRenderer nozzle6;
    private final ModelRenderer nozzle3;
    private final ModelRenderer nozzle2;
    private final ModelRenderer nozzle1;
    private final ModelRenderer glass;
    private final ModelRenderer portToggle1;
    private final ModelRenderer portToggle2;

    public ModelChemicalDissolutionChamber() {
        super(RenderType::func_228634_a_);
        textureWidth = 128;
        textureHeight = 64;

        support2 = new ModelRenderer(this, 4, 0);
        support2.func_228304_a_(0F, 0F, 0F, 1, 2, 1, false);
        support2.setRotationPoint(6F, 9F, -7F);
        support2.setTextureSize(128, 64);
        support2.mirror = true;
        setRotation(support2, 0F, 0F, 0F);
        vat5 = new ModelRenderer(this, 0, 23);
        vat5.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat5.setRotationPoint(-1.5F, 13F, -1.5F);
        vat5.setTextureSize(128, 64);
        vat5.mirror = true;
        setRotation(vat5, 0F, 0F, 0F);
        top2 = new ModelRenderer(this, 0, 40);
        top2.func_228304_a_(0F, 0F, 0F, 16, 1, 15, false);
        top2.setRotationPoint(-8F, 11F, -8F);
        top2.setTextureSize(128, 64);
        top2.mirror = true;
        setRotation(top2, 0F, 0F, 0F);
        top = new ModelRenderer(this, 0, 23);
        top.func_228304_a_(0F, 0F, 0F, 16, 1, 16, false);
        top.setRotationPoint(-8F, 8F, -8F);
        top.setTextureSize(128, 64);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        base = new ModelRenderer(this, 0, 0);
        base.func_228304_a_(0F, 0F, 0F, 16, 7, 16, false);
        base.setRotationPoint(-8F, 17F, -8F);
        base.setTextureSize(128, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        vat2 = new ModelRenderer(this, 0, 23);
        vat2.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat2.setRotationPoint(-5F, 13F, -1.5F);
        vat2.setTextureSize(128, 64);
        vat2.mirror = true;
        setRotation(vat2, 0F, 0F, 0F);
        vat3 = new ModelRenderer(this, 0, 23);
        vat3.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat3.setRotationPoint(-5F, 13F, 2F);
        vat3.setTextureSize(128, 64);
        vat3.mirror = true;
        setRotation(vat3, 0F, 0F, 0F);
        vat6 = new ModelRenderer(this, 0, 23);
        vat6.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat6.setRotationPoint(-1.5F, 13F, 2F);
        vat6.setTextureSize(128, 64);
        vat6.mirror = true;
        setRotation(vat6, 0F, 0F, 0F);
        vat9 = new ModelRenderer(this, 0, 23);
        vat9.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat9.setRotationPoint(2F, 13F, 2F);
        vat9.setTextureSize(128, 64);
        vat9.mirror = true;
        setRotation(vat9, 0F, 0F, 0F);
        vat8 = new ModelRenderer(this, 0, 23);
        vat8.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat8.setRotationPoint(2F, 13F, -1.5F);
        vat8.setTextureSize(128, 64);
        vat8.mirror = true;
        setRotation(vat8, 0F, 0F, 0F);
        vat7 = new ModelRenderer(this, 0, 23);
        vat7.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat7.setRotationPoint(2F, 13F, -5F);
        vat7.setTextureSize(128, 64);
        vat7.mirror = true;
        setRotation(vat7, 0F, 0F, 0F);
        vat4 = new ModelRenderer(this, 0, 23);
        vat4.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat4.setRotationPoint(-1.5F, 13F, -5F);
        vat4.setTextureSize(128, 64);
        vat4.mirror = true;
        setRotation(vat4, 0F, 0F, 0F);
        backEdge2 = new ModelRenderer(this, 8, 0);
        backEdge2.func_228304_a_(0F, 0F, 0F, 1, 8, 1, false);
        backEdge2.setRotationPoint(7F, 9F, 7F);
        backEdge2.setTextureSize(128, 64);
        backEdge2.mirror = true;
        setRotation(backEdge2, 0F, 0F, 0F);
        back = new ModelRenderer(this, 48, 0);
        back.func_228304_a_(0F, 0F, 0F, 14, 8, 2, false);
        back.setRotationPoint(-7F, 9F, 6F);
        back.setTextureSize(128, 64);
        back.mirror = true;
        setRotation(back, 0F, 0F, 0F);
        backEdge1 = new ModelRenderer(this, 8, 0);
        backEdge1.func_228304_a_(0F, 0F, 0F, 1, 8, 1, false);
        backEdge1.setRotationPoint(-8F, 9F, 7F);
        backEdge1.setTextureSize(128, 64);
        backEdge1.mirror = true;
        setRotation(backEdge1, 0F, 0F, 0F);
        vents = new ModelRenderer(this, 70, 0);
        vents.func_228304_a_(0F, 0F, 0F, 8, 2, 10, false);
        vents.setRotationPoint(-4F, 9F, -4F);
        vents.setTextureSize(128, 64);
        vents.mirror = true;
        setRotation(vents, 0F, 0F, 0F);
        support1 = new ModelRenderer(this, 4, 0);
        support1.func_228304_a_(0F, 0F, 0F, 1, 2, 1, false);
        support1.setRotationPoint(-7F, 9F, -7F);
        support1.setTextureSize(128, 64);
        support1.mirror = true;
        setRotation(support1, 0F, 0F, 0F);
        vat1 = new ModelRenderer(this, 0, 23);
        vat1.func_228304_a_(0F, 0F, 0F, 3, 4, 3, false);
        vat1.setRotationPoint(-5F, 13F, -5F);
        vat1.setTextureSize(128, 64);
        vat1.mirror = true;
        setRotation(vat1, 0F, 0F, 0F);
        nozzle8 = new ModelRenderer(this, 0, 0);
        nozzle8.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle8.setRotationPoint(3F, 11.5F, -0.5F);
        nozzle8.setTextureSize(128, 64);
        nozzle8.mirror = true;
        setRotation(nozzle8, 0F, 0F, 0F);
        nozzle5 = new ModelRenderer(this, 0, 0);
        nozzle5.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle5.setRotationPoint(-0.5F, 11.5F, -0.5F);
        nozzle5.setTextureSize(128, 64);
        nozzle5.mirror = true;
        setRotation(nozzle5, 0F, 0F, 0F);
        nozzle7 = new ModelRenderer(this, 0, 0);
        nozzle7.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle7.setRotationPoint(3F, 11.5F, -4F);
        nozzle7.setTextureSize(128, 64);
        nozzle7.mirror = true;
        setRotation(nozzle7, 0F, 0F, 0F);
        nozzle4 = new ModelRenderer(this, 0, 0);
        nozzle4.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle4.setRotationPoint(-0.5F, 11.5F, -4F);
        nozzle4.setTextureSize(128, 64);
        nozzle4.mirror = true;
        setRotation(nozzle4, 0F, 0F, 0F);
        nozzle9 = new ModelRenderer(this, 0, 0);
        nozzle9.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle9.setRotationPoint(3F, 11.5F, 3F);
        nozzle9.setTextureSize(128, 64);
        nozzle9.mirror = true;
        setRotation(nozzle9, 0F, 0F, 0F);
        nozzle6 = new ModelRenderer(this, 0, 0);
        nozzle6.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle6.setRotationPoint(-0.5F, 11.5F, 3F);
        nozzle6.setTextureSize(128, 64);
        nozzle6.mirror = true;
        setRotation(nozzle6, 0F, 0F, 0F);
        nozzle3 = new ModelRenderer(this, 0, 0);
        nozzle3.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle3.setRotationPoint(-4F, 11.5F, 3F);
        nozzle3.setTextureSize(128, 64);
        nozzle3.mirror = true;
        setRotation(nozzle3, 0F, 0F, 0F);
        nozzle2 = new ModelRenderer(this, 0, 0);
        nozzle2.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle2.setRotationPoint(-4F, 11.5F, -0.5F);
        nozzle2.setTextureSize(128, 64);
        nozzle2.mirror = true;
        setRotation(nozzle2, 0F, 0F, 0F);
        nozzle1 = new ModelRenderer(this, 0, 0);
        nozzle1.func_228304_a_(0F, 0F, 0F, 1, 1, 1, false);
        nozzle1.setRotationPoint(-4F, 11.5F, -4F);
        nozzle1.setTextureSize(128, 64);
        nozzle1.mirror = true;
        setRotation(nozzle1, 0F, 0F, 0F);
        glass = new ModelRenderer(this, 64, 14);
        glass.func_228304_a_(0F, 0F, 0F, 14, 5, 13, false);
        glass.setRotationPoint(-7F, 12F, -7F);
        glass.setTextureSize(128, 64);
        glass.mirror = true;
        setRotation(glass, 0F, 0F, 0F);
        portToggle1 = new ModelRenderer(this, 106, 0);
        portToggle1.func_228304_a_(0F, 0F, 0F, 1, 10, 10, false);
        portToggle1.setRotationPoint(-8.01F, 10.99F, -5F);
        portToggle1.setTextureSize(128, 64);
        portToggle1.mirror = true;
        setRotation(portToggle1, 0F, 0F, 0F);
        portToggle2 = new ModelRenderer(this, 64, 32);
        portToggle2.func_228304_a_(0F, 0F, 0F, 1, 8, 8, false);
        portToggle2.setRotationPoint(7.01F, 12F, -4F);
        portToggle2.setTextureSize(128, 64);
        portToggle2.mirror = true;
        setRotation(portToggle2, 0F, 0F, 0F);
    }

    public void render(@Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer renderer, int light, int otherLight) {
        func_225598_a_(matrix, renderer.getBuffer(RENDER_TYPE), light, otherLight, 1, 1, 1, 1);
        //Render the glass on a more translucent layer
        //Note: The glass makes water, ice etc behind it invisible. This is due to an engine limitation
        glass.func_228309_a_(matrix, renderer.getBuffer(GLASS_RENDER_TYPE), light, otherLight, 1, 1, 1, 1);
    }

    @Override
    public void func_225598_a_(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder vertexBuilder, int light, int otherLight, float red, float green, float blue, float alpha) {
        support2.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat5.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        top2.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        top.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        base.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat2.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat3.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat6.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat9.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat8.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat7.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat4.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        backEdge2.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        back.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        backEdge1.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vents.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        support1.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        vat1.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle8.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle5.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle7.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle4.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle9.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle6.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle3.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle2.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        nozzle1.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        portToggle1.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
        portToggle2.func_228309_a_(matrix, vertexBuilder, light, otherLight, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}