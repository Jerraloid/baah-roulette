package org.jerraloid.baahroulette.model.command;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class ComplimentCommand extends AbstractCommand {

	/**
	 * Custom constructor
	 * 
	 * @param command Commandname
	 * @param alias Other commandnames
	 * @param description Description of what it does
	 * @param usage How to use the command
	 */
	public ComplimentCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * Default constructor
	 */
	public ComplimentCommand() {
		super("callout", Arrays.asList("compliment"), "Gives someone a compliment.", Ref.PREFIX + "callout [someone]");
	}
	
	/**
	 * Gives someone a compliment
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		//swear at someone
		if(!(parameters == null)) {
			if (!parameters.isEmpty()) {
				channel.sendMessage(parameters.get(0) + " you are a " + getCompliment() + ".").queue();
				return "";
			}
		}
		
		//swear at yourself
		channel.sendMessage(author.getAsMention() + " you are a " + getCompliment() + ".").queue();
		return "";
	}
	
	/**
     * Gets a random compliment
     * 
     * @return a compliment
     */
    private static String getCompliment() {
    	Random randomizer = new Random();
		String compliment = Ref.COMPLIMENTS[randomizer.nextInt(Ref.COMPLIMENTS.length)];
		return compliment;
    }

	
}
