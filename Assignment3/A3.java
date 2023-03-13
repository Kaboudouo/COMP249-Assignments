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
       System.out.println("\nThis record has too FEW fields: \n" + record);
    }
}

class TooManyFieldsException extends Exception{
    public TooManyFieldsException(String record){
       System.out.println("\nThis record has too MANY fields: \n" + record);
    }
}

class MissingFieldException extends Exception{
    public MissingFieldException(String record, String fieldCodes){
       System.out.println("\nThis record is missing fields: \n" + record + "  Missing Field Code: " + fieldCodes);
    }
}

class UnkownGenreException extends Exception{
    public UnkownGenreException(String record){
      System.out.println("\nInvalid genre: \n" + record);
    }
}

class BadPriceException extends Exception{
    public BadPriceException(String record){
       System.out.println("\nInvalid Price: \n" + record);
    }
}

class BadYearException extends Exception{
    public BadYearException(String record){
       System.out.println("\nInvalid Year: \n" + record);
    }
}
class BadIsbn10Exception extends Exception{
    public BadIsbn10Exception(String record){
        System.out.println("\nBad ISBN-10: \n" + record);
    }
}
class BadIsbn13Exception extends Exception{
    public BadIsbn13Exception(String record){
        System.out.println("\nBad ISBN-13: \n" + record);
    }
}


public class A3 {

    static void writeToCsv(String book, String genre){
        // System.out.println("\n" + book);
    }

    static String[] createFields(String book){
        String[] fields = {"", "", "", "", "", ""};
        int quoteCount = 0;
        int fieldCount = 0;

        for (char c : book.toCharArray()){
            if (c == '"'){
                quoteCount++;
                continue;
            }
            
            if (c == ',' && quoteCount != 1){
                fieldCount++;
                if (fieldCount > 5){
                    break;
                }
                continue;
            }
            fields[fieldCount] += String.valueOf(c);
        }

        try{
            if (fieldCount > 5){
                throw new TooManyFieldsException(book);
            } else if (fieldCount < 5){
                throw new TooFewFieldsException(book);
            }
        } catch(TooManyFieldsException e){
        } catch (TooFewFieldsException e){
        }
        return fields;
    }

    static void validateSyntax(String book){

        String[] fields;

        fields = createFields(book);
        
        try {
            String missingFieldCode = "";
            for (int i = 0; i < fields.length; i++){
                // System.out.print(i + ": " + fields[i] + " ");
                if( fields[i] == ""){
                    missingFieldCode += i;
                }
            }
            if (missingFieldCode != ""){
                throw new MissingFieldException(book, missingFieldCode);
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
        } catch (UnkownGenreException e){
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

        while (bookScanner.hasNextLine()){
            validateSyntax(bookScanner.nextLine());
        }
        bookScanner.close();
    }

    static void do_part1(){
        // Pull file from path
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

        //checkBooks("books2010.csv.txt");
    }

    // Writes to new .ser files
    static void writeToSer(String book, String path){
        System.out.println("\nVALID BOOK: " + book);
    }

    // Validates each field of the syntactically correct books
    static void validateFields(String book, String originPath){
        String[] fields = createFields(book);

        try{
            // Price check
            double price = Float.parseFloat(fields[2]);
            if (price < 0){
                throw new BadPriceException(book);
            }

            // ISBN check, String > Char > Int
            String isbn = fields[3];
            int sum = 0;
            if (isbn.length() == 10){
                for (int i = 0; i < 10; i++){
                    sum += Math.abs(i-10) * Character.getNumericValue(isbn.charAt(i));
                }
                if (sum % 11 != 0){
                    throw new BadIsbn10Exception(book);
                }
            } else if (isbn.length() == 13){
                for (int i = 0; i < 13; i++){
                    sum += ((i%2 * 2) + 1) * Character.getNumericValue(isbn.charAt(i));
                }
                if (sum % 10 != 0){
                    throw new BadIsbn13Exception(book);
                }
            }

            //Year check
            int year = Integer.parseInt(fields[5]);
            if (year < 1995 || year > 2010){
                throw new BadYearException(book);
            }

            // Book is now certainly syntactically and semantically correct
            writeToSer(book, originPath);

        }catch (BadPriceException e){
        }catch (BadYearException e){
        }catch (BadIsbn10Exception e){
        }catch (BadIsbn13Exception e){
        }

    }   

    static void do_part2(){
        String[] pathsToOpen = {"Cartoons_Comics.csv", "Hobbies_Collectibles_Books.csv", "Movies_TV.csv", "Music_Radio_Books.csv", "Nostalgia_Electric_Books.csv", "Old_Time_Radio.csv", "Sports_Memorabilia.csv" + "Trains_Planes_Automobiles.csv"};
        Scanner scanner = null;

        for (int i = 0; i < pathsToOpen.length; i++){
            try {
                InputStream inputStream = new FileInputStream("./Assignment3/output/" + pathsToOpen[i]);
                new BufferedInputStream(inputStream);
                scanner = new Scanner (inputStream);
            } catch (FileNotFoundException e){
                System.out.println("Could not find input name file.");
                System.exit(0);
            }

            while (scanner.hasNextLine()){
                validateFields(scanner.nextLine(), pathsToOpen[i]);
            }
        }
    }

    public static void main (String[] args){
        do_part1();
        do_part2();
    }
}
