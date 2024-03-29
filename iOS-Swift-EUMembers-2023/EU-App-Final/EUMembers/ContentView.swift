//
//  ContentView.swift
//  EUMembers
//
//  Created by Ej Sobrepeña on 14.4.2023.
//

import SwiftUI

struct ContentView: View {
    @EnvironmentObject var manager: CountryManager
    
    var body: some View {
        
        NavigationStack{
            List(){
                Section(header: Text ("Current Members")){
                    ForEach(manager.countries){ country in
                        if(country.isMember){
                            NavigationLink{
                                CountryView(country: country)
                            }label: {
                                ListView(country: country)
                            }
                        }
                    }
                }
                Section(header: Text ("Past Members")){
                    ForEach(manager.countries){ country in
                        if(!country.isMember){
                            NavigationLink{
                                CountryView(country: country)
                            }label: {
                                ListView(country: country)
                            }
                        }
                    }
                }
            }
            .navigationTitle("European Union")
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(CountryManager())
    }
}

