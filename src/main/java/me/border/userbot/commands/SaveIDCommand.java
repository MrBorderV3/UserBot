package me.border.userbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.User;

public class SaveIDCommand extends Command {

    public SaveIDCommand(){
        this.name = "saveid";
        this.help = "Save your ID in the database";
    }

    @Override
    protected void execute(CommandEvent e) {
        if (!e.getArgs().equalsIgnoreCase(""))
            e.reply("Invalid usage!");

        User user = e.getAuthor();

    }
}
