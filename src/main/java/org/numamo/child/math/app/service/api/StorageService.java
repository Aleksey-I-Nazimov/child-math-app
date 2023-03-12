package org.numamo.child.math.app.service.api;

import org.numamo.child.math.app.model.task.UserLessonDmo;

import java.util.Optional;


public interface StorageService {


    void saveLesson(UserLessonDmo userLesson);


    Optional<UserLessonDmo> getCurrent();

    StorageService setCurrent(UserLessonDmo lesson);


}
