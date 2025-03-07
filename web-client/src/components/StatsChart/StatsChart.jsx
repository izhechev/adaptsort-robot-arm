import React from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import styles from '../../styles/statschart.module.css';

const ChartDisplay = ({ data }) => {
    // Find the maximum value in the data to determine the domain of Y-axis
    const maxValue = Math.max(...data.map(item => item.amount));

    // Determine the domain of Y-axis from 0 to the next integer above the maximum value
    const yDomain = [0, Math.ceil(maxValue)];

    return (
        <div className={styles.chartContainer}> {/* Assuming you have styling for the container */}
            <h2 className={styles.heading}>OBJECT STATISTICS</h2>
            <ResponsiveContainer width="123%" height={400}>
                <BarChart data={data}>
                    <CartesianGrid/>
                    <XAxis dataKey="name" stroke="#cccccc"/>
                    <YAxis stroke="#CCCCCC" domain={yDomain} tickCount={Math.ceil(maxValue) + 1} interval={0}/>
                    <Tooltip/>
                    <Bar dataKey="amount" fill="#799fff"/>
                </BarChart>
            </ResponsiveContainer>
        </div>
);
};

export default ChartDisplay;
