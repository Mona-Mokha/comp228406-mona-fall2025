package ex1;

public class SingersDriver {
    public static void main(String[] args) {

        Singers singer1  = new  Singers();
        System.out.println("Default values of singer1: ");
        singer1.displaySingerInfo();
        System.out.println("****************************");


        singer1.setAllValues(1,"Fadl","Lebanon", "Sep 20, 1950", 60);
        System.out.println("All values of singer1: ");
        singer1.displaySingerInfo();
        System.out.println("****************************");

        singer1.setSingerId(2);
        singer1.setSingerName("Jack");
        singer1.setSingerAddress("US");
        singer1.setDateOfBirth("Jan 1, 1970");
        singer1.setNumberOfAlbumsPublished(200);

        System.out.println("values of singer1 after update: ");
        System.out.println("Singer ID: " + singer1.getSingerId());
        System.out.println("Singer Name: " + singer1.getSingerName());
        System.out.println("Singer Address: " + singer1.getSingerAddress());
        System.out.println("Singer Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Singer Number of Albums Published: " + singer1.getNumberOfAlbumsPublished());
        System.out.println("****************************");

        Singers singer2 = new  Singers(3,"Hafiz", "Cairo", "June 10,1920", 500);
        System.out.println("Default values of singer2: ");
        singer2.displaySingerInfo();


    }
}
