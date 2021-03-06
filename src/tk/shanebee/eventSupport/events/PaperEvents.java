package tk.shanebee.eventSupport.events;

import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
import com.destroystokyo.paper.event.entity.SkeletonHorseTrapEvent;
import com.destroystokyo.paper.event.entity.TurtleLayEggEvent;
import com.destroystokyo.paper.event.entity.WitchThrowPotionEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tk.shanebee.eventSupport.EventSupport;

public class PaperEvents implements Listener {
    private EventSupport plugin;

    public PaperEvents(EventSupport instance) {
        plugin = instance;
    }

    private FileConfiguration config() {
        return plugin.getConfig();
    }

    // Stops player's from jumping
    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        Player p = e.getPlayer();
        if(config().getBoolean("Player Events.Jump.Cancel")) {
            if(!p.hasPermission("eventhandler.bypass.jump")) {
                e.setCancelled(true);
                if(config().getBoolean("Player Events.Jump.Message.Enabled")) {
                    String msg = config().getString("Player Events.Jump.Message.Message");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // Stops anvils from breaking
    @EventHandler
    public void onAnvilBreak(AnvilDamagedEvent e) {
        if(config().getBoolean("Block Events.Anvil Break.Cancel")) {
            e.setCancelled(true);
        }
    }

    // Stops turtles from laying eggs
    @EventHandler
    public void onTurtleLayEgg(TurtleLayEggEvent e) {
        if(config().getBoolean("Entity Events.Turtle Lay Eggs.Cancel")) {
            e.setCancelled(true);
        }
    }

    // Stops skeleton traps from spawning
    @EventHandler
    public void onSkeletonTrap(SkeletonHorseTrapEvent e) {
        if(config().getBoolean("Entity Events.Skeleton Trap.Cancel")) {
            e.setCancelled(true);
        }
    }

    // Stops witches from throwing potions
    @EventHandler
    public void onWitchThrowPotion(WitchThrowPotionEvent e) {
        if(config().getBoolean("Entity Events.Witch Throw Potion.Cancel")) {
            e.setCancelled(true);
        }
    }


}
