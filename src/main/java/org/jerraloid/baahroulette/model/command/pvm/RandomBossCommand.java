package org.jerraloid.baahroulette.model.command.pvm;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class RandomBossCommand extends AbstractCommand {

	/**
	 * Custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public RandomBossCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * Default constructor
	 */
	public RandomBossCommand() {
		super("randomboss", Arrays.asList("randomizeboss", "chooseboss", "pickboss"), "Chooses a random boss for you to do.", Ref.PREFIX + "randomboss");
	}
	
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		
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
    	
    	//get the pool
    	List<Map.Entry<String, Boolean>> bossPool = Ref.bossPool.get(guildHash);
    	
    	//send message
    	channel.sendMessage("You are gonna do... :\n**" + getRandomBoss(bossPool) + "**").queue();
    	
		return "";
	}
	
	/**
	 * Chooses a random boss to do
	 * 
	 * @param bossPool Pool with chosen bosses for that specific discord
	 * @return Random chosen boss
	 */
	private String getRandomBoss(List<Map.Entry<String, Boolean>> bossPool) {
		//init variables
		List<String> pool = new ArrayList<>();
		Random randomizer = new Random();
		
		//set bosses in pool
		for(Map.Entry<String, Boolean> item : bossPool) {
			if(item.getValue()) pool.add(item.getKey());
		}
		
		if (pool.isEmpty()) {
			return "Hol' up, the pool is empty you basterd!";
		}
		
		//get the index number of the chosen boss
		int index = randomizer.nextInt(pool.size());
		
		//return chosen boss
		return pool.get(index);
	}

}
