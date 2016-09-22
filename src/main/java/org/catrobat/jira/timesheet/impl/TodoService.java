package org.catrobat.jira.timesheet.impl;

import com.atlassian.activeobjects.tx.Transactional;

import java.util.List;

@Transactional
public interface TodoService {
    Todo add(String description);

    List<Todo> all();
}
