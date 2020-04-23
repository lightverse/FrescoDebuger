package com.github.lightverse.fresco.learner;

import android.util.Log;

import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.ProducerContext;

import java.util.Map;

import androidx.annotation.NonNull;

public class LoggingListener implements RequestListener2 {
    @Override
    public void onRequestStart(@NonNull ProducerContext producerContext) {
        Log.d("onRequestStart",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onRequestSuccess(@NonNull ProducerContext producerContext) {
        Log.d("onRequestSuccess",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onRequestFailure(@NonNull ProducerContext producerContext, Throwable throwable) {
        Log.d("onRequestFailure",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onRequestCancellation(@NonNull ProducerContext producerContext) {
        Log.d("onRequestCancellation",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onProducerStart(@NonNull ProducerContext producerContext, @NonNull String producerName) {
        Log.d("onProducerStart",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onProducerEvent(@NonNull ProducerContext producerContext, @NonNull String producerName, @NonNull String eventName) {
        Log.d("onProducerEvent",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onProducerFinishWithSuccess(@NonNull ProducerContext producerContext, @NonNull String producerName, Map<String, String> extraMap) {
        Log.d("onProducerFinishWithSu",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onProducerFinishWithFailure(@NonNull ProducerContext producerContext, String producerName, Throwable t, Map<String, String> extraMap) {
        Log.d("onProducerFinishWithFa",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onProducerFinishWithCancellation(@NonNull ProducerContext producerContext, @NonNull String producerName,  Map<String, String> extraMap) {
        Log.d("onProducerFinishWithCa",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public void onUltimateProducerReached(@NonNull ProducerContext producerContext, @NonNull String producerName, boolean successful) {
        Log.d("onUltimateProducerRe",producerContext.getImageRequest().getSourceUri().toString());
    }

    @Override
    public boolean requiresExtraMap(@NonNull ProducerContext producerContext, @NonNull String producerName) {
        Log.d("requiresExtraMap",producerContext.getImageRequest().getSourceUri().toString());
        return true;
    }
}
