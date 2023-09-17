package se.su.dsv.grupp03.utilities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringFormatterTest {

    private static StringFormatter fc;

    @BeforeAll
    static void init(){
        fc = new StringFormatter();
    }

    @Test
    void doesNormalizeWhitespace(){
        assertEquals("hello world", fc.normalizeWhitespace("  hello   world  "));
    }

    @Test
    void doesRemoveWhitespace(){
        assertEquals("helloworld", fc.removeWhitespace("  he  ll  o   world  "));
    }

}