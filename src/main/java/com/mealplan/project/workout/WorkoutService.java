package com.mealplan.project.workout;

import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    private static final Logger log = LoggerFactory.getLogger(WorkoutService.class);

    public List<Workout> list() {
        log.info("Begun retrieving all workouts...");
        try {
            List<Workout> workouts = workoutRepository.findAll();
            log.info("Retrieved {} workouts successfully", workouts.size());
            return workouts;
        } catch (Exception e) {
            log.error("Error occurred while retrieving workouts: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Workout getWorkoutById(int id) {
        log.info("Begun retrieving workout with ID: {}", id);
        try {
            Workout workout = workoutRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Workout not found"));
            log.info("Retrieved workout with ID: {} successfully", id);
            return workout;
        } catch (NoSuchElementException e) {
            log.warn("No workout found with ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while retrieving workout with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public void deleteWorkout(Workout workout) {
        log.info("Begun deleting workout with ID: {}", workout.getId());
        try {
            workoutRepository.delete(workout);
            log.info("Deleted workout with ID: {}", workout.getId());
        } catch (Exception e) {
            log.error("Error occurred while deleting workout with ID {}: {}", workout.getId(), e.getMessage(), e);
            throw e;
        }
    }

    @SuppressWarnings("null")
    public Workout save(Workout workout) {
       log.info("Begun saving workout with id: {}", workout != null && workout.getId() != null ? workout.getId() : "null");
       try {
          Workout savedWorkout = workoutRepository.save(workout);
          log.info("Saving Workout with id: {} successfully!", savedWorkout.getId());
          return savedWorkout;
       } catch (Exception e) {
          log.error("Saving Workout with id: {} unsuccessful! Exception: {}", workout != null && workout.getId() != null ? workout.getId() : "null", e.getMessage(), e);
          return null;
        }
    }
}