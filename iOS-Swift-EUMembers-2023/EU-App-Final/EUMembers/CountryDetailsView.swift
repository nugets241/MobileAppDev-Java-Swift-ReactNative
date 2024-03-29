//
//  CountryDetailsView.swift
//  EUMembers
//
//  Created by Ej Sobrepeña on 14.4.2023.
//

import SwiftUI


struct CountryDetailsView: View {
    @Binding var country: Country
    
    var body: some View {
        VStack {
            Text("Population: \(country.population)")
            Text("Area: \(country.area) km\u{B2}")
            Text("Population density: \(country.populationDensity) persons per km\u{B2}")
            
            MembershipView(country: $country)
            Text("EU Member: \(country.isMember ? "Yes" : "No")")
            Text("Schengen Area: \(country.isSchengen ? "Yes" : "No")")
            Text("EuroZone: \(country.isEuroZone ? "Yes" : "No")\n")
            
            let url = "https://en.m.wikipedia.org/wiki/" + country.name.replacingOccurrences(of: " ", with: "_")
            
            Link("\(Image(systemName: "link")) Learn more", destination: URL(string: url)!)

        }
    }
}

struct CountryDetailsView_Preview: PreviewProvider {
    static var previews: some View {
        CountryDetailsView(country: .constant(finland))
    }
}


