package application.Engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jens on 02/12/2016.
 */
public class FakeDataBase {


    private Map<String,bulletInfo> mymap = new HashMap<String,bulletInfo>(){
        {
            put("Spear",new bulletInfo(200));
            put("Arrow",new bulletInfo(100));
            put("Bolt",new bulletInfo(50));
        }
    };
    public long getTimeFromWeapon(String weapon){
        return mymap.get(weapon).getTime();
    }


    public class bulletInfo{

        private long time;


        public bulletInfo(long time){
            this.time = time;
        }

        public long getTime(){
            return this.time;
        }


    }

}
