// Sidebar.js
import React from 'react';
import styles from './Sidebar.module.css';

const Sidebar = ({ visible }) => {
  return (
    <div className={visible ? styles.sidebar : `${styles.sidebar} ${styles.sidebarHidden}`}>
            <div className={styles.branding}>

              <h2>MyFitness DePaul</h2>
            </div>
      <a href="#overview">Overview</a>
      <a href="#myWorkout">My Workout</a>
      <a href="#myMealplan">My Mealplan</a>
    
      <div className={styles.sidebarFooter}>
        <a href="#help">Help</a>
        <a href="#contact">Contact us</a>
      </div>
    </div>
  );
};

export default Sidebar;

// Within your component
<div className={styles.sidebar}>
  {/* ... */}
</div>



