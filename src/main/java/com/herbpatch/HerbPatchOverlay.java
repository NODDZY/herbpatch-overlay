package com.herbpatch;

import com.herbpatch.constants.HerbPatchConstants;
import com.herbpatch.constants.HerbPatchStages;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

import javax.inject.Inject;
import java.awt.*;
import java.util.Objects;

// Transmog controllers for various farming patches
import static net.runelite.api.Varbits.FARMING_4771; // Troll Stronghold, Weiss
import static net.runelite.api.Varbits.FARMING_4772; // Harmony Island
import static net.runelite.api.Varbits.FARMING_4774; // Falador, Port Phasmatys, Hosidius, Ardougne, Catherby
import static net.runelite.api.Varbits.FARMING_4775; // Farming Guild

public class HerbPatchOverlay extends Overlay {
    private static final int MAX_DISTANCE = 2350;
    private final Client client;
    private final HerbPatchPlugin plugin;

    @Inject
    private HerbPatchConfig config;

    @Inject
    private HerbPatchOverlay(Client client, HerbPatchPlugin plugin) {
        this.plugin = plugin;
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.LOW);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        GameObject patchObject = plugin.getHerbPatchObject();
        if (Objects.isNull(patchObject)) {
            return null;
        }
        int state = -1;

        // Switch to see what patch is rendered as some patches uses different transmog controllers
        switch (patchObject.getId()) {
            case HerbPatchConstants.FALADOR:
            case HerbPatchConstants.PHASMATYS:
            case HerbPatchConstants.CATHERBY:
            case HerbPatchConstants.ARDOUGNE:
            case HerbPatchConstants.HOSIDIUS:
                state = client.getVarbitValue(FARMING_4774);
                break;
            case HerbPatchConstants.STRONGHOLD:
            case HerbPatchConstants.WEISS:
                state = client.getVarbitValue(FARMING_4771);
                break;
            case HerbPatchConstants.GUILD:
                state = client.getVarbitValue(FARMING_4775);
                break;
            case HerbPatchConstants.HARMONY:
                state = client.getVarbitValue(FARMING_4772);
                break;
            default:
                break;
        }

        // If state is not default and user wants to render for current state -> render overlay
        if (state != -1) {
            Color color = getHerbOverlayColor(state);
            if (!Objects.isNull(color)) {
                drawOverlay(patchObject, graphics, color);
            }
        }

        return null;
    }

    /**
     * Method to draw object overlay on screen. Should be run every frame.
     * @param object Object to draw overlay on/above
     * @param graphics Main Java AWT 2D graphics object
     * @param color Overlay main outline color
     */
    private void drawOverlay(GameObject object, Graphics2D graphics, Color color) {
        // Get player location, herb patch location and mouse position
        LocalPoint localLocation = client.getLocalPlayer().getLocalLocation();
        if (Objects.isNull(localLocation)) {
            return;
        }
        Point mousePosition = client.getMouseCanvasPosition();
        LocalPoint herbLocation = object.getLocalLocation();

        // Check if herb patch is within set max distance
        if (localLocation.distanceTo(herbLocation) <= MAX_DISTANCE) {
            // Overlay shape
            Shape objectConvexHull = object.getConvexHull();

            if (objectConvexHull != null) {
                if (objectConvexHull.contains(mousePosition.getX(), mousePosition.getY())) {
                    // Darken color if pointer is hovering herb
                    graphics.setColor(color.darker());
                } else {
                    graphics.setColor(color);
                }

                // Render overlay
                graphics.draw(objectConvexHull);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
                graphics.fill(objectConvexHull);
            }
        }
    }

    /**
     * Get the appropriate color based on herb state.
     * @param state The server controlled integer representing the state of the herb
     * @return The color to render the overlay
     * @see HerbPatchStages
     */
    private Color getHerbOverlayColor(int state) {
        switch (HerbPatchStages.getHerbStage(state)) {
            case OVERGROWN:
                if (config.renderIfOvergrown()) {
                    return config.overgrownColor();
                }
                return null;
            case EMPTY:
                if (config.renderIfEmpty()) {
                    return config.emptyColor();
                }
                return null;
            case GROWING:
                return config.unripeColor();
            case GROWN:
                return config.matureColor();
            case DISEASED:
                return config.diseasedColor();
            case DEAD:
                return config.deadColor();
            default:
                return null;
        }
    }
}
