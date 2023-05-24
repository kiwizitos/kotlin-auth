package com.kiwizitos.login.core

import android.widget.EditText

class Validator {

    private fun verifyEmail(emailField: EditText) {
        if (emailField.text.isEmpty()) {
            emailField.setError("O email nao pode estar vazio")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailField.text).matches()) {
            emailField.setError("O email digitado é inválido")
        }
    }

    private fun verifyPassword(passwordField: EditText) {
        if (passwordField.text.isEmpty()) {
            passwordField.setError("A senha não pode estar vazia")
        } else if (passwordField.text.length < 6) {
            passwordField.setError("A senha precisa ter 6 dígitos")
        }
    }

    fun validateLogin(emailField: EditText, passwordField: EditText) {
        verifyEmail(emailField)
        verifyPassword(passwordField)


    }

    fun validateRegister(
        emailField: EditText,
        passwordField: EditText,
        confirmPasswordField: EditText
    ) {
        verifyEmail(emailField)
        verifyPassword(passwordField)

        if (passwordField.text.toString() != confirmPasswordField.text.toString()) {
            confirmPasswordField.setError("As senhas não batem")
        }
    }
}