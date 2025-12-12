package com.teera.filework;

import java.util.prefs.Preferences;

public class Pref
{
    public static final String LEADING = "teeraTextAreaLeading";
    private static Preferences preferences = Preferences.userRoot();

    public static Preferences getPreferences()
    {
        return preferences;
    }

}