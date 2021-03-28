package pl.lodz.p.zzpj.testing.junit.stack;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;

public class StackExerciseTest {

    StackExercise stack;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        stack = new StackExercise();
        stack.push("Daniel");
        stack.push("Julia");
        stack.push("Szymon");
        stack.push("Karolina");
    }

    @Test
    public void pop() throws StackEmptyException {
        assertEquals("Karolina", stack.pop());
        assertEquals("Szymon", stack.pop());
        assertEquals("Julia", stack.pop());
        assertEquals("Daniel", stack.pop());

    }

    @Test()
    public void popException() throws StackEmptyException {
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        exception.expect(StackEmptyException.class);
        stack.pop();
    }

    @Test
    public void push() throws StackEmptyException {
        assertNotEquals("jamnik", stack.top());
        stack.push("jamnik");
        assertEquals("jamnik", stack.top());
    }

    @Test
    public void top() throws StackEmptyException {
        assertEquals("Karolina", stack.top());
    }

    @Test
    public void topException() throws StackEmptyException {
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertThatThrownBy( () -> stack.top()).isInstanceOf(StackEmptyException.class);

        assertThatExceptionOfType(StackEmptyException.class).isThrownBy( () -> stack.top());
    }

    @Test
    public void isEmpty() throws StackEmptyException {
        assertFalse(stack.isEmpty());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}
