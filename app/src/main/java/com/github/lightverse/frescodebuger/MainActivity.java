package com.github.lightverse.frescodebuger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.github.lightverse.frescodebuger.function.Consumer;
import com.github.lightverse.frescodebuger.function.Function;
import com.github.lightverse.frescodebuger.function.FunctionHelper;
import com.github.lightverse.frescodebuger.util.PackageManagerUtils;
import com.github.lightverse.frescodebuger.util.StartActivityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_VIEW = "com.github.lightverse.fresco.VIEW";

    public static final String DATA_KEY_NAME = "name";
    public static final String DATA_KEY_TITLE = "title";

    public ListView mActivityListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityListView = findViewById(R.id.activity_list);

        final Intent intent = new Intent(ACTION_VIEW);
        final List<ActivityInfo> activitiesName = PackageManagerUtils.queryIntentActivitiesInfo(this, intent);
        final List<Map<String, String>> listData = FunctionHelper.mapList(activitiesName, new Function<ActivityInfo, Map<String, String>>() {
            @Override
            public Map<String, String> apply(ActivityInfo activityInfo) {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put(DATA_KEY_NAME, activityInfo.name);
                hashMap.put(DATA_KEY_TITLE,getTitle(activityInfo));
                return hashMap;
            }
        });

        mActivityListView.setAdapter(new SimpleAdapter(this,listData,R.layout.activity_list_item,
                new String[]{DATA_KEY_TITLE},new int[]{R.id.title}));
        mActivityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StartActivityUtils.startByName(MainActivity.this,listData.get(position).get(DATA_KEY_NAME));
            }
        });
    }



    private String getTitle(ActivityInfo activityInfo){
        if(activityInfo.labelRes != 0){
            return getString(activityInfo.labelRes);
        }else if(!TextUtils.isEmpty(activityInfo.nonLocalizedLabel)){
            return activityInfo.nonLocalizedLabel.toString();
        }
        return activityInfo.name;
    }
}
