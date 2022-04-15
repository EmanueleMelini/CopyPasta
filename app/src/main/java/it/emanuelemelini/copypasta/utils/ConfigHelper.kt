package it.emanuelemelini.copypasta.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import it.emanuelemelini.copypasta.R;

public class ConfigHelper {

    public static String getConfigValue(Context context, String name) {
        Resources resources = context.getResources();

        try {
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            return properties.getProperty(name);
        } catch (Resources.NotFoundException e) {
            Log.e("Config", "Unable to find the config file: " + e.getMessage());
        } catch (IOException e) {
            Log.e("Config", "Failed to open config file.");
        }

        return null;
    }
}
