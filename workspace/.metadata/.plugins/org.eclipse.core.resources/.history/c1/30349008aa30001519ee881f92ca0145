package com.px.tool;

import android.content.Context;
import android.content.SharedPreferences;

public final class Preference {
    
	@SuppressWarnings("unused")
	private static final String TAG = "Preference";
    private static String mPrefName = "RAP_Preference";
    public static final String isFirst = "isFirst";
    
    public static final String PREF_GCM = "PREF_GCM";

    private Preference() {
        // not called
    }
    
    public static void putBoolean(final Context context,
            final String key, final boolean value) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);

        editor.apply();
    }

    public static void putFloat(final Context context,
            final String key, final float value) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key, value);

        editor.apply();

    }

    public static void putInt(final Context context, final String key, final int value) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);

        editor.apply();
    }

    public static void putLong(final Context context, final String key, final long value) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);

        editor.apply();
    }

    public static void putString(final Context context, final String key, final String value) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);

        editor.apply();
        //ObiLog.i(TAG, "putString() success - k:" + key + ", v:" + value);
    }

    public static boolean getBoolean(final Context context, final String key) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);

        try {
            boolean v = prefs.getBoolean(key, false);
            return v;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public static float getFloat(final Context context, final String key) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
        try {
            float v = prefs.getFloat(key, Float.NaN);
            return v;
        } catch (ClassCastException e) {
            return Float.NaN;
        }
    }

    public static int getInt(final Context context, final String key) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);

        try {
            int v = prefs.getInt(key, 0);
            return v;
        } catch (ClassCastException e) {
            return Integer.MIN_VALUE;
        }
    }

    public static long getLong(final Context context, final String key) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);

        try {
            long v = prefs.getLong(key, Long.MIN_VALUE);
            return v;
        } catch (ClassCastException e) {
            return Long.MIN_VALUE;
        }
    }
    
    public static String getString(final Context context, final String key) {
        SharedPreferences prefs = context
                .getSharedPreferences(mPrefName, Context.MODE_PRIVATE);

        try {
            String v = prefs.getString(key, "");
            return v;
        } catch (ClassCastException e) {
        	
            return "";
        }
    }

    public static String getPreferenceString(Context context, String key, String defValue) {
        return getPreferenceString(context, mPrefName, key, defValue);
    }
    public static String getPreferenceString(Context context, String name, String key, String defValue) {
        if (context == null) {
            return "";
        }

        SharedPreferences sharedPreferences = null;

        try {
            sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        if (sharedPreferences == null) {
            return "";
        }

        return sharedPreferences.getString(key, defValue);
    }
    
    public static boolean setPreferenceString(Context context, String key, String value) {
        return setPreferenceString(context, mPrefName, key, value);
    }
    public static boolean setPreferenceString(Context context, String name, String key, String value) {
        if (context == null) {
            return false;
        }
        SharedPreferences pref = null;
        pref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        return editor.commit();
    }
}
