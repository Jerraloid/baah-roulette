package org.jerraloid.baahroulette.handler;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.Ref;
import org.reflections.Reflections;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class CommandHandler {

	//command lists
	private static final List<String> commandHelp = new ArrayList<>();
	private static final HashMap<String, AbstractCommand> commands = new HashMap<>();
	private static final HashMap<String, AbstractCommand> commandsAlias = new HashMap<>();
	
	/**
	 * Handles the message
	 * 
	 * @param msg Message
	 * @param author The one who sends the message
	 * @param guild The discord server where the message is sent
	 * @param channel The channel where the message is sent
	 */
	public static void handle(Message msg, User author, Guild guild, MessageChannel channel) {
		String logMessage = "";
		String inputMessage = msg.getContentRaw();
		
		if(!inputMessage.startsWith(Ref.PREFIX)) {
			return;
		}
		
		//removes prefix from message
		inputMessage = inputMessage.replace(Ref.PREFIX, "").trim();
		
		//if the bot can't talk in the chat, return;
		if(!((TextChannel)channel).canTalk()) {
			return;
		}
		
		//if the author is a bot, return;
		if(author.isBot()) {
			return;
		}
		
		//get input and parameters/arguments
		List<String> input = Arrays.asList(inputMessage.split(" "));
		List<String> parameters = null;
		
		if(input.size() > 1) {
			parameters = input.subList(1, input.size());
		}
		
		//get the right command
		if(commands.containsKey(input.get(0)) || commandsAlias.containsKey(input.get(0))) {
			AbstractCommand command = commands.containsKey(input.get(0)) ? commands.get(input.get(0)) : commandsAlias.get(input.get(0));
			
			//executes command
			logMessage = command.execute(parameters, author, guild, channel);
			
			//logs message (doesn't save the log yet)
			if (logMessage == null) logMessage = "";
			if(!(logMessage.isEmpty())) System.out.println(logMessage);
			
		}
		else {
			channel.sendMessage("Baah... *(I didn't recognize the command...)*").queue();
			channel.addReactionById(msg.getIdLong(), "U+1F914");
		}
	}
	
	/**
	 * Initializes all commands, aliases
	 */
	public static void init() {
		loadCommands();
		loadAliases();
		loadCommandHelp();
	}
	
	/**
	 * Loads in all commands
	 */
	private static void loadCommands() {
		//gets classes to read
		Reflections reflections = new Reflections("org.jerraloid.baahroulette.model.command");
		Set<Class<? extends AbstractCommand>> classes = reflections.getSubTypesOf(AbstractCommand.class);
		
		//for each class
		for(Class<? extends AbstractCommand> s : classes) {
			try {
				//has to be abstract
				if(Modifier.isAbstract(s.getModifiers())) {
					continue;
				}
				
				//make new instance
				AbstractCommand c = s.getConstructor().newInstance();
				
				//is enabled
				if(!c.isEnabled()) {
					continue;
				}
				
				//add it to the commandlist.
				commands.put(c.getCommand(), c);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * loads all aliases
	 */
	private static void loadAliases() {
		//for every command
		for(AbstractCommand command : commands.values()) {
			//read out all the aliases
			for(String alias : command.getAlias()) {
				//put them in the aliaslist
				commandsAlias.put(alias, command);
			}
		}
	}
	
	/**
	 * loads all information about a command
	 */
	private static void loadCommandHelp() {
		for(AbstractCommand command : commands.values()) {
			StringBuilder sb = new StringBuilder();
			
			//add main command to message
			sb.append("**" + command.getCommand() + "** ");
			
			//add aliases to message
			for(String alias : command.getAlias()) {
				sb.append("| **" + alias + "** ");
			}
			
			//add description
			sb.append("- " + command.getDescription());

			//add them to the list
			commandHelp.add(sb.toString());
		}
	}
	
	/**
	 * Checks if the message is a command
	 * 
	 * @param msg message
	 * @return true or false
	 */
	public static boolean isCommand(String msg) {
		return msg.startsWith(Ref.PREFIX);
	}
	
	/**
	 * returns one command by key/name
	 * 
	 * @param key The key value in the hashmap
	 * @return one command
	 */
	public static AbstractCommand getCommand(String key) {
		//removes prefix
		if(key.startsWith(Ref.PREFIX)) {
			key = key.substring(Ref.PREFIX.length()).trim();
		}
		
		//gets right command by name
		if(commands.containsKey(key)) {
			return commands.get(key);
		}
		
		//gets right command by alias
		if(commandsAlias.containsKey(key)) {
			return commandsAlias.get(key);
		}
		
		//returns null if not exist
		return null;
	}
	
	/**
	 * getter for commandhelp
	 * 
	 * @return all command descriptions
	 */
	public static List<String> getCommandHelp() {
		return commandHelp;
	}
}
