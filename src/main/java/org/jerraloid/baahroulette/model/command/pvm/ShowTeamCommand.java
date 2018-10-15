package org.jerraloid.baahroulette.model.command.pvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.MiscUtil;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class ShowTeamCommand extends AbstractCommand {

	/**
	 * Custom constructor
	 * 
	 * @param command commandName
	 * @param alias Other commandnames
	 * @param description Description of the command
	 * @param usage How is it used
	 */
	public ShowTeamCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * Default constructor
	 */
	public ShowTeamCommand() {
		super("showteam", Arrays.asList("team", "currentteam"), "Shows the current team that is set in the bot.", Ref.PREFIX + "showteam");
	}
	
	/**
	 * shows the current team
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
		
		//checks if the team is empty
		if(!(team.size() > 0)) {
			channel.sendMessage(Ref.PREFIX + "setteam [player1, player2...]").queue();
			return "";
		}
		
		//gets the message ready
		StringBuilder sb = new StringBuilder();
		for(String player : team) {
			sb.append("- " + player + "\n");
		}
		
		//sends message
		MiscUtil.sendEmbedMessage(channel, "Current Team", sb.toString());
	
		return "";
	}
}
