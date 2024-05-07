import React from 'react';
import styles from './WorkoutPlan.module.css';

const WorkoutPlan = () => {
  const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

  return (
    <div className={styles.workoutPlan}>
      <h2>Workout Plan</h2>
      {days.map(day => (
        <div key={day} className={styles.day}>
          <h3>{day}</h3>
          <ul>
            <li>30 minute cardio</li>
            <li>15 minute jumping jacks</li>
          </ul>
        </div>
      ))}
    </div>
  );
};

export default WorkoutPlan;

// Within your component
<div className={styles.workoutPlan}>
  {/* ... */}
</div>
