package org.catrobat.jira.timesheet.impl;

import com.atlassian.activeobjects.external.ActiveObjects;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

public class TodoServiceImpl implements TodoService {
    private final ActiveObjects ao;

    public TodoServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }

    @Override
    public Todo add(String description) {
        final Todo todo = ao.create(Todo.class);
        todo.setDescription(description);
        todo.setComplete(false);
        todo.save();
        return todo;
    }

    @Override
    public List<Todo> all() {
        return newArrayList(ao.find(Todo.class));
    }
}
