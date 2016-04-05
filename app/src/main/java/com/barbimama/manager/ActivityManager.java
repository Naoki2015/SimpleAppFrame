package com.barbimama.manager;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yiwei on 16/3/24.
 */
public class ActivityManager {

    private static ActivityManager instance = new ActivityManager();
    private List<Activity> activities = new LinkedList<Activity>();
    public static ActivityManager getInstance(){
        return instance;
    }

    public void addActivity(Activity activity){
        if(activities != null)
            activities.add(activity);
    }

    public void removeActivity(Activity activity){
        if(activities!= null && activities.contains(activity)){
            activities.remove(activity);
        }
    }

    public void clear(){
        if(activities != null && activities.size()>0){
            for(int i=0; i<activities.size(); i++){
                Activity activity = activities.get(i);
                if(activity!= null){
                    activity.finish();
                }
            }
            activities.clear();
        }
    }
}
