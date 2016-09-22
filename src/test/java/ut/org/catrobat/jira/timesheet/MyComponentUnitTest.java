package ut.org.catrobat.jira.timesheet;

import org.junit.Test;
import org.catrobat.jira.timesheet.api.MyPluginComponent;
import org.catrobat.jira.timesheet.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}