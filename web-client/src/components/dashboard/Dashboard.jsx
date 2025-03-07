import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import Header from "../common/Header";
import Button from "../common/Button";
import InfoCard from '../common/InfoCard';
import ChartDisplay from "../StatsChart/StatsChart";
import Table from "../HistoryTable/Table";
import styles from '../../styles/dashboard.module.css';

const Dashboard = () => {
    const ITEMS_URL = 'http://localhost:8080/api/v1/items';
    const EXECUTIONS_URL = 'http://localhost:8080/api/v1/executions';

    const [chartData, setChartData] = useState([]);
    const [tableData, setTableData] = useState([]);
    const [objectsSorted, setObjectsSorted] = useState(0);
    const [objectsNotSorted, setObjectsNotSorted] = useState(0);
    const [sortingAccuracy, setSortingAccuracy] = useState('0%');
    const [totalExecutions, setTotalExecutions] = useState(0);
    const [isInitialLoad, setIsInitialLoad] = useState(true);  // New state to track the initial load

    const fetchChartData = async () => {
        try {
            const response = await axios.get(ITEMS_URL);
            return response.data;
        } catch (error) {
            console.error('Failed to fetch chart data:', error);
            throw error;
        }
    };

    const fetchTableData = async () => {
        try {
            const response = await axios.get(EXECUTIONS_URL);
            return response.data;
        } catch (error) {
            console.error('Failed to fetch table data:', error);
            throw error;
        }
    };

    const checkForUpdates = (newChartData) => {
        let sorted = 0, notSorted = 0;
        newChartData.forEach(item => {
            if (item.name === "") {
                notSorted += 1;
            } else {
                sorted += 1;
            }
        });

        const newTotalExecutions = newChartData.reduce((acc, item) => acc + item.amount, 0);

        if (!isInitialLoad && newTotalExecutions > totalExecutions) {
            toast.success("Update successful!");
        } else if (!isInitialLoad && newTotalExecutions === totalExecutions) {
            toast.info("No new updates");
        }

        setTotalExecutions(newTotalExecutions);
        setObjectsSorted(sorted);
        setObjectsNotSorted(notSorted);
        setIsInitialLoad(false);  // Set to false after the first data fetch is processed
    };

    const handleRefreshClick = async () => {
        try {
            const [newChartData, newTableData] = await Promise.all([fetchChartData(), fetchTableData()]);
            setChartData(newChartData);
            setTableData(newTableData);
            checkForUpdates(newChartData, newTableData);
        } catch (error) {
            toast.error("Failed to refresh data!");
        }
    };

    useEffect(() => {
        handleRefreshClick();  // Initial data fetch
    }, []);

    useEffect(() => {
        const total = objectsSorted + objectsNotSorted;
        const accuracy = total > 0 ? `${(objectsSorted / total * 100).toFixed(2)}%` : '0%';
        setSortingAccuracy(accuracy);
    }, [objectsSorted, objectsNotSorted]);

    const columns = React.useMemo(() => [
        { Header: 'Start Time', accessor: 'startTime' },
        { Header: 'Item Name', accessor: 'itemName' },
        { Header: 'Robot', accessor: 'robot' },
        { Header: 'Passed', accessor: 'passed' },
        { Header: 'Failed', accessor: 'failed' }
    ], []);

    return (
        <div>
            <Header />
            <div className={styles.infoAndActions}>
                <div className={styles.infoSection}>
                    <InfoCard title="Total Executions" value={totalExecutions} />
                    <InfoCard title="Sorting Accuracy" value={sortingAccuracy} />
                    <InfoCard title="Unique Objects" value={objectsSorted} />
                    <InfoCard title="Objects Not Sorted" value={objectsNotSorted} />
                </div>
                <Button label="Refresh" onClick={handleRefreshClick} />
                <div className={styles.dashboardContent}>
                    <ChartDisplay data={chartData} />
                    <Table columns={columns} data={tableData} />
                </div>
            </div>
            <ToastContainer
                position="top-right"
                autoClose={3000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="dark"
                className="toast-container"
            />
        </div>
    );
};

export default Dashboard;