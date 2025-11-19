package com.test.dsalg.dsa;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class DsaTrialsTest {

    @Test
    public void checkTargetSum() {
        DsaTrials dsaTrials = new DsaTrials();
        int[] mySourceArray = new int[]{1,3, 17, 2, 18, 5};
        log.debug(Arrays.toString(dsaTrials.findTargetSumInArray(mySourceArray, 10)));
    }
}
