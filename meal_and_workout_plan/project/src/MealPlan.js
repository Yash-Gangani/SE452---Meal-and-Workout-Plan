import React from 'react';
import styles from './MealPlan.module.css';

const MealPlan = () => {
  const meals = ['chicken', 'rice', 'tofu', 'apple', 'carrot'];

  return (
    <div className={styles.mealPlan}>
      <h2>Meal Plan</h2>
      <ul>
        {meals.map(meal => <li key={meal}>{meal}</li>)}
      </ul>
    </div>
  );
};

export default MealPlan;

<div className={styles.mealPlan}>
  {/* ... */}
</div>
