package Controller;

import DAO.MesaDAO;
import Model.Mesa;
import java.util.List;

public class MesaController {

    private MesaDAO mesaDAO;

    public MesaController() {
        this.mesaDAO = new MesaDAO();
    }

    public boolean cadastrar() {
        Mesa mesa = new Mesa();
        mesa.setStatus("livre");
        return mesaDAO.cadastrar(mesa);
    }

    public boolean alterar(int id, String status) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return false;
        }
        if (!status.equals("livre") && !status.equals("ocupada")) {
            System.out.println("Status inválido. Use 'livre' ou 'ocupada'.");
            return false;
        }
        Mesa mesa = new Mesa();
        mesa.setId(id);
        mesa.setStatus(status);
        return mesaDAO.alterar(mesa);
    }

    public List<Mesa> consultar() {
        return mesaDAO.consultar();
    }

    public boolean excluir(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return false;
        }
        return mesaDAO.excluir(id);
    }
}
