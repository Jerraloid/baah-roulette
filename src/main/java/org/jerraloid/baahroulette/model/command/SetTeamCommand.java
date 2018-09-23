package org.jerraloid.baahroulette.model.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;
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
		super("setteam", Arrays.asList("update"), "Updates the team for PvM randomized roles.", "setteam [member1, member2, member3...]");
	}
	
	/**
	 * Sets the team
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		if(parameters.isEmpty()) {
			channel.sendMessage("Baah: *(Use the command like this:)* " + super.getUsage()).queue();
			return "";
		}
		
		setTeam(parameters, guild.toString());
		channel.sendMessage("Team set!").queue();
		
		return "";
	}
	
	/**
     * Sets the team
     * 
     * @param team Team you want to set
     * @param guild For which server?
     */
    private void setTeam(List<String> newTeam, String guild) {
    	//if the discordserver doesn't have a team, make a new one
    	if(!Ref.bossTeams.containsKey(guild)) {
    		Ref.bossTeams.put(guild, new ArrayList<>());
    	}
    	
    	//gets team
    	List<String> team = Ref.bossTeams.get(guild);
    	
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
