package Assignment3;

import java.util.Scanner;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.File;

class TooFewFieldsException extends Exception{
    public TooFewFieldsException(String record){
       // System.out.println("\nThis record has too FEW fields: \n" + record);
    }
}

class TooManyFieldsException extends Exception{
    public TooManyFieldsException(String record){
       // System.out.println("\nThis record has too MANY fields: \n" + record);
    }
}

class MissingFieldException extends Exception{
    public MissingFieldException(String record){
       // System.out.println("\nThis record is missing fields: \n" + record);
    }
}

class UnkownGenreException extends Exception{
    public UnkownGenreException(String record){
      // System.out.println("\nInvalid genre: \n" + record);
    }
}

class BadPriceException extends Exception{
    public BadPriceException(String record){
       // System.out.println("\nInvalid Price: \n" + record);
    }
}

class BadYearException extends Exception{
    public BadYearException(String record){
       // System.out.println("\nInvalid Year: \n" + record);
    }
}
class BadIsbn10Exception extends Exception{
    public BadIsbn10Exception(String record){
        // System.out.println("\nBad ISBN-10: \n" + record);
    }
}
class BadIsbn13Exception extends Exception{
    public BadIsbn13Exception(String record){
        // System.out.println("\nBad ISBN-13: \n" + record);
    }
}
class GeneralSemanticsException extends Exception{
    public GeneralSemanticsException(String record){
        // System.out.println("Invalid semantics: \n" + record);
    }
}


public class A3 {
    static PrintWriter cartoonsWriter = null;
    static PrintWriter hobbiesWriter = null;
    static PrintWriter moviesWriter = null;
    static PrintWriter musicWriter = null;
    static PrintWriter nostalgiaWriter = null;
    static PrintWriter oldWriter = null;
    static PrintWriter sportWriter = null;
    static PrintWriter trainsWriter = null;

    static PrintWriter syntaxWriter = null;
    static PrintWriter semanticWriter = null;


    static String oldPath = "";

    // Id 0: Close All pw, Id 1: Set pw to part 1 files, Id 2: Set pw to part 2 files
    static void manipulateWriters(int id){
        if (id == 0){
            cartoonsWriter.close();
            hobbiesWriter.close();
            moviesWriter.close();
            musicWriter.close();
            nostalgiaWriter.close();
            oldWriter.close();
            sportWriter.close();
            trainsWriter.close();
            if (syntaxWriter != null){
                syntaxWriter.close();
            }
            if (semanticWriter != null){
                semanticWriter.close();
            }
        }else if (id == 1){
            try{
                cartoonsWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Cartoons_Comics.csv"));
                hobbiesWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Hobbies_Collectibles.csv"));
                moviesWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Movies_TV_Books.csv"));
                musicWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Music_Radio_Books.csv"));
                nostalgiaWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Nostalgia_Electronic_Books.csv"));
                oldWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Old_Time_Radio.csv"));
                sportWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Sport_Memorabilia.csv"));
                trainsWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/Trains_Planes_Automobiles.csv"));
                syntaxWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part1/syntax_error_file.txt"));
            } catch (Exception e){
                System.out.println("Could not find one or more of the output files (Part 1)");
                System.exit(0);
            }
        } else if (id == 2){
            try{
                cartoonsWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Cartoons_Comics.csv.ser"));
                hobbiesWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Hobbies_Collectibles.csv.ser"));
                moviesWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Movies_TV_Books.csv.ser"));
                musicWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Music_Radio_Books.csv.ser"));
                nostalgiaWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Nostalgia_Electronic_Books.csv.ser"));
                oldWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Old_Time_Radio.csv.ser"));
                sportWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Sport_Memorabilia.csv.ser"));
                trainsWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/Trains_Planes_Automobiles.csv.ser"));
                semanticWriter = new PrintWriter(new FileOutputStream("./Assignment3/output_part2/semantic_error_file.txt"));
            } catch (Exception e){
                System.out.println("Could not find one or more of the output files (Part 1)");
                System.exit(0);
            }
        }
    }

    static void writeToSyntaxFile(String book, String path, String msg){
        if (!oldPath.equals(path)){
            oldPath = path;
            syntaxWriter.println("Syntax Error in File: " + path + "\n======================================");
        }
        syntaxWriter.println("Error: " + msg);
        syntaxWriter.println("Record: " + book + "\n");
    }

