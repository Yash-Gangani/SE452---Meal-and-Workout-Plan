import React from 'react';
import styles from './Header.module.css';
import profilePic from './profile.jpg';
const Header = () => {
  return (
    <header className={styles.header}>
      <div>
        <h1>Welcome, User</h1>
        <p>Here is your Meal and Workout plan, let's get it!</p>
      </div>
      <div className={styles.profile}>
            <img src={profilePic} alt="User profile" />
        </div>
    </header>
  );
};

export default Header;