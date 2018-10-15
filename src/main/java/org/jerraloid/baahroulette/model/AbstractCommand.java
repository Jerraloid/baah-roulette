package org.jerraloid.baahroulette.model;

import java.util.List;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public abstract class AbstractCommand {

	private String command;
	private List<String> alias;
	private String description;
	private String usage;
	private boolean enabled;
	
	/**
	 * custom constructor
	 * 
	 * @param command
	 * @param alias
	 * @param description
	 * @param usage
	 */
	public AbstractCommand(String command, List<String> alias, String description, String usage) {
		this.command = command;
		this.alias = alias;
		this.description = description;
		this.usage = usage;
		enabled = true;
	}
	
	/**
	 * default constructor
	 */
	public AbstractCommand() {
		
	}
	
	/**
	 * executes a command
	 * 
	 * @return a message which can be saved into the log
	 */
	public abstract String execute(List<String> parameters, User author, Guild guild, MessageChannel channel);

	
	//=== GETTERS and SETTERS ===
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
