package org.jerraloid.baahroulette.model.command.pvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;
import org.jerraloid.baahroulette.util.MiscUtil;
import org.jerraloid.baahroulette.util.Ref;

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
		super("setroles", Arrays.asList("roles", "randomroles", "doroles"), "Randomizes PvM roles.", Ref.PREFIX + "roles [boss]");
	}

	/**
	 * Randomly assignes roles to teammembers
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		
		//get unique id from guild
		String guildHash = guild.toString();
		
		//if the discordserver doesn't have a team, make a new one
    	if(!Ref.bossTeams.containsKey(guildHash)) {
    		Ref.bossTeams.put(guildHash, new ArrayList<>());
    	}
		
    	//get the team of the discord
    	List<String> team = Ref.bossTeams.get(guildHash);
    	
		//checks if params are valid
		if(!paramsValid(parameters)) {
			channel.sendMessage("I didn't find any parameters, try using the command like this:\n" + getUsage()).queue();
			return "";
		}
			
		//checks if theres a team
		if(!(team.size() > 0)) {
			channel.sendMessage("There is no team in the queue, please use '" + Ref.PREFIX + "setTeam [player1, player2...]'.").queue();
			return "";
		}
		
		//randomize roles
		String bossname = parameters.get(0);
		String result = "";
		
		switch(bossname) {
			case "bm": 
				result = doRoles(Ref.bmRoles, team);
				break;
			case "yaka":
				result = doRoles(Ref.yakaRoles, team);
				break;
			case "poison":
				result = doRoles(Ref.poisonRoles, team);
				break;
			case "aod":
				result = doRoles(Ref.aodRoles, team);
				break;
			case "minions":
				result = doRoles(Ref.minionRoles, team);
				break;
			case "kk":
				result = doRoles(Ref.kkRoles, team);
				break;
			case "meme":
				result = doRoles(Ref.memeRoles, team);
				break;
			default:
				result = bossname + " is not a command m' lady. *tips fedora*";
				break;
		}
		
		//makes message
		MiscUtil.sendEmbedMessage(channel, "Roles " + bossname, result);
    		
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
	
	/**
     * Randomizes roles
     * 
     * @param roles available roles
     * @return randomized roles in a string for a message
     */
    private String doRoles(ArrayList<String> roles, List<String> team) {
    	//checks if the teamsize is smaller than the rolesize
    	if(team.size() < roles.size()) {
    		return "Team is too small for this boss...\nYou need atleast " + roles.size() + " teammembers.";
    	}
    	
    	//randomizes roles
    	List<String> copyTeam = new ArrayList<>(team);
    	StringBuilder sb = new StringBuilder();
    	Random randomizer = new Random();
    	
    	for(String role : roles) {
    		int index = randomizer.nextInt(copyTeam.size());
    		String player = copyTeam.get(index);
    		
    		sb.append(role + ": " + player + "\n");
    		copyTeam.remove(index);
    	}
    	
    	return sb.toString();
    }

}
