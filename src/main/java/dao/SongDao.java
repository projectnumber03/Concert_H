package dao;

import models.Song;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class SongDao {
    public Song findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Song.class, id);
    }

    public void save(Song song) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(song);
        tx1.commit();
        session.close();
    }

    public void update(Song song) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(song);
        tx1.commit();
        session.close();
    }

    public void delete(Song song) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(song);
        tx1.commit();
        session.close();
    }

    public Song findSongById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Song.class, id);
    }

    public Song findSongByName(String name) {
        return (Song) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Song where name = :name").setParameter("name", name).list().get(0);
    }

    public List<Song> findAll() {
        List<Song> songs = (List<Song>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Song").list();
        return songs;
    }
}
