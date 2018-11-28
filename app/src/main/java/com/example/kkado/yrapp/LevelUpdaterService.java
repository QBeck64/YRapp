package com.example.kkado.yrapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

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
    // TODO: Customize helper method
    public static void startActionUpdateCdb(Context context) {
        Intent intent = new Intent(context, LevelUpdaterService.class);
        intent.setAction(ACTION_UPDATE_CDB);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action UPDATE_CG with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionUpdateCg(Context context, int idCg) {
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
                handleActionUpdateCdb();
            } else if (ACTION_UPDATE_CG.equals(action)) {
                final int param1 = intent.getIntExtra(CG_ID, 0);
                handleActionUpdateCg(param1);
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
    private void handleActionUpdateCdb() {

        throw new UnsupportedOperationException("Not yet implemented");
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
    private void handleActionUpdateCg(int id) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
