package com.herbpatch;

import com.herbpatch.constants.HerbPatchConstants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GameObject;
import net.runelite.api.GameState;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Herb Patch Overlay"
)
public class HerbPatchPlugin extends Plugin {
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private HerbPatchOverlay herbOverlay;

	@Getter(AccessLevel.PACKAGE)
	private GameObject herbPatchObject;

	@Override
	protected void startUp() {
		// Add HerbPatchOverlay to OverlayManager at start up
		overlayManager.add(herbOverlay);
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event) {
		// Check if any spawned objects are herbs/patches
		HerbPatchConstants.allHerbObjects.stream()
			.filter(id -> id == event.getGameObject().getId())
			.findFirst()
			.ifPresent(value -> {
				herbPatchObject = event.getGameObject();
				log.debug("Herb patch [" + herbPatchObject.getId() + "] spawned");
			});
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event) {
		// When changing state (e.g. teleporting) clear herb object
		if (event.getGameState() == GameState.LOADING) {
			herbPatchObject = null;
		}
	}
}
