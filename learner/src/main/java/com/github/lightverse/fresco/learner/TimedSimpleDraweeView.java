package com.github.lightverse.fresco.learner;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class TimedSimpleDraweeView extends SimpleDraweeView {

    public String url;

    public static final String TAG = TimedSimpleDraweeView.class.getSimpleName();
    public TimedSimpleDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public TimedSimpleDraweeView(Context context) {
        super(context);
    }

    public TimedSimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimedSimpleDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TimedSimpleDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void setImageURI(Uri uri, Object callerContext) {
        AbstractDraweeControllerBuilder<?,?,?,?> controllerBuilder = getControllerBuilder();
        controllerBuilder.setCallerContext(callerContext)
                        .setUri(uri)
                        .setOldController(getController());
        controllerBuilder.setControllerListener(new BaseControllerListener<Object>(){

            long startTime = 0;
            @Override
            public void onFinalImageSet(String id, Object imageInfo,Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                long finishTime = System.currentTimeMillis();
                Log.d(TAG,"load pic time: "+(finishTime - startTime));
            }

            @Override
            public void onSubmit(String id, Object callerContext) {
                super.onSubmit(id, callerContext);
                startTime = System.currentTimeMillis();
            }
        });
        setController(controllerBuilder.build());
    }


    public void delayLoadUrl(String url){
        this.url = url;
    }


    public void load(){
        setImageURI(url);
    }
}
