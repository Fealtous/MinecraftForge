/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.debug.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.gametest.GameTest;
import net.minecraftforge.gametest.GameTestNamespace;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.test.BaseTestMod;

@GameTestNamespace("forge")
@Mod(FluidBucketModelTest.MODID)
public class FluidBucketModelTest extends BaseTestMod {
    public static final String MODID = "fluid_bucket_model";

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);
    public static final RegistryObject<Item> BUCKET = ITEMS.register("bucket", () -> new BucketItem(() -> Fluids.LAVA, new Item.Properties().setId(ITEMS.key("bucket"))));

    public FluidBucketModelTest(FMLJavaModLoadingContext context) {
        super(context, false, true);
        this.testItem(lookup -> BUCKET.get().getDefaultInstance());
    }

    @GameTest
    public static void item_model(GameTestHelper helper) {
        var manager = Minecraft.getInstance().getModelManager();

        var key = rl("pig_head");
        var model = manager.getItemModel(key);
        if (model == null)
            helper.fail("Failed to retreive " + key + " item model");

        if (model == manager.getMissingModel())
            helper.fail("Itme Model was the missing model");

        helper.succeed();
    }
}
