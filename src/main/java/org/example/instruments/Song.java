package org.example.instruments;

import java.util.ArrayList;
import java.util.List;

public class Song {

    private long startTime;

    private long currentSongPartStartTime;

    private long songLengthSeconds;

    private long songPartLengthSeconds;

    private boolean logSongParts = false;

    private boolean logTimeBoxes = false;

    private long logTimeBoxStartTime;

    private long logTimeBox;

    private SongPart currentSongPart = SongPart.FIRST;

    private final List<Instrument> instruments = new ArrayList<>();

    public void play() {
        this.startTime = System.currentTimeMillis();
        this.currentSongPartStartTime = System.currentTimeMillis();
        this.logTimeBoxStartTime = System.currentTimeMillis();

        logSongPart();

        while (!isSongFinished()) {

            logTimeBox();
            setCurrentSongPart();

            instruments.forEach(instrument -> {
                if (instrument.isReadyToPlay(currentSongPart)) {
                    instrument.play();
                }
            });
        }
    }

    public Song withInstrument(Instrument instrument) {
        this.instruments.add(instrument);
        return this;
    }

    public Song secondsSongLength(long secondsSongLength) {
        this.songLengthSeconds = secondsSongLength;
        this.songPartLengthSeconds = secondsSongLength / 4;
        return this;
    }

    public Song logSongParts(boolean log) {
        this.logSongParts = log;
        return this;
    }

    public Song logTimeBoxes(long timebox) {
        this.logTimeBoxes = true;
        this.logTimeBox = timebox;
        return this;
    }

    private void logTimeBox() {
        if (this.logTimeBoxes && System.currentTimeMillis() - this.logTimeBoxStartTime >= this.logTimeBox) {
            System.out.println("\n" + (System.currentTimeMillis() - this.startTime) + "\n");
            this.logTimeBoxStartTime = System.currentTimeMillis();
        }
    }

    private void logSongPart() {
        if (this.logSongParts) {
            System.out.println("\n# " + this.currentSongPart + " song part:\n");
        }
    }

    private boolean isSongFinished() {
        return System.currentTimeMillis() - this.startTime >= this.songLengthSeconds * 1000;
    }

    private void setCurrentSongPart() {
        if (System.currentTimeMillis() - this.currentSongPartStartTime >= this.songPartLengthSeconds * 1000) {
            this.currentSongPart = SongPart.nextPart(this.currentSongPart);
            this.currentSongPartStartTime = System.currentTimeMillis();
            logSongPart();
        }
    }

}
