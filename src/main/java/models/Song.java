package models;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song implements Comparable<Song>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public Song() {

    }

    public String getName() {
        return name;
    }

    public Song(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Song o) {
        return this.getName().compareTo(o.getName());
    }
}
