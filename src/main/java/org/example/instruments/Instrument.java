package org.example.instruments;

import java.util.Arrays;
import java.util.List;

public abstract class Instrument {

    private long timePlayed = 0;

    private long soundDelay;

    private List<SongPart> songPartsToPlayIn;

    public Instrument() {
        setDefaultInstrumentValues();
    }

    public void play() {
        System.out.println(getSound());
        this.timePlayed = System.currentTimeMillis();
    }

    public boolean isReadyToPlay(SongPart currentSongPart) {
        return System.currentTimeMillis() - this.timePlayed >= this.soundDelay
            && songPartsToPlayIn.contains(currentSongPart);
    }

    public Instrument soundDelay(long soundDelay) {
        this.soundDelay = soundDelay;
        return this;
    }

    public Instrument songPartsToPlayIn(SongPart... songParts) {
        this.songPartsToPlayIn = Arrays.asList(songParts);
        return this;
    }

    protected abstract String getSound();

    private void setDefaultInstrumentValues() {
        this.soundDelay = 1000;
        this.songPartsToPlayIn =
            Arrays.asList(SongPart.FIRST, SongPart.SECOND, SongPart.THIRD, SongPart.FOURTH);
    }
}
