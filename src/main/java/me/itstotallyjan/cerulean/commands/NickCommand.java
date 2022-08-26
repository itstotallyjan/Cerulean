package me.itstotallyjan.cerulean.commands;

import games.negative.framework.command.Command;
import games.negative.framework.command.annotation.CommandInfo;
import games.negative.framework.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name="nick",description="Changes your nickname.",aliases={"nickname"},playerOnly=true,permission="cerulean.nick")
public class NickCommand extends Command {
    @Override
    public void onCommand(CommandSender commandSender, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0) {
            player.setDisplayName(player.getName());
            new Message(ChatColor.GREEN + "Your nickname has been reset.").send(player);
        }
        if (strings.length == 1) {
            player.setDisplayName(strings[0]);
            new Message(ChatColor.GREEN + "Your nickname has been set to %.".replace("%", strings[0])).send(player);
        }
        if (strings.length == 2 && commandSender.hasPermission("cerulean.nick.others")) {
            Player target = Bukkit.getPlayerExact(strings[0]);
            if (target == null) {
                new Message(ChatColor.RED + "Could not find %.".replace("%", strings[0])).send(player);
                return;
            }
            target.setDisplayName(strings[1]);
            new Message(ChatColor.GREEN + "The nickname of % has been set to #.".replace("%", target.getName()).replace("#", strings[1])).send(player);
        }
    }
}
