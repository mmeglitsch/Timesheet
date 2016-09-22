package org.catrobat.jira.timesheet.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.activeobjects.test.TestActiveObjects;
import net.java.ao.EntityManager;
import net.java.ao.test.junit.ActiveObjectsJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/* Here are a few things to note and understand:

    (1) we're using the ActiveObjectsJUnitRunner to run this test, this will help us access a correctly configured Active Objects instance for testing.
        It also wraps each tests in a transaction that is rolled-back after each of them. This will leave the test database in the same state for each test.
    (2) this is the EntityManager that will be automatically injected by the ActiveObjectsJUnitRunner.
        We will be able to use that entity manager to create an ActiveObjects instance to use with our service implementation.
    (3) the service under test,
    (4) here we're just checking that the EntityManager is not null to make sure we've configured our test correctly,
    (5) we instantiate the service with a specific TestActiveObjects instance.
*/

@RunWith(ActiveObjectsJUnitRunner.class)
public class TodoServiceImplTest {
    private EntityManager entityManager;

    private ActiveObjects ao; // (1)

    private TodoServiceImpl todoService;

    @Before
    public void setUp() throws Exception {
        assertNotNull(entityManager);
        ao = new TestActiveObjects(entityManager);
        todoService = new TodoServiceImpl(ao);
    }

    @Test
    public void testAdd() throws Exception {
        final String description = "This is a todo";

        ao.migrate(Todo.class); // (2)

        assertEquals(0, ao.find(Todo.class).length);

        final Todo add = todoService.add(description);
        assertFalse(add.getID() == 0);

        ao.flushAll(); // (3) clear all caches

        final Todo[] todos = ao.find(Todo.class);
        assertEquals(1, todos.length);
        assertEquals(description, todos[0].getDescription());
        assertEquals(false, todos[0].isComplete());
    }

    @Test
    public void testAll() throws Exception {
        ao.migrate(Todo.class); // (2)

        assertTrue(todoService.all().isEmpty());

        final Todo todo = ao.create(Todo.class);
        todo.setDescription("Some todo");
        todo.save();

        ao.flushAll(); // (3) clear all caches

        final List<Todo> all = todoService.all();
        assertEquals(1, all.size());
        assertEquals(todo.getID(), all.get(0).getID());
    }
}