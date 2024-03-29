# iOS-Swift-EUMembers-2023

This is an iOS application developed in Swift 5.0 that provides information about the member countries of the European Union.

## Features

- List of EU member countries
- Detailed view of each country
- Indicators for Eurozone and Schengen membership

## Getting Started

### Prerequisites

- macOS
- Xcode 14.1 or later
- Swift 5.0 or later

### Building

1. Clone the repository
2. Open the `EUMembers.xcodeproj` file in Xcode
3. Select the desired target (EUMembers)
4. Build and run the project

## Application Structure

The application is structured into several Swift files:

- [`EUMembersApp.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/EUMembersApp.swift): The main entry point of the application.
- [`Country.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/Country.swift): Defines the `Country` model.
- [`CountryManager.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/CountryManager.swift): Manages the list of countries.
- [`CountryDetailsView.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/CountryDetailsView.swift): Displays the details of a selected country.
- [`MembershipView.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/MembershipView.swift): Displays the membership status of a country.
- [`ContentView.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/ContentView.swift): The main content view of the application.
- [`CountryView.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/CountryView.swift): Displays a single country in the list.
- [`ListView.swift`](iOS-EUMembers-2023/EU-App-Final/EUMembers/ListView.swift): Displays the list of countries.
