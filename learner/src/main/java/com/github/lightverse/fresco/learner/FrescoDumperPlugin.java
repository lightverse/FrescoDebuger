package com.github.lightverse.fresco.learner;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.CountingLruMap;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.stetho.dumpapp.ArgsHelper;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.github.lightverse.fresco.learner.utils.ReadableUtils;
import com.github.lightverse.fresco.learner.utils.ReflectUtils;

import java.io.PrintStream;
import java.util.Iterator;

public class FrescoDumperPlugin implements DumperPlugin {

    private static final String NAME = "fresco";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void dump(DumperContext dumpContext) throws DumpException {
        Iterator<String> args = dumpContext.getArgsAsList().iterator();

        String command = ArgsHelper.nextOptionalArg(args, "");
        if ("memoryCache".equals(command)) {
            doMemCache(dumpContext.getStdout(),args);
        } else {
            doUsage(dumpContext.getStdout());
            if (!"".equals(command)) {
                throw new DumpUsageException("Unknown command: " + command);
            }
        }
    }

    private void doMemCache(PrintStream writer, Iterator<String> args) {
        String param = ArgsHelper.nextOptionalArg(args, "");
        InstrumentedMemoryCache<CacheKey, CloseableImage> bitmapMemoryCache = ImagePipelineFactory.getInstance().getBitmapMemoryCache();
        InstrumentedMemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache = ImagePipelineFactory.getInstance().getEncodedMemoryCache();
        if("clear".equals(param)){
            Fresco.getImagePipeline().clearMemoryCaches();
        }else if("ls".equals(param)){
            CountingMemoryCache mDelegate = ReflectUtils.getField(bitmapMemoryCache, "mDelegate", CountingMemoryCache.class);
            writer.println("mDelegate: " + mDelegate);
            if(mDelegate != null){
                CountingLruMap<CacheKey, Object> mCachedEntries = ReflectUtils.getField(mDelegate, "mCachedEntries", CountingLruMap.class);
                writer.println("mCachedEntries: " + mCachedEntries);
                if(mCachedEntries != null) {
                    CacheKey firstKey = mCachedEntries.getFirstKey();
                    if(firstKey != null) {
                        writer.println("firstKey-url: " + mCachedEntries.getFirstKey().getUriString());
                        Object firstValue = mCachedEntries.get(mCachedEntries.getFirstKey());
                        CloseableReference<CloseableImage> valueRef = ReflectUtils.getField(firstValue, "valueRef", CloseableReference.class);
                        if (valueRef != null && valueRef.get() != null) {
                            writer.println("firstValue-size: " + ReadableUtils.bytesToReadable(valueRef.get().getSizeInBytes()));
                        }
                    }
                }
            }
        }else {
            writer.println("BitmapMemoryCache size: " + ReadableUtils.bytesToReadable(bitmapMemoryCache.getSizeInBytes()));
            writer.println("BitmapMemoryCache count: " + bitmapMemoryCache.getCount());
            writer.println("EncodedMemoryCache size: " + ReadableUtils.bytesToReadable(encodedMemoryCache.getSizeInBytes()));
            writer.println("EncodedMemoryCache count: " + encodedMemoryCache.getCount());
        }

    }


    private void doUsage(PrintStream writer) {
        final String cmdName = "dumpapp " + NAME;

        String usagePrefix = "Usage: " + cmdName + " ";
        String blankPrefix = "       " + cmdName + " ";
        writer.println(usagePrefix + "<command> [command-options]");
        writer.println(blankPrefix + "memCache");
        writer.println();
        writer.println(cmdName + " memCache: get or clear memory cache");
        writer.println();
     }
}
