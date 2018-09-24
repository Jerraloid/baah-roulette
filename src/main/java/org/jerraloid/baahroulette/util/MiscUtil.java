package org.jerraloid.baahroulette.util;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

public class MiscUtil {

	
	/**
	 * Sends an embed message
	 * 
	 * @param messageChannel Channel where the message is sent
	 * @param title Title of the message
	 * @param content Whats in the message
	 */
	public static void sendEmbedMessage(MessageChannel channel, String title, String content) {
    	//makes message
		EmbedBuilder newEmbed = new EmbedBuilder();
		newEmbed.addField(title, content, true);
		newEmbed.setColor(Color.PINK);
		MessageBuilder newMessage = new MessageBuilder(newEmbed.build());
		
		channel.sendMessage(newMessage.build()).queue();
    }
}
