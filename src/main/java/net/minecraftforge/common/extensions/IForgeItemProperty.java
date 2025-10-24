package net.minecraftforge.common.extensions;

import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;

public interface IForgeItemProperty {
    default Item.Properties self() {
        return (Item.Properties) this;
    }

    default Item.Properties actAsShears() {
        return self().harvestLikeShears().carveLikeShears().canDisarmLikeShears();
    }

    /**
     * Any {@link Item} with this property will create a sweep attack when hitting an enemy like a sword does.
     * Testing for this property should be done by testing for the {@link DataComponents#SWEEP} component.
     */
    default Item.Properties sweepLikeSword() {
        return self().component(DataComponents.SWEEP, Unit.INSTANCE);
    }

    /**
     * Any {@link Item} with this property will carve pumpkins like vanilla shears do.
     * Testing for this property should be done by testing for the {@link DataComponents#CAN_CARVE} component.
     */
    default Item.Properties carveLikeShears() {
        return self().component(DataComponents.CAN_CARVE, Unit.INSTANCE);
    }

    /**
     * Any {@link Item} with this property will harvest a block like shears do.
     * Vanilla's uses are resetting the honey level in a beehive and clipping the antenna off a copper golem.
     * Testing for this property should be done by testing for the {@link DataComponents#CAN_SHEAR_HARVEST} component.
     */
    default Item.Properties harvestLikeShears() {
        return self().component(DataComponents.CAN_SHEAR_HARVEST, Unit.INSTANCE);
    }

    /**
     * Any {@link Item} with this property will disarm tripwires when breaking them like shears do.
     * Testing for this property should be done by testing for the {@link DataComponents#CAN_DISARM} component.
     */
    default Item.Properties canDisarmLikeShears() {
        return self().component(DataComponents.CAN_DISARM, Unit.INSTANCE);
    }
}
