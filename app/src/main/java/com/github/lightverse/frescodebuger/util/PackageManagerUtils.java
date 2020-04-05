package com.github.lightverse.frescodebuger.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.github.lightverse.frescodebuger.function.Function;
import com.github.lightverse.frescodebuger.function.FunctionHelper;

import java.util.List;

public class PackageManagerUtils {


    public static List<String> queryIntentActivitiesName(Context context, Intent intent){
        final PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
        return FunctionHelper.mapList(resolveInfos, new Function<ResolveInfo, String>() {
            @Override
            public String apply(ResolveInfo resolveInfo) {
                return resolveInfo.activityInfo.name;
            }
        });
    }


    public static List<ActivityInfo> queryIntentActivitiesInfo(Context context, Intent intent){
        final PackageManager pm = context.getPackageManager();
        final List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
        return FunctionHelper.mapList(resolveInfoList, new Function<ResolveInfo, ActivityInfo>() {
            @Override
            public ActivityInfo apply(ResolveInfo resolveInfo) {
                return resolveInfo.activityInfo;
            }
        });
    }
}
