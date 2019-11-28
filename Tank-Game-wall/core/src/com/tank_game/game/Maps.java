package com.tank_game.game;

import java.util.ArrayList;

public class Maps {
    public ArrayList<Wall> map1;
    public ArrayList<Wall> map2;
    public ArrayList<Wall> map3;

    public Maps(){
        map1 = new ArrayList<Wall>();
        map2 = new ArrayList<Wall>();
        map3 = new ArrayList<Wall>();

        map1.add(new Wall(100,100,0));
        map1.add(new Wall(200,100,0));
        map1.add(new Wall(300,100,0));
        map1.add(new Wall(400,100,0));

        map2.add(new Wall(100,100,90));
        map2.add(new Wall(200,100,90));
        map2.add(new Wall(300,100,90));
        map2.add(new Wall(400,100,90));

        map3.add(new Wall(100,100,45));
        map3.add(new Wall(200,100,45));
        map3.add(new Wall(300,100,45));
        map3.add(new Wall(400,100,45));
    }

    public ArrayList<Wall> getMap(int map_num) {
        if(map_num==1){
            return map1;
        }
        if(map_num==2){
            return map2;
        }
        if(map_num==3){
            return map3;
        }
        System.out.println("non existing map");
        return null;
    }
}
