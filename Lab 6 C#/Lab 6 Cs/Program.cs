using System;
using System.Collections.Generic;
using System.IO;

public class Glossary
{
    public static void Main(string[] args)
    {
        Dictionary<string, string> dataStructureGlossary = new Dictionary<string, string>();
        FillGlossary(dataStructureGlossary);
        bool go = true;
        Console.WriteLine("Hello and welcome to the Data Structure Glossary!");
        Console.WriteLine("This glossary contains a list of data structures and what they are used for.");
        while (go)
        {
            Console.WriteLine("What would you like to do?");
            Console.WriteLine("1. View the glossary");
            Console.WriteLine("2. Add to the glossary");
            Console.WriteLine("3. Search the glossary");
            Console.WriteLine("4. Exit");
            int choice = int.Parse(Console.ReadLine());
            switch (choice)
            {
                case 1:
                    PrintGlossary(dataStructureGlossary);
                    break;
                case 2:
                    WriteToGlossary(dataStructureGlossary);
                    break;
                case 3:
                    SearchGlossary(dataStructureGlossary);
                    break;
                case 4:
                    go = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static void PrintGlossary(Dictionary<string, string> dataStructureGlossary)
    {
        foreach (var entry in dataStructureGlossary)
        {
            Console.WriteLine("Data Structure: " + entry.Key);
            Console.WriteLine("What it's used for: " + entry.Value);
            Console.WriteLine();
        }
    }

    public static void SearchGlossary(Dictionary<string, string> dataStructureGlossary)
    {
        Console.WriteLine("Please enter the data structure you'd like to know the use of: ");
        string obj = Console.ReadLine().ToLower();
        if (dataStructureGlossary.ContainsKey(obj))
        {
            Console.WriteLine("Data Structure: " + obj);
            Console.WriteLine("What it's used for: " + dataStructureGlossary[obj]);
        }
        else
        {
            Console.WriteLine("This data structure is not in our database.");
        }
    }

    public static void WriteToGlossary(Dictionary<string, string> dataStructureGlossary)
    {
        try
        {
            using (StreamWriter writer = new StreamWriter("dataStructureGlossary.txt", true))
            {
                Console.WriteLine("Please enter a data structure: ");
                string obj = Console.ReadLine().ToLower();
                Console.WriteLine("Please enter what it's used for: ");
                string meaning = Console.ReadLine();
                dataStructureGlossary[obj] = meaning;
                writer.WriteLine("Data Structure: " + obj);
                writer.WriteLine("What it's used for: " + meaning);
                writer.WriteLine();
            }
        }
        catch (IOException e)
        {
            Console.WriteLine("An error occurred.");
            Console.WriteLine(e.Message);
        }
    }

    public static void FillGlossary(Dictionary<string, string> dataStructureGlossary)
    {
        try
        {
            using (StreamReader reader = new StreamReader("dataStructureGlossary.txt"))
            {
                string line;
                string obj = null;
                string meaning = null;
                while ((line = reader.ReadLine()) != null)
                {
                    if (line.StartsWith("Data Structure: "))
                    {
                        obj = line.Substring("Data Structure: ".Length).ToLower();
                    }
                    else if (line.StartsWith("What it's used for: "))
                    {
                        meaning = line.Substring("What it's used for: ".Length);
                    }
                    else if (string.IsNullOrWhiteSpace(line) && obj != null && meaning != null)
                    {
                        dataStructureGlossary[obj] = meaning;
                        obj = null;
                        meaning = null;
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            Console.WriteLine("An error occurred.");
            Console.WriteLine(e.Message);
        }
    }
}

