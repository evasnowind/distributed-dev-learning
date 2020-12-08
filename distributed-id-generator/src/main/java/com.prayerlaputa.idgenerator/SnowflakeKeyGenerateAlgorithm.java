package com.prayerlaputa.idgenerator;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 *
 * 此处snowflake代码实现取自shardingsphere
 *
 * @author chenglong.yu
 * created on 2020/12/8
 */
public class SnowflakeKeyGenerateAlgorithm {

    public static final long EPOCH;

    private static final String WORKER_ID_KEY = "worker-id";

    private static final String MAX_VIBRATION_OFFSET_KEY = "max-vibration-offset";

    private static final String MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS_KEY = "max-tolerate-time-difference-milliseconds";

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

    private static final long WORKER_ID = 0;

    private static final int DEFAULT_VIBRATION_VALUE = 1;

    private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;

    @Setter
    private static TimeService timeService = new TimeService();

    @Getter
    @Setter
    private Properties props = new Properties();

    private long workerId;

    private int maxVibrationOffset;

    private int maxTolerateTimeDifferenceMilliseconds;

    private int sequenceOffset = -1;

    private long sequence;

    private long lastMilliseconds;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }


    public void init() {
        workerId = getWorkerId();
        maxVibrationOffset = getMaxVibrationOffset();
        maxTolerateTimeDifferenceMilliseconds = getMaxTolerateTimeDifferenceMilliseconds();
    }

    private long getWorkerId() {
        long result = Long.parseLong(props.getOrDefault(WORKER_ID_KEY, WORKER_ID).toString());
        Preconditions.checkArgument(result >= 0L && result < WORKER_ID_MAX_VALUE, "Illegal worker id.");
        return result;
    }

    private int getMaxVibrationOffset() {
        int result = Integer.parseInt(props.getOrDefault(MAX_VIBRATION_OFFSET_KEY, DEFAULT_VIBRATION_VALUE).toString());
        Preconditions.checkArgument(result >= 0 && result <= SEQUENCE_MASK, "Illegal max vibration offset.");
        return result;
    }

    private int getMaxTolerateTimeDifferenceMilliseconds() {
        return Integer.parseInt(props.getOrDefault(MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS_KEY, MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS).toString());
    }


    public synchronized Comparable<?> generateKey() {
        long currentMilliseconds = timeService.getCurrentMillis();
        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
            currentMilliseconds = timeService.getCurrentMillis();
        }
        if (lastMilliseconds == currentMilliseconds) {
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                currentMilliseconds = waitUntilNextTime(currentMilliseconds);
            }
        } else {
            vibrateSequenceOffset();
            sequence = sequenceOffset;
        }
        lastMilliseconds = currentMilliseconds;
        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    @SneakyThrows(InterruptedException.class)
    private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) {
        if (lastMilliseconds <= currentMilliseconds) {
            return false;
        }
        long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;
        Preconditions.checkState(timeDifferenceMilliseconds < maxTolerateTimeDifferenceMilliseconds,
                "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastMilliseconds, currentMilliseconds);
        Thread.sleep(timeDifferenceMilliseconds);
        return true;
    }

    private long waitUntilNextTime(final long lastTime) {
        long result = timeService.getCurrentMillis();
        while (result <= lastTime) {
            result = timeService.getCurrentMillis();
        }
        return result;
    }

    private void vibrateSequenceOffset() {
        sequenceOffset = sequenceOffset >= maxVibrationOffset ? 0 : sequenceOffset + 1;
    }


    public String getType() {
        return "SNOWFLAKE";
    }


    public static void main(String[] args) {
        SnowflakeKeyGenerateAlgorithm snowflake = new SnowflakeKeyGenerateAlgorithm();
        snowflake.init();
        HashMap<Long, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            Long id = (Long) snowflake.generateKey();
            Long remain = id % 16;
            cntMap.put(remain, cntMap.getOrDefault(remain, 0) + 1);
            System.out.println("snowflake id=" + id);
        }

        Iterator iterator = cntMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = (Map.Entry<Long, Integer>) iterator.next();
            System.out.println("idx=" + entry.getKey() + " val=" + entry.getValue());
        }
    }
}
