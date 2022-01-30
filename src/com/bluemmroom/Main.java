package com.bluemmroom;

import com.bluemmroom.model.Artist;
import com.bluemmroom.model.Datasource;
import com.bluemmroom.model.SongArtist;

import java.util.List;
import java.util.Scanner;

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

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", datasource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        songArtists.forEach(s -> System.out.println("Artist name = " + s.getArtistName() +
                " Album name = " + s.getAlbumName() +
                " Track = " + s.getTrack()));

        datasource.querySongsMetadata();

        datasource.getCount("Songs");

        datasource.createViewForSongArtists();

        // for SQL INJECTION
/*        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();

        songArtists = datasource.querySongInfoView(title);
        if (songArtists.isEmpty()) {
            System.out.println("Couldn.t find the artist for the song");
            return;
        }

        for (SongArtist songArtist : songArtists) {
            System.out.println("FROM VIEW - Artist name =  " + songArtist.getAlbumName() +
                    " Album name = " + songArtist.getAlbumName() +
                    " Track number = " + songArtist.getTrack());
        }*/

        datasource.insertSong("Touch of Grey", "Grateful Dead", "In the Dark", 1);
        datasource.close();
    }
}
