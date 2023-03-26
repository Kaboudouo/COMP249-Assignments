package Assignment4;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

class Book{
    String title; 
    String author;
    double price;
    long isbn;
    String genre;
    int year;

    public Book(String _title, String _author, double _price, long _isbn, String _genre, int _year){
        title = _title;
        author = _author;
        price = _price;
        isbn = _isbn;
        genre = _genre;
        year = _year;
    }

    public String toString(){
        return title + ", " + author + ", " + price + ", " + isbn + ", " + genre + ", " + year;
    }
}


class BookList{
    private class Node<Type>{
        private Book b;
        private Node<Book> next;
    
        public Node(Book _b, Node<Book> _next){
            b = _b;
            next = _next;
        }
    }
    
   Node<Book> head;

    public BookList(){
        head = null; 
    }

    public void addToStart(Book b){
        head = new Node<Book>(b, head);
    }

    public void storeRecordsByYear(int yr){
        Node<Book> tmp = head;
        PrintWriter pw = null;

        while (tmp != null){
            if ((int) tmp.b.year == (int) yr){
                if (pw == null){
                    try {
                        pw = new PrintWriter( new FileOutputStream("./Assignment4/SortedByYear/"+ yr + ".txt"));
                    } catch (IOException e){
                        System.out.println("Encountered IO error.");
                        System.exit(0);
                    }
                }
                pw.println(tmp.b.toString());
            }
            tmp = tmp.next;
        }

        pw.close();
    }

    public boolean insertBefore(long isbn, Book b){
        return true;
    }

    public boolean insertBetween(long isbn1, long isbn2, Book b){
        return true;
    }

    public void displayContent(){

    }

    public boolean delConsecutiveRepeatedRecords(){
        return true;
    }

    public BookList extractAuthList(String aut){
        return new BookList();
    }

    public boolean swap(long isbn1, long isbn2){
        return true;
    }

    public void commit(){

    }


}

public class A4 {

    static String[] createFields(String book){
        String[] fields = {"", "", "", "", "", ""};
        int fieldCount = 0;

        for (char c : book.toCharArray()){
            
            // Counts how many commas have passed, thus counting the number of fields except if comma is in quoted title
            if (c == ','){
                fieldCount++;
                continue;
            }

            // Eliminates extra spaces past author field as to not cause type errors later
            if (c == ' ' && fieldCount > 1){
                continue;
            }

            // Reconstruct field from char
            fields[fieldCount] += String.valueOf(c);
        }

        return fields;
    }

    static void checkBooks(ArrayList<Book> arrList, BookList bkList){
        Scanner bookScanner = null;

        try {
            InputStream bookStream = new FileInputStream("./Assignment4/Books.txt");
            new BufferedInputStream(bookStream);
            bookScanner = new Scanner (bookStream);
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find file");
            return;
        }

        // Quickly checks if the next line is empty, then validates
        while (bookScanner.hasNextLine()){
            String newLine = bookScanner.nextLine();
            if (newLine == ""){
                continue;
            }
            String[] newFields = createFields(newLine);

            // Year validation 
            int year = Integer.parseInt(newFields[5]);
            Book newBook = new Book(newFields[0], newFields[1], Double.parseDouble(newFields[2]), Long.parseLong(newFields[3]), newFields[4], year);
            if (year > 2024){
                arrList.add(newBook);
                continue;
            }
            bkList.addToStart(newBook);
        }

        bookScanner.close();
    }

    public static void printYrErr(ArrayList<Book> arrList){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("./Assignment4/YearErr.txt"));
        } catch (IOException e){
            System.exit(0);
        }

        for (Book book : arrList){
            pw.println(book.toString());
        }

        pw.close();
    }

    public  static void main(String[] args){
        ArrayList<Book> arrLst = new ArrayList<Book>();
        BookList bkLst = new BookList();

        checkBooks(arrLst, bkLst);

        // Creates YearErr and prints books (if any)
        if (arrLst.size() != 0){
            //printYrErr(arrLst);
        }

        bkLst.storeRecordsByYear(1905);
        

        //UI

    }

}
