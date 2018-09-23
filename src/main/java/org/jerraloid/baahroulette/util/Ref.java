package org.jerraloid.baahroulette.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Referenced variables
 * 
 * @author Jerry
 *
 */
public class Ref {
	
	//General Properties
	public static String TOKEN = "";
	public static String PREFIX = "";
	
	//Bossing
	public static HashMap<String, List<String>> bossTeams = new HashMap<>();
	
	//bosses
	public static ArrayList<String> bosslist = new ArrayList<>(Arrays.asList(
			"bm",
			"yaka",
			"poison",
			"aod",
			"minions",
			"kk",
			"meme"
	));
	
	//roles
	public static ArrayList<String> bmRoles = new ArrayList<>(Arrays.asList(
			"Base Tank",
			"Backup",
			"Pet Tank 1/3",
			"Pet Tank 2",
			"North Chargers"
	));
	public static ArrayList<String> yakaRoles = new ArrayList<>(Arrays.asList(
			"Base Tank",
			"North Tank",
			"Stun 5",
			"Stun 5",
			"Shark 10",
			"CPR",
			"Poison Tank",
			"Double Poison",
			"Jelly Wrangler"
	));
	public static ArrayList<String> poisonRoles = new ArrayList<>(Arrays.asList(
			"Poison Tank",
			"Double Poison",
			"CPR"
	));
	public static ArrayList<String> aodRoles = new ArrayList<>(Arrays.asList(
			"Base Tank 1",
			"Base Tank 2",
			"Base Tank 3",
			"Umbra",
			"Glacies",
			"Cruor",
			"Fumus"
	));
	public static ArrayList<String> minionRoles = new ArrayList<>(Arrays.asList(
			"Umbra",
			"Glacies",
			"Cruor",
			"Fumus"
	));
	public static ArrayList<String> kkRoles = new ArrayList<>(Arrays.asList(
			"Tank",
			"Voke"
	));
	public static ArrayList<String> memeRoles = new ArrayList<>(Arrays.asList(
			"Leech",
			"Dropspawner",
			"Debuff man", 
			"Overload caller"
	));
	
	//misc
	public static final String[] COMMANDS = {
			"**help** | **commands** - Shows you a list of all the bot commands.",
			"**baah** - Baah!",
			"**setteam** | **update** - Updates the team for PvM randomized roles.",
			"**team** | **showteam** | **currentteam** - Shows the current team that is set in the bot.",
			"**addmember** | **add** - Adds a member to the team.",
			"**removemember** | **remove** - Removes a member from the team.",
			"**bosses** | **bosslist** - Shows all available bosses compatible with the bot.",
			"**roles** | **setroles** | **randomroles** | **doroles** - Randomizes PvM roles.",
			"**callout** - Gives someone a compliment.",
			"**dropchance** - Calculates for you when you get a drop."
	};
	
	public static final String[] COMPLIMENTS = {
			"dunce", 
			"conehead", 
			"shithead",
			"dickhead",
			"basterd",
			"wanker",
			"knobhead",
			"bald person",
			"furry",
			"weeb",
			"nitwit",
			"futuristic mcdonald's employee",
			"noob",
			"scrublord",
			"...",
			"nibba",
			"arsewipe",
			"berk",
			"cunt",
			"bugger",
			"duffer",
			"manky",
			"nutter",
			"plonker",
			"prat",
			"brat", 
			"twat",
			"shitbag",
			"nugget",
			"git",
			"less attractive man than Woox",
			"gey",
			"little shit",
			"dorito eating basterd",
			"spastic",
			"banana sucking ant", 
			"maggot",
			"THOT, begone pls",
			"pinhead",
			"dick",
			"person who needs to get elbowed by tanner",
			"blatte",
			"jake paul fan",
			"vegan",
			"dolloman",
			"homeless man",
			"guy who looks like this guy: https://prnt.sc/kr2ble ",
			"buttplug tester",
			"bad runescape player",
			"fat fuck",
			"ginger",
			"redneck",
			"neckbeard",
			"basement crawler",
			"midget", 
			"neet",
			"guy who doesn't even say goodnight to his fbi agent",
			"guy who uses cheats for wii fit",
			"person so bald, Mr. Clean got jealous",
			"tryhard",
			"elitist",
			"fadget",
			"fagit"
	};
}
