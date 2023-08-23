package com.farmpatch;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PatchPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(PatchPlugin.class);
		RuneLite.main(args);
	}
}
