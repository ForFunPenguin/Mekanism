package mekanism.api.datagen.recipe.builder;

import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import mekanism.api.MekanismAPI;
import mekanism.api.SerializerHelper;
import mekanism.api.annotations.FieldsAreNonnullByDefault;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.gas.GasStack;
import mekanism.api.recipes.inputs.GasStackIngredient;
import net.minecraft.advancements.Advancement;
import net.minecraft.util.ResourceLocation;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ChemicalInfuserRecipeBuilder extends MekanismRecipeBuilder<ChemicalInfuserRecipeBuilder> {

    private final GasStackIngredient leftInput;
    private final GasStackIngredient rightInput;
    private final GasStack output;

    protected ChemicalInfuserRecipeBuilder(GasStackIngredient leftInput, GasStackIngredient rightInput, GasStack output) {
        super(new ResourceLocation(MekanismAPI.MEKANISM_MODID, "chemical_infusing"));
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.output = output;
    }

    public static ChemicalInfuserRecipeBuilder chemicalInfusing(GasStackIngredient leftInput, GasStackIngredient rightInput, GasStack output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This chemical infusing recipe requires a non empty gas output.");
        }
        return new ChemicalInfuserRecipeBuilder(leftInput, rightInput, output);
    }

    @Override
    protected ChemicalInfuserRecipeResult getResult(ResourceLocation id) {
        return new ChemicalInfuserRecipeResult(id, leftInput, rightInput, output, advancementBuilder,
              new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath()), serializerName);
    }

    public static class ChemicalInfuserRecipeResult extends RecipeResult {

        private final GasStackIngredient leftInput;
        private final GasStackIngredient rightInput;
        private final GasStack output;

        public ChemicalInfuserRecipeResult(ResourceLocation id, GasStackIngredient leftInput, GasStackIngredient rightInput, GasStack output,
              Advancement.Builder advancementBuilder, ResourceLocation advancementId, ResourceLocation serializerName) {
            super(id, advancementBuilder, advancementId, serializerName);
            this.leftInput = leftInput;
            this.rightInput = rightInput;
            this.output = output;
        }

        @Override
        public void serialize(@Nonnull JsonObject json) {
            json.add("leftInput", leftInput.serialize());
            json.add("rightInput", rightInput.serialize());
            json.add("output", SerializerHelper.serializeGasStack(output));
        }
    }
}