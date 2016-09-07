package me.raulsmail.spigotlobby.commands;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by raulsmail.
 */
public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (SpigotLobby.getPlugin().getCommonUtilities().hasPlayerPermission(player, "fly")) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage(SpigotLobby.getPlugin().getMessages().getString("commands.fly.disable").replaceAll("&", "§"));
                } else {
                    player.setAllowFlight(true);
                    player.setFlying(false);
                    player.sendMessage(SpigotLobby.getPlugin().getMessages().getString("commands.fly.enable").replaceAll("&", "§"));
                }
            } else {
                sender.sendMessage(SpigotLobby.getPlugin().getMessages().getString("general.noPermissions").replaceAll("&", "§"));
            }
        } else {
            sender.sendMessage(SpigotLobby.getPlugin().getMessages().getString("commands.notPlayer").replaceAll("&", "§"));
        }

        return false;
    }
}
