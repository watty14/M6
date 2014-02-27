package com.example.wallt;

import java.util.LinkedList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseManager {

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "wallt_database";

    //Table Names
    private static final String TABLE_USERS = "users_table";
    private static final String TABLE_BANKACCOUNTS = "bankAccounts_table";
    private static final String TABLE_TRANSACTIONS = "transactions_table";

    //Common column names
    private static final String KEY_ID = "id";
    private static final String USERNAME = "username";

    //USERS TABLE - column names
    private static final String PASSWORD = "password";

    //BANKACCOUNTS TABLE - column names
    private static final String ACCOUNT_NUMBER = "accountNumber";
    private static final String BANK_NAME = "bankName";
    private static final String BALANCE = "balance";
    
    //TRANSACTIONS TABLE - column names
    private static final String TRANSACTION_AMOUNT = "transactionAmount";

    //Create USERS Table
    private static final String CREATE_TABLE_USERS = "create table "
        + TABLE_USERS + " (" + KEY_ID + " integer primary key autoincrement,"
        + USERNAME + " text not null unique, "
        + PASSWORD + " text not null"
        + ");";

    //Create BANKACCOUNTS Table
    private static final String CREATE_TABLE_BANKACCOUNTS = "create table "
        + TABLE_BANKACCOUNTS + " (" + KEY_ID
        + " integer primary key autoincrement,"
        + USERNAME + " text not null,"
        + ACCOUNT_NUMBER + " text not null,"
        + BANK_NAME + " text not null, "
        + BALANCE + " integer not null"
        + " unique (" + BANK_NAME + ", " + ACCOUNT_NUMBER + ")"
        + ");";
    
    //Create TRANSACTIONS Table
    private static final String CREATE_TABLE_TRANSACTIONS = "create table "
		+ TABLE_TRANSACTIONS + " (" + KEY_ID
		+ "integer primary key autoincrement,"
		+ BANK_NAME + " text not null,"
		+ ACCOUNT_NUMBER + " text not null,"
		+ TRANSACTION_AMOUNT + " integer not null"
		+ ");";

    private SQLiteDatabase database;

    public DataBaseManager(Context context) {
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        this.database = helper.getWritableDatabase();
    }

    public long addUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD, password);
        long userId = -1;
        try {
            userId = database.insert(TABLE_USERS, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
        return userId;
    }
    
    public long addTransaction(String bankName, String accountNumber, int transactionAmount) {
        ContentValues values = new ContentValues();
        values.put(BANK_NAME, bankName);
        values.put(ACCOUNT_NUMBER, accountNumber);
        values.put(TRANSACTION_AMOUNT, transactionAmount);
        long userId = -1;
        try {
            userId = database.insert(TABLE_TRANSACTIONS, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
        return userId;
    }

    public long addBankAccount(String username, String accountNumber,
            String bankName, double balance) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(ACCOUNT_NUMBER, accountNumber);
        values.put(BANK_NAME, bankName);
        values.put(BALANCE, balance);
        long userId = -1;
        try {
            userId = database.insert(TABLE_BANKACCOUNTS, null, values);
            System.out.println(userId);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
        return userId;
    }
    
    public LinkedList<BankAccount> getBankAccounts(String username) {
        LinkedList<BankAccount> list = new LinkedList<BankAccount>();
        String selectQuery = "SELECT * FROM " + TABLE_BANKACCOUNTS + " WHERE "
                + USERNAME + "= '" + username + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
            	int key = cursor.getInt(0);
                String accountNumber = cursor.getString(2);
                String bankName = cursor.getString(3);
                int balance = cursor.getInt(4);
                BankAccount bankAccount = new BankAccount(key, accountNumber,
                        balance, bankName);
                list.add(bankAccount);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    
    public LinkedList<Double> getTransactions(String bankName, String accountNumber) {
        LinkedList<Double> list = new LinkedList<Double>();
        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE "
                + BANK_NAME + "= '" + bankName + "'" + " AND "
        		+ ACCOUNT_NUMBER + "= '" + accountNumber + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
            	double amount = cursor.getInt(3);
                list.add(amount);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    
    public BankAccount getOneAccount(int key) {
        BankAccount bankAccount = null;
    	String selectQuery = "SELECT * FROM " + TABLE_BANKACCOUNTS + " WHERE "
                + KEY_ID + "= '" + key + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String accountNumber = cursor.getString(2);
                String bankName = cursor.getString(3);
                int balance = cursor.getInt(4);
                bankAccount = new BankAccount(key, accountNumber,
                        balance, bankName);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return bankAccount;
    }

    public void deleteUser(String userName) {
        try {
            database.delete(TABLE_USERS, USERNAME + "= '"
                    + userName + "'", null);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }

    public void deleteBankAccount(String userName, int accountNumber,
            String bankName) {
        try {
            database.delete(TABLE_USERS, USERNAME + "= '"
                    + userName + "'" + " AND "
                    + ACCOUNT_NUMBER + "= '" + accountNumber + "'" + " AND "
                    + BANK_NAME + "= '" + bankName + "'", null);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }

    public void updateBalance(int key, double balance) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, key);
        try {
            database.update(TABLE_BANKACCOUNTS, values,
                    BALANCE + "= " + balance,
                    null);
            System.out.println("Balance updated");
        } catch (Exception e) {
            Log.e("DB Error", e.toString());
            e.printStackTrace();
        }
    }

    public void closeDataBase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public boolean loginVerify(String username, String password) {
        boolean found = false;
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE "
            + USERNAME + "= '" + username + "'" + " AND " + PASSWORD
            + "= '" + password + "'";
        Cursor c = database.rawQuery(selectQuery, null);
        if (c != null && !c.isAfterLast()) {
           found = true;
        }
        return found;
    }

    public boolean registerVerify(String username, String password) {
        Cursor c = null;
        boolean added = false;
        if (!username.equals("") && !password.equals("")) {
            String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + USERNAME + "= '" + username + "'";
            c = database.rawQuery(selectQuery, null);
            if (c.isAfterLast()) {
                addUser(username, password);
                added = true;
            }
        }
        return added;
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase database) {
            database.execSQL(CREATE_TABLE_USERS);
            database.execSQL(CREATE_TABLE_BANKACCOUNTS);
            database.execSQL(CREATE_TABLE_TRANSACTIONS);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                int newVersion) {
        }
    }

}
