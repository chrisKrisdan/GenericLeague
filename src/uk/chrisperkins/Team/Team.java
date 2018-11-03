package uk.chrisperkins.Team;

import uk.chrisperkins.Ranked;
import uk.chrisperkins.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team<T extends Player<T>> implements Ranked, Comparable<T> {

    // == FIELDS ==
    private String teamName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;

    private List<Player<T>> players;

    // == CONSTRUCTORS ==

    public Team(String teamName) {
        this.setTeamName(teamName);
        this.players = new ArrayList<Player<T>>();
    }


    // == HELPER METHODS ==

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public List<Player<T>> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player<T>> players) {
        this.players = players;
    }

    public int getGamesDrawn() {

        int played = this.getGamesPlayed();
        int won = this.getGamesWon();
        int lost = this.getGamesLost();

        return (played - (won + lost));
    }

    public void genStats() {
        int max = 10;
        int min = 0;

        Random random = new Random();
        int numGames = random.nextInt(((min + max) - min));
        int gamesWon = random.nextInt((numGames));
        int gamesLost = random.nextInt((numGames - gamesWon));

        this.setGamesPlayed(numGames);
        this.setGamesWon(gamesWon);
        this.setGamesLost(gamesLost);
    }

    /**
     * Adds a Player tot the team.
     * @param player
     * @return
     */
    public boolean add(Player<T> player) {

        boolean joinedTeam = false;

        if(this.players.contains(player)) {
            System.out.println(player.getName() + " is already in the team.");
        } else {
            System.out.println(player.getName() + " has joined the team.");
            this.players.add(player);
            joinedTeam = true;
        }
        return joinedTeam;
    }

    /**
     * Removes a Player from the team.
     * @param player
     * @return
     */
    public boolean remove(Player<Player> player) {

        boolean leftTeam = false;

        if(this.players.contains(player)) {
            System.out.println(player.getName() +
                    " is no longer in the " + this.getTeamName() + " team.");
            this.players.remove(player);
            leftTeam = true;
        } else {
            System.out.println(player.getName() +
                    " was not in the " + this.getTeamName() + " team.");
        }
        return leftTeam;
    }


    // == INTERFACE METHODS ==

    @Override
    public int ranking() {

        int points = this.getGamesDrawn() + (this.getGamesWon() * 2);
        return points / this.getGamesPlayed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team<?> team = (Team<?>) o;

        if (gamesPlayed != team.gamesPlayed) return false;
        if (gamesWon != team.gamesWon) return false;
        if (gamesLost != team.gamesLost) return false;
        if (teamName != null ? !teamName.equals(team.teamName) : team.teamName != null) return false;
        return players != null ? players.equals(team.players) : team.players == null;
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + gamesPlayed;
        result = 31 * result + gamesWon;
        result = 31 * result + gamesLost;
        result = 31 * result + (players != null ? players.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(T o) {

        int same = 0;

        if(this.ranking() > o.ranking()) {
            same = -1;
        } else if (this.ranking() < o.ranking()) {
            same = 1;
        }
        return same;
    }
}