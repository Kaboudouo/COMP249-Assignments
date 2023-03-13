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



public class A3 {

    static void writeToCsv(String book, String genre){
        System.out.println("\n" + book);
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

    static void validateFields(String book, String[] fields){
        System.out.println("\n" + book);
    }   

    static void do_part2(){

    }

    public static void main (String[] args){
        do_part1();
        do_part2();
    }
}
