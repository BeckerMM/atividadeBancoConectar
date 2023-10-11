import classes.Cliente;
import classes.Produto;
import classesDAO.ClienteDAO;
import classesDAO.ICRUD;
import classesDAO.ProdutoDAO;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try (ICRUD<Cliente, Integer> crudCliente = new ClienteDAO(); ICRUD<Produto, Integer> crudProduto = new ProdutoDAO()) {
            System.out.println("foi");
//          inserirCliente(new Cliente(2, "nome2", 20, new Produto(2, "produto2", 20000000.0)), crudCliente,crudProduto);

            System.out.println(buscarUm(2, crudCliente, crudProduto));
            System.out.println(buscarTodos(crudCliente, crudProduto));

            atualizar(new Cliente(2, "nome2", 21), crudCliente,crudProduto);
            // Remover Cliente
//            try {
//                crudCliente.deletar(1);
//            }catch (Exception e){
//                crudProduto.deletar(crudCliente.buscarUm(1).getProduto().getId());
//                crudCliente.deletar(1);
//            }

            //Buscar um
//            try{
//                System.out.println(crudCliente.buscarUm(1));
//                System.out.println("foi");
//            }catch (Exception e ){
//                throw new RuntimeException(e);
//            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void inserirCliente(Cliente cliente, ICRUD<Cliente, Integer> crudCliente, ICRUD<Produto, Integer> crudProduto) {
        if (cliente.getProduto() != null) {
            try {
                crudProduto.buscarUm(cliente.getProduto().getId());
            } catch (Exception e) {
                crudProduto.inserir(cliente.getProduto());
            }
        }
        crudCliente.inserir(cliente);
    }

    private static Cliente buscarUm(Integer id, ICRUD<Cliente, Integer> crudCliente, ICRUD<Produto, Integer> crudProduto) {
        try {
            Cliente cliente = crudCliente.buscarUm(id);
            if (cliente.getProduto().getId() != 0) {
                cliente.setProduto(crudProduto.buscarUm(cliente.getProduto().getId()));
            }
            return cliente;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<Cliente> buscarTodos(ICRUD<Cliente, Integer> crudCliente, ICRUD<Produto, Integer> crudProduto) {
        Set<Cliente> lista;
        try {
            lista = crudCliente.buscarTodos();
            for (Cliente cliente : lista) {

                if( cliente.getProduto() != null && cliente.getProduto().getId() != 0 ){
                    cliente.setProduto(crudProduto.buscarUm(cliente.getProduto().getId()));
                }
            }
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void atualizar (Cliente cliente , ICRUD<Cliente, Integer> crudCliente, ICRUD<Produto, Integer> crudProduto){
        try {
            crudCliente.atualizar(cliente);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
