package com.herbpatch.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static net.runelite.api.gameval.ObjectID.*;
// Transmog controllers for various farming patches
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_A; // Troll Stronghold, Weiss
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_B; // Harmony Island
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_D; // Falador, Port Phasmatys, Hosidius, Ardougne, Catherby, Varlamore
import static net.runelite.api.gameval.VarbitID.FARMING_TRANSMIT_E; // Farming Guild

/**
 * Enum containing ObjectIDs and VarbitIDs for the various herb patches around Gilenor
 */
public enum HerbPatch {
    FALADOR    (FARMING_HERB_PATCH_1,  FARMING_TRANSMIT_D),
    CATHERBY   (FARMING_HERB_PATCH_2,  FARMING_TRANSMIT_D),
    ARDOUGNE   (FARMING_HERB_PATCH_3,  FARMING_TRANSMIT_D),
    PHASMATYS  (FARMING_HERB_PATCH_4,  FARMING_TRANSMIT_D),
    HARMONY    (FARMING_HERB_PATCH_5,  FARMING_TRANSMIT_B),
    STRONGHOLD (MYARM_HERBPATCH,       FARMING_TRANSMIT_A),
    HOSIDIUS   (FARMING_HERB_PATCH_6,  FARMING_TRANSMIT_D),
    WEISS      (MY2ARM_HERBPATCH_FAKE, FARMING_TRANSMIT_A),
    GUILD      (FARMING_HERB_PATCH_7,  FARMING_TRANSMIT_E),
    VARLAMORE  (FARMING_HERB_PATCH_8,  FARMING_TRANSMIT_D);

    public final int objectId;
    public final int varbit;

    HerbPatch(int objectId, int varbit) {
        this.objectId = objectId;
        this.varbit = varbit;
    }

    private static final Map<Integer, HerbPatch> BY_ID = Arrays.stream(values()).collect(Collectors.toMap(p -> p.objectId, p -> p));

    public static HerbPatch byObjectId(int id) {
        return BY_ID.get(id);
    }
}
