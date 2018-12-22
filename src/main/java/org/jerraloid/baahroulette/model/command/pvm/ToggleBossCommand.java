package org.jerraloid.baahroulette.model.command.pvm;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.MiscUtil;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class ToggleBossCommand extends AbstractCommand {

	//IDEA: uwu toggleboss all -> toggles all bosses
	//send 1 message instead of 1000
	
	/**
	 * Custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public ToggleBossCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * Default constructor
	 */
	public ToggleBossCommand() {
		super("toggleboss", Arrays.asList("togglebosses"), "Toggles bosses for in the pool while randomizing.", Ref.PREFIX + "toggleboss [bossname1, bossname2, bossname3...]");
	}
	
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		//check parameters
		if(!paramsValid(parameters)) {
			channel.sendMessage("Baah *(Use the command like this)*: " + super.getUsage()).queue();
			return "";
		}
		
		//get unique id from guild
		String guildHash = guild.toString();
		
		//if the discordserver doesn't have a bosslist yet, make a new one
    	if(!Ref.bossPool.containsKey(guildHash)) {
    		//init list
    		List<Map.Entry<String, Boolean>> newPool = new ArrayList<>();
    		
    		//fill bosspool
    		for(String boss : Ref.bossPoolList) {
    			newPool.add(new AbstractMap.SimpleEntry<>(boss, true));
    		}
    		
    		//link the list to the guild
    		Ref.bossPool.put(guildHash, newPool);
    	}
		
		//get the list
		List<Map.Entry<String, Boolean>> pool = Ref.bossPool.get(guildHash);
		
		//extra check on parameter length
		if(parameters.size() > pool.size()) {
			channel.sendMessage("Too gave me too many bosses, can't handle it. UwU").queue();
			return "";
		}
		
		//init the message
		StringBuilder sb = new StringBuilder();
		
		//for each bossname you give
		for(String bossName : parameters) {
			bossName = bossName.trim().replaceAll(",$", "");
			boolean exists = false;
			
			//look through the bosslist 
			for(Map.Entry<String, Boolean> item : pool) {
				
				//toggle if item found
				if (item.getKey().equalsIgnoreCase(bossName)) {
					Boolean newValue = (item.getValue()) ? false : true;
					Map.Entry<String, Boolean> newPair = new AbstractMap.SimpleEntry<>(item.getKey(), newValue);
					pool.remove(pool.indexOf(item));
					pool.add(newPair);
					sb.append("Baah *(Toggled the boss named: "+ bossName +" " + ((newValue) ? "**ON**" : "**OFF**") + ")*\n");
					exists = true;
					break;
				}
			}
			
			//send message if boss does not exist
			if(!exists) {
				sb.append("Baah *(Could not find the boss named: "+ bossName +")*\n");
			}
		}
		
		//update the pool
		Ref.bossPool.put(guildHash, pool);
		//send an embedmessage
		MiscUtil.sendEmbedMessage(channel, "Toggled Bosses", sb.toString());
		
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
