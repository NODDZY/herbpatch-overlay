package com.herbpatch.constants;

import java.util.Arrays;
import java.util.List;

import static net.runelite.api.gameval.ObjectID.*;

/**
 * Class containing ObjectIDs for the various herb patches around Gilenor
 */
public class HerbPatch {
    public static final int FALADOR    = FARMING_HERB_PATCH_1;
    public static final int CATHERBY   = FARMING_HERB_PATCH_2;
    public static final int ARDOUGNE   = FARMING_HERB_PATCH_3;
    public static final int PHASMATYS  = FARMING_HERB_PATCH_4;
    public static final int HARMONY    = FARMING_HERB_PATCH_5;
    public static final int STRONGHOLD = MYARM_HERBPATCH;
    public static final int HOSIDIUS   = FARMING_HERB_PATCH_6;
    public static final int WEISS      = MY2ARM_HERBPATCH_FAKE;
    public static final int GUILD      = FARMING_HERB_PATCH_7;
    public static final int VARLAMORE  = FARMING_HERB_PATCH_8;

    public static final List<Integer> allHerbObjects = Arrays.asList(FALADOR, PHASMATYS, CATHERBY, ARDOUGNE, HOSIDIUS, STRONGHOLD, HARMONY, WEISS, GUILD, VARLAMORE);
}
