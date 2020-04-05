package com.github.lightverse.frescodebuger.util;

import android.content.Context;
import android.content.Intent;

public class StartActivityUtils {

    public static void startByName(Context context,String name){
        Intent intent = new Intent();
        intent.setClassName(context,name);
        context.startActivity(intent);
    }
}
