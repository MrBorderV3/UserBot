package me.border.userbot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.border.userbot.commands.SaveIDCommand;
import me.border.userbot.storage.MySQLDB;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDA jda;
    private static CommandClient commandClient;

    private static MySQLDB db;

    public static void main(String[] args) throws LoginException {
        startCommandClient();
        startJDA();

        db = new MySQLDB(Credentials.DB_HOST, Credentials.DB_DATABASE, Credentials.DB_USERNAME, Credentials.DB_PASSWORD, Credentials.DB_PORT);
        db.createIdsTable();
    }

    private static void startCommandClient(){
        commandClient = new CommandClientBuilder()
                .setPrefix("!")
                .setHelpWord("help")
                .setOwnerId("456802337030144011")
                .addCommands(new SaveIDCommand())
                .setActivity(Activity.of(Activity.ActivityType.WATCHING, "User IDS"))
                .build();
    }

    private static void startJDA() throws LoginException {
        jda = JDABuilder.createDefault(Credentials.TOKEN)
                .addEventListeners(commandClient)
                .setStatus(OnlineStatus.ONLINE)
                .build();
    }

    public static MySQLDB getDB() {
        return db;
    }
}
