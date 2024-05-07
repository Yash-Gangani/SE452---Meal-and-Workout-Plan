import React from 'react';
import styles from './MacrosCalculator.module.css';

const MacrosCalculator = () => {
  return (
    <div className={styles.Header}>
      <h2>Macros Calculator</h2>
      
      <div className={styles.macrosCalculator}>
        {/* Note how we use styles.tableRow and styles.tableCell */}
        <div className={styles.tableRow}>
          <div className={styles.tableCell}>Protein</div>
          <div className={styles.tableCell}>150g</div>
        </div>
        <div className={styles.tableRow}>
          <div className={styles.tableCell}>Carbs</div>
          <div className={styles.tableCell}>200g</div>
        </div>
        <div className={styles.tableRow}>
          <div className={styles.tableCell}>Fats</div>
          <div className={styles.tableCell}>50g</div>
        </div>
        <div className={styles.tableRow}>
          <div className={styles.tableCell}>Fiber</div>
          <div className={styles.tableCell}>10g</div>
        </div>
      </div>
    </div>
  );
};

export default MacrosCalculator;
