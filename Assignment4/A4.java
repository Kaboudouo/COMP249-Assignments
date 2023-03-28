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

// -----------------------------------------------------
// Assignment 4
// Written by: Noa Chayer 40223439
// Due: April 17 2023
// -----------------------------------------------------

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

    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (obj.getClass() != this.getClass()) {return false;}

        Book otherBook = (Book) obj;
        return this.title.equals(otherBook.title) && this.author.equals(otherBook.author) && this.price == otherBook.price && this.isbn == otherBook.isbn && this.genre.equals(otherBook.genre) && this.year == otherBook.year;
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

    public boolean insertBefore(long _isbn, Book b){
        if ((long) head.b.isbn == (long) _isbn){
            addToStart(b);
            return true;
        }

        Node<Book> prev = null;
        Node<Book> curr = head;

        while (curr != null && (long) curr.b.isbn != (long) _isbn){
            prev = curr;
            curr = curr.next;
        }
        if (curr != null){
            prev.next = new Node<Book>(b, curr);
            return true;
        }
        return false;
    }

    public boolean insertBetween(long isbn1, long isbn2, Book b){
        Node<Book> prev = head;
        Node<Book> curr = head.next;

        while (curr != null && prev != null){
            if ((long) curr.b.isbn == (long) isbn2 && (long) prev.b.isbn == (long) isbn1){
                prev.next = new Node<Book>(b, curr);
                return true;
            }else {
                prev = curr;
                curr = curr.next;
            }
        }
        return false;
    }

    public void displayContent(){
        Node<Book> tmp = head;
        while (tmp != null){
            System.out.println(tmp.b.toString() + " ==>");
            tmp = tmp.next;
        }
        System.out.println("===> head");
    }

    public boolean delConsecutiveRepeatedRecords(){
        Node<Book> comparator = head;
        Node<Book> comparee = head.next;
        boolean delRecords = false;
        boolean looped = false;

        while (comparator != null && comparee != null){
            while (comparator.b.equals(comparee.b)){
                if (comparee.next == null){
                    comparee = head;
                    looped = true;
                } else{
                    comparee = comparee.next;
                }
                delRecords = true;
            }

            if (looped){
                comparator.next = null;
                head = comparee;
            } else{
                comparator.next = comparee;
            }

            comparator = comparator.next;
            comparee = comparee.next;
        }

        return delRecords;
    }

    public BookList extractAuthList(String aut){

        Node<Book> tmpNode = head;
        BookList autBkLst = new BookList();
        ArrayList<Book> tmpLst = new ArrayList<Book>();


        while (tmpNode != null){
            if (tmpNode.b.author.equalsIgnoreCase(aut)){
                tmpLst.add(tmpNode.b);
            }
            tmpNode = tmpNode.next;
        }

        for (int i = tmpLst.size()-1; i >=0; i--){
            autBkLst.addToStart(tmpLst.get(i));
        }

        return autBkLst;
    }

    public boolean swap(long isbn1, long isbn2){
        Node<Book> prevS1 = null;
        Node<Book> swap1 = head;
        Node<Book> prevS2 = null;
        Node<Book> swap2 = head;

        boolean foundSwap1 = false;
        boolean foundSwap2 = false;

        while ((!foundSwap1 || !foundSwap2) && swap1 != null && swap2 != null){
            if ((long) swap1.b.isbn == (long) isbn1){
                foundSwap1 = true;
            } else{
                prevS1 = swap1;
                swap1 = swap1.next;
            }

            if ((long) swap2.b.isbn == (long) isbn2){
                foundSwap2 = true;
            } else{
                prevS2 = swap2;
                swap2 = swap2.next;
            }
        }

        if (foundSwap1 && foundSwap2){
            if (prevS1 != null){
                prevS1.next = swap2;
            }

            if(prevS2 != null){
                prevS2.next = swap1;
            }

            Node<Book> tmp = swap2.next;
            swap2.next = swap1.next;
            swap1.next = tmp;

            // If either of the concerned nodes are the head, they need to be switched
            if (swap1 == head){
                head = swap2;
            } else if (swap2 == head){
                head = swap1;
            }

            return true;
        } else{
            return false;
        }
    }

    public void commit(){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileOutputStream("./Assignment4/Update_Books.txt"));
        } catch (IOException e){
            System.out.println("IO Exception occured.");
            System.exit(0);
        }

        Node<Book> tmp = head;
        while(tmp != null){
            pw.println(tmp.b.toString());
            tmp = tmp.next;
        }

        pw.close();
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

        ArrayList<Book> tempList = new ArrayList<Book>();
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
            tempList.add(newBook);
        }

        // Created new list just to add nodes in 'correct' order
        for (int i = tempList.size() - 1; i >= 0; i--){
            bkList.addToStart(tempList.get(i));
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

    // Esthetics
    static String printEquals(int n){
        String deco = "";
        for (int i = 0; i < n; i++){
            deco += "=";
        }
        return deco;
    }

    static void printDisplay(String msg){
        System.out.println("\nHere are the contents of the list" + msg);
        System.out.println("=================================" + printEquals(msg.length()));
        
    }

    static Book createNewBook(Scanner in){
        String title;
        String author;
        double price;
        long isbn;
        String genre;
        int year;

        System.out.println("\nAll right I am now going to ask you to fill out the book's fields...");
        System.out.print("\nWhat is the book title?: ");
        title = "\"" + in.nextLine() + "\"";
        System.out.print("\nWho's the author?: ");
        author = in.nextLine();
        while (true){
            System.out.print("\nHow much does it cost?: ");
            try {
                price = Math.floor(in.nextDouble() * 100) / 100;
                in.nextLine();
                break;
            } catch (Exception e){
                in.nextLine();
                System.out.println("\nSorry, that was not a valid input, please try again.");
                continue;
            }
        }
        while (true){
            System.out.print("\nWhat is this book's ISBN?: ");
            try {
                isbn = in.nextLong();
                in.nextLine();
                break;
            } catch (Exception e){
                in.nextLine();
                System.out.println("\nSorry, that was not a valid input, please try again.");
                continue;
            }
        }
        System.out.print("\nWhat is the book's genre?: ");
        genre = in.next();
        while (true){
            System.out.print("\nFinally, in what year was the book published?: ");
            try {
                year = in.nextInt();
                in.nextLine();
                if (year > 2024){
                    System.out.println("\nSorry, but that year is not possible yet, please try again.");
                    continue;
                }
                break;
            } catch (Exception e){
                in.nextLine();
                System.out.println("\nSorry, that was not a valid input, please try again.");
                continue;
            }
        }

        return new Book(title, author, price, isbn, genre, year);
    }

    static void startUI(BookList bkLst){
        Scanner in = new Scanner(System.in);
        int response = 0;
        printDisplay("");
        while(true){
            bkLst.displayContent();
            System.out.println("What would you like to do with the list of books?");
            System.out.println("    1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
            System.out.println("    2) Ask me to delete all consecutive repeated records;");
            System.out.println("    3) Give me an author name and I will create a new list with the records of this author and display them;");
            System.out.println("    4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;");
            System.out.println("    5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!");
            System.out.println("    6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
            System.out.println("    7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;");
            System.out.println("    8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");

            System.out.print("Enter your selection: ");

            try{
                response = in.nextInt();
                in.nextLine();
                if (response < 1 || response > 8){
                    System.out.println("\nSorry, that is not a valid option, please try again.");
                    continue;
                }

            } catch (Exception e){
                in.nextLine();
                System.out.println("\nSorry, that was not a valid input, please try again.");
                continue;
            }
            
            if (response == 1){
                while (true){
                    System.out.print("\nPlease enter the year for which you would like to extract: ");
                    try{
                        response = in.nextInt();
                        if (response > 2024){
                            System.out.println("\nSorry, that is not a valid year, please try again.");
                            in.nextLine();
                            continue;
                        }
                        bkLst.storeRecordsByYear(response);

                        System.out.println("\nFile for books from " + response + " has been created.");
                        break;
                    } catch (NullPointerException e){
                        System.out.println("\nSorry, I couldn't find any records for that year.");
                        break;
                    } catch (Exception e){
                        System.out.println("\nSorry, that is not a valid input, please try again.");
                        in.nextLine();
                        continue;
                    }
                }
                printDisplay("");
            } else if (response == 2){
                bkLst.delConsecutiveRepeatedRecords();
                System.out.println("\nAll consecutive records have been deleted.");
                printDisplay(" after deleting consecutive duplicate records");
            } else if (response == 3){
                while(true){
                    System.out.print("\nPlease provide the author's name (Space sensitive): ");
                    if (in.hasNextLine()){
                        String author = in.nextLine();
                        System.out.println("\nHere's what I could find for " + author + ":\n");
                        bkLst.extractAuthList(author).displayContent();
                        break;
                    } else{
                        System.out.println("\nSorry, I didn't quite catch that, please try again.");
                    }
                }
                printDisplay("");
            } else if (response == 4){
                long isbnToCheck;
                while(true){
                    System.out.print("\nPlease enter the ISBN of the book you would like to succeed this new entry: ");

                    try{
                        isbnToCheck = in.nextLong();
                        in.nextLine();
                        break;
                    } catch (Exception e){
                        in.nextLine();
                        System.out.println("\nSorry, that was not a valid input, please try again.");
                        continue;
                    }
                }    
                bkLst.insertBefore(isbnToCheck, createNewBook(in));
                System.out.println("\nYou new book entry has been inserted.");
                printDisplay(" after inserting the new record");
            } else if (response == 5){
                long isbnToCheck1;
                    long isbnToCheck2;
                    while(true){
                        System.out.print("\nPlease enter the ISBNs of the books you would like to both precede and succeed this new entry: ");
                        try{
                            isbnToCheck1 = in.nextLong();
                            isbnToCheck2 = in.nextLong();
                            in.nextLine();
                            break;
                        } catch (Exception e){
                            in.nextLine();
                            in.nextLine();
                            System.out.println("\nSorry, that was not a valid input, please try again.");
                            continue;
                        }
                    }      
                    if (bkLst.insertBetween(isbnToCheck1, isbnToCheck2, createNewBook(in))){
                        System.out.println("\nYou new book entry has been inserted.");
                        printDisplay(" after inserting the new record");
                    } else{
                        System.out.println("\nSorry, I couldn't find any books by those ISBNs so I couldn't insert your new entry.");
                        printDisplay("");
                    }


            } else if (response == 6){
                long isbnToSwap1;
                    long isbnToSwap2;
                    while(true){
                        System.out.print("\nPlease enter the ISBNs of the books you would like to swap: ");
                        try{
                            isbnToSwap1 = in.nextLong();
                            isbnToSwap2 = in.nextLong();
                            in.nextLine();
                            break;
                        } catch (Exception e){
                            in.nextLine();
                            in.nextLine();
                            System.out.println("\nSorry, that was not a valid input, please try again.");
                            continue;
                        }
                    }      
                    if (bkLst.swap(isbnToSwap1, isbnToSwap2)){
                        System.out.println("\nBooks have been swaped.");
                        printDisplay(" after swaping records");
                    } else{
                        System.out.println("\nCouldn't swap the requested books.");
                        printDisplay("");
                    }
            } else if (response == 7){
                bkLst.commit();
                System.out.println("\nChanges to the book list have been commited.");
                printDisplay(" that is now commited");
            } else{
                System.out.println("\nNow exiting program...");
                break;
            }     
        }

        System.out.println("\nThank you for using Kab's amazing book list manager!");
        in.close();
        System.exit(0);

    }

    public  static void main(String[] args){
        ArrayList<Book> arrLst = new ArrayList<Book>();
        BookList bkLst = new BookList();

        checkBooks(arrLst, bkLst);

        if (arrLst.size() != 0){
            //printYrErr(arrLst);
            System.out.println("YearError File Created.");
        }

        System.out.println("Welcome to Kab's amazing book list manager!\n");

        startUI(bkLst);
    }

}