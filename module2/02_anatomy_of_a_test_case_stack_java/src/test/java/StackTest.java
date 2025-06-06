import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack<String> names;

    @BeforeEach
    void setUp() {
        names = new Stack<>();
    }

    @AfterEach
    void tearDown() {
        names = null;
    }

    @DisplayName("Testing push or adding items to the stack")
    @Test
    void testPush() {
        names.push("name1");
        names.push("name2");
        names.push("name3");

        assertEquals(3, names.size());
    }

    @DisplayName("Testing pop or removing items from the stack")
    @Test
    void popShouldBeAbleToRemoveItemFromStack() {
        names.push("item");

        String removedItem = names.pop();

        assertEquals("item", removedItem);
        assertEquals(0, names.size());
    }

    @DisplayName("Pop should remove last item added first")
    @Test
    void popShouldRemoveLastItemAddedFirst() {
        names.push("name1");
        names.push("name2");
        names.push("name3");

        String removedFirstItem = names.pop();
        String removedSecondItem = names.pop();

        assertEquals("name3", removedFirstItem, "'name3' should be the first item removed");
        assertEquals("name2", removedSecondItem, "'name2' should be the second item removed");
    }

    @DisplayName("Pop should throw an error when there are no items to remove")
    @Test
    void popShouldThrowErrorWhenThereAreNoItemsToRemove() {
        Exception exception = assertThrows(EmptyStackException.class
                , () -> names.pop());
        assertEquals("Stack is empty. There are no items no remove", exception.getMessage());
    }

    @DisplayName("Peek: Should be able to see last item added on the stack without removing it")
    @Test
    void peekShouldBeAbleToSeeLastItemAdded() {
        names.push("name1");
        names.push("name2");

        String peekedItem = names.peek();

        assertEquals("name2", peekedItem);
        assertEquals(2, names.size());
    }

    @DisplayName("Peek: Should throw error if there are no items in the stack")
    @Test
    void peekShouldThrowErrorIfTheStackIsEmpty() {
        Exception exception = assertThrows(EmptyStackException.class,
                () -> names.peek());
        assertEquals("Stack is empty. There are no items no peek", exception.getMessage());
    }
}