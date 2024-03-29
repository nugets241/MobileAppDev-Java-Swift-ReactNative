//
//  ListView.swift
//  EUMembers
//
//  Created by Ej Sobrepeña on 10.5.2023.
//

import SwiftUI

struct ListView: View {
    @State var country: Country
    
    var body: some View {
        HStack{            
            Image(country.code.lowercased())
                .resizable()
                .frame(width: 32, height: 21.3)
                .border(.black)
            Text(country.name)
                .font(.system(size:20.0))
            Spacer()            
            if country.isSchengen{
                Image("sch")
                    .resizable()
                    .frame(width: 35, height: 35)
            }
            if country.isEuroZone{
                let customColor = Color(Color.RGBColorSpace.sRGB, red: 38/255, green: 50/255, blue: 133/255, opacity: 1)
                Image(systemName: "eurosign.circle.fill")
                    .font(.system(size: 30))
                    .foregroundColor(customColor)                
            }
        }
    }
}

struct ListView_Preview:PreviewProvider{
    static var previews: some View{
        ListView(country: finland)
    }
}
