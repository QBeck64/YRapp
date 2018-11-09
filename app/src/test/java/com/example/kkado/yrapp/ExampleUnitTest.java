package com.example.kkado.yrapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    public void testAddressFormat(Address address) {
        assertEquals(address.getZipCode().length(), 5);
        assertEquals(address.getProvince().length(), 2);
    }

    public void testEmailFormat(Email email) {
        assertTrue(email.getAddress().contains("@"));
    }

    public void testCellPhoneFormat(Phone phone) {
        assertEquals(phone.getNumber().length(), 10);
    }
}