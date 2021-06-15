package me.border.userbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.border.userbot.Main;
import me.border.utilities.scheduler.AsyncTasker;
import net.dv8tion.jda.api.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        String id = user.getId();
        AsyncTasker.runTaskAsync(() -> {
            ResultSet rs = Main.getDB().executeQuery("SELECT * FROM ids WHERE id='" + id + "';");
            try {
                if (rs.next()) {
                    e.reply("Your ID is already saved in the database!");
                    return;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
                e.reply("An error has occured, please let the bot owner know!");
                return;
            }

            Main.getDB().executeUpdate("INSERT INTO ids(id) VALUES ('" + user.getId() + "');");
            e.reply("Successfully saved your ID in the database.");
        });

    }
}