    // Outputs both syntax and semantic records when needed
    static void writeToCsv(String book, String genre){
        switch (genre){
            case "CCB":
                cartoonsWriter.println(book);
                break;
            case "HCB":
                hobbiesWriter.println(book);
                break;
            case "MTV":
                moviesWriter.println(book);
                break;
            case "MRB":
                musicWriter.println(book);
                break;
            case "NEB":
                nostalgiaWriter.println(book);
                break;
            case "OTR":
                oldWriter.println(book);
                break;
            case "SSM":
                sportWriter.println(book);
                break;
            case "TPA":
                trainsWriter.println(book);
                break;
            default:
                System.out.println("Somehow an invalid genre slipped past...");
                System.exit(0);
        }
    }

    static String[] createFields(String book, String path){
        String[] fields = {"", "", "", "", "", ""};
        int quoteCount = 0;
        int fieldCount = 0;

        for (char c : book.toCharArray()){

            // Is title is quotes check
            if (c == '"'){
                quoteCount++;
                continue;
            }
            
            // Counts how many commas have passed, thus counting the number of fields except if comma is in quoted title
            if (c == ',' && quoteCount != 1){
                fieldCount++;
                if (fieldCount > 5){
                    break;
                }
                continue;
            }

            // Eliminates extra spaces past author field as to not cause type errors later
            if (c == ' ' && fieldCount > 1){
                continue;
            }

            // Reconstruct field from char
            fields[fieldCount] += String.valueOf(c);
        }

        try{
            if (fieldCount > 5){
                throw new TooManyFieldsException(book);
            } else if (fieldCount < 5){
                throw new TooFewFieldsException(book);
            }
        } catch(TooManyFieldsException e){
            writeToSyntaxFile(book, path, "Too Many Fields");
        } catch (TooFewFieldsException e){
            writeToSyntaxFile(book, path, "Too Few Fields");
        }
        return fields;
    }

