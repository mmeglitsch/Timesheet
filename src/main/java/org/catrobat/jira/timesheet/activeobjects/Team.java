/*
 * Copyright 2016 Adrian Schnedlitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.catrobat.jira.timesheet.activeobjects;

import net.java.ao.Entity;
import net.java.ao.ManyToMany;
import net.java.ao.OneToMany;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.Unique;

public interface Team extends Entity {

    @Unique
    @NotNull
    String getTeamName();
    void setTeamName(String name);

    Config getConfiguration();
    void setConfiguration(Config configuration);

    @ManyToMany(value = TeamToGroup.class, reverse = "getTeam", through = "getGroup")
    Group[] getGroups();

    @ManyToMany(value = CategoryToTeam.class, reverse = "getTeam", through = "getCategory")
    Category[] getCategories();

    @OneToMany(reverse = "getTeam")
    TimesheetEntry[] getEntries();
}
