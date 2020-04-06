package com.github.lightverse.fresco.learner;

import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

import java.util.concurrent.TimeUnit;

public class EmptyMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
    private static final long PARAMS_CHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);
    @Override
    public MemoryCacheParams get() {
        return new MemoryCacheParams(
                1,
                1,
                1,
                1,
                1,
                PARAMS_CHECK_INTERVAL_MS);
    }
}
