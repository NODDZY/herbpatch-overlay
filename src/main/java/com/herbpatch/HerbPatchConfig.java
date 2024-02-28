package com.herbpatch;

import lombok.AllArgsConstructor;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup(HerbPatchConfig.CONFIG_GROUP)
public interface HerbPatchConfig extends Config {
    String CONFIG_GROUP = "herbpatchoverlay";

    @ConfigItem(
        keyName = "overlaySelector",
        name = "Overlay type",
        description = "Configure overlay type",
        position = 101
    )
    default OverlayType overlaySelector() {
        return OverlayType.HULL;
    }

    @ConfigItem(
        keyName = "renderIfOvergrown",
        name = "Render overgrown overlay",
        description = "Render overlay on overgrown herb patches",
        position = 102
    )
    default boolean renderIfOvergrown() {
        return true;
    }

    @ConfigItem(
        keyName = "renderIfEmpty",
        name = "Render empty overlay",
        description = "Render overlay on empty herb patches",
        position = 103
    )
    default boolean renderIfEmpty() {
        return true;
    }

    @ConfigSection(
            name = "Herb Overlay Color",
            description = "Custom overlay colors for herbs",
            position = 200
    )
    String customColorSection = "customColor";

    @ConfigItem(
            keyName = "overgrownColor",
            name = "Overgrown",
            description = "",
            section = customColorSection,
            position = 201
    )
    default Color overgrownColor() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "emptyColor",
            name = "Empty",
            description = "",
            section = customColorSection,
            position = 202
    )
    default Color emptyColor() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "unripeColor",
            name = "Unripe",
            description = "",
            section = customColorSection,
            position = 203
    )
    default Color unripeColor() {
        return Color.YELLOW;
    }

    @ConfigItem(
            keyName = "matureColor",
            name = "Mature",
            description = "",
            section = customColorSection,
            position = 204
    )
    default Color matureColor() {
        return Color.GREEN;
    }

    @ConfigItem(
            keyName = "diseasedColor",
            name = "Diseased",
            description = "",
            section = customColorSection,
            position = 205
    )
    default Color diseasedColor() {
        return Color.MAGENTA;
    }

    @ConfigItem(
            keyName = "deadColor",
            name = "Dead",
            description = "",
            section = customColorSection,
            position = 206
    )
    default Color deadColor() {
        return Color.RED;
    }

    @AllArgsConstructor
    enum OverlayType {
        HULL("Hull"),
        OUTLINE("Outline"),
        TILE("Tile");

        private final String value;

        @Override
        public String toString() {
            return value;
        }
    }
}
