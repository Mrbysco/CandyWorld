package com.mrbysco.candyworld.enums;

import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;

public enum EnumChocolate implements StringRepresentable {

	MILK(0, "milk"),
	WHITE(1, "white"),
	DARK(2, "dark");

	public static final EnumChocolate[] META_LOOKUP = new EnumChocolate[values().length];

	static {
		for (EnumChocolate enumchocolate : values()) {
			META_LOOKUP[enumchocolate.getMetadata()] = enumchocolate;
		}
	}

	private final int meta;
	private final String name;

	EnumChocolate(int meta, String name) {
		this.meta = meta;
		this.name = name;
	}

	public int getMetadata() {
		return this.meta;
	}

	@Nonnull
	@Override
	public String getSerializedName() {
		return this.name;
	}
}
