package org.jerraloid.baahroulette.model.command;

import java.util.ArrayList;
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

	private final int MAX_MESSAGE_LENGTH = 1000;

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

			String fullMessage = sb.toString();
			int messageAmount = fullMessage.length() / MAX_MESSAGE_LENGTH;

			//check if the message is too large
			if (messageAmount > 0) {
				List<String> messageToSend = new ArrayList<>();
				messageToSend.add(fullMessage);
				int index = 0;

				//keep splitting the message
				while (!messageArrayValid(messageToSend)) {
					String splitMessage = messageToSend.get(index);
					int splitPosition = splitMessage.substring(0, (MAX_MESSAGE_LENGTH - 1)).lastIndexOf("\n");
					messageToSend.set(index, splitMessage.substring(0, splitPosition));
					messageToSend.add(splitMessage.substring(splitPosition));
					index++;
				}

				//send the message in multiple messages
				for(String messagePart : messageToSend) {
					MiscUtil.sendEmbedMessage(channel, "Bot Commands", messagePart);
				}
			} else {
				//send command descriptions
				MiscUtil.sendEmbedMessage(channel, "Bot Commands", fullMessage);
			}
		}
			
		return "";
	}

	/**
	 * checks the message length
	 *
	 * @param message
	 * @return
	 */
	private boolean messageArrayValid(List<String> message) {
		for (String messagePart : message) {
			if (messagePart.length() >= MAX_MESSAGE_LENGTH) {
				return false;
			}
		}

		return true;
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
