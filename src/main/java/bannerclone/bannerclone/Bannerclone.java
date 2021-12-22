package bannerclone.bannerclone;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Bannerclone extends JavaPlugin {

    @Override
    public void onEnable() {
        new File("banners").mkdirs();
        System.out.println("Max Croft banner clone is enabled");
        this.getCommand("bannerCreate").setExecutor(new BannerCraft());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
