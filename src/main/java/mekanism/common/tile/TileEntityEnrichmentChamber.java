package mekanism.common.tile;

import java.util.List;
import mekanism.api.recipes.ItemStackToItemStackRecipe;
import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.tile.prefab.TileEntityElectricMachine;

public class TileEntityEnrichmentChamber extends TileEntityElectricMachine<ItemStackToItemStackRecipe> {

    public TileEntityEnrichmentChamber() {
        super("enrichment", MachineType.ENRICHMENT_CHAMBER, 200);
    }

    @Override
    public List<ItemStackToItemStackRecipe> getRecipes() {
        return Recipe.ENRICHMENT_CHAMBER.get();
    }
}