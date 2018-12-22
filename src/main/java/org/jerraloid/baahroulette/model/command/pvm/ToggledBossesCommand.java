package org.jerraloid.baahroulette.model.command.pvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.MiscUtil;
import org.jerraloid.baahroulette.util.Ref;

import javafx.util.Pair;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class ToggledBossesCommand extends AbstractCommand {

	/**
	 * Custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public ToggledBossesCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * Default constructor
	 */
	public ToggledBossesCommand() {
		super("toggledbosses", Arrays.asList("bosstoggles"), "Shows all toggled bosses to show in the bosspool while randomizing.", Ref.PREFIX + "toggledbosses");
	}
	
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		
		//get unique id from guild
		String guildHash = guild.toString();
		
		//if the discordserver doesn't have a bosslist yet, make a new one
    	if(!Ref.bossPool.containsKey(guildHash)) {
    		//init list
    		List<Pair<String, Boolean>> newPool = new ArrayList<>();
    		
    		//fill bosspool
    		for(String boss : Ref.bossPoolList) {
    			newPool.add(new Pair<>(boss, true));
    		}
    		
    		//link the list to the guild
    		Ref.bossPool.put(guildHash, newPool);
    	}
		
		//get the list
		List<Pair<String, Boolean>> pool = Ref.bossPool.get(guildHash);
		
		//creates a list of the bosses
		StringBuilder sb = new StringBuilder();
		for(Pair<String, Boolean> boss : pool) {
			sb.append("- " + boss.getKey() + " is toggled **" + ((boss.getValue()) ? "ON" : "OFF") + "**\n");
		}
		
		//send an embedmessage
		MiscUtil.sendEmbedMessage(channel, "Toggled Bosses", sb.toString());
		
		return "";
	}

}
