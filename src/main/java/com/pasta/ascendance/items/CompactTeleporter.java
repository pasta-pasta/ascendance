package com.pasta.ascendance.items;

import com.pasta.ascendance.compacted.ASCCompactedFunctions;
import com.pasta.ascendance.core.reggers.DimensionRegger;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class CompactTeleporter extends Item {
    public CompactTeleporter(Properties builder) {
        super(builder);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()){
            ServerLevel serverWorld = (ServerLevel) pLevel;
            ServerLevel overworld = serverWorld.getServer().getLevel(Level.OVERWORLD);

            ItemStack teleporter = player.getItemInHand(pUsedHand);
            CompoundTag tag = teleporter.getOrCreateTag();
            double x = tag.getInt("x");
            double y = tag.getInt("y");
            double z = tag.getInt("y");

            Vec3 tppos = new Vec3(x, y, z);

            ASCCompactedFunctions.visit(tppos, overworld, player);

        }
        return super.use(pLevel, player, pUsedHand);

    }
}
