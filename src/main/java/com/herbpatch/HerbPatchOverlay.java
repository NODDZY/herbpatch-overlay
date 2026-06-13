package com.herbpatch;

import com.herbpatch.constants.HerbStages;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;
import java.util.Objects;

import static com.herbpatch.constants.HerbPatch.*;
// Transmog controllers for various farming patches
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_A; // Troll Stronghold, Weiss
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_B; // Harmony Island
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_D; // Falador, Port Phasmatys, Hosidius, Ardougne, Catherby
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_E; // Farming Guild

public class HerbPatchOverlay extends Overlay {
    private static final int MAX_DISTANCE = 2500;
    private static final int FILL_ALPHA = 20;
    private static final int OUTLINE_THICKNESS = 5;
    private static final int OUTLINE_FEATHER = 4;
    private static final int OVERLAY_PRIORITY = 0;

    private final Client client;
    private final HerbPatchPlugin plugin;
    private final ModelOutlineRenderer modelOutlineRenderer;

    @Inject private HerbPatchConfig config;

    @Inject
    private HerbPatchOverlay(Client client, HerbPatchPlugin plugin, ModelOutlineRenderer modelOutlineRenderer) {
        this.plugin = plugin;
        this.client = client;
        this.modelOutlineRenderer = modelOutlineRenderer;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OVERLAY_PRIORITY);
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
            case FALADOR:
            case PHASMATYS:
            case CATHERBY:
            case ARDOUGNE:
            case HOSIDIUS:
                state = client.getVarbitValue(FARMING_TRANSMIT_D);
                break;
            case STRONGHOLD:
            case WEISS:
                state = client.getVarbitValue(FARMING_TRANSMIT_A);
                break;
            case GUILD:
                state = client.getVarbitValue(FARMING_TRANSMIT_E);
                break;
            case HARMONY:
                state = client.getVarbitValue(FARMING_TRANSMIT_B);
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
        // Get player location and herb patch location
        LocalPoint herbLocation = object.getLocalLocation();
        LocalPoint playerLocation = client.getLocalPlayer().getLocalLocation();
        if (Objects.isNull(playerLocation)) {
            return;
        }

        // Check if herb patch is within set max distance
        if (playerLocation.distanceTo(herbLocation) <= MAX_DISTANCE) {
            Shape herb;
            // Set the overlay shape (object hull or tile)
            switch (config.overlaySelector()) {
                case TILE:
                    herb = Perspective.getCanvasTilePoly(client, herbLocation, object.getPlane());
                    break;
                case OUTLINE:
                    modelOutlineRenderer.drawOutline(object, OUTLINE_THICKNESS, color, OUTLINE_FEATHER);
                    return;
                case HULL:
                    herb = object.getConvexHull();
                    break;
                default:
                    return;
            }

            Point mousePosition = client.getMouseCanvasPosition();
            if (herb != null) {
                // Check if user is hovering the herb
                if (herb.contains(mousePosition.getX(), mousePosition.getY())) {
                    // Darken color if pointer is hovering herb
                    graphics.setColor(color.darker());
                } else {
                    graphics.setColor(color);
                }

                // Render overlay
                graphics.draw(herb);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), FILL_ALPHA));
                graphics.fill(herb);
            }
        }
    }

    /**
     * Get the appropriate color based on herb state.
     * @param state The server controlled integer representing the state of the herb
     * @return The color to render the overlay
     * @see HerbStages
     */
    private Color getHerbOverlayColor(int state) {
        switch (HerbStages.getHerbStage(state)) {
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
