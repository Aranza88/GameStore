package usuarios;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import excepciones.CreditoInsuficiente;

 /**
  * La clase Cliente se encarga de modelar las características de un usuario de tipo cliente o comprador, es decir un usuario cualquiera.
  * Esta clase hereda de la clase abstracta Usuario.
  */
public class Cliente extends Usuario{
    private double credito;
    private int artComprados, armasCompradas, artefactosComprados;

    /**
     * El método constructor Cliente se encarga de la inicialización de los atributos de una instancia tipo Cliente.
     * @param user Este parámetro representa el nombre de usuario del administrador.
     * @param password Este parámetro representa la contraseña de acceso del administrador.
     */
    public Cliente(String user,String password){
        super(user,password);
        credito=0;
        artComprados=0;
        armasCompradas=0;
        artefactosComprados=0;
    }

    /**
     * Métodos setters y getters de los atributos exclusivos de la clase cliente, donde:
     * - credito representa con cuanto dinero cuenta el usuario;
     * - artComprados representa la cantidad total de articulos comprados por el usuario;
     * - armasCompradas representa la cantidad total de armas que ha comprado el usuario;
     * - artefactosComprados representa la cantidad total de artefactos comprados por el usuario.
     */
    public void setCredito(double credito){ this.credito=credito; }
    public double getCredito(){ return credito; }
    public void setArtComprados(int artComprados){ this.artComprados=artComprados; }
    public int getArtComprados(){ return artComprados; }
    public void setArmasCompradas(int armasCompradas){ this.armasCompradas=armasCompradas; }
    public int getArmasCompradas(){ return armasCompradas; }
    public void setArtefactosComprados(int artefactosComprados){ this.artefactosComprados=artefactosComprados; }
    public int getArtefactosComprados(){ return artefactosComprados; }

    /**
     * El método toString() se encarga de la transformación de todos los atributos del usuario a String para la impresión en archivo.
     * Este método se escribe a partir del método abstracto toString() de la clase Usuario.
     * @return Este método regresa los atributos del administrador transformados en un solo String.
     */
    public String toString(){
        return user+", "+password+", "+credito+", "+artComprados+", "+armasCompradas+", "+artefactosComprados;
    }

    /**
     * El método print() se encarga de imprimir uno a uno los atributos del usuario, exceptuando la contraseña.
     * Este método se escribe a partir del método abstracto print() de la clase Usuario.
     */
    public void print(){
        System.out.println("Nombre de usuario: "+user);
        System.out.println("Credito: "+credito);
        System.out.println("Articulos comprados: "+artComprados);
        System.out.println("Armas compradas: "+armasCompradas);
        System.out.println("Artefactos comprados: "+artefactosComprados);
    }

    /**
     * El método consultorCredito() se encarga de revisar si el crédito del usuario es suficiente para pagar un precio.
     * Este método arroja una excepción CreditoInsuficiente en caso de que el crédito del usuario no sea suficiente.
     * @param precio Este parámetro es la canidad que el usuario desea pagar.
     */
    public void consultarCredito(double precio) throws CreditoInsuficiente{
        if(credito<precio)
            throw new CreditoInsuficiente(this);
    }
}