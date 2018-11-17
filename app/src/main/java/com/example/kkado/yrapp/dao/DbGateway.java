package com.example.kkado.yrapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 */
public class DbGateway {
    private static DbGateway gw;
    private SQLiteDatabase db;

    private DbGateway(Context ctx) {
        SqliteAdapter helper = new SqliteAdapter(ctx);
        db = helper.getWritableDatabase();
    }

    /**
     *
     * @param myContext
     * @return
     */
    public static DbGateway getInstance(Context myContext) {
        if (gw == null)
            gw = new DbGateway(myContext);
        return gw;
    }

    /**
     *
     * @return
     */
    public SQLiteDatabase getDatabase() {
        return this.db;
    }
}
