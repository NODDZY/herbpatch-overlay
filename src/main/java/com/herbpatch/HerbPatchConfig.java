package com.herbpatch;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup("herbpatch-overlay")
public interface HerbPatchConfig extends Config {
    @ConfigSection(
            name = "Herb Overlay Color",
            description = "Custom overlay colors for herbs",
            position = 0
    )
    String customColorSection = "customColor";

    @ConfigItem(
            keyName = "overgrownColor",
            name = "Overgrown",
            description = "",
            section = customColorSection,
            position = 0
    )
    default Color overgrownColor() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "emptyColor",
            name = "Empty",
            description = "",
            section = customColorSection,
            position = 1
    )
    default Color emptyColor() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "unripeColor",
            name = "Unripe",
            description = "",
            section = customColorSection,
            position = 2
    )
    default Color unripeColor() {
        return Color.YELLOW;
    }

    @ConfigItem(
            keyName = "matureColor",
            name = "Mature",
            description = "",
            section = customColorSection,
            position = 3
    )
    default Color matureColor() {
        return Color.GREEN;
    }

    @ConfigItem(
            keyName = "diseasedColor",
            name = "Diseased",
            description = "",
            section = customColorSection,
            position = 4
    )
    default Color diseasedColor() {
        return Color.MAGENTA;
    }

    @ConfigItem(
            keyName = "deadColor",
            name = "Dead",
            description = "",
            section = customColorSection,
            position = 5
    )
    default Color deadColor() {
        return Color.RED;
    }
}
