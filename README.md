# No Phone

## Author
Jonathan Ortiz Guzman

## Description
An Android app designed to discourage drivers from using their phone while driving. 
The app uses a Google’s Activity Recognition API to estimate when the phone is moving in a vechile. The API analyzes patterns using phone sensors and notifies if the phone is in a 'IN_VEHICLE' state.
When vehicle motion is detected, the app will also check if the phone screen is unlocked and being used. With both of these checks are true, then the app displays a warning screen to the user that there is potential distracted driving.
The app is intended to reduce unnecessary activities such as messaging, social media, web browsing, and entertainment while preserving access to important driving-related and emergency functions such as emergency calls, navigation, hands-free calling, etc.

### Features
- Detects when the phone is likely in a vehicle moving
- Detects when the phone is unlocked and interacted with
- A visual screen that dislpays a message to remind the driver that they may be on the road
- An override method to where a a passenger can unlock the app for situations when the phone owner or someone else is not driving (a loose method)

### Limitations
- The app cannot distingush whether the user is the driver or passenger. The passenger override is therefore needed and only applied when vehicle motion and screen unlock is true as this can be triggered multiple times in a trip.