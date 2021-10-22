package com.example.segundoparcial.dataMC;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfigMC {
    private SharedPreferences preferences;

    public SharedPreferenceConfigMC(Context context){
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void delete(){
        preferences.edit().clear();
    }
}
