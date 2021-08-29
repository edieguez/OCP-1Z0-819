package com.artemisa;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
public class CodeChallengeTest {
    @Test
    void simpleTest() {
        log.info("Result {}", solution("011100")); // Should return 7
    }

    public int solution(String S) {
        int count = 0;

        while (S.contains("1")) {
            if (S.endsWith("1")) {
                S = binarySubtraction(S);
            } else {
                S = binaryDivision(S);
            }

            count++;
        }

        return count;
    }

    private String binaryDivision(String input) {
        return input.substring(0, input.length() - 1);
    }

    private String binarySubtraction(String input) {
        return input.replaceAll("1$", "0");
    }
}
