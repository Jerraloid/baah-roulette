package org.jerraloid.baahroulette.model.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;
import org.jerraloid.baahroulette.util.TimeUtil;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class UptimeCommand extends AbstractCommand {

	private Date onlineSince = new Date();
	
	/**
	 * custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public UptimeCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * default constructor
	 */
	public UptimeCommand() {
		super("uptime", new ArrayList<>(), "For how long am I online?", "uptime");
	}
	
	/**
	 * Says the time since when the bot is online
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		//calculates difference between the time now and when it came online
		long diff = new Date().getTime() - onlineSince.getTime();
		
		//sends message
		channel.sendMessage("I'm online for: " + TimeUtil.getRelativeTime(diff)).queue();
		
		return "";
	}

}
