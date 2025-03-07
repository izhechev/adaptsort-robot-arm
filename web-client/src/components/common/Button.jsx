import React from 'react';
import styles from '../../styles/button.module.css';

const Button = ({ onClick }) => {
    return (
        <div className={styles.refreshButtonContainer}>
            <button
                className={styles.refreshButton}
                onClick={(e) => {
                    onClick();
                    e.currentTarget.blur(); // This removes focus from the button after click
                }}
            >
                <span className={"material-icons " + styles.refreshButtonIcon}>refresh</span>
                Refresh
            </button>
        </div>
    );
};

export default Button;