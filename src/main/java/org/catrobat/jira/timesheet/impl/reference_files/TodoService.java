package org.catrobat.jira.timesheet.impl.reference_files;

import com.atlassian.activeobjects.tx.Transactional;

import java.util.List;

@Transactional
public interface TodoService {
    Todo add(String description);

    List<Todo> all();
}
