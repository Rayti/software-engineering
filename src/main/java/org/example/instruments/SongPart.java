package org.example.instruments;

import java.util.Arrays;

public enum SongPart {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4);

    private final int value;

    SongPart(int value) {
        this.value = value;
    }

    public static SongPart nextPart(SongPart songPart) {
        return getByValue(songPart.value + 1);
    }

    private static SongPart getByValue(final int val) {
        SongPart[] songParts = SongPart.values();
        int lastPartVal = songParts[songParts.length - 1].value;

        if (val > lastPartVal) {
            return songParts[songParts.length - 1];
        }

        return Arrays.stream(SongPart.values())
            .filter(songPart -> songPart.value == val)
            .findFirst()
            .orElseThrow();
    }
}
