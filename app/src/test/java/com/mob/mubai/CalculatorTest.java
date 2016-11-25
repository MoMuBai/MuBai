package com.mob.mubai;

import com.mob.mubai.data.model.Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lzw on 2016/11/25.
 */

public class CalculatorTest {

        public static void main(String[] args) {
                Calculator calculator = new Calculator();
                int sum = calculator.sum(1, 2);
                if (sum == 3) {
                        System.out.println("add() works!");
                } else {
                        System.out.println("add() does not works!");
                }

                int product = calculator.multiply(2, 4);
                if (product == 8) {
                        System.out.println("multiply() works!");
                } else {
                        System.out.println("multiply() does not works!");
                }
        }
}
