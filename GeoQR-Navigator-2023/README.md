# GeoQR Navigator

GeoQR Navigator is an Android application that allows users to scan QR codes containing geolocation data (in the form of `geo:` URIs), and calculate the distance from their current location to the location encoded in the QR code.

## Features

- QR Code Scanning: The application uses the ZXing library to scan QR codes.
- Geolocation: The application uses the Google Play Services Location API to get the user's current location.
- Distance Calculation: The application calculates the distance from the user's current location to the location encoded in the QR code.

## Permissions

The application requires the following permissions:

- Camera: To scan QR codes.
- Location: To get the user's current location.

## Building the Project

This is a Gradle-based project. You can build it using the `gradlew build` command or by importing it into Android Studio and building it there.

## Running the Project

To run the project, you can use the `gradlew run` command or use the Run feature of Android Studio.
