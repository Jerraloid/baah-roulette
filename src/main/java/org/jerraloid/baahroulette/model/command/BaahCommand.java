package org.jerraloid.baahroulette.model.command;

import java.util.ArrayList;
import java.util.List;

import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class BaahCommand extends AbstractCommand {

	/**
	 * custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public BaahCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * default constructor
	 */
	public BaahCommand() {
		super("baah", new ArrayList<>(), "Baah!", "Baah.");
	}

	/**
	 * executes the BAAH command
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		if((author.getName() + "#" + author.getDiscriminator()).equals("God Baah#9429")) {
			channel.sendMessage("Baah? *(Good day my lord, is there anything I can do for you?)*").queue();
		} else {
			channel.sendMessage("Baah!").queue();
		}
		
		return "";
	}
	
}
