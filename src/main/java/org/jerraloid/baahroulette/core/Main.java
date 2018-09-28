package org.jerraloid.baahroulette.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jerraloid.baahroulette.handler.CommandHandler;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;

/**
 * This app is gonna randomly assign roles to players
 * This is a Discord bot
 * I may add more stuff in the future
 * 
 * I got help from these: 
 * https://www.youtube.com/watch?v=59OllBJlU1c
 * https://medium.com/discord-bots/making-a-basic-discord-bot-with-java-834949008c2b
 * https://github.com/DV8FromTheWorld/JDA
 * https://github.com/Kaaz/DiscordBot
 * 
 * @author Jerry
 * 
 */
public class Main
{
	//De bot
	public static JDA jda;
	
	public static void main( String[] args ) throws Exception
    {
		//Reads out config.properties
    	loadConfig();
    	
    	if(Ref.TOKEN == "") {
    		System.out.println("Missing token in the config.properties.");
    		return;
    	}
    	
    	//init + connect the bot to the discord application
    	JDABuilder builder = new JDABuilder(AccountType.BOT);
    	builder.setToken(Ref.TOKEN);
    	builder.setAutoReconnect(true); //so the bot never disconnects
    	builder.setStatus(OnlineStatus.ONLINE);
    	
    	//this connects you to the discord
    	jda = builder.build();
    	
    	//inits all commands
    	CommandHandler.init();
    	
    	//adds new listener which can read the chat
    	jda.addEventListener(new MessageListener());
    }
    
    /**
     * Loads config.properties
     */
    public static void loadConfig() {
    	Properties prop = new Properties();
    	InputStream input = null;
    	
    	try {
    		//which file to load
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    		input = classloader.getResourceAsStream("config.properties");
    		
    		//load properties
    		prop.load(input);
    		
    		//bind values to variables
    		Ref.TOKEN = prop.getProperty("token");
    		Ref.PREFIX = prop.getProperty("prefix") + " ";
    		
    		//print prefix & token
    		System.out.println("Token set to: " + Ref.TOKEN);
    		System.out.println("Prefix set to: " + Ref.PREFIX);
    		
    	} 
    	catch (IOException ex) {
    		ex.printStackTrace();
    	} 
    	finally {
    		//close the connection
    		if(input != null) {
    			try {
    				input.close();
    			}
    			catch(IOException ex) {
    				ex.printStackTrace();
    			}
    		}
    	}
    }
    
    
}
