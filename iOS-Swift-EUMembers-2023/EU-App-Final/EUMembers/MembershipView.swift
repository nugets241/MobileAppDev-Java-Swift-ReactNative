//
//  MembershipView.swift
//  EUMembers
//
//  Created by Ej Sobrepeña on 14.4.2023.
//

import SwiftUI

struct MembershipView: View {
    @Binding var country: Country
    
    
    var body: some View {
        HStack{
            let badgeSize: CGFloat = 90
            
            if country.isMember{
                Image("eu")
                    .resizable()
                    .frame(width: badgeSize, height: badgeSize)
            }
            
            if country.isSchengen{
                Image("sch")
                    .resizable()
                    .frame(width: badgeSize, height: badgeSize)
            }
            
            let customColor = Color(Color.RGBColorSpace.sRGB, red: 38/255, green: 50/255, blue: 133/255, opacity: 1)
            if country.isEuroZone{
                Image(systemName: "eurosign.circle.fill")
                    .font(.system(size: badgeSize-14))
                    .foregroundColor(customColor)
                

            }
        }
    }
}

struct MembershipView_Preview:PreviewProvider{
    static var previews: some View{
        MembershipView(country: .constant(finland))
    }
}
