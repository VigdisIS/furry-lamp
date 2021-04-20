package application.persistance;

import application.meters.AbstractMeter;

import java.io.*;
import java.util.Scanner;

public class LoadSave {

    public void writeToFile(String filNavn, AbstractMeter meter){
        try (FileWriter f = new FileWriter(filNavn, true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            p.println(String.valueOf(meter.getMeterVal()));
            System.out.println(String.valueOf(meter.getMeterVal()) + " lagret.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public String readFromFile(String filNavn) {

        String results = "";

        try
        {
            File openFile = new File(filNavn);
            Scanner fileScanner = new Scanner(openFile);

            String currentLine = fileScanner.nextLine();
            while(fileScanner.hasNextLine()) {
                results+= currentLine + "\n";
                currentLine = fileScanner.nextLine();
            }
            results += currentLine + "\n";
            fileScanner.close();

        }
        catch(IOException error) {
            error.printStackTrace();
            System.exit(1);
        }
        System.out.println("Loaded values: \n"+ results);
        return results;


    }
}
