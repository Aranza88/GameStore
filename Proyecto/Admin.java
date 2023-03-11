package usuarios;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

 /**
  * La clase Admin se encarga de modelar las características de un usuario de tipo administrador.
  * Esta clase hereda de la clase abstracta Usuario.
  */
public class Admin extends Usuario{
    private int key;

    /**
     * El método constructor Admin se encarga de la inicialización de los atributos de una instancia tipo Admin.
     * @param user Este parámetro representa el nombre de usuario del administrador.
     * @param password Este parámetro representa la contraseña de acceso del administrador.
     */
    public Admin(String user,String password){
        super(user,password);
        key=(int) (Math.random() * 100000 + 1);
    }
    
    /** Métodos setter y getter del atributo exclusivo de Admin: key, el cual representa la clave del administrador. */
    public void setKey(int key){ this.key=key; }
    public int getKey(){ return key; }

    /**
     * El método toString() se encarga de la transformación de todos los atributos del administrador a String para la impresión en archivo.
     * Este método se escribe a partir del método abstracto toString() de la clase Usuario.
     * @return Este método regresa los atributos del administrador transformados en un solo String.
     */
    public String toString(){
        return key+", "+user+", "+password;
    }

    /**
     * El método print() se encarga de imprimir uno a uno los atributos del administrador, exceptuando la contraseña.
     * Este método se escribe a partir del método abstracto print() de la clase Usuario.
     */
    public void print(){
        System.out.println("Clave de administrador: "+key);
        System.out.println("Nombre de usuario: "+user);
    }
}