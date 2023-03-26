package Assignment4;

import java.util.ArrayList;

class Book{
    String title; 
    String autjor;
    double price;
    long ISBN;
    String genre;
    int year;
}


class BookList{
    private class Node{
        private Book b;
        private Book next;
    
        public Node(Book _b){
            b = _b;
        }
    }
    
   Node head;

    public BookList(){
        head = null; 
    }

    public void addToStart(Book b){
        head = new Node(b);
    }

    public void storeRecordsByYear(int yr){

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
    ArrayList arrLst = new ArrayList<Book>();
    BookList bkLst = new BookList();

    //File Extraction

    //UI
}
