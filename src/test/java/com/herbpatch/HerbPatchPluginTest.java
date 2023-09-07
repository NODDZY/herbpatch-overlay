package com.herbpatch;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HerbPatchPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(HerbPatchPlugin.class);
		RuneLite.main(args);
	}
}
