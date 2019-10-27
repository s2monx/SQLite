package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "book_list", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table Book(id integer primary key,"+"title text,author text)");
        sqLiteDatabase.execSQL("create table Authors(id_author integer primary key,name text,address text, email text)");

        sqLiteDatabase.execSQL("create table Book(id_book integer primary key,"+"title text," +
                "id_author integer " + "constraint fk_id_author references Authors(id_author)" + "on delete cascade on update cascade)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Authors");
        onCreate(sqLiteDatabase);
    }


    public boolean insertBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_book",book.getId_book());
        contentValues.put("title",book.getTitle());
        contentValues.put("id_author",book.getId_author());
        db.insert("Book",null, contentValues);
        return true;
    }
    public boolean insertAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author",author.getId_author());
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        db.insert("Authors",null, contentValues);
        return true;

    }

    public  Book getBook(int id_book){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book where id_book="+
                id_book, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        cursor.close();
        db.close();
        return book;
    }

    public  Author getAuthor(int id_author){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors where id_author="+
                id_author, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Author author = new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        cursor.close();
        db.close();
        return author;
    }
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book", null, null );
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors", null );
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean deleteBook(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("delete from Book where id_book ="+id,null);
        if (cursor == null){
            return false;
        }
        else{
            cursor.moveToFirst();
            cursor.close();
        }
        return true;
    }
    public boolean deleteAuthor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("delete from Authors where id_author ="+id,null);
        if (cursor == null){
            return false;
        }
        else{
            cursor.moveToFirst();
            cursor.close();
        }
        return true;
    }
    public boolean updateBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", book.getTitle());
        contentValues.put("id_author", book.getId_author());
        contentValues.put("id_book", book.getId_book());
        db.update("Book", contentValues, "id_book = ?", new String[]{String.valueOf(book.getId_book())});
        return true;
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("address", author.getAuthorAddress());
//        contentValues.put("email", author.getAuthorEmail());
//        contentValues.put("id_author", author.getAuthorID());
//        contentValues.put("name", author.getAuthorName());
//        db.update("Author", contentValues, "id_author = ?",
//                new String[]{Integer.toString(author.getAuthorID())});
//        return true;
    }

    public ArrayList<String> getBookAuthor(int id){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlstr= "select Books.id_author, title" +
                "from Authors inner join Books on" +
                "Authors.id_author = Books.id_author" +
                "where Authors.id_author="+id;
        Cursor cursor = db.rawQuery(sqlstr, null);
        if (cursor == null) {
            cursor.moveToFirst();
        }
        while(cursor.isAfterLast() ==  false){
            list.add(cursor.getInt(0) +"");
            list.add(cursor.getString(1));
            cursor.moveToNext();

        }
        return list;
    }

}
