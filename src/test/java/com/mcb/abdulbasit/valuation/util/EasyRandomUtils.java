package com.mcb.abdulbasit.valuation.util;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jeasy.random.EasyRandom;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class EasyRandomUtils {

    private static final EasyRandom easyRandom = new EasyRandom();

    public <T> T mock(Class<T> clazz) {
        return easyRandom.nextObject(clazz);
    }

    public <T> List<T> mock(Class<T> clazz, int count) {
        return easyRandom.objects(clazz, count).collect(Collectors.toList());
    }

}
