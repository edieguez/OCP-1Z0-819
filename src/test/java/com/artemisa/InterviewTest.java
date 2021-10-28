package com.artemisa;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class InterviewTest {
    @Test
    void getFactorialTest() {
        assertEquals(BigInteger.ONE, factorial(0));
        assertEquals(BigInteger.ONE, factorial(1));
        assertEquals(new BigInteger("720"), factorial(6));
        assertEquals(new BigInteger("3628800"), factorial(10));
        assertEquals(new BigInteger("2432902008176640000"), factorial(20));
        assertEquals(new BigInteger("265252859812191058636308480000000"), factorial(30));
    }

    private BigInteger factorial(int n) {
        BigInteger accumulator = BigInteger.ONE;

        for (int i = n; i > 0; i--) {
            accumulator = accumulator.multiply(new BigInteger(String.valueOf(i)));
        }

        return accumulator;
    }

    @Test
    void hasPairsClosedTest() {
        assertTrue(checkPairs("{[()]}"));
        assertFalse(checkPairs("[{]}"));
        assertFalse(checkPairs("([{"));
        assertFalse(checkPairs("]["));
        assertTrue(checkPairs("()()()()()()()()()()()()()"));
        assertTrue(checkPairs("([{{[[{{{{((((([[[[]]]])))))}}}}]]}}])"));
        assertFalse(checkPairs("([{{[[{{{{((((([[[[[]]])))))}}}}]]}}])"));
        assertFalse(checkPairs(""));
    }

    private boolean checkPairs(String input) {
        if (input.length() == 0) {
            return false;
        }

        char currentCharacter = input.charAt(0);

        if (!isOpeningCharacter(currentCharacter)) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        stack.push(currentCharacter);

        Map<Character, Character> closingCharacters = new HashMap<>();
        closingCharacters.put('{', '}');
        closingCharacters.put('[', ']');
        closingCharacters.put('(', ')');

        for (int i = 1; i < input.length(); i++) {
            currentCharacter = input.charAt(i);

            if (isOpeningCharacter(currentCharacter)) {
                stack.push(currentCharacter);
            } else if (closingCharacters.get(stack.peek()) == currentCharacter) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    private boolean isOpeningCharacter(char character) {
        return "{[(".contains(String.valueOf(character));
    }
}
