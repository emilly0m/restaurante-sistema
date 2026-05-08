package Controller;

import DAO.GarcomDAO;
import Model.Garcom;
import java.util.List;

public class GarcomController {

    private GarcomDAO garcomDAO;

    public GarcomController() {
        this.garcomDAO = new GarcomDAO();
    }

    public boolean cadastrar(String nome, String login, String senha, String perfil) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return false;
        }
        if (login == null || login.trim().isEmpty()) {
            System.out.println("Login não pode ser vazio.");
            return false;
        }
        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Senha não pode ser vazia.");
            return false;
        }
        Garcom garcom = new Garcom();
        garcom.setNome(nome.trim());
        garcom.setLogin(login.trim());
        garcom.setSenha(senha.trim());
        garcom.setPerfil(perfil);
        return garcomDAO.cadastrar(garcom);
    }

    public boolean alterar(int id, String nome, String login, String senha, String perfil) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return false;
        }
        if (login == null || login.trim().isEmpty()) {
            System.out.println("Login não pode ser vazio.");
            return false;
        }
        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Senha não pode ser vazia.");
            return false;
        }
        Garcom garcom = new Garcom();
        garcom.setId(id);
        garcom.setNome(nome.trim());
        garcom.setLogin(login.trim());
        garcom.setSenha(senha.trim());
        garcom.setPerfil(perfil);
        return garcomDAO.alterar(garcom);
    }

    public List<Garcom> consultar() {
        return garcomDAO.consultar();
    }

    public boolean excluir(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return false;
        }
        return garcomDAO.excluir(id);
    }
}
