package com.herbpatch.constants;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Enum representing stages of growth for herbs.
 * Each enum corresponds to a specific stage of growth and includes all varbit values associated with that stage.
 *
 * @see <a href="https://oldschool.runescape.wiki/w/RuneScape:Varbit">Wiki</a>
 * @see <a href="https://oldschool.runescape.wiki/w/Property:Variable_index">Varbit index</a>
 */
@Slf4j
public enum HerbPatchStages {
    OVERGROWN(0, 1, 2),
    EMPTY(3),
    GROWN(8,9,10,15,16,17,22,23,24,29,30,31,36,37,38,43,44,45,50,51,52,57,58,59,72,73,74,79,80,81,86,87,88,93,94,95,100,101,102,107,108,109),
    DISEASED(128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169),
    DEAD(170,171,172),
    GROWING;

    private final int[] numbers;

    HerbPatchStages(int... numbers) {
        this.numbers = numbers;
    }

    /**
     * Checks if given varbit value is a part of this stage of growth
     * @param number State of herb object
     */
    public boolean contains(int number) {
        return Arrays.stream(numbers).anyMatch(n -> n == number);
    }

    /**
     * Get the enum corresponding to a given varbit number
     * @param number The varbit number
     * @return The corresponding enum
     */
    public static HerbPatchStages getHerbStage(int number) {
        for (HerbPatchStages stage : HerbPatchStages.values()) {
            if (stage.contains(number)) {
                log.trace("Herb [" + number + "] is " + stage);
                return stage;
            }
        }
        log.trace("Herb [" + number + "] is " + GROWING);
        return GROWING;
    }
}
