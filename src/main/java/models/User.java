package models;

import services.SongService;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "isloggedin")
    private boolean isLoggedIn;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
        isLoggedIn = false;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void vote(String name){
        if(isLoggedIn){
            Song song = new Song(name);
            SongService songService = new SongService();
            core.Main.sys.addSong(song);
            System.out.println("Отдан голос за песню: " + name);
            try {
                song = songService.findSongByName(name);

            }catch (Exception e){
                songService.saveSong(song);
            }
        } else {
            System.out.println("Вы не зарегистрировались в системе");
        }
    }
}
