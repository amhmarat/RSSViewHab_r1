package ru.sberbank.ma.rssviewhab;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by internet on 05.02.2018.
 */

public class RecViewActivity extends MainActivity {
    protected static final String INTENT_ID = "INTENT_ID";

    @Override
    protected Fragment getFragment() {
        return RecViewControl.getInstance();
    }

    public static Intent newIntent(Context context, UUID uuid) {
        Intent intent = new Intent(context, RecViewActivity.class);
        intent.putExtra(INTENT_ID, uuid);
        return intent;
    }
}
