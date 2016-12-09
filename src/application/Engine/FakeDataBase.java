package application.Engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jens on 02/12/2016.
 */
 class FakeDataBase {


    private Map<String,bulletInfo> mymap = new HashMap<String,bulletInfo>(){
        {
            put("Spear",new bulletInfo(200));
            put("Arrow",new bulletInfo(180));
            put("Bolt",new bulletInfo(160));
        }
    };

    long getTimeFromWeapon(String weapon){
        return mymap.get(weapon).getTime();
    }


    private class bulletInfo{

        private long time;


        bulletInfo(long time){
            this.time = time;
        }

        long getTime(){
            return this.time;
        }


    }

}
