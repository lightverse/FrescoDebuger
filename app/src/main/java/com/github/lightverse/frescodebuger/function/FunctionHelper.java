package com.github.lightverse.frescodebuger.function;

import java.util.ArrayList;
import java.util.List;

public class FunctionHelper {


    public static <T, R> List<R> mapList(
            List<? extends T> list,
            Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for(T item: list){
            R tempR = function.apply(item);
            result.add(tempR);
        }
        return result;
    }

    public static <T> void forEachList(List<? extends T> list,
                                       Consumer<T> consumer){
        for(T item: list){
            consumer.accept(item);
        }
    }

}
