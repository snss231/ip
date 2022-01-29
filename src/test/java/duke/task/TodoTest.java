package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_unmarked_noX() {
        Todo todo = new Todo("hi");
        assertEquals("[T][ ] hi", todo.toString());
    }

    @Test
    public void toString_marked_X() {
        Todo todo = new Todo("hello there!");
        todo.mark();
        assertEquals("[T][X] hello there!", todo.toString());
    }
}
