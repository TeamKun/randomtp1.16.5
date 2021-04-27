package kun.randomtp1_16_5;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public final class Randomtp1_16_5  extends JavaPlugin implements Listener {

    private int radius;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        radius = 500;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("exerandomTp")){
            if (args.length != 1){sender.sendMessage("/exerandomTp <プレイヤー>");return false;}

            List<Entity> entities = Bukkit.selectEntities(sender,args[0]);

            Player p = (Player)sender;
            Random r = new Random();
            double playerX = p.getLocation().getX();
            double playerZ = p.getLocation().getZ();

            for (Entity entity : entities) {
                double x = r.nextInt(radius * 2) - radius + playerX;
                double z = r.nextInt(radius * 2) - radius + playerZ;

                entity.teleport(new Location(p.getWorld(), x,255,z));
            }
            sender.sendMessage(String.format("%d体のエンティティをランダムTPしました",entities.size()));

            return true;
        }
        if(cmd.getName().equalsIgnoreCase("setRadius")){
            if (args.length != 1){sender.sendMessage("/setRadius <TPの半径>");return false;}

            try {
                radius = Integer.parseInt(args[0]);
                sender.sendMessage("半径を" + args[0] + "に設定しました");
                return true;

            }catch (Exception e){
                sender.sendMessage("/setRadius <TPの半径>");
                return false;
            }
        }
        return false;
    }
}
