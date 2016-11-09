package com.mob.mubai;

import com.mob.mubai.data.model.Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mubai on 2016/11/3.
 */

public class CalculatorUnitTest {

    Calculator calculator;
    @Before
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
         Assert.assertEquals(3, calculator.sum(1,2));
    }

    @Test
    public void testSubstract()throws Exception {
        Assert.assertEquals(1,calculator.substract(3,2));
    }

    @Test
    public void TestMultiply() throws Exception {
        Assert.assertEquals(10,calculator.multiply(2,5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestDivide() throws Exception {
        Assert.assertEquals(0,calculator.divide(6,0));
    }
}
