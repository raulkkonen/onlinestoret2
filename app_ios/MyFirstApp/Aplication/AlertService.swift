//
//  AlertService.swift
//  MyFirstApp
//
//  Created by Oscar on 5/09/22.
//

import Foundation
import UIKit

class AlertService {
    
    func alertSuccess() -> UIAlertController {
    
        let alert01 = UIAlertController(title: "Bienvenido", message: "Acceso correcto", preferredStyle: .alert)
        let action = UIAlertAction(title: "Aceptar", style: .cancel, handler: nil)
        alert01.addAction(action)
        return alert01
    }
    
    func alertFailure() -> UIAlertController {
    
        let alert02 = UIAlertController(title: "Error", message: "Acceso denegado", preferredStyle: .alert)
        let action = UIAlertAction(title: "Aceptar", style: .cancel, handler: nil)
        alert02.addAction(action)
        return alert02
    }
}

