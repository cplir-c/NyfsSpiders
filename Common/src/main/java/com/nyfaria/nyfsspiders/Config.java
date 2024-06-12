package com.nyfaria.nyfsspiders;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
	public static final ModConfigSpec COMMON;

	public static final ModConfigSpec.BooleanValue PATH_FINDER_DEBUG_PREVIEW;
	public static final ModConfigSpec.BooleanValue PREVENT_CLIMBING_IN_RAIN;

	static {
		ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

		PATH_FINDER_DEBUG_PREVIEW = builder
				.worldRestart()
				.comment("Whether the path finder debug preview should be enabled.")
				.define("path_finder_debug_preview", false);
		PREVENT_CLIMBING_IN_RAIN = builder
				.worldRestart()
				.comment("Whether spiders should be prevented from climbing in rain.")
				.define("prevent_climbing_in_rain", false);
		COMMON = builder.build();
	}
}
