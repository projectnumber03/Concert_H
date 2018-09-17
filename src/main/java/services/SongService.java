package services;

import dao.SongDao;
import models.Song;

import java.util.List;

public class SongService {
    private SongDao songDao = new SongDao();

    public SongService() {
    }

    public Song findSong(int id) {
        return songDao.findById(id);
    }

    public void saveSong(Song song) {
        songDao.save(song);
    }

    public void deleteSong(Song song) {
        songDao.delete(song);
    }

    public void updateSong(Song song) {
        songDao.update(song);
    }

    public List<Song> findAllSongs() {
        return songDao.findAll();
    }

    public Song findSongById(int id) {
        return songDao.findSongById(id);
    }

    public Song findSongByName(String name) {
        return songDao.findSongByName(name);
    }
}
