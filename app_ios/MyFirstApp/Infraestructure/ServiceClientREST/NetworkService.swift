//
//  Network.swift
//  MyFirstApp
//
//  Created by Oscar on 5/09/22.
//

import Foundation
import Alamofire
import SwiftUI

enum MyResult<T, E: Error> {
    
    case success(T)
    case failure(E)
}

class NetworkService {
    
    let baseURL = "http://localhost:8095"
    
    func handleResponse(for request: URLRequest, completion: @escaping (Result<Login, Error>) -> Void) {
        
        let session = URLSession.shared

        let task = session.dataTask(with: request) { (data, response, error) in
            
            DispatchQueue.main.async {
  
                guard let unwrappedResponse = response as? HTTPURLResponse else {
                    completion(.failure(NetworkError.badResponse))
                    return
                }

                switch unwrappedResponse.statusCode {
                    
                case 200 ..< 300:
                    print("SUCCESS")
                    print(unwrappedResponse.statusCode)
                    
                default:
                    print("FAILURE")
                }
                
                if let unwrappedError = error {
                    completion(.failure(unwrappedError))
                    return
                }
                
                if let unwrappedData = data {
                    
                    do {
                        let json = try JSONSerialization.jsonObject(with: unwrappedData, options: [])
                        print(json)
                        
                        if let user = try? JSONDecoder().decode(Login.self, from: unwrappedData) {
                            completion(.success(user))
                        }
                    } catch {
                        completion(.failure(error))
                    }
                }
            }
        }
        task.resume()
    }
    
    func handleResponse(for request: URLRequest, completion: @escaping (Result<Registro, Error>) -> Void) {
        
        let session = URLSession.shared
        
        let task = session.dataTask(with: request) { (data, response, error) in
            
            DispatchQueue.main.async {
                
                guard let unwrappedResponse = response as? HTTPURLResponse else {
                    completion(.failure(NetworkError.badResponse))
                    return
                }

                switch unwrappedResponse.statusCode {
                    
                case 200 ..< 300:
                    print("SUCCESS")
                    print(unwrappedResponse.statusCode)
                    
                default:
                    print("FAILURE")
                }
                
                if let unwrappedError = error {
                    completion(.failure(unwrappedError))
                    return
                }
                
                if let unwrappedData = data {
                    
                    do {
                        let json = try JSONSerialization.jsonObject(with: unwrappedData, options: [])
                        print(json)
                        
                        if let user = try? JSONDecoder().decode(Registro.self, from: unwrappedData) {
                            completion(.success(user))
                        }
                    } catch {
                        completion(.failure(error))
                    }
                }
            }
        }
        task.resume()
    }
    
    func request(endpoint: String, parameters: [String: Any], completion: @escaping (Result<Login, Error>) -> Void) {
            
        guard let url = URL(string: baseURL + endpoint) else {
            completion(.failure(NetworkError.badURL))
            return
        }
        
        var request = URLRequest(url: url)
        var components = URLComponents()
        var queryItems = [URLQueryItem]()
        
        for (key, value) in parameters {
            let queryItem = URLQueryItem(name: key, value: String(describing: value))
            queryItems.append(queryItem)
        }
        
        components.queryItems = queryItems
        
        let queryItemData = components.query?.data(using: .utf8)
        
        request.httpBody = queryItemData
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        handleResponse(for: request, completion: completion)
    }
    
    func request(endpoint: String, parameters: [String: Any], completion: @escaping (Result<Registro, Error>) -> Void) {
            
        guard let url = URL(string: baseURL + endpoint) else {
            completion(.failure(NetworkError.badURL))
            return
        }
        
        var request = URLRequest(url: url)
        var components = URLComponents()
        var queryItems = [URLQueryItem]()
       
        for (key, value) in parameters {
            let queryItem = URLQueryItem(name: key, value: String(describing: value))
            queryItems.append(queryItem)
        }
        
        components.queryItems = queryItems
        
        let queryItemData = components.query?.data(using: .utf8)
        
        request.httpBody = queryItemData
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        handleResponse(for: request, completion: completion)
    }

    func request(endpoint: String, loginObject: Login, completion: @escaping (Result<Login, Error>) -> Void) {
            
        guard let url = URL(string: baseURL + endpoint) else {
            completion(.failure(NetworkError.badURL))
            return
        }
        var request = URLRequest(url: url)
        
        do {
            let loginData = try JSONEncoder().encode(loginObject)
            request.httpBody = loginData
        } catch {
            completion(.failure(NetworkError.badEncoding))
        }
        
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        handleResponse(for: request, completion: completion)
    }
    
    func request(endpoint: String, loginObject: Registro, completion: @escaping (Result<Registro, Error>) -> Void) {
            
        guard let url = URL(string: baseURL + endpoint) else {
            completion(.failure(NetworkError.badURL))
            return
        }
        var request = URLRequest(url: url)
        
        do {
            let registroData = try JSONEncoder().encode(loginObject)
            request.httpBody = registroData
        } catch {
            completion(.failure(NetworkError.badEncoding))
        }
        
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        handleResponse(for: request, completion: completion)
    }
}

enum NetworkError: Error {
    case badURL
    case badResponse
    case badEncoding
}

