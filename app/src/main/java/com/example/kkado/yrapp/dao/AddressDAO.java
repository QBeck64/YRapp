package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.Enum.TypeAddress;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    private final String TABLE = "Address";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**Address data access object using AddressDAO
     * @param myContext
     */
    public AddressDAO(Context myContext) {
        initializeDataBase(myContext);
    }

    /**Executes the strategy to initialize the database for the given context.
     * @param myContext
     */
    private void initializeDataBase(Context myContext) {
        dbHelper = new SqliteAdapter(myContext);

        try {
            dbHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mySQLiteDatabase = dbHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    /**Boolean contains multiple field whose type is boolean. In addition, this class provides many methods for converting a boolean 
      to a String and a String to a boolean, as well as other constants and methods useful when dealing with a boolean.
     * @param address
     * @return
     */
    public boolean save(Address address) {
        ContentValues cv = new ContentValues();
        cv.put("type", address.getType().getId());
        cv.put("nameAddress", address.getNameAddress());
        cv.put("numberAddress", address.getNumberAddress());
        cv.put("complement", address.getComplement());
        cv.put("province", address.getProvince());
        cv.put("city", address.getCity());
        cv.put("country", address.getCountry());
        cv.put("postalCode", address.getPostalCode());
        cv.put("idPerson", address.getIdPerson());

        if (address.getIdAddress() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idAddress=?", new String[]{address.getIdAddress() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     *Long saveAddress contains fields whose type is long.
     * @param address
     * @return
     */
    public long saveAddress(Address address) {
        ContentValues cv = new ContentValues();

        cv.put("type", address.getType().getId());
        cv.put("nameAddress", address.getNameAddress());
        cv.put("numberAddress", address.getNumberAddress());
        cv.put("complement", address.getComplement());
        cv.put("province", address.getProvince());
        cv.put("city", address.getCity());
        cv.put("country", address.getCountry());
        cv.put("postalCode", address.getPostalCode());
        cv.put("idPerson", address.getIdPerson());

        if (address.getIdAddress() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idAddress=?", new String[]{address.getIdAddress() + ""}) ;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv);
    }

    /**Implement boolean for delete in the mySQLiteDatabase.delete in the table idAddres.
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idAddress=?", new String[]{id + ""}) > 0;
    }

    /**Implement Listto retrieve address and location.
     * @return
     * @throws Exception
     */
    public List<Address> select() throws Exception {
        List<Address> addressList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Address", null);

        while (cursor.moveToNext()) {
            int idAddress = cursor.getInt(cursor.getColumnIndex("idAddress"));
            TypeAddress type = TypeAddress.getTypeAddressDescription(cursor.getString(cursor.getColumnIndex("type")));
            String nameAddress = cursor.getString(cursor.getColumnIndex("nameAddress"));
            int numberAddress = cursor.getInt(cursor.getColumnIndex("numberAddress"));
            String complement = cursor.getString(cursor.getColumnIndex("complement"));
            String province = cursor.getString(cursor.getColumnIndex("province"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String country = cursor.getString(cursor.getColumnIndex("country"));
            String postalCode = cursor.getString(cursor.getColumnIndex("postalCode"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));

            addressList.add(new Address(idAddress, type, nameAddress, numberAddress, complement, province, city, country, postalCode, idPerson));
        }
        cursor.close();

        return addressList;
    }

    /**Use of Address to select and tegorize ID, then identify the address and information
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Address selectId(long id) throws Exception {
        Address address = null;
        String[] params = new String[]{String.valueOf(id)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Address WHERE idAddress = ? ", params);

        while (cursor.moveToNext()) {
            int idAddress = cursor.getInt(cursor.getColumnIndex("idAddress"));
            TypeAddress type = TypeAddress.getTypeAddressDescription(cursor.getString(cursor.getColumnIndex("type")));
            String nameAddress = cursor.getString(cursor.getColumnIndex("nameAddress"));
            int numberAddress = cursor.getInt(cursor.getColumnIndex("numberAddress"));
            String complement = cursor.getString(cursor.getColumnIndex("complement"));
            String province = cursor.getString(cursor.getColumnIndex("province"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String country = cursor.getString(cursor.getColumnIndex("country"));
            String postalCode = cursor.getString(cursor.getColumnIndex("postalCode"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));

            address = new Address(idAddress, type, nameAddress, numberAddress, complement, province, city, country, postalCode, idPerson);
        }
        cursor.close();

        return address;
    }

    public Address selectPersonId(long id) throws Exception {
        Address address = null;
        String[] params = new String[]{String.valueOf(id)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Address WHERE idPerson = ? ", params);

        while (cursor.moveToNext()) {
            int idAddress = cursor.getInt(cursor.getColumnIndex("idAddress"));
            TypeAddress type = TypeAddress.getTypeAddressDescription(cursor.getString(cursor.getColumnIndex("type")));
            String nameAddress = cursor.getString(cursor.getColumnIndex("nameAddress"));
            int numberAddress = cursor.getInt(cursor.getColumnIndex("numberAddress"));
            String complement = cursor.getString(cursor.getColumnIndex("complement"));
            String province = cursor.getString(cursor.getColumnIndex("province"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String country = cursor.getString(cursor.getColumnIndex("country"));
            String postalCode = cursor.getString(cursor.getColumnIndex("postalCode"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));

            address = new Address(idAddress, type, nameAddress, numberAddress, complement, province, city, country, postalCode, idPerson);
        }
        cursor.close();

        return address;
    }

}
