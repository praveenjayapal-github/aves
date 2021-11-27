//
//  Home.swift
//  MyFlat
//
//  Created by Manisankar Sittarthan on 23/11/21.
//

import SwiftUI

struct Home: View {
    var body: some View {
        
        ZStack{
            Image("bg")
                .resizable()
                .scaleEffect()
                .ignoresSafeArea()
            
            VStack{
                
                HStack {
                    Image("logo_mini")
                        .padding()
                        
                    Spacer()
                }//end of Stack
                
                HStack{
                    
                    Button {
                        
                    } label: {
                        VStack {
                            
                            Image("btn_residents")
                                .resizable()
                            .scaleEffect().scaledToFit()
                            
                            Text("Residents")
                                .foregroundColor(Color.white)
                            
                        }//end of Stack
                        
                    }
                    Button {
                        
                    } label: {
                        VStack {
                            Image("btn_expenses")
                                .resizable()
                                .scaleEffect().scaledToFit()
                        
                        
                            Text("Expenses")
                                .foregroundColor(Color.white)
                        }//end of Stack
                    }
                    Button {
                        
                    } label: {
                        VStack {
                            Image("btn_contacts")
                                .resizable()
                            .scaleEffect().scaledToFit()
                        
                        
                        Text("Contacts")
                            .foregroundColor(Color.white)
                        
                        }//end of HStack
                    
                    }

                }
                
                HStack{
                    Text("Notifications")
                        .padding()
                        .foregroundColor(Color.white)
                    
                    Spacer()
                }
                
                VStack {
                    List{
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                        Text("Notication1")
                    }
                }
                .cornerRadius(10)
                .padding()
                .opacity(0.7)
                
                Spacer()
            }
                
        }
    }
}

struct Home_Previews: PreviewProvider {
    static var previews: some View {
        Home()
.previewInterfaceOrientation(.portrait)
    }
}
