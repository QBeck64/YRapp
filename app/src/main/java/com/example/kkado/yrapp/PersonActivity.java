package com.example.kkado.yrapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.dao.SqliteAdapter;
import com.example.kkado.yrapp.entity.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {
private SqliteAdapter myDataBase;
private ListView lvPerson;
private List<Person> personList = new ArrayList<Person>();
private ArrayAdapter<Person> arrayAdapterPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initializeResources();
        initializeDataBase();
        try {
            initializePersonList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initializeResources() {
        lvPerson=(ListView)findViewById(R.id.lvPerson);
    }

    private void initializePersonList() throws Exception {
        PersonDAO dao =new PersonDAO(this);
        personList.clear();
        personList=dao.select();
        arrayAdapterPerson=new ArrayAdapter<Person>(this,android.R.layout.simple_list_item_1, personList);
        lvPerson.setAdapter(arrayAdapterPerson);
    }

    private void initializeDataBase() {
        myDataBase = new SqliteAdapter(this);
        File database = getApplicationContext().getDatabasePath(SqliteAdapter.DATABASE_NAME);
        if(database.exists()==false){
            myDataBase.getReadableDatabase();
            if(copyDataBase(this)){
                alert("Success");

            }else{
                alert("Error");
            }
        }
    }

    private void alert(String error) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }

    private boolean copyDataBase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(SqliteAdapter.DATABASE_NAME);
            String outFile = SqliteAdapter.LOCALDB + SqliteAdapter.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int lenght = 0;
            while ((lenght = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, lenght);

            }
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        }
    }



