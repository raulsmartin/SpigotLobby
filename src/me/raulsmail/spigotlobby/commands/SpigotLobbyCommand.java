package me.raulsmail.spigotlobby.commands;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by raulsmail.
 */
public class SpigotLobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (SpigotLobby.getPlugin().getCommonUtilities().hasPlayerPermission(player, "spigotlobby")) {
                if (args == null || args.length <= 0) {
                    SpigotLobby.getPlugin().getCommonUtilities().showHelp(player, 1);
                } else {
                    if (args.length >= 1) {
                        String option = args[0];
                        if (option.equalsIgnoreCase("help")) {
                            Integer page = 1;
                            if (args.length > 1) {
                                String number = args[1];
                                if (number != null) {
                                    page = SpigotLobby.getPlugin().getCommonUtilities().getInteger(player, number);
                                }
                            }
                            SpigotLobby.getPlugin().getCommonUtilities().showHelp(player, page);
                        } else if (option.equalsIgnoreCase("menu")) {
                            if (args.length > 1) {
                                String type = args[1].toLowerCase();
                                if (!type.isEmpty()) {
                                    switch (type) {
                                        case "cosmetics":
                                            SpigotLobby.getPlugin().getCommonUtilities().getMenus().getCosmeticsMenu().openMainMenu(SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer(player));
                                            return false;
                                        case "options":
                                            SpigotLobby.getPlugin().getCommonUtilities().getMenus().getOptionsMenu().openMainMenu(SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer(player));
                                            return false;
                                    }
                                }
                            }
                            player.sendMessage(SpigotLobby.getPlugin().getMessages().getString("commands.specifyMenuType").replaceAll("&", "§"));
                        }
                    }
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
