package chatz.chats;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static chatz.chats.MessageFormatter.format;
import static chatz.Main.getMessageRange;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMessageSend(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        Player p = e.getPlayer();
        char pre = msg.charAt(0);
        switch(pre){
            case '!':
                if(msg.substring(1).equals("")) e.setCancelled(true);
                e.setMessage(msg.substring(1));
                e.setFormat(format(p, ChatColor.DARK_GREEN + "!") + ChatColor.DARK_GREEN + "%2$s");
                break;
            case '?':
                if(msg.substring(1).equals("")) e.setCancelled(true);
                e.setMessage(msg.substring(1));
                e.setFormat(format(p, ChatColor.AQUA+ "?") + ChatColor.AQUA + "%2$s");
                break;
            case '$':
                if(msg.substring(1).equals("")) e.setCancelled(true);
                e.setMessage(msg.substring(1));
                e.setFormat(format(p, ChatColor.GOLD + "$") + ChatColor.GOLD + "%2$s");
                break;
            default:
                e.setCancelled(true);
                int radius = getMessageRange();
                for(Entity entity : p.getNearbyEntities(radius, radius, radius)){
                    if(entity instanceof Player){
                        entity.sendMessage(format(p, msg, p.getLocation().distance(entity.getLocation())));
                    }
                }
                p.sendMessage(format(p) + msg);
        }
    }
}
