package org.jerraloid.baahroulette.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;

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
	public static HashMap<String, List<Pair<String, Boolean>>> bossPool = new HashMap<>();
	
	//all bosses
	public static ArrayList<String> bossPoolList = new ArrayList<>(Arrays.asList(
			"Vorago",
			"Solak",
			"Rots",
			"Araxxor",
			"KK",
			"WildyWyrm",
			"QBD",
			"Corp",
			"Magister",
			"AOD",
			"Nex",
			"Kril",
			"Graardor",
			"Zilyana",
			"Kreearra",
			"Telos",
			"Gregorovic",
			"Twins",
			"Vindicta",
			"Helwyr",
			"Legios",
			"Kalphite_Queen",
			"Chaos_Elemental",
			"KBD",
			"Giant_Mole",
			"DKS",
			"Har-Aken",
			"Beastmaster",
			"Yakamaru",
			"Barrows"
	));
	
	//bosses for random roles
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
