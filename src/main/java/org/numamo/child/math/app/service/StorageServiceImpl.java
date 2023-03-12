package org.numamo.child.math.app.service;

import org.numamo.child.math.app.model.task.UserLessonDmo;
import org.numamo.child.math.app.service.api.StorageService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.slf4j.LoggerFactory.getLogger;


@Service
public final class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = getLogger(StorageServiceImpl.class);

    private UserLessonDmo current;

    @Override
    public void saveLesson(UserLessonDmo userLesson) {
        LOGGER.info("Saved lesson {}", userLesson);
        if (Objects.equals(current, userLesson)) {
            current = null;
        }
    }

    @Override
    public Optional<UserLessonDmo> getCurrent() {
        return Optional.ofNullable(current);
    }

    @Override
    public StorageService setCurrent(final UserLessonDmo lesson) {
        if (isNull(this.current)) {
            this.current = lesson;
            LOGGER.debug("The new current lesson was set: {}", lesson);
        } else {
            throw new IllegalArgumentException(
                    "The new lesson was not added before saving previous: " + this.current + " new: " + lesson);
        }
        return this;
    }
}
