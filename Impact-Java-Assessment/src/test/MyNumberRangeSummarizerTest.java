/**
 * @author Ryan Josias
 **/

package test;

import numberrangesummarizer.MyNumberRangeSummarizer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyNumberRangeSummarizerTest {

    @Test
    void testCollectionSorting() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        List<Integer> expectedResult = Arrays.asList(5, 10, 21, 22, 23, 60, 71, 72, 73);
        assertEquals(expectedResult, summarizer.collect("10,22,21,23,5,60,73,71,72"));
    }

    @Test
    void testCollectionWithSingleNumber() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        List<Integer> expectedResult = Arrays.asList(9);
        assertEquals(expectedResult, summarizer.collect("9"));
    }

    @Test
    void testSummaryWithSingleNumber() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        String expectedResult = "9";
        List<Integer> input = Arrays.asList(9);
        assertEquals(expectedResult, summarizer.summarizeCollection(input));
    }

    @Test
    void testCollectionWithNoInput() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect("");
        });
    }

    @Test
    void testCollectionWithInvalidCharacters() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect("1,2,3,4,a,b,c");
        });
    }

    @Test
    void testCollectionWithInvalidInput() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect("qwertyuiop[];'.,");
        });
    }

    @Test
    void testSummaryMultipleGroupingsOfNumbers() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        String expectedResult = "1-3, 5-7, 10-12, 15, 21-24";
        List<Integer> input = Arrays.asList(1, 2, 3, 5, 6, 7, 10, 11, 12, 15, 21, 22, 23, 24);
        assertEquals(expectedResult, summarizer.summarizeCollection(input));
    }

    @Test
    void testSummaryTwoConsecutiveNumberGroupings() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        String expectedResult = "1-2, 4-5, 7, 10-11, 14-15, 20, 22-23, 29";
        List<Integer> input = Arrays.asList(1, 2, 4, 5, 7, 10, 11, 14, 15, 20, 22, 23, 29);
        assertEquals(expectedResult, summarizer.summarizeCollection(input));
    }


    @Test
    void testCollectionWithNegativeNumbers() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        List<Integer> expectedResult = Arrays.asList(-3,-2,-1,1,2,3);
        assertEquals(expectedResult, summarizer.collect("-3,-2,-1,1,2,3"));
    }

    @Test
    void testSummaryWithNegativeNumbers() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        String expectedResult = "-3--1, 1-3";
        List<Integer> input = Arrays.asList(-3, -2, -1, 1, 2, 3);
        assertEquals(expectedResult, summarizer.summarizeCollection(input));
    }

    @Test
    void testCollectionWithAdditionalWhitespace() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        List<Integer> expectedResult = Arrays.asList(-3,-2,-1,1,2,3);
        assertEquals(expectedResult, summarizer.collect("-3,-2,-1 ,1,2, 3"));
    }

    @Test
    void testCollectionWithDuplicateNumbers() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        List<Integer> expectedResult = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5);
        assertEquals(expectedResult, summarizer.collect("1,2,2,3,3,3,4,5"));
    }

    @Test
    void testSummaryWithDuplicateNumbers() {
        MyNumberRangeSummarizer summarizer = new MyNumberRangeSummarizer();
        String expectedResult = "1-5";
        List<Integer> input = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5);
        assertEquals(expectedResult, summarizer.summarizeCollection(input));

    }

}