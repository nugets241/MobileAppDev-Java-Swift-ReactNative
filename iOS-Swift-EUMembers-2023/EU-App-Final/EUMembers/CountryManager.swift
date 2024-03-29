//
//  CountryManager.swift
//  EUMembers
//
//  Created by Ej Sobrepeña on 19.4.2023.
//

import Foundation

class CountryManager: ObservableObject{
    @Published   var countries : [Country]
    
    init() {
        self.countries = []
        self.loadCountriesWithURLSession()
    }
    let urlString = "https://wy3dsyi6k62vlns5tmm72zlbcu0vnxda.lambda-url.eu-north-1.on.aws/"
    
    private func loadCountriesWithURLSession(){
        if let url = URL(string: urlString){
            print("About to hit lambda function URL to get countries (Data)")
            
            let task = URLSession.shared.dataTask(with: url) { data, response, error in
                if let jsonData = data {
                    let decoder = JSONDecoder()
                    if let countries = try? decoder.decode([Country].self, from: jsonData){
                        print("Countries loaded from lambda function URL (URLSession)")
                        DispatchQueue.main.async {
                            self.countries = []
                        }
                        for country in countries {
                            DispatchQueue.main.async {
                                self.countries.append(country)
                            }
                            Thread.sleep(forTimeInterval: 0.2)
                        }
                    }
                    else{
                        print("Error in parsing JSON data")
                    }
                }
                else if let error = error {
                    print("Unable to retrieve JSON data, error: \(error)")
                }
            }
            
            task.resume()
            
        }
        else{
            print("BAD URL")
        }
    }
}
