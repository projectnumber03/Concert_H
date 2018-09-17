package core;

import services.SongService;
import services.UserService;

import java.util.Scanner;

public class Main {

    public static System sys = new System();

    public static void main(String[] args) {

        Scanner scan = new Scanner(java.lang.System.in);
        sys.getCommandList();
        while (true){
            try{
                String command = scan.nextLine();
                sys.execute(command);
            }catch (Exception e){
                break;
            }
        }
    }
}
