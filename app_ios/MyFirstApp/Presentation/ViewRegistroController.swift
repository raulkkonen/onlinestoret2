//
//  ViewRegistroController.swift
//  MyFirstApp
//
//  Created by Oscar on 9/09/22.
//

import UIKit

class ViewRegistroController: UIViewController {
    
    // MARK: - Registro UI
    @IBOutlet weak var userRegistroTextField: UITextField!
    @IBOutlet weak var passRegistroTextField: UITextField!
    @IBOutlet weak var confRegistroTextField: UITextField!
    @IBOutlet weak var emailRegistroTextField: UITextField!
    
    // ALERTAS DE ERROR
    let alertService = AlertService()
    
    // CONEXIÓN LOGIN
    let networkService = NetworkService()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        passRegistroTextField.isSecureTextEntry = true
        confRegistroTextField.isSecureTextEntry = true
}
    
    // BOTÓN REGISTRARSE
    @IBAction func registrarseButtonAction() {
    
        guard
        let username = userRegistroTextField.text,
        let password = passRegistroTextField.text,
        let confirmPassword = confRegistroTextField.text,
        let email = emailRegistroTextField.text
        else {return}

        jsonRequest(username: username, password: password, confirmPassword: confirmPassword, email: email)
        
    }
    
    // FUNCIÓN QUE DEVUELVE EL JSON
    func jsonRequest(username: String, password: String, confirmPassword: String, email: String){
        
        let registro = Registro(username: username, password: password, confirmPassword: confirmPassword, email: email)
        
        networkService.request(endpoint: "/security/users", loginObject: registro) { [weak self] (result) in
            
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
