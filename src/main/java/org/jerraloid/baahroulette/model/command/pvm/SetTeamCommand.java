package org.jerraloid.baahroulette.model.command.pvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class SetTeamCommand extends AbstractCommand {

	/**
	 * custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public SetTeamCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * default constructor
	 */
	public SetTeamCommand() {
		super("setteam", Arrays.asList("update"), "Updates the team for PvM randomized roles.", Ref.PREFIX + "setteam [member1, member2, member3...]");
	}
	
	/**
	 * Sets the team
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		if(!paramsValid(parameters)) {
			channel.sendMessage("Baah *(Use the command like this)*: " + super.getUsage()).queue();
			return "";
		}
		
		setTeam(parameters, guild);
		channel.sendMessage("Team set!").queue();
		
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
	
	/**
     * Sets the team
     * 
     * @param team Team you want to set
     * @param guild For which server?
     */
    private void setTeam(List<String> newTeam, Guild guild) {
    	//gets an unique stringvalue of a guild
    	String guildHash = guild.toString();
    	
    	//if the discordserver doesn't have a team, make a new one
    	if(!Ref.bossTeams.containsKey(guildHash)) {
    		Ref.bossTeams.put(guildHash, new ArrayList<>());
    	}
    	
    	//gets team
    	List<String> team = Ref.bossTeams.get(guildHash);
    	
    	//clears last team
		team.clear();
		
		//adds new members
		for(String player : newTeam) {
			player = player.trim();
			player = player.replaceAll(",$", "");
			team.add(player);
		}
    }

}
