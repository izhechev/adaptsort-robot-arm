import React from 'react';
import styles from '../../styles/infocard.module.css';

const InfoCard = ({ title, value }) => {
    return (
        <div className={styles.card}>
            <div className={styles.value}>{value}</div>
            <div className={styles.title}>{title}</div>
        </div>
    );
};

export default InfoCard;
