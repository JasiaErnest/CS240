import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class glossary {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        HashMap<String, String> dataStructureGlossary = new HashMap<String, String>();
        fillGlossary(dataStructureGlossary);
        boolean go = true;
        System.out.println("Hello and welcome to the Data Structure Glossary!");
        System.out.println("This glossary contains a list of data structures and what they are used for.");
        while (go) {
            System.out.println("What would you like to do?");
            System.out.println("1. View the glossary");
            System.out.println("2. Add to the glossary");
            System.out.println("3. Search the glossary");
            System.out.println("4. Exit");
            int choice = reader.nextInt();
            reader.nextLine();
            switch (choice) {
                case 1:
                    printGlossary(dataStructureGlossary);
                    break;
                case 2:
                    writeToGlossary(dataStructureGlossary, reader);
                    break;
                case 3:
                    searchGlossary(dataStructureGlossary, reader);
                    break;
                case 4:
                    go = false;
                    break;
                default:
                    break;
            }
    
        }
    }

    public static void printGlossary(HashMap<String, String> dataStructureGlossary) {
        for (String object : dataStructureGlossary.keySet()) {
            System.out.println("Data Structure: " + object);
            System.out.println("What it's used for: " + dataStructureGlossary.get(object));
            System.out.println();
        }
    }

    public static void searchGlossary(HashMap<String, String> dataStructureGlossary, Scanner reader) {
        System.out.println("Please enter the data structure you'd like to know the use of: ");
        String object = reader.nextLine().toLowerCase();
        if (dataStructureGlossary.containsKey(object)) {
            System.out.println("Data Structure: " + object);
            System.out.println("What it's used for: " + dataStructureGlossary.get(object));
        } else {
            System.out.println("This data structure is not in our database.");
        }
    }

    public static void writeToGlossary(HashMap<String, String> dataStructureGlossary, Scanner reader) {
        try {
            FileWriter writeToFile = new FileWriter("dataStructureGlossary.txt", true);
            System.out.println("Please enter a data structure: ");
            String object = reader.nextLine().toLowerCase();
            System.out.println("Please enter what it's used for: ");
            String meaning = reader.nextLine();
            dataStructureGlossary.put(object, meaning);
            writeToFile.write("Data Structure: " + object + "\n");
            writeToFile.write("What it's used for: " + meaning + "\n\n");
            writeToFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void fillGlossary(HashMap<String, String> dataStructureGlossary) {
        try {
            File myFile = new File("dataStructureGlossary.txt");
            Scanner reader = new Scanner(myFile);
            String beforeString = null;
            String line = null;

            //initialize variables to store contact info
            String object = null;
            String meaning = null;

            //read until end of file and get contact info
            while(reader.hasNextLine()) {
                //read SSN number
                beforeString = "Data Structure: ";
                line = reader.nextLine();
                if (line.contains(beforeString)) {
                    String data = line.substring(line.indexOf(beforeString) + beforeString.length());
                    object = data.toLowerCase();
                }//end if statement
                
                //read full name
                beforeString = "What it's used for: ";
                line = reader.nextLine();
                if (line.contains(beforeString)) {
                    String data = line.substring(line.indexOf(beforeString) + beforeString.length());
                    meaning = data;
                }//end if statement

                //read empty line
                if (reader.hasNextLine()) {
                    line = reader.nextLine();
                }//end if statement
                System.out.println();

                dataStructureGlossary.put(object, meaning);
            
            }//end while loop
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}