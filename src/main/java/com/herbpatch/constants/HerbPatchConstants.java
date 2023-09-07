package com.herbpatch.constants;

import java.util.Arrays;
import java.util.List;

import static net.runelite.api.NullObjectID.*;

/**
 * Class containing constants like ObjectIDs for the various herb patches around Gilenor.
 */
public class HerbPatchConstants {
    public static final int FALADOR     = NULL_8150;
    public static final int PHASMATYS   = NULL_8153;
    public static final int CATHERBY    = NULL_8151;
    public static final int ARDOUGNE    = NULL_8152;
    public static final int HOSIDIUS    = NULL_27115;
    public static final int STRONGHOLD  = NULL_18816;
    public static final int HARMONY     = NULL_9372;
    public static final int WEISS       = NULL_33313;
    public static final int GUILD       = NULL_33979;

    public static final List<Integer> allHerbObjects = Arrays.asList(FALADOR, PHASMATYS, CATHERBY, ARDOUGNE, HOSIDIUS, STRONGHOLD, HARMONY, WEISS, GUILD);
}

