package com.herbpatch.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Enum corresponding to a specific stage of growth.
 * Includes all known herb patch varbit values associated with that stage.
 *
 * @see <a href="https://oldschool.runescape.wiki/w/Bucket:Varbit">Varbit index</a> for the FARMING_TRANSMIT
 * @see net.runelite.client.plugins.timetracking.farming.PatchImplementation#HERB
 */
public enum HerbStages {
    OVERGROWN(0,1,2),
    EMPTY(3),
    GROWN(8,9,10,15,16,17,22,23,24,29,30,31,36,37,38,43,44,45,50,51,52,57,58,59,64,65,66,72,73,74,79,80,81,86,87,88,93,94,95,100,101,102,107,108,109,196,197),
    DISEASED(128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,173,174,175,198,199,200),
    DEAD(170,171,172,201,202,203),
    GROWING(4,5,6,7,11,12,13,14,18,19,20,21,25,26,27,28,32,33,34,35,39,40,41,42,46,47,48,49,53,54,55,56,60,61,62,63,68,69,70,71,75,76,77,78,82,83,84,85,89,90,91,92,96,97,98,99,103,104,105,106,192,193,194,195),
    UNKNOWN;

    // Build HashMap for lookups during rendering
    private static final Map<Integer, HerbStages> BY_STATE;
    static {
        Map<Integer, HerbStages> map = new HashMap<>();
        for (HerbStages stage : values()) {
            for (int n : stage.numbers) {
                map.put(n, stage);
            }
        }
        BY_STATE = Collections.unmodifiableMap(map);
    }

    private final int[] numbers;

    HerbStages(int... numbers) {
        this.numbers = numbers;
    }

    /**
     * Get the enum corresponding to a given varbit number
     * @param number The varbit number
     * @return The corresponding enum
     */
    public static HerbStages getHerbStage(int number) {
        return BY_STATE.getOrDefault(number, UNKNOWN);
    }
}
