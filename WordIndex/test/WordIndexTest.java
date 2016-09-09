import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ WordIndexTest.AddTests.class, WordIndexTest.CountTests.class,
        WordIndexTest.ContainsTests.class, WordIndexTest.GetTests.class })
public class WordIndexTest {

    /** Prevents tests from running forever. */
    @Rule
    public TestRule globalTimeout = Timeout.seconds(30);

    /**
     * Test the {@link WordIndex#add(String, int) method.
     */
    public static class AddTests {

        private WordIndex index;

        @Before
        public void setup() {
            index = new WordIndex();
        }

        @Test
        public void testCleanAdd() {
            index.add("puffin", 42);

            String result = index.toString();
            boolean actual = result.contains("puffin");

            Assert.assertTrue(result, actual);
        }

        @Test
        public void testUniqueWords() {
            index.add("giraffe", 2);
            index.add("peacock", 7);

            String result = index.toString();
            boolean actual = result.contains("giraffe")
                    && result.contains("peacock");

            Assert.assertTrue(result, actual);
        }

        @Test
        public void testDuplicateWord() {
            index.add("panda", 9);
            index.add("panda", 22);

            String result = index.toString();
            boolean actual = result.contains("panda") && result.contains("9")
                    && result.contains("22");
            Assert.assertTrue(result, actual);
            
        }

        @Test
        public void testDuplicatePosition() {
            index.add("panda", 9);
            index.add("panda", 9);

            String result = index.toString();
            boolean actual = result.contains("panda") && result.contains("9");

            Assert.assertTrue(result, actual);
        }
    }

    /**
     * Tests the {@link WordIndex#count(String)} and {@link WordIndex#words()}
     * methods.
     */
    public static class CountTests {

        private WordIndex index;

        @Before
        public void setup() {
            index = new WordIndex();
        }

        @Test
        public void testCountEmpty() {
            String result = index.toString();
            System.out.println(index.toString());
            int actual = index.count("hello");
            int expected = 0;
            Assert.assertEquals(result, actual, expected);
        }

        @Test
        public void testCountDuplicates() {
            index.add("pie", 1);
            index.add("pie", 2);
            index.add("pie", 2);

            String result = index.toString();
            int actual = index.count("pie");
            int expected = 2;
            Assert.assertEquals(result, actual, expected);
        }

        @Test
        public void testWordsEmpty() {
            String result = index.toString();
            int actual = index.words();
            int expected = 0;
            Assert.assertEquals(result, actual, expected);
        }

        @Test
        public void testWordsNoDuplicates() {
            index.add("cupcake", 1);
            index.add("cheesecake", 9);
            index.add("cakewalk", 3);

            String result = index.toString();
            int actual = index.words();
            int expected = 3;
            Assert.assertEquals(result, actual, expected);
        }

        @Test
        public void testWordsDuplicates() {
            index.add("cupcake", 1);
            index.add("cupcake", 1);
            index.add("cheesecake", 9);
            index.add("cakewalk", 3);
            index.add("cakewalk", 7);

            String result = index.toString();
            int actual = index.words();
            int expected = 3;
            Assert.assertEquals(result, actual, expected);
        }
    }

    /**
     * Tests the {@link WordIndex#contains(String)} method.
     */
    public static class ContainsTests {

        private WordIndex index;

        @Before
        public void setup() {
            index = new WordIndex();
        }

        @Test
        public void testEmpty() {
            String result = index.toString();
            boolean actual = index.contains("hello");

            Assert.assertFalse(result, actual);
        }

        @Test
        public void testMissing() {
            index.add("hello", 7);

            String result = index.toString();
            boolean actual = index.contains("world");

            Assert.assertFalse(result, actual);
        }

        @Test
        public void testUnique() {
            index.add("hello", 7);
            index.add("world", 2);

            String result = index.toString();
            boolean actual = index.contains("world");

            Assert.assertTrue(result, actual);
        }

        @Test
        public void testDuplicates() {
            index.add("hello", 7);
            index.add("world", 11);
            index.add("world", 15);

            String result = index.toString();
            boolean actual = index.contains("world");

            Assert.assertTrue(result, actual);
        }
    }

    /**
     * Tests the {@link WordIndex#getWords()} and the
     * {@link WordIndex#getPositions(String)} methods.
     */
    public static class GetTests {

        private WordIndex index;

        @Before
        public void setup() {
            index = new WordIndex();

            index.add("ant", 2);
            index.add("ant", 11);
            index.add("ant", 9);
            index.add("ant", 2);

            index.add("bat", 2);
            index.add("cat", 19);
            index.add("rat", 82);
        }

        @Test
        public void testGetWords() {
            String result = index.toString();

            List<String> actual = index.getWords();
            List<String> expected = new ArrayList<>();
            Collections.addAll(expected,
                    new String[] { "ant", "bat", "cat", "rat" });

            Assert.assertEquals(result, actual, expected);
        }

        @Test
        public void testGetPositions() {
            String result = index.toString();

            List<Integer> actual = index.getPositions("ant");
            List<Integer> expected = new ArrayList<>();
            Collections.addAll(expected, new Integer[] { 2, 9, 11 });

            Assert.assertEquals(result, actual, expected);
        }
    }
}
