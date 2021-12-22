package bannerclone.bannerclone;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.getServer;

public class BannerCraft implements CommandExecutor {
    private Server server;
    private Pattern pattern;
    private Matcher matcher;

    public BannerCraft(){
        server = getServer();
        pattern = Pattern.compile("BANNER");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Boolean output = false;
        if (sender instanceof Player && args.length>0)
        {
            Player player = (Player) sender;
            EntityEquipment equipment = player.getEquipment();
            if (equipment != null)
            {
                ItemStack holdingItem = equipment.getItemInMainHand();
                Material material = holdingItem.getType();
                matcher = pattern.matcher(material.toString());
                if(matcher.find())
                {
                    output = true;
                    try {
                        server.dispatchCommand(server.getConsoleSender(),
                                "msg "+ player.getDisplayName() + " " + saveBanner(args[0],holdingItem));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        matcher = null;
        return output;
    }
    private String saveBanner(String name, ItemStack banner) throws IOException {
        String output = "Command Failed";
        String[] bannerList = new File("banners").list();
        if (Arrays.asList(bannerList).contains(name))
        {
            output = "BANNER NAME TAKEN";
        }
        else
        {
            FileWriter fileWriter = new FileWriter("banners/"+name);
            fileWriter.write(banner.getItemMeta().toString());
            fileWriter.close();
            return "BANNER SAVED";
        }
        return output;
    }
}
