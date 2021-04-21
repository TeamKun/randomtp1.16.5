package kun.randomtp1_16_5;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
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
        //TODO Bukkit.selectEntities()を作ってセレクタ引数を追加する
        if(cmd.getName().equalsIgnoreCase("exerandomTp")){

            Player p = (Player)sender;
            Random r = new Random();
            double playerX = p.getLocation().getX();
            double playerZ = p.getLocation().getZ();

            Collection<? extends Player> players = Bukkit.getOnlinePlayers();

            for (Player player : players) {
                double x = r.nextInt(radius * 2) - radius + playerX;
                double z = r.nextInt(radius * 2) - radius + playerZ;

                Location location = new Location(p.getWorld(), x,255,z);
                player.teleport(location);
            }
            sender.sendMessage("ランダムTPを実行しました");

            return true;
        }
        if(cmd.getName().equalsIgnoreCase("setRadius")){
            if (args.length != 1){sender.sendMessage("/setRadius <TPの半径>");return false;}

            radius = Integer.parseInt(args[0]);
            sender.sendMessage("半径を" + args[0] + "に設定しました");
            return true;
        }
        return false;
    }
}
