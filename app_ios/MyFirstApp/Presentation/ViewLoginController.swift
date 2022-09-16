//
//  ViewController.swift
//  MyFirstApp
//
//  Created by Oscar on 5/08/22.
//

import UIKit

class ViewLoginController: UIViewController {
    
    // MARK: - Login UI
    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    // ALERTAS DE ERROR
    let alertService = AlertService()
    
    // CONEXIÓN LOGIN
    let networkService = NetworkService()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        passwordTextField.isSecureTextEntry = true
}
    
    // BOTON LOGIN
    @IBAction func loginButtonAction() {
               
        guard
        let username = usernameTextField.text,
        let password = passwordTextField.text
        else {return}
        
        jsonRequest(username: username, password: password)

    }
    
    // BOTON REGISTRARME
    @IBAction func registrarmeUIButton() {
    }
    
    // FUNCIÓN QUE DEVUELVE EL JSON
    func jsonRequest(username: String, password: String){
        
        let login = Login(username: username, password: password)
        
        networkService.request(endpoint: "/security/login", loginObject: login) { [weak self] (result) in
            
            switch result {
            
            case .success(_):
                guard let alert01 = self?.alertService.alertSuccess() else {
                    return }
                self?.present(alert01, animated: true)
                
            case .failure(_):
                guard let alert02 = self?.alertService.alertFailure() else {
                    return }
                self?.present(alert02, animated: true)
            }
        }
    }
}
