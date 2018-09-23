package org.jerraloid.baahroulette.model.command;

import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class SetRolesCommand extends AbstractCommand {

	/**
	 * custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public SetRolesCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * default constructor
	 */
	public SetRolesCommand() {
		super("setroles", Arrays.asList("roles", "randomroles", "doroles"), "Randomizes PvM roles.", "roles [boss]");
	}

	/**
	 * Randomly assignes roles to teammembers
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		//TODO: dit
		return null;
	}

}
