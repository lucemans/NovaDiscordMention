package nl.lucemans.mention;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.core.OnlineStatus;
import github.scarsz.discordsrv.dependencies.jda.core.entities.Member;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by MrDis at 14/04/2018
 */
public class DiscordMention extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

    }

    @EventHandler
    public void tabComplete(TabCompleteEvent event) {
        if (event.getBuffer().startsWith("/"))
            return;
        String lastWord = event.getBuffer().split(" ")[event.getBuffer().split(" ").length - 1];
        if (!lastWord.startsWith("@"))
            return;
        DiscordSRV.getPlugin().getMainTextChannel();
        List<Member> members  = DiscordSRV.getPlugin().getMainTextChannel().getMembers();
        ArrayList<String> players = new ArrayList<String>();
        for (Member m : members) {
            if (m.getOnlineStatus() == OnlineStatus.ONLINE || m.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB)
                players.add(m.getAsMention());
        }

        for (String comp : players)
            if (comp.startsWith(lastWord))
                event.getCompletions().add(comp);
    }
}
