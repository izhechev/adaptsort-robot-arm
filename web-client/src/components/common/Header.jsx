import React from 'react';
import styles from '../../styles/header.module.css';
import logoImage from '../../assets/logo.png';

const Header = () => {
    return (
        <div className={styles.header}>
            <img src={logoImage} alt="Logo" className={styles.logo} />
            <h1 className={styles.heading}>AdaptSort GUI Dashboard</h1>
        </div>
    );
};
export default Header;