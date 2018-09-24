package org.jerraloid.baahroulette.model.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class DropChanceCommand extends AbstractCommand {

	/**
	 * Custom constructor
	 * 
	 * @param command Commandname
	 * @param alias Other commandnames to call the comand
	 * @param description Description of what it does
	 * @param usage How to use the command
	 */
	public DropChanceCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * Default constructor
	 */
	public DropChanceCommand() {
		super(
				"dropchance", 
				new ArrayList<>(), 
				"Calculates for you when you get a drop.", 
				Ref.PREFIX + "dropchance [chance] [current kc] \n" +
				"[chance] = the dropchance, if the drop is 1/500 then the number is 500.\n" +
				"[current kc] = how many kills do you have?"
		);
	}
	
	/**
	 * calculates a dropchance
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		
		//checks if parameters are valid
		if(!paramsValid(parameters)) {
			channel.sendMessage("No valid parameters found, try: \n" + getUsage()).queue();
			return "";
		}
		
		try {
			//calculates the chance
			int chance = Integer.parseInt(parameters.get(0));
			int currentKC = Integer.parseInt(parameters.get(1));
			
			channel.sendMessage(calcDropChance(chance, currentKC)).queue();
		}
		catch (NumberFormatException ex) {
			//if the format goes wrong
			channel.sendMessage("You probably typed in the wrong format, try: \n" + getUsage()).queue();
		}
		
		return "";
	}
	
	/**
	 * checks if the parameters are valid for this command
	 * 
	 * @param parameters Parameters
	 * @return true or false
	 */
	private static boolean paramsValid(List<String> parameters) {
		//checks if the variable is created
		if(parameters == null) {
			return false;
		}
				
		//checks if there are parameters/is not empty
		if(parameters.isEmpty()) {
			return false;
		}
		
		//checks if there are atleast 2 parameters
		if(parameters.size() < 2) {
			return false;
		}
		
		return true;
	}
	
	/**
     * Calculates dropchance
     * 
     * @param chance chance
     * @param currentKC current killcount
     * @return a message with the chance
     */
	private static String calcDropChance(int chance, int currentKC) {
    	//checks if the chance is higher than 0
    	if(!((chance > 0) || (currentKC > 0))) {
    		return "You have to choose a positive number!";
    	}
    	
    	//randomize
    	Random randomizer = new Random();
    	int tries = 0;
    	int number = 1;
    	do {
    		tries++;
    		number = randomizer.nextInt(chance);
    		
    	} while(number != 0);
    	
    	return "You will get your drop on **" + (currentKC + tries) + "** killcount.";
    }
}
