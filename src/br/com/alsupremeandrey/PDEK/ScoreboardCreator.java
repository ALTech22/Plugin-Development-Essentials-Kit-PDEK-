package br.com.alsupremeandrey.PDEK;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.md_5.bungee.api.ChatColor;

public class ScoreboardCreator {
	private ScoreboardManager SBM;
	private Scoreboard scoreBoard;
	private Objective obj;
	private Plugin plugin;
	private Player player;
	private int scoreSize;
	
	public ScoreboardCreator(Plugin plugin, Player player) {
		SBM = Bukkit.getScoreboardManager();
		scoreBoard = SBM.getNewScoreboard();
		scoreSize = 1;
		this.plugin = plugin;
		this.player = player;
	}	
	
	public void setObj(String ScoreBoardName, String criteria, String ScoreBoardTitle) {
		obj = scoreBoard.registerNewObjective(ScoreBoardName, criteria, ChatColor.translateAlternateColorCodes('&', ScoreBoardTitle));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	
	public Objective getObj() {
		return this.obj;
	}
	
	public void setScore(String Score) {
		Score score = obj.getScore(ChatColor.translateAlternateColorCodes('&', Score));
		score.setScore(getScoreSize());
		setScoreSize(getScoreSize() + 1);
	}
	public void setScore(String Score, int arg0) {
		Score score = obj.getScore(ChatColor.translateAlternateColorCodes('&', Score));
		score.setScore(arg0);
	}
	public void setScoreSize(int size) {
		this.scoreSize = size;
	}
	public int getScoreSize() {
		return scoreSize;
	}
	public void genScoreBoard() {
		player.setScoreboard(scoreBoard);
	}
}
