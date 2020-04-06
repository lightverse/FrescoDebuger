package com.github.lightverse.fresco.learner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

public class LearnerActivity extends AppCompatActivity {


    public static final String PIC_URL = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3801527567,35691958&fm=26&gp=0.jpg";

    private Button mLoadPicBtn;

    private SimpleDraweeView mSimpleDraweeView;
    private SimpleDraweeView mSimpleDraweeView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setDiskCacheEnabled(false)
                .setBitmapMemoryCacheParamsSupplier(new EmptyMemoryCacheParamsSupplier())
                .setEncodedMemoryCacheParamsSupplier(new EmptyMemoryCacheParamsSupplier())
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
        mLoadPicBtn = findViewById(R.id.load_pic_btn);
        mSimpleDraweeView = findViewById(R.id.picture);
        mSimpleDraweeView2 = findViewById(R.id.picture2);
        mLoadPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSimpleDraweeView.setImageURI(PIC_URL);
                mSimpleDraweeView.setImageURI(PIC_URL);
            }
        });
    }
}
