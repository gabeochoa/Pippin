package pippin;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ShowLongFileOnConsole {

    public static void main(String[] args) {
        System.out.println("Please enter the name of the file with longs");
        System.out.println("If it is not in the Project directory,");        
        System.out.println("give the full path, e.g. C:\\FileIO\\WriteInt.txt");        
        Scanner keyboard = new Scanner(System.in);
        String fileName = keyboard.nextLine();

        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            DataInputStream dis = new DataInputStream(fis);
            while(dis.available() > 0) {
                long ln = dis.readLong();
                System.out.println(ln + " = " + Long.toBinaryString(ln));
            }
            dis.close();

        }
        catch (IOException e)
        {
            System.out.println("IOException : " + e);
        }

    }
} 