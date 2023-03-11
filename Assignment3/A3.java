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

public class A3 {
    public static void do_part1(){
        // Pull file from path
        Scanner scanner = null;
        try {
            InputStream inputStream = new FileInputStream("./Assignment3/docs/part1_input_file_names.txt");
            new BufferedInputStream(inputStream);
            scanner = new Scanner (inputStream);
        } catch (FileNotFoundException e){
            System.out.println("Could not find file.");
            System.exit(0);
        }

        // Instantiate Books Path Array
        int n =  scanner.nextInt();
        String[] input_files = new String[n];
        scanner.nextLine();
        for (int i = 0; i < n; i++){
            input_files[i] = scanner.next();
            System.out.println(input_files[i]);
        }
    }

    public static void main (String[] args){
        do_part1();
    }
}
