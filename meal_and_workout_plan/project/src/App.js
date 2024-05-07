import React, { useState } from 'react';
import Sidebar from './Sidebar';
import WorkoutPlan from './WorkoutPlan';
import MealPlan from './MealPlan';
import MacrosCalculator from './MacrosCalculator';
import Header from './Header';
import './App.css';
import './index.css';
import './Sidebar.module.css';

function App() {
  const [sidebarVisible, setSidebarVisible] = useState(false);

  const toggleSidebar = () => {
    setSidebarVisible(!sidebarVisible);
  };
  return (
    <div className="app">

      <button onClick={toggleSidebar} className="toggle-button">
      ☰
      </button>
      <div className={sidebarVisible ? "sidebar-visible" : "sidebar-hidden"}>
        <Sidebar />
      </div>
      <div className={`main-content ${sidebarVisible ? 'with-sidebar' : ''}`}>
      <Header />
      <div className="plans-container">
        <div className="workoutPlan-container">
          <WorkoutPlan />
        </div>
        <div className="mealPlan-macros-container">
          <MealPlan />
          <MacrosCalculator />
        </div>
      </div>
    </div>
  </div>
  );
}


export default App;

// index.css
/* Global styles will be added here. */
