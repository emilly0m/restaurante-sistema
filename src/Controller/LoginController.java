package Controller;

import DAO.GarcomDAO;
import Model.Garcom;

public class LoginController {

    private GarcomDAO garcomDAO;

    public LoginController() {
        this.garcomDAO = new GarcomDAO();
    }

    public Garcom autenticar(String login, String senha) {
        if (login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            return null;
        }
        return garcomDAO.autenticar(login, senha);
    }
}