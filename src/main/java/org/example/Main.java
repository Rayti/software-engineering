package org.example;

import org.example.instruments.Drum;
import org.example.instruments.Guitar;
import org.example.instruments.Song;
import org.example.instruments.SongPart;
import org.example.instruments.Violin;

public class Main {
    public static void main(String[] args) {
        System.out.println("Your sound:");

        // defaultSong();
        // defaultSongLasting5Seconds();
        // nextSongPart();
        // songWith1Instrument();
        songWithAllInstruments();
    }

    public static void defaultSong() {
        new Song().play();
    }

    public static void defaultSongLasting5Seconds() {
        new Song()
            .secondsSongLength(5)
            .play();
    }

    public static void songWith1Instrument() {
        new Song()
            .secondsSongLength(8)
            .withInstrument(new Drum().songPartsToPlayIn(SongPart.SECOND, SongPart.FOURTH).soundDelay(250))
            .logSongParts(true)
            .logTimeBoxes(500)
            .play();
    }

    public static void songWithAllInstruments() {
        new Song()
            .secondsSongLength(20)
            .withInstrument(new Guitar().songPartsToPlayIn(SongPart.FIRST, SongPart.SECOND, SongPart.THIRD)
                .soundDelay(400))
            .withInstrument(new Violin().songPartsToPlayIn(SongPart.SECOND).soundDelay(200))
            .withInstrument(
                new Drum().songPartsToPlayIn(SongPart.FIRST, SongPart.SECOND, SongPart.THIRD, SongPart.FOURTH)
                    .soundDelay(1000))
            .withInstrument(new Guitar().songPartsToPlayIn(SongPart.FOURTH).soundDelay(50))
            .logSongParts(true)
            .logTimeBoxes(1000)
            .play();
    }

    public static void nextSongPart() {
        boolean result = SongPart.nextPart(SongPart.THIRD).equals(SongPart.FOURTH);
        System.out.println(result);

        result = SongPart.nextPart(SongPart.FOURTH).equals(SongPart.FIRST);
        System.out.println(result);
    }

}