//
//  CountryView.swift
//  EUMembers
//
//  Created by Ej Sobrepeña on 21.4.2023.
//

import SwiftUI
struct CountryView:View{
    @State var country: Country
    
    var body: some View {
        VStack {
            Text("\(country.name)")
                .font(.largeTitle)
            
            Image(country.code.lowercased())
                .resizable()
                .scaledToFit()
                .border(Color.black, width: 1)
            
            CountryDetailsView(country: $country)
        }
        .padding()
        
    }
        
}

struct Countryview_Previews:PreviewProvider{
    static var previews: some View{
        CountryView(country: finland)
    }
}
