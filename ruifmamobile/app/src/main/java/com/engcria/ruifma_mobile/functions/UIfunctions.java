package com.engcria.ruifma_mobile.functions;

import android.app.UiModeManager;
import android.content.Context;

import androidx.core.content.ContextCompat;

public class UIfunctions {
    private Context contextoLocal;

    public UIfunctions(Context contextoLocal) {
        this.contextoLocal = contextoLocal;
    }

    /*
        Return == 1 -> Thema ligth
        Return != 1 -> Theme Dark
     */
    public int currentTheme(){
        UiModeManager uiManager = (UiModeManager) contextoLocal.getSystemService(Context.UI_MODE_SERVICE);
        int UITheme = uiManager.getNightMode();
        if(UITheme == 1){
            return 1;
        }else{
            return 0;
        }
    }

    public void updateTheme(int currentTheme, int themeLigth, int themeDark){
        if(currentTheme == 1){
            contextoLocal.setTheme(themeLigth);
        }else{
            contextoLocal.setTheme(themeDark);
        }
    }

    public Context getContext() {
        return contextoLocal;
    }

    public void setContext(Context context) {
        this.contextoLocal = context;
    }
}
