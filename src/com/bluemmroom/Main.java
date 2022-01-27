package com.bluemmroom;

import com.bluemmroom.model.Artist;
import com.bluemmroom.model.Datasource;
import com.bluemmroom.model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
        if (artists == null) {
            System.out.println("No artists!");
            return;
        }

        artists.forEach(s -> System.out.println("ID = " + s.getId() + ", Name " + s.getName()));

        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Pink Floyd", Datasource.ORDER_BY_ASC);

        albumsForArtist.forEach(System.out::println);

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Heartless", datasource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        songArtists.forEach(s -> System.out.println("Artist name = " + s.getArtistName() +
                " Album name = " + s.getAlbumName() +
                " Track = " + s.getTrack()));

        datasource.close();
    }
}
