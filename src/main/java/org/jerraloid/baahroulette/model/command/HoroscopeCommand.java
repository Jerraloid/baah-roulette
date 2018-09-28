package org.jerraloid.baahroulette.model.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jerraloid.baahroulette.model.Horoscope;
import org.jerraloid.baahroulette.model.abstractmodel.AbstractCommand;
import org.jerraloid.baahroulette.service.HoroscopeService;
import org.jerraloid.baahroulette.util.MiscUtil;
import org.jerraloid.baahroulette.util.Ref;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class HoroscopeCommand extends AbstractCommand {

	/**
	 * custom constructor
	 * 
	 * @param command The commandname
	 * @param alias The other names to call the command
	 * @param description What does it do?
	 * @param usage How to use it?
	 */
	public HoroscopeCommand(String command, List<String> alias, String description, String usage) {
		super(command, alias, description, usage);
	}
	
	/**
	 * default constructor
	 */
	public HoroscopeCommand() {
		super("horoscope", new ArrayList<>(), "Gives your horoscope.", Ref.PREFIX + "horoscope [horoscope name]");
	}
	
	@Override
	public String execute(List<String> parameters, User author, Guild guild, MessageChannel channel) {
		
		//checks if parameters are valid
		if(!paramsValid(parameters)) {
			channel.sendMessage("No valid parameters found, try: \n" + getUsage()).queue();
			return "";
		}
		//gets your horoscope
		Horoscope horoscope = null;
		
		try {
			 horoscope = HoroscopeService.getHoroscope(parameters.get(0));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		//sends message
		if(horoscope != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("**Quote of the day:** " + horoscope.getDescription() + "\n");
			sb.append("**Mood:** " + horoscope.getMood() + "\n");
			sb.append("**Today's color:** " + horoscope.getColor() + "\n");
			sb.append("**Lucky time:** " + horoscope.getLuckyTime() + "\n");
			sb.append("**Lucky number:** " + horoscope.getLuckyNumber() + "\n");
			sb.append("**Your best friends are:** " + horoscope.getCompatibility());
			
			MiscUtil.sendEmbedMessage(channel, "Horoscope " + horoscope.getName(), sb.toString());
		} else {
			channel.sendMessage("Baah. *(I couldn't find the horoscope you're looking for.)*").queue();
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
		
		return true;
	}
}
