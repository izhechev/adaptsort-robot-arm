/*
 * @brief Main defenitions for motor control ESP
 * @author Wouter Swinkels
 * @date 2024-04-18
 */
#pragma once

#define LOGGING_UART_BAUDRATE 115200

#define UART2_RX 16
#define UART2_TX 17
#define UART2_BAUDRATE 2400 // Adjust per settings of the controller on the other end.

#define CHANNEL_MIN 0
#define CHANNEL_MAX 31
#define PW_MIN 500
#define PW_MAX 2500