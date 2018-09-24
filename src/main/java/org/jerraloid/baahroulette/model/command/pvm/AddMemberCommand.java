package org.jerraloid.baahroulette.model.command.pvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class AddMemberCommand extends AbstractCommand {

	/**
	 * custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public AddMemberCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * default constructor
	 */
	public AddMemberCommand() {
		super("addmember", Arrays.asList("add"), "Adds a member to the team.", Ref.PREFIX + "addmember [member]");
	}
	
	/**
	 * adds a member to the team
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		//get unique id from guild
		String guildHash = guild.toString();
		
		//if the discordserver doesn't have a team, make a new one
    	if(!Ref.bossTeams.containsKey(guildHash)) {
    		Ref.bossTeams.put(guildHash, new ArrayList<>());
    	}
		    	
		//checks parameters
		if(!(paramsValid(parameters))) {
			channel.sendMessage("Baah? *(Wait... Who did you want to add to the team?)*").queue();
			return "";
		}
		
		//adds a member to the team
		String member = parameters.get(0);
		Ref.bossTeams.get(guildHash).add(member);
		channel.sendMessage("Added '" + member  + "' to the team!").queue();
		
		return "";
	}
	
	/**
	 * Checks if parameters are valid
	 * 
	 * @param parameters Parameters
	 * @return true or false
	 */
	private boolean paramsValid(List<String> parameters) {
		if(parameters == null) {
			return false;
		}
		
		if(parameters.isEmpty()) {
			return false;
		}
		
		return true;
	}
}