    static void validateSyntax(String book, String path){

        String[] fields;

        fields = createFields(book, path);
        
        String missingFieldMsg = "Missing";
        // "first" boolean is purely for esthetics
        try {
            boolean first = true;
            for (int i = 0; i < fields.length; i++){
                if( fields[i] == ""){
                    if (!first){
                        missingFieldMsg += ",";
                    }
                    first = false;
                    switch(i){
                        case 0:
                            missingFieldMsg += " Title";
                            break;
                        case 1:
                            missingFieldMsg += " Author";
                            break;
                        case 2:
                            missingFieldMsg += " Price";
                            break;
                        case 3:
                            missingFieldMsg += " ISBN";
                            break;
                        case 4:
                            missingFieldMsg += " Genre";
                            break;
                        case 5:
                            missingFieldMsg += " Year";
                            break;
                        default:
                            System.out.println("Out of Range Error occured by now...");
                            System.exit(0);
                    }
                }
            }
            if (missingFieldMsg != "Missing"){
                throw new MissingFieldException(book);
            }

            boolean validGenre = false;
            String[] genreCodes = {"CCB","HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
            for (int i = 0; i < genreCodes.length; i++){
                if (fields[4].equals(genreCodes[i])){
                    validGenre = true;
                    break;
                }
            }
            if (!validGenre){
                throw new UnkownGenreException(book);
            }

            // Any field array we return now contains a syntactically correct book
            writeToCsv(book, fields[4]);

        } catch (MissingFieldException e){
            writeToSyntaxFile(book, path, missingFieldMsg);
        } catch (UnkownGenreException e){
            writeToSyntaxFile(book, path, "Invalid Genre");
        }
    }

    static void checkBooks(String path){
        Scanner bookScanner = null;

        try {
            InputStream bookStream = new FileInputStream("./Assignment3/docs/"+path);
            new BufferedInputStream(bookStream);
            bookScanner = new Scanner (bookStream);
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find file: " + path);
            return;
        }

        // Quickly checks if the next line is empty, then validates
        while (bookScanner.hasNextLine()){
            String newLine = bookScanner.nextLine();
            if (newLine == ""){
                continue;
            }
            validateSyntax(newLine, path);
        }

        bookScanner.close();
    }

    static void do_part1(){
        // Pull file from path
        manipulateWriters(1);

        Scanner scanner = null;
        try {
            InputStream inputStream = new FileInputStream("./Assignment3/docs/part1_input_file_names.txt");
            new BufferedInputStream(inputStream);
            scanner = new Scanner (inputStream);
        } catch (FileNotFoundException e){
            System.out.println("Could not find input name file.");
            System.exit(0);
        }

        // Instantiate Books Path Array
        int n =  scanner.nextInt();
        String[] input_files = new String[n];
        scanner.nextLine();
        for (int i = 0; i < n; i++){
            if (scanner.hasNextLine()){
                input_files[i] = scanner.nextLine();
                checkBooks(input_files[i]);
            }
        }

        scanner.close();
        manipulateWriters(0);
        //checkBooks("books2010.csv.txt");
    }

    static void writeToSemanticFile(String book, String path, String msg){
        if (!oldPath.equals(path)){
            oldPath = path;
            semanticWriter.println("Semantic Error in File: " + path + "\n======================================");
        }
        semanticWriter.println("Error: " + msg);
        semanticWriter.println("Record: " + book + "\n");
    }

    // Validates each field of the syntactically correct books
    static void validateFields(String book, String originPath){
        String[] fields = createFields(book, originPath);
        String superMsg = "Invalid";

        try{
            // Price check
            double price = Float.parseFloat(fields[2]);
            if (price < 0){
                superMsg += " Price";
                //throw new BadPriceException(book);
            }

            // ISBN check, String > Char > Int
            String isbn = fields[3];
            int sum = 0;
            if (isbn.length() == 10){
                for (int i = 0; i < 10; i++){
                    sum += Math.abs(i-10) * Character.getNumericValue(isbn.charAt(i));
                }
                if (sum % 11 != 0){
                    superMsg += " ISBN-10";
                    // throw new BadIsbn10Exception(book);
                }
            } else if (isbn.length() == 13){
                for (int i = 0; i < 13; i++){
                    sum += ((i%2 * 2) + 1) * Character.getNumericValue(isbn.charAt(i));
                }
                if (sum % 10 != 0){
                    superMsg += " ISBN-13";
                    // throw new BadIsbn13Exception(book);
                }
            }

            //Year check
            int year = Integer.parseInt(fields[5]);
            if (year < 1995 || year > 2010){
                superMsg += " Year";
                // throw new BadYearException(book);
            }

            // Creates error message including all semantic errors instead of just the thrown one
            if (superMsg != "Invalid"){
                throw new GeneralSemanticsException(book);
            }

            // Book is now certainly syntactically and semantically correct
            writeToCsv(book, fields[4]);

        } catch (GeneralSemanticsException e){
            writeToSemanticFile(book, originPath, superMsg);
        }
        // }catch (BadPriceException e){
        //     writeToSemanticFile(book, originPath, "Invalid Price");
        // }catch (BadYearException e){
        //     writeToSemanticFile(book, originPath, "Invalid Year");
        // }catch (BadIsbn10Exception e){
        //     writeToSemanticFile(book, originPath, "Invalid ISBN-10");
        // }catch (BadIsbn13Exception e){
        //     writeToSemanticFile(book, originPath, "Invalid ISBN-13");
        // }

    }   

    static void do_part2(){
        manipulateWriters(2);
        String[] pathsToOpen = {"Cartoons_Comics.csv", "Hobbies_Collectibles.csv", "Movies_TV_Books.csv", "Music_Radio_Books.csv", "Nostalgia_Electronic_Books.csv", "Old_Time_Radio.csv", "Sport_Memorabilia.csv", "Trains_Planes_Automobiles.csv"};
        Scanner scanner = null;

        for (int i = 0; i < pathsToOpen.length; i++){
            try {
                InputStream inputStream = new FileInputStream("./Assignment3/output_part1/" + pathsToOpen[i]);
                new BufferedInputStream(inputStream);
                scanner = new Scanner (inputStream);
            } catch (FileNotFoundException e){
                System.out.println("Could not find file: " + pathsToOpen[i]);
                System.exit(0);
            }

            while (scanner.hasNextLine()){
                String newLine = scanner.nextLine();
                if (newLine == ""){
                    continue;
                }
                validateFields(newLine, pathsToOpen[i]);
            }
        }

        manipulateWriters(0);
    }

    public static void main (String[] args){

        do_part1();
        do_part2();
    }
}
