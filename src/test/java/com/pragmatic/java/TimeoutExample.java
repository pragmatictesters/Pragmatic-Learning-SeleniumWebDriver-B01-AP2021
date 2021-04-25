package com.pragmatic.java;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TimeoutExample {


    public static void main(String[] args) {


        Duration timeoutInSeconds = Duration.ofSeconds(10);
        Clock clock = Clock.systemDefaultZone();
        Instant end = clock.instant().plus(timeoutInSeconds);
        int count = 0;

        while (clock.instant().isBefore(end)){
            System.out.println(" In the loop " + ++count);
        }
    }

}
