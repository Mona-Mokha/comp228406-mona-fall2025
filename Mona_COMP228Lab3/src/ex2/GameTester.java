package ex2;

public abstract class GameTester {
    protected String name;
    protected boolean isFullTime;

    public GameTester(String nameOfGame, boolean isFullTime) {
        this.name = nameOfGame;
        this.isFullTime = isFullTime;
    }
    public abstract double salary();

}
