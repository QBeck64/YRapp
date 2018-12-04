package com.example.kkado.yrapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.kkado.yrapp.dao.InvoicingDAO;
import com.example.kkado.yrapp.dao.PeriodDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Invoicing;
import com.example.kkado.yrapp.entity.Period;
import com.example.kkado.yrapp.entity.Person;

import java.util.Date;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class LevelUpdaterService extends IntentService {

    private static final String ACTION_UPDATE_CDB = "com.example.kkado.yrapp.action.UPDATE_CDB";
    private static final String ACTION_UPDATE_CG = "com.example.kkado.yrapp.action.UPDATE_CG";
    private final String TAG = "LevelUpdaterService";

    private static final String PERIOD_ID = "com.example.kkado.yrapp.extra.PERIOD_ID";
    private static final String PERIOD_FDATE = "com.example.kkado.yrapp.extra.PERIOD_FDATE";
    private static final String PERIOD_SDATE = "com.example.kkado.yrapp.extra.PERIOD_SDATE";
    private static final String CG_ID = "com.example.kkado.yrapp.extra.CG_ID";

    public LevelUpdaterService() {
        super("LevelUpdaterService");
    }

    /**
     * Starts this service to perform action UPDATE_CDB. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateCdb(Context context, Period period) {
        Intent intent = new Intent(context, LevelUpdaterService.class);
        intent.setAction(ACTION_UPDATE_CDB);
        intent.putExtra("period", period);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action UPDATE_CG with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateCg(Context context, long idCg) {
        Intent intent = new Intent(context, LevelUpdaterService.class);
        intent.setAction(ACTION_UPDATE_CG);
        intent.putExtra(CG_ID, idCg);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_CDB.equals(action)) {
                Bundle data = intent.getExtras();
                handleActionUpdateCdb((Period)data.getParcelable("period"));
            } else if (ACTION_UPDATE_CG.equals(action)) {
                final long param1 = intent.getLongExtra(CG_ID, 0);
                try {
                    handleActionUpdateCg(param1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Handle action UPDATE_CDB in the provided background thread.
     * This will, for each salesperson:
     *  - compare the current invoicing with the various invoicing targets
     *  - set the level to the target reached according to company requirements.
     *  - reset current invoicing
     *  - update the DB with the info
     */
    /*
     * - reset and create also new periods? Maybe it's better to do this in the caller
     * */
    private void handleActionUpdateCdb(Period period) {
        //DAO initialization
        PersonDAO dao = new PersonDAO(this);
        InvoicingDAO iDao = new InvoicingDAO(this);
        try {
            //fetching data
            List<Person> contacts = dao.select();
            List<Invoicing> invoicings = iDao.select();
            Resources res = getResources();
            int[] invoicingBoundaries = res.getIntArray(R.array.groupSizes);
            //iteration
            for (Person p:contacts) {
                for (Invoicing i:invoicings) {
                    //check if we found the right invoicing, meaning the one connected to the current
                    //person and to the current period
                    if (i.getIdPeriod() == period.getIdPeriod() && i.getIdPerson() == p.getIdPerson()) {
                        //check invoicing target reached
                        int newPersonLevel = 0;
                        for (int c = 0; invoicingBoundaries[c] <= i.getInvoicing() && c < invoicingBoundaries.length; c++)
                            newPersonLevel++;

                        //save eventual new person
                        if (newPersonLevel != p.getLevel()) {
                            p.setLevel(newPersonLevel);
                            dao.save(p);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }

    }

    /**
     * Handle action UPDATE_CG in the provided background thread.
     * The given id will be used to retrieve the corresponding Person, and if the person exists
     * (where exists means id != 0) and is a group leader (which means  6 <= person.level <= 11)
     * then check the number of people in her group (which means all people whose parentId = id).
     * This count is then compared to the group level requirements, and person.level is updated
     * if the count satisfies a new requirement.
     * Then the database is updated.
     */
    private void handleActionUpdateCg(long id) {
        //retrieve person by id
        try {
            PersonDAO dao = new PersonDAO(this);
            Person groupLeader = null;
            groupLeader = dao.selectId(id);
            if (groupLeader.getLevel() >= 6 && groupLeader.getLevel() <= 11) {

                //count group dimension
                int groupSize = 0;
                List<Person> contacts = dao.select();
                for (Person p:contacts)
                {
                    if (p.getIdPersonParent().equals(groupLeader.getIdPerson()))
                        groupSize++;
                }

                //get group boundaries
                Resources res = getResources();
                int[] groupBoundaries = res.getIntArray(R.array.groupSizes);
                //6 is the lowest group leader level
                int newGroupLevel = 6;
                for (int i = 0;groupBoundaries[i] <= groupSize && i < groupBoundaries.length;i++)
                {
                    newGroupLevel++;
                }
                groupLeader.setLevel(newGroupLevel);
                if (dao.save(groupLeader))
                {
                    Log.i(TAG, "Saved group leader into DB");
                }
                else
                {
                    Log.e(TAG, "error in saving into DB");
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        //check if the level is group leader

    }
}
