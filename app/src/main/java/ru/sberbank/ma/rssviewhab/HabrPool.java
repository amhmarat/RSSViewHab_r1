package ru.sberbank.ma.rssviewhab;

/**
 * Created by internet on 05.02.2018.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class HabrPool {
    private static HabrPool sHabrPool;
    private static List<Habr> mHabrList;

    private HabrPool(Context context) {
        mHabrList = new ArrayList<>();
    }

    public static HabrPool getInstance(Context context) {
        if (mHabrList == null)
            return sHabrPool = new HabrPool(context);
        return sHabrPool;
    }

    public static List<Habr> getHabrList() {
        return mHabrList;
    }

    public static Habr getHabr(UUID uuid) {
        for (Habr habr : mHabrList)
            if (habr.getUUID().equals(uuid))
                return habr;
        return null;
    }

    static void addHabr(Habr habr) {
        mHabrList.add(habr);
    }
}
