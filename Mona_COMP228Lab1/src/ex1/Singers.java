package ex1;

public class Singers {
    private int singerId;
    private String singerName;
    private String singerAddress;
    private String dateOfBirth;
    private int numberOfAlbumsPublished;


    public Singers() {
        this.singerId = 0;
        this.singerName = "";
        this.singerAddress = "";
        this.dateOfBirth = "";
        this.numberOfAlbumsPublished = 0;
    }

    public Singers(int singerId, String singerName, String singerAddress, String dateOfBirth, int numberOfAlbumsPublished) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerAddress = singerAddress;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;

    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }
    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
    public void setSingerAddress(String singerAddress) {
        this.singerAddress = singerAddress;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setNumberOfAlbumsPublished(int numberOfAlbumsPublished) {
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }

    public void setAllValues(int singerId, String singerName, String singerAddress, String dateOfBirth, int numberOfAlbumsPublished) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerAddress = singerAddress;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;

    }

    public int getSingerId() {
        return singerId;
    }
    public String getSingerName() {
        return singerName;
    }
    public String getSingerAddress() {
        return singerAddress;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public int getNumberOfAlbumsPublished() {
        return numberOfAlbumsPublished;
    }

    public void displaySingerInfo() {
        System.out.println("Singer ID: " + this.singerId);
        System.out.println("Singer Name: " + this.singerName);
        System.out.println("Singer Address: " + this.singerAddress);
        System.out.println("Date of Birth: " + this.dateOfBirth);
        System.out.println("Number of Albums Published: " + this.numberOfAlbumsPublished);

    }



}
