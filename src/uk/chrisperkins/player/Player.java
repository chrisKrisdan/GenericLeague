package uk.chrisperkins.player;

import uk.chrisperkins.Ranked;

import java.util.Random;

public abstract class Player<T extends Player> implements Ranked, Comparable {

    // == FIELDS ==
    private String name;
    private int numGames;
    private int pointsScored;


    // == CONSTRUCTORS ==

    public Player(String name) {
        this.setName(name);
    }

/*    public Player(String name, int numGames) {
        this.setName(name);
        this.setNumGames(numGames);
    }

    public Player(String name, int numGames, int pointsScored) {
        this.setName(name);
        this.setNumGames(numGames);
        this.setPointsScored(pointsScored);
    }*/

    //== HELPER METHODS ==

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumGames() {
        return numGames;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }

    public int getPointsScored() {
        return pointsScored;
    }

    public void setPointsScored(int pointsScored) {
        this.pointsScored = pointsScored;
    }

    public void genStats() {
        int max = 10;
        int min = 0;

        Random random = new Random();
        int numGames = random.nextInt((min + max) - min);
        int numPoints = random.nextInt((numGames * 4));

        this.setNumGames(numGames);
        this.setPointsScored(numPoints);
    }

    // == INTERFACE METHODS ==


    @Override
    public int ranking() {
        return this.getNumGames() / this.getPointsScored();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player<T> player = (Player<T>) o;

        if (numGames != player.numGames) return false;
        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + numGames;
        return result;
    }

    @Override
    public int compareTo(Object o) {

        Player<T> player = (Player<T>) o;

        int comparison = 0;

        if (this.ranking() > player.ranking()) {
            comparison = -1;
        } else if (this.ranking() < player.ranking()) {
            comparison = 1;
        }
        return comparison;
    }

    @Override
    public String toString() {

        String newLine = System.getProperty("line.separator");

        StringBuilder output = new StringBuilder();

        output.append("(------------------------------------------------------)");
        output.append(newLine);
        output.append("Class: " + this.getClass().getSimpleName());
        output.append(newLine);
        output.append(newLine);
        output.append("Name: " + this.getName());
        output.append(newLine);
        output.append("Games Played: " + this.getNumGames());
        output.append(newLine);
        output.append("Points Scored: " +this.getPointsScored());
        output.append(newLine);
        output.append("(------------------------------------------------------)");
        output.append(newLine);

        return output.toString();
    }
}
