package com.mealplan.project.mealplan;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealPlanService {
    @Autowired
    private MealPlanRepository repo;

    public List<MealPlan> list() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public MealPlan getMealPlanById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public MealPlan save(MealPlan mealPlan) {
        repo.save(mealPlan);
        return mealPlan;
    }

    public void delete(MealPlan mealPlan) {
        repo.delete(mealPlan);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
