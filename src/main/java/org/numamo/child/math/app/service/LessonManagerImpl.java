package org.numamo.child.math.app.service;

import org.numamo.child.math.app.model.table.api.TableTaskAnswerModel;
import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserLessonDmo;
import org.numamo.child.math.app.service.api.LessonManager;
import org.numamo.child.math.app.service.api.StorageService;
import org.numamo.child.math.app.service.api.UserLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public final class LessonManagerImpl implements LessonManager {

    private final UserLessonService lessonService;
    private final StorageService storageService;
    private final TableTaskAnswerModel tableTaskAnswerModel;

    @Autowired
    public LessonManagerImpl(
            UserLessonService lessonService,
            StorageService storageService,
            TableTaskAnswerModel tableTaskAnswerModel
    ) {
        this.lessonService = lessonService;
        this.storageService = storageService;
        this.tableTaskAnswerModel = tableTaskAnswerModel;
    }

    @Override
    public void start() {

        final UserLessonDmo newLesson = lessonService.makeLesson();
        final UserAssignmentDmo newAssignment = newLesson.getIterator().next();

        storageService.setCurrent(newLesson);
        tableTaskAnswerModel.update(newAssignment.getUserTasks());
    }


}
