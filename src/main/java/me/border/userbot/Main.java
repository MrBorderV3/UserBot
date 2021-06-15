package me.border.userbot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.border.userbot.commands.SaveIDCommand;
import me.border.userbot.constants.Credentials;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDA jda;
    private static CommandClient commandClient;

    public static void main(String[] args) throws LoginException {
        startCommandClient();
        startJDA();
    }

    private static void startCommandClient(){
        commandClient = new CommandClientBuilder()
                .setPrefix("1")
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
}
