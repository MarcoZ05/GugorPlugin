package gugor.gugor;

import org.bukkit.entity.Player;

public class location {
    public static String getDimension(Player p) {
        String dimension;
         switch(p.getWorld().getEnvironment()) {
             case NORMAL:
                 dimension = "overworld";
             break;
             case NETHER:
                 dimension = "nether";
             break;
             case THE_END:
                 dimension = "end";
             break;
             default:
                 dimension = "unknown";
                 break;
         }
        return dimension;
    }
}
