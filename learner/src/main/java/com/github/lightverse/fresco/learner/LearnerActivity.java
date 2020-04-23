package com.github.lightverse.fresco.learner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

import java.util.HashSet;
import java.util.Set;

public class LearnerActivity extends AppCompatActivity {


//    public static final String[] PIC_URLS = {"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3801527567,35691958&fm=26&gp=0.jpg",
//            "https://img14.360buyimg.com/n1/jfs/t1/105040/14/17800/504137/5e8c2ea9Ec6c943dc/0fa6bc5e3d425281.jpg",
//            "https://img14.360buyimg.com/pop/s1180x940_jfs/t1/101020/22/17114/70560/5e83f328E206dbfe4/927dd84d75d6fded.jpg.webp",
//            "https://img11.360buyimg.com/pop/s1180x940_jfs/t1/108823/7/10816/98403/5e83d912Ecb1b1a84/68e74b2e88c52080.jpg.webp"
//    };

    public static final String[] PIC_URLS = {"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3801527567,35691958&fm=26&gp=0.jpg"};

    private Button mLoadPicBtn;

    private LinearLayout mPicContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoggingListener loggingListener = new LoggingListener();
        Set<RequestListener2> requestListener2s = new HashSet<>();
        requestListener2s.add(loggingListener);
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setDiskCacheEnabled(false)
                .setBitmapMemoryCacheParamsSupplier(new EmptyMemoryCacheParamsSupplier())
                .setEncodedMemoryCacheParamsSupplier(new EmptyMemoryCacheParamsSupplier())
                .setRequestListener2s(requestListener2s)
                .build();
        Fresco.initialize(this,imagePipelineConfig,null);

        FrescoSystrace.provide(new FrescoSystrace.Systrace() {
            @Override
            public void beginSection(String name) {
                Log.e("LearnerActivity","trace-"+name);
            }

            @Override
            public FrescoSystrace.ArgsBuilder beginSectionWithArgs(String name) {
                return null;
            }

            @Override
            public void endSection() {

            }

            @Override
            public boolean isTracing() {
                return true;
            }
        });
        setContentView(R.layout.activity_learner);
        mPicContainer = findViewById(R.id.pic_container);
        showPictures(PIC_URLS);
        mLoadPicBtn = findViewById(R.id.load_pic_btn);
        mLoadPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUrls();
            }
        });
    }



    private void showPictures(String[] picUrls){
        mPicContainer.removeAllViews();
        if(picUrls != null){
            for (String picUrl : picUrls) {
                TimedSimpleDraweeView simpleDraweeView = new TimedSimpleDraweeView(this);
                simpleDraweeView.delayLoadUrl(picUrl);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);
                simpleDraweeView.setLayoutParams(params);
                mPicContainer.addView(simpleDraweeView);
            }
        }
    }


    private void loadUrls(){
        int childCount = mPicContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = mPicContainer.getChildAt(i);
            if(childAt instanceof TimedSimpleDraweeView){
                ((TimedSimpleDraweeView) childAt).load();
            }
        }
    }
}
