# Herb Patch Overlay

Renders colored overlays on herb patches to easily see if herb is ready to be harvested.

## Installation Instructions

This plugin is designed for use within the open source OSRS client [RuneLite](https://runelite.net/) which is free to use.

To install the plugin, start up the RuneLite client and search for the *Herb Patch Overlay* plugin in the Plugin Hub.
Detailed instructions on how to achieve this can be found on the [RuneLite wiki](https://github.com/runelite/runelite/wiki/Information-about-the-Plugin-Hub).

## Configuration

Plugin settings are found in the RuneLite plugin configuration panel.

| Option                   | Description                                       |
|--------------------------|---------------------------------------------------|
| Herb Overlay Color       | Section where user can set custom overlay colors. |
| Render overgrown overlay | Render overlay on overgrown herb patches.         |
| Render empty overlay     | Render overlay on empty herb patches.             |

### Default colors

| Herb      |    Hex    |                        Color                         |
|:----------|:---------:|:----------------------------------------------------:|
| Overgrown | `#FF0000` | ![#FF0000](https://placehold.co/15x15/FF0000/FF0000) |
| Empty     | `#FF0000` | ![#FF0000](https://placehold.co/15x15/FF0000/FF0000) |
| Unripe    | `#FFFF00` | ![#FFFF00](https://placehold.co/15x15/FFFF00/FFFF00) |
| Mature    | `#00FF00` | ![#00FF00](https://placehold.co/15x15/00FF00/00FF00) |
| Diseased  | `#FF00FF` | ![#FF00FF](https://placehold.co/15x15/FF00FF/FF00FF) |
| Dead      | `#FF0000` | ![#FF0000](https://placehold.co/15x15/FF0000/FF0000) |

## Development

It is recommended to use the [IntelliJ IDEA](https://www.jetbrains.com/idea/) development environment for this project.

Useful resources include the [RuneLite wiki](https://github.com/runelite/runelite/wiki) and the [OSRS wiki variable index](https://oldschool.runescape.wiki/w/Property:Variable_index).