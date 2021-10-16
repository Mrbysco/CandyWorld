package com.mrbysco.candyworld.block;

import com.mrbysco.candyworld.enums.EnumChocolate;
import com.mrbysco.candyworld.enums.EnumGummy;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockProperties {

    public static final EnumProperty<EnumChocolate> CHOCOLATE_TYPE = EnumProperty.create("type", EnumChocolate.class);
    public static final EnumProperty<EnumGummy> GUMMY_TYPE = EnumProperty.create("type", EnumGummy.class);
//    public static final EnumProperty<EnumCandyCane> CANDY_CANE_TYPE = EnumProperty.create("type", EnumCandyCane.class);

    public static final BooleanProperty IS_SUGAR_VARIANT = BooleanProperty.create("sugar_variant");
}
