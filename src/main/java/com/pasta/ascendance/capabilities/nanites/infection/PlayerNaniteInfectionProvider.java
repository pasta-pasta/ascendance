package com.pasta.ascendance.capabilities.nanites.infection;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerNaniteInfectionProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerNaniteInfection> PLAYER_INFECTION =
            CapabilityManager.get(new CapabilityToken<PlayerNaniteInfection>() {});

    private PlayerNaniteInfection infection = null;
    private final LazyOptional<PlayerNaniteInfection> optional = LazyOptional.of(this::createPlayerInfection);

    private PlayerNaniteInfection createPlayerInfection() {
        if(this.infection == null){
            this.infection = new PlayerNaniteInfection();
        }

        return this.infection;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_INFECTION){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerInfection().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerInfection().loadNBTData(nbt);
    }
}
