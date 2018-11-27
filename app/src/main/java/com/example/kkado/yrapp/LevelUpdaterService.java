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


//    private static final String EXTRA_PARAM1 = "com.example.kkado.yrapp.extra.PARAM1";
//    private static final String EXTRA_PARAM2 = "com.example.kkado.yrapp.extra.PARAM2";

    public LevelUpdaterService() {
        super("LevelUpdaterService");
    }

    /**
     * Starts this service to perform action UPDATE_CDB with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context) {
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
    public static void startActionBaz(Context context) {
        Intent intent = new Intent(context, LevelUpdaterService.class);
        intent.setAction(ACTION_UPDATE_CG);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_CDB.equals(action)) {
                handleActionUpdateCdb();
            } else if (ACTION_UPDATE_CG.equals(action)) {
                handleActionUpdateCg();
            }
        }
    }

    /**
     * Handle action UPDATE_CDB in the provided background thread.
     */
    private void handleActionUpdateCdb() {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action UPDATE_CG in the provided background thread.
     */
    private void handleActionUpdateCg() {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
