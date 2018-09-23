package org.jerraloid.baahroulette.core;

import java.awt.Color;
import java.util.Random;

import org.jerraloid.baahroulette.handler.CommandHandler;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

	
	/**
     * Reads the messages in chat
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
    	//===Objects===
    	User author = event.getAuthor(); 
    	MessageChannel channel = event.getChannel();  
    	Message msg = event.getMessage();
    	Guild guild = event.getGuild();
    	
    	//:Omegalul:
    	if(event.getMessage().isTTS()) {
    		channel.sendMessage("Baah. *(Shut up homo.)* :middle_finger:").queue();
    		return;
    	}
    	
    	//handle commands
		CommandHandler.handle(msg, author, guild, channel);
		
		
    	//shows the current team
    	/*if(command.equalsIgnoreCase("team") || command.equalsIgnoreCase("showteam") || command.equalsIgnoreCase("currentteam")) {
    		
    		//get the team of the discord
    		List<String> team = discordTeam.get(guild);
    		
    		//checks if the team is empty
    		if(!(team.size() > 0)) {
    			sendMessage(messageChannel, "There is no team in the queue, please use 'uwu update [player1, player2...]'.");
    			return;
    		}
    		
    		//gets the message ready
    		StringBuilder sb = new StringBuilder();
    		for(String player : team) {
    			sb.append("- " + player + "\n");
    		}
    		
    		//sends
    		sendMessage(messageChannel, "Current Team", sb.toString());
    	}*/
    	
    	//adds a member to the team
    	/*if(command.equalsIgnoreCase("addmember") || command.equalsIgnoreCase("add")) {
    		//checks parameters
    		if(!(messageParts.size() > 2)) {
    			sendMessage(messageChannel, "Wait... Who did you want to add to the team?");
    			return;
    		}
    		
    		String member = messageParts.get(2);
    		discordTeam.get(guild).add(member);
    		sendMessage(messageChannel, "Added '" + member  + "' to the team!");
    	}*/
    	
    	//removes a member from the team
    	/*if(command.equalsIgnoreCase("removemember") || command.equalsIgnoreCase("remove")) {
    		if(!(messageParts.size() > 2)) {
    			sendMessage(messageChannel, "Wait... Who did you want to remove?");
    			return;
    		}
    		
    		String member = messageParts.get(2);
    		List<String> team = discordTeam.get(guild);
    		
    		try {
    			team.remove(team.indexOf(member));
    			sendMessage(messageChannel, "Removed '" + member + "' from the team!");
    		}
    		catch (Exception ex) {
    			sendMessage(messageChannel, "Member does not exist.");
    		}
    		
    	}*/
    	
    	//shows bosslist
    	/*if(command.equalsIgnoreCase("bosses") || command.equalsIgnoreCase("bosslist")) {
    		StringBuilder sb = new StringBuilder();
    		for(String boss : Ref.bosslist) {
    			sb.append(boss + "\n");
    		}
    		sendMessage(messageChannel, "Boss List", sb.toString());
    	}*/
		
		//Randomly assign roles
		/*if(command.equalsIgnoreCase("roles") || command.equalsIgnoreCase("setroles") || command.equalsIgnoreCase("randomroles") || command.equalsIgnoreCase("doroles")) {
			//get the team of the discord
			List<String> team = discordTeam.get(guild);
			
			//sets team if you give the roles teammembers
			if(messageParts.size() > 3) { //3 parameters: uwu, roles and [bossname]
				setTeam(messageParts.subList(3, messageParts.size()), guild);
				sendMessage(messageChannel, "Team set!");
			} 
			
			//no boss parameter
			if(messageParts.size() < 3) {
				sendMessage(messageChannel, "No [boss] parameter, try: 'uwu roles [bossname]'.");
				return;
			}
			
			//checks if theres a team
			if(!(team.size() > 0)) {
				sendMessage(messageChannel, "There is no team in the queue, please use 'uwu update [player1, player2...]'.");
				return;
			}
			
			//randomize roles
			String bossname = messageParts.get(2);
			String result = "";
			
			switch(bossname) {
				case "bm": 
					result = doRoles(Ref.bmRoles, guild);
					break;
				case "yaka":
					result = doRoles(Ref.yakaRoles, guild);
					break;
				case "poison":
					result = doRoles(Ref.poisonRoles, guild);
					break;
				case "aod":
					result = doRoles(Ref.aodRoles, guild);
					break;
				case "minions":
					result = doRoles(Ref.minionRoles, guild);
					break;
				case "kk":
					result = doRoles(Ref.kkRoles, guild);
					break;
				case "meme":
					result = doRoles(Ref.memeRoles, guild);
					break;
				default:
					result = bossname + " is not a command m' lady. *tips fedora*";
					break;
			}
			
			//makes message
    		sendMessage(messageChannel, "Roles " + bossname, result);
		}*/
    	
		//swear at someone
		/*if(command.equalsIgnoreCase("callout")) {
			if (messageParts.size() < 3) {
				sendMessage(messageChannel, user.getAsMention() + " you are a " + getCompliment() + ".");
			}
			else {
				sendMessage(messageChannel, messageParts.get(2) + " you are a " + getCompliment() + ".");
			}
		}*/
		
		//calculates a drop
		/*if(command.equalsIgnoreCase("dropchance")) {
			//must have atleast 4 parameters
			if(messageParts.size() < 4) {
				sendMessage(
						messageChannel, 
						"Dropchance Command",
						"You probably typed in the wrong format, try:\n" +
						"**'uwu dropchance [chance] [current kc]'**\n" + 
						"[chance] = the dropchance, if the drop is 1/500 then the number is 500.\n" +
						"[current kc] = how many kills do you have?"
				);
				return;
			}
			
			try {
				//calculates the chance
				int chance = Integer.parseInt(messageParts.get(2));
				int currentKC = Integer.parseInt(messageParts.get(3));
				
				sendMessage(messageChannel, calcDropChance(chance, currentKC));
			}
			catch (Exception ex) {
				//if the format goes wrong
				sendMessage(
						messageChannel, 
						"Dropchance Command",
						"You probably typed in the wrong format, try:\n" +
						"**'uwu dropchance [chance] [current kc]'**\n" + 
						"[chance] = the dropchance, if the drop is 1/500 then the number is 500.\n" +
						"[current kc] = how many kills do you have?"
				);
			}
		}*/
    	
    }
    
    /**
     * Sends an embed message
     */
    @SuppressWarnings("unused")
	private static void sendMessage(MessageChannel messageChannel, String title, String content) {
    	//makes message
		EmbedBuilder newEmbed = new EmbedBuilder();
		newEmbed.addField(title, content, true);
		newEmbed.setColor(Color.PINK);
		MessageBuilder newMessage = new MessageBuilder(newEmbed.build());
		
		messageChannel.sendMessage(newMessage.build()).queue();
    }
    
    /**
     * Gets a random compliment
     * 
     * @return a compliment
     */
    private static String getCompliment() {
    	Random randomizer = new Random();
		String compliment = Ref.COMPLIMENTS[randomizer.nextInt(Ref.COMPLIMENTS.length)];
		return compliment;
    }
    
    
    
    /**
     * Randomizes roles
     * 
     * @param roles available roles
     * @return randomized roles in a string for a message
     */
    /*private String doRoles(ArrayList<String> roles, String guild) {
    	//get discord team
    	List<String> team = discordTeam.get(guild);
    	
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
    }*/
    
    /**
     * Calculates dropchance
     * 
     * @param chance chance
     * @param currentKC current killcount
     * @return a message with the chance
     */
    @SuppressWarnings("unused")
	private static String calcDropChance(int chance, int currentKC) {
    	//checks if the chance is higher than 0
    	if(!(chance > 0)) {
    		return "You have to choose a positive number you " + getCompliment() + "!";
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
