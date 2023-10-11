import classes.Cliente;
import classes.Produto;
import classesDAO.ClienteDAO;
import classesDAO.ICRUD;
import classesDAO.ProdutoDAO;

public class Main {
    public static void main(String[] args) {
        try (ICRUD<Cliente, Integer> crudCliente = new ClienteDAO(); ICRUD<Produto, Integer> crudProduto = new ProdutoDAO()) {

            Cliente cliente = new Cliente(1, "nome1", 20, new Produto(1, "produto1", 20000.0));

            try{
                crudProduto.buscarUm(cliente.getId());
            }catch (Exception e){
                crudProduto.inserir(cliente.getProduto());

            }
        } catch (Exception e) {

        }
    }


}
