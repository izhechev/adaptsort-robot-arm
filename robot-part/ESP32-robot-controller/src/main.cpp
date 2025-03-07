/*
 * @brief Main gateway for motor control using an ESP32
 * @author Wouter Swinkels
 * @date 2024-05-02
 */

#include <Arduino.h>
#include <string>
#include <sstream>
#include "main.hpp"

/// @brief Constructor function for command string to write to a given serial output
/// @param channel Servo motor to write to
/// @param pulseWidth Pulse width to write to servo control
/// @return Motor steering command string with formatted entered parameters
std::string getServoMotorSteerCommand(int channel, int pulseWidth) {
    if (channel < CHANNEL_MIN || channel > CHANNEL_MAX || pulseWidth < PW_MIN || pulseWidth > PW_MAX) {
        return ""; // @TODO implement some runtime exception handling here
    }
    
    std::stringstream command;
	
    // Construct the command string from basic command string:
    command << "#" << channel << " P" << pulseWidth << " \r";
    return command.str();
}

/// @brief Constructor function for command string to retrieve the software version
/// @return Software version request command
std::string getVersionRetrievalCommand() {
    std::stringstream command;
    command << "VER" << " \r";
    return command.str();
}

void versionTest() {
    Serial.println("Version test...");
    delay(2000);

    Serial.println("Starting write test");  
    if (Serial2.availableForWrite()) {
        Serial2.write(getVersionRetrievalCommand().c_str());
    }
    Serial2.flush(); // Send entire buffer immediately
    delay(100);      // Give some time for a response
    
    // Checking if there is data available to read from Serial2
    if (Serial2.available()) {
        Serial.println("Reading from Serial2:");

        while (Serial2.available()) {
            char incomingByte = Serial2.read();
            Serial.print(incomingByte);
        }
        Serial.println(); // Print a newline for better readability
    }
    Serial.println("\ntest done");
}

void readIncomingMotorCommands() {

    if (Serial.available() <= 0) return;

    // Buffers to store the incoming command
    char commandBuffer[32]; 
    int motorNumber, pulseWidth;
    int bytesRead = 0;

    bytesRead = Serial.readBytesUntil('\n', commandBuffer, sizeof(commandBuffer) - 1);
    commandBuffer[bytesRead] = '\0'; // Null terminate the string for sscanf()

    // Attempt to parse motor number and pulse width:
    if (sscanf(commandBuffer, "%d %d", &motorNumber, &pulseWidth) != 2) {
        Serial.println("Failed to parse motor steer command.");
        return;
    }

    String command = getServoMotorSteerCommand(motorNumber, pulseWidth);
    if (command.isEmpty()) { // @todo add exception catching here in the future
        Serial.println("Invalid motor number or pulse width.");
        return;
    }
    Serial2.write(command.c_str()); // Send command
    Serial2.flush();                // Ensure the command is sent immediately
}

void setup() {
    Serial2.begin(UART2_BAUDRATE, SERIAL_8N1, UART2_RX, UART2_TX);
    Serial.begin(LOGGING_UART_BAUDRATE);

    versionTest(); // send test to ensure proper robot UART wiring
}

void loop() {
    readIncomingMotorCommands();
}