package excepciones;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import usuarios.Cliente;

 /**
  * La clase CreditoInsuficiente modela una excepción que se da en caso de intentar hacer una compra con credito insuficiente.
  * Esta clase hereda de la clase Exception.
  */
public class CreditoInsuficiente extends Exception {
    Cliente user;

    /**
     * El método constructor CreditoInsuficiente inicializa el aributo heredado msg.
     * @param msg Este parámetro representa el mensaje que será almacenado en el atributo msg.
     */
    public CreditoInsuficiente(String msg) {
        super(msg);
    }

    /**
     * El método constructor CreditoInsuficiente inicializa el aributo cliente.
     * @param user Este parámetro representa el cliente que está verificando su crédito.
     */
    public CreditoInsuficiente(Cliente user){
        this.user=user;
    }

    /**
     * El método toString() se encarga de informar al usuario que su crédito es insuficiente.
     * @return El método devuelve un mensaje informante que el crédito es insuficiente.
     */
    public String toString() {
        return "El usuario "+user.getUser()+" tiene $"+user.getCredito()+", lo cual es insuficiente para la transacci\u00f3n que se desea realizar.";
    }
}