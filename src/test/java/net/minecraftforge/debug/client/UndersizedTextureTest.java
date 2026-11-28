package net.minecraftforge.debug.client;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.gametest.GameTestNamespace;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.test.BaseTestMod;

@GameTestNamespace("forge")
@Mod(UndersizedTextureTest.MODID)
public class UndersizedTextureTest extends BaseTestMod {
    public static final String MODID = "undersized_texture_test";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("test_undersized_resource",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("test_undersized_resource"))
                    .mapColor(MapColor.STONE)
            )
    );
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("test_undersized_resource",
            () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().setId(ITEMS.key("test_undersized_resource")))
    );

    /// This test acts as a source of blame. View {@linkplain net.minecraftforge.client.textures.UndersizedSpriteUpscaler}
    /// For the actual code.
    public UndersizedTextureTest(FMLJavaModLoadingContext context) {
        super(context, false, false);

        var busgroup = context.getModBusGroup();

        BLOCKS.register(busgroup);
        ITEMS.register(busgroup);

        BuildCreativeModeTabContentsEvent.BUS.addListener(UndersizedTextureTest::addCreative);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
    }
}
