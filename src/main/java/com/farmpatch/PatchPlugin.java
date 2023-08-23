package com.farmpatch;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Farming Patch Overlay"
)
public class PatchPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private PatchConfig config;

	@Provides
	PatchConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(PatchConfig.class);
	}
}
