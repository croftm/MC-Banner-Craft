package bannerclone.bannerclone;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.getServer;

public class BannerList implements CommandExecutor {
    private Server server;
    public BannerList(){
        server = getServer();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Boolean output = false;
        String[] bannerList = new File("banners").list();
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            String banners = "Banners saved:\n";
            for(int i=0;i<bannerList.length;i++){banners+=(bannerList[i]+="\n");}
            server.dispatchCommand(server.getConsoleSender(), "msg "+ player.getDisplayName() + " " + banners);
            output = true;
        }
        return output;
    }
}
