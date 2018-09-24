package org.jerraloid.baahroulette.core;

import org.jerraloid.baahroulette.handler.CommandHandler;

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
    	if(msg.isTTS()) {
    		channel.sendMessage("Baah. *(Shut up homo.)* :middle_finger:").queue();
    		return;
    	}
    	
    	//handle commands
		CommandHandler.handle(msg, author, guild, channel);
    }
    
    
    
    
    
    
    
    
    
    
	
}
