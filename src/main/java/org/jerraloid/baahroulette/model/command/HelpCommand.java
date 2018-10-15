package org.jerraloid.baahroulette.model.command;

import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.handler.CommandHandler;
import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.MiscUtil;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class HelpCommand extends AbstractCommand {

	/**
	 * custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public HelpCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * default constructor
	 */
	public HelpCommand() {
		super("help", Arrays.asList("commands", "functions"), "Shows you a list of all the bot commands.", Ref.PREFIX + "help [command]");
	}
	
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		//if there are valid parameters
		if(paramsValid(parameters)) {
			//gets the command from the parameters
			AbstractCommand command = CommandHandler.getCommand(parameters.get(0));
			
			//if it exists
			if(!(command == null)) {
				//show usage
				MiscUtil.sendEmbedMessage(channel, command.getCommand() + " usage:", command.getUsage());
			}
			else {
				//if not, show that its not a command
				channel.sendMessage(parameters.get(0) + " is not a command.").queue();
			}
			
		} else {
			StringBuilder sb = new StringBuilder();
			
			//for each command there is, read out the description
			for(String description : CommandHandler.getCommandHelp()) {
				sb.append(description + "\n");
			}
			
			//send all command descriptions
			MiscUtil.sendEmbedMessage(channel, "Bot Commands", sb.toString());
		}
			
		return "";
	}
	
	/**
	 * checks if the parameters are valid
	 * 
	 * @param parameters Parameters
	 * @return true or false
	 */
	private boolean paramsValid(List<String> parameters) {
		//no boss parameter
		if(parameters == null) {
			return false;
		}
		
		if(parameters.isEmpty()) {
			return false;
		}
		
		return true;
	}
}
