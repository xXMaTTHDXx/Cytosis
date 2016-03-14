package io.matthd.cytosis.game;

import io.matthd.cytosis.Cytosis;
import io.matthd.cytosis.team.Team;
import io.matthd.cytosis.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * Created by Matt on 2016-03-11.
 */
public abstract class Game {

    private Cytosis instance = Cytosis.getInstance();

    private String name;
    private Map<UUID, Team> players;
    private List<Team> teams;
    private GameSettings settings;
    private State state;

    private int gameTick = 0;
    private int lobbyTick = 0;

    private int minPlayers;
    private int maxPlayers;

    public Game(String name, int minSize, int maxSize, GameSettings settings){
        this.name = name;
        this.players = new HashMap<>();
        this.settings = settings;
        this.state = State.LOBBY;
        this.minPlayers = minSize;
        this.maxPlayers = maxSize;
    }

    public Game(String name, int minSize, int maxSize, GameSettings settings, List<Team> teams){
        this.name = name;
        this.players = new HashMap<>();
        this.settings = settings;
        this.teams = teams;
        this.state = State.LOBBY;
        this.minPlayers = minSize;
        this.maxPlayers = maxSize;
    }

    public void addPlayer(UUID uuid, Team team){
        team.add(uuid);
        players.put(uuid, team);
    }

    public void changeTeam(UUID playerUUID, Team team) {
        if(!players.containsKey(playerUUID)) return;
        players.put(playerUUID, team);
    }

    public void removePlayer(UUID uuid){
        Team team = getTeam(uuid);
        team.remove(uuid);
        if(!shouldContinue()){
            stop(true);
        }
    }

    public Map<UUID, Team> getPlayers() {
        return players;
    }

    public Team getTeam(UUID uuid){
        return players.get(uuid);
    }

    public String getPrefix(){
        return ChatColor.GRAY + "[" + ChatColor.RED + name + ChatColor.GRAY + "]";
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    public void registerTeam(Team team){
        this.teams.add(team);
    }

    public void registerTeams(Team... teams){
        this.teams.addAll(Arrays.asList(teams));
    }

    public Team getTeam(String name){
        for(Team t : teams){
            if(t.getName().equalsIgnoreCase(name)){
                return t;
            }
        }
        return null;
    }

    public Team getPlayerTeam(Player player){
        return players.get(player.getUniqueId());
    }

    public void start(){
        if(state != State.LOBBY){
            System.out.println("Error: Game is already starting!");
            return;
        }
        setLobbyTick(10);
        new BukkitRunnable(){
            public void run(){

                if(state == State.STARTING){
                    ChatUtils.messageToAll(getInstance(), "Game starting in: " + getLobbyTick());
                    setLobbyTick(getLobbyTick() - 1);

                    if(getLobbyTick() <= 0){
                        setLobbyTick(0);
                        setState(State.INGAME);
                        for(Player pl : Bukkit.getOnlinePlayers()){
                            onJoin(pl);
                            pl.teleport(getTeam(pl.getUniqueId()).getSpawn());
                        }
                    }
                }
                else if(state == State.INGAME){

                    if(getGameTick() % getGameMessageInterval() == 0){
                        ChatUtils.messageToAll(getInstance(), "Game ending in: " + getGameTick());
                        return;
                    }

                    if(getGameTick() <= 0){
                        stop(false);
                    }
                }
            }
        }.runTaskTimerAsynchronously(instance, 0L, 20L);
    }

    public void lobby(){
        ChatUtils.messageToAll(getInstance(), "Teleporting to lobby in 10 seconds.");
        new BukkitRunnable(){
            public void run(){
                for(Player pl : Bukkit.getOnlinePlayers()){
                    onLobby(pl);
                    pl.teleport(getLobby());
                }
            }
        }.runTaskLater(instance, 10*20L);
    }

    public void stop(boolean forced){
        if(state != State.INGAME){
            System.out.println("Error: Game is already ended!");
            return;
        }

        onFinish(forced);
    }

    public Team getRandomTeam(){
        return teams.get(new Random().nextInt(teams.size()));
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getGameTick() {
        return gameTick;
    }

    public void setGameTick(int gameTick) {
        this.gameTick = gameTick;
    }

    public int getLobbyTick() {
        return lobbyTick;
    }

    public void setLobbyTick(int lobbyTick) {
        this.lobbyTick = lobbyTick;
    }

    public abstract boolean shouldContinue();
    public abstract void onLobbyJoin(Player player);
    public abstract Location getLobby();
    public abstract void onLobbyQuit(Player player);
    public abstract void onJoin(Player player);
    public abstract void onLobby(Player player);
    public abstract void onQuit(Player player);
    public abstract void onDeath(Player player, PlayerDeathEvent e);
    public abstract void onFinish(boolean forced);

    public abstract int getGameMessageInterval();

    public enum State {
        LOBBY, INGAME, STARTING;
    }
    public Game getInstance(){
        return this;
    }
}
