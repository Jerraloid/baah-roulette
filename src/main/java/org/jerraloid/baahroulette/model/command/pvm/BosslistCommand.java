package org.jerraloid.baahroulette.model.command.pvm;

import java.util.Arrays;
import java.util.List;

import org.jerraloid.baahroulette.model.AbstractCommand;
import org.jerraloid.baahroulette.util.MiscUtil;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class BosslistCommand extends AbstractCommand {

	/**
	 * Custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public BosslistCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * Default constructor
	 */
	public BosslistCommand() {
		super("bosslist", Arrays.asList("bosses"), "Shows all available bosses compatible with the bot.", Ref.PREFIX + "bosslist");
	}
	
	/**
	 * shows bosslist
	 */
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
	
		//creates a list of the bosses
		StringBuilder sb = new StringBuilder();
		for(String boss : Ref.bosslist) {
			sb.append("- " + boss + "\n");
		}
		
		//send an embedmessage
		MiscUtil.sendEmbedMessage(channel, "Boss List", sb.toString());
		
		return "";
	}
	
}
