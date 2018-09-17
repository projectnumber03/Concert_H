package core;

import models.Song;
import models.User;
import services.UserService;

import java.util.*;

public class System {
    private TreeMap<Song, Integer> trackList = new TreeMap<>(); //Список песен
    private User currentUser; //Пользователь, работающий с системой в данный момент

    //Метод для вывода полного списка песен
    private void getTrackList() {
        for (Map.Entry<Song, Integer> item : trackList.entrySet()){
            java.lang.System.out.println(item.getKey().getName() + " (" +item.getValue() + " голосов)");
        }
    }
    //Метод для вывода 5 самых востребованных песен
    private void getTopFive(){
        Map<Song, Integer> sortedMap = sortByValues(trackList);
        Set<Map.Entry<Song, Integer>> set = sortedMap.entrySet();
        Iterator<Map.Entry<Song, Integer>> i = set.iterator();
        if (sortedMap.size() >= 5){
            for (int j = 0; j < 5; j++) {
                Map.Entry<Song, Integer> me = i.next();
                java.lang.System.out.print(me.getKey().getName() + ": ");
                java.lang.System.out.println(me.getValue() + " голосов");
            }
        }else {
            java.lang.System.out.println("В списке еще недостаточно песен");
        }
    }
    //Регистрация нового пользователя
    private void register(String name, String password){
        UserService userService = new UserService();
        User user;
        try {
            user = userService.findUserByName(name);
            java.lang.System.out.println("Пользователь " + name + " уже существует в системе");
        } catch (Exception e){
            user = new User(name, password);
            userService.saveUser(user);
            java.lang.System.out.println("Пользователь " + name + " добавлен в систему");
        }
    }
    //Вход пользователя в систему
    private void login(String name, String password){
        boolean fail = true;
        UserService userService = new UserService();
        try{
            User user = userService.findUserByName(name);
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                user.setLoggedIn(true);
                currentUser = user;
                java.lang.System.out.println("Вы вошли как " + name);
                fail = false;
            }
        }catch (Exception e){
            //todo
        }
        if (fail) java.lang.System.out.println("Пользователь отсутствует в системе или введены неверные имя/пароль");
    }
    //Разлогин пользователя
    private void exit(){
        currentUser.setLoggedIn(false);
        java.lang.System.out.println("Пользователь " + currentUser.getName() + " вышел из системы");
        currentUser = null;
    }
    //Метод добавления песни в список
    public void addSong(Song song){
        if(trackList.containsKey(song)){
            trackList.replace(song, trackList.get(song) + 1);
        } else {
            trackList.put(song, 1);
        }
    }
    //Метод исполнения комманд
    public void execute(String command){
        Scanner scan = new Scanner(java.lang.System.in);
        String name;
        String password;
        String songName;
        switch (command){
            case "?":
                getCommandList();
                break;
            case "r":
                java.lang.System.out.println("Введите имя пользователя:");
                name =  scan.nextLine();
                java.lang.System.out.println("Введите пароль:");
                password = scan.nextLine();
                register(name, password);
                break;
            case "l":
                java.lang.System.out.println("Введите имя пользователя:");
                name =  scan.nextLine();
                java.lang.System.out.println("Введите пароль:");
                password = scan.nextLine();
                login(name, password);
                break;
            case "exit":
                if(currentUser != null){
                    exit();
                }
                break;
            case "v":
                if(currentUser != null){
                    java.lang.System.out.println("Введите название песни:");
                    songName = scan.nextLine();
                    currentUser.vote(songName);
                }else {
                    java.lang.System.out.println("Вы не зарегистрированы");
                }
                break;
            case "top":
                if(currentUser != null){
                    getTopFive();
                }else {
                    java.lang.System.out.println("Вы не зарегистрированы");
                }
                break;
            case "list":
                if(currentUser != null){
                    getTrackList();
                }else {
                    java.lang.System.out.println("Вы не зарегистрированы");
                }
                break;
            case "close":
                java.lang.System.exit(0);
                break;
            default:
                java.lang.System.out.println("Команда введена некорректно");
                break;
        }
    }
    //Вывод списка комманд для управления системой
    public void getCommandList(){
        java.lang.System.out.println("Список комманд:");
        java.lang.System.out.println("? - отобразить список комманд");
        java.lang.System.out.println("r - зарегистрировать нового пользователя");
        java.lang.System.out.println("l - войти под существующей учетной записью");
        java.lang.System.out.println("v - голосовать");
        java.lang.System.out.println("top - отобразить топ5");
        java.lang.System.out.println("exit - выйти из системы");
        java.lang.System.out.println("close - завершить работу");
    }
    //Компаратор для сортировки голосов
    private static <Song, Integer extends Comparable<Integer>> Map<Song, Integer>
    sortByValues(final Map<Song, Integer> map) {
        Comparator<Song> valueComparator =
                (k1, k2) -> {
                    int compare = map.get(k1).compareTo(map.get(k2));
                    if (compare == 0)
                        return 1;
                    else
                        return -compare;
                };
        Map<Song, Integer> sortedByValues = new TreeMap<>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}
