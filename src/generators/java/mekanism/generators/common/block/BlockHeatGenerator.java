package mekanism.generators.common.block;

import java.util.Random;
import javax.annotation.Nonnull;
import mekanism.api.block.IBlockDisableable;
import mekanism.api.block.IBlockElectric;
import mekanism.api.block.IBlockSound;
import mekanism.api.block.IHasInventory;
import mekanism.api.block.IHasSecurity;
import mekanism.api.block.IHasTileEntity;
import mekanism.api.block.ISupportsComparator;
import mekanism.common.Mekanism;
import mekanism.common.block.BlockMekanismContainer;
import mekanism.common.block.interfaces.IHasGui;
import mekanism.common.block.states.IStateFacing;
import mekanism.common.inventory.container.ContainerProvider;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.base.WrenchResult;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.SecurityUtils;
import mekanism.generators.common.MekanismGenerators;
import mekanism.generators.common.inventory.container.fuel.HeatGeneratorContainer;
import mekanism.generators.common.tile.GeneratorsTileEntityTypes;
import mekanism.generators.common.tile.TileEntityHeatGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class BlockHeatGenerator extends BlockMekanismContainer implements IHasGui<TileEntityHeatGenerator>, IBlockElectric, IStateFacing, IHasInventory, IHasSecurity, IBlockSound,
      IHasTileEntity<TileEntityHeatGenerator>, IBlockDisableable, ISupportsComparator {

    private static final SoundEvent SOUND_EVENT = new SoundEvent(new ResourceLocation(Mekanism.MODID, "tile.gen.heat"));

    private BooleanValue enabledReference;

    public BlockHeatGenerator() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F, 8F));
        setRegistryName(new ResourceLocation(MekanismGenerators.MODID, "heat_generator"));
    }

    @Override
    @Deprecated
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        if (!world.isRemote) {
            final TileEntity tileEntity = MekanismUtils.getTileEntity(world, pos);
            if (tileEntity instanceof TileEntityMekanism) {
                ((TileEntityMekanism) tileEntity).onNeighborChange(neighborBlock);
            }
        }
    }

    @Override
    @Deprecated
    public float getPlayerRelativeBlockHardness(BlockState state, @Nonnull PlayerEntity player, @Nonnull IBlockReader world, @Nonnull BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        return SecurityUtils.canAccess(player, tile) ? super.getPlayerRelativeBlockHardness(state, player, world, pos) : 0.0F;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        TileEntityMekanism tileEntity = (TileEntityMekanism) world.getTileEntity(pos);
        if (MekanismUtils.isActive(world, pos)) {
            float xRandom = (float) pos.getX() + 0.5F;
            float yRandom = (float) pos.getY() + random.nextFloat() * 6.0F / 16.0F;
            float zRandom = (float) pos.getZ() + 0.5F;
            float iRandom = 0.52F;
            float jRandom = random.nextFloat() * 0.6F - 0.3F;
            if (tileEntity.getDirection() == Direction.WEST) {
                world.addParticle(ParticleTypes.SMOKE, xRandom + iRandom, yRandom, zRandom - jRandom, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, xRandom + iRandom, yRandom, zRandom - jRandom, 0.0D, 0.0D, 0.0D);
            } else if (tileEntity.getDirection() == Direction.EAST) {
                world.addParticle(ParticleTypes.SMOKE, xRandom + iRandom, yRandom + 0.5F, zRandom - jRandom, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, xRandom + iRandom, yRandom + 0.5F, zRandom - jRandom, 0.0D, 0.0D, 0.0D);
            } else if (tileEntity.getDirection() == Direction.NORTH) {
                world.addParticle(ParticleTypes.SMOKE, xRandom - jRandom, yRandom + 0.5F, zRandom - iRandom, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, xRandom - jRandom, yRandom + 0.5F, zRandom - iRandom, 0.0D, 0.0D, 0.0D);
            } else if (tileEntity.getDirection() == Direction.SOUTH) {
                world.addParticle(ParticleTypes.SMOKE, xRandom - jRandom, yRandom + 0.5F, zRandom + iRandom, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, xRandom - jRandom, yRandom + 0.5F, zRandom + iRandom, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.isRemote) {
            return true;
        }
        TileEntityMekanism tileEntity = (TileEntityMekanism) world.getTileEntity(pos);
        if (tileEntity.tryWrench(state, player, hand, hit) != WrenchResult.PASS) {
            return true;
        }
        if (tileEntity.openGui(player)) {
            return true;
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Nonnull
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public double getStorage() {
        return 160000;
    }

    @Override
    public int getInventorySize() {
        return 2;
    }

    @Nonnull
    @Override
    public SoundEvent getSoundEvent() {
        return SOUND_EVENT;
    }

    @Override
    public boolean isEnabled() {
        return enabledReference == null ? true : enabledReference.get();
    }

    @Override
    public void setEnabledConfigReference(BooleanValue enabledReference) {
        this.enabledReference = enabledReference;
    }

    @Override
    public INamedContainerProvider getProvider(TileEntityHeatGenerator tile) {
        return new ContainerProvider("mekanismgenerators.container.heat_generator", (i, inv, player) -> new HeatGeneratorContainer(i, inv, tile));
    }

    @Override
    public TileEntityType<TileEntityHeatGenerator> getTileType() {
        return GeneratorsTileEntityTypes.HEAT_GENERATOR;
    }
}