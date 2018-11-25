
import business.TunaFacadeRemote;
import entity.Tuna;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rasna
 */
public class TunaConsoleClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System. in);
        TunaFacadeRemote session = null;
        
        // CORBA properties and values and lookup taken after earlier work provided by
        // Todd Kelley (2016) Personal Communication
        System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

        try {
            System.out.println("Enter Omega");
            String omega = scanner.next();
            System.out.println("Enter Delta");
            String delta = scanner.next();
            System.out.println("Enter Theta");
            String theta = scanner.next();
            System.out.println("Enter UUID");
            String uuid = scanner.next();
            
            System.out.println("about to try for a session...");
            InitialContext ic = new InitialContext();
            session = (TunaFacadeRemote) ic.lookup("java:global/TunaApplication/TunaApplication-ejb/TunaFacade");
            System.out.println("Got a session :) ");
            
            System.out.println("Creating and inserting a Stuff record into database");
            Tuna tuna = new Tuna();
            tuna.setOmega(omega);
            tuna.setDelta(delta);
            tuna.setTheta(theta);
            tuna.setUuid(uuid);
            session.create(tuna);

            System.out.println("Listing contents of database");
            for(Tuna t: session.findAll()){
                System.out.printf("omega:%s delta:%s theta:%s uuid:%s%n", t.getOmega(), t.getDelta(), t.getTheta(), t.getUuid());
            }  

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}
