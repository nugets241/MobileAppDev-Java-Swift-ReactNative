//
//  EUMembersApp.swift
//  EUMembers
//
//  Created by Ej Sobrepeña on 14.4.2023.
//

import SwiftUI


let finland = Country(
    code: "FI",
    name: "Finland",
    area: 338_435,
    population: 5_555_300,
    isMember: true,
    isEuroZone: true,
    isSchengen: true)

@main
struct EUMembersApp: App {
    
    @StateObject private var manager = CountryManager()
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(manager)
        }
    }
}

