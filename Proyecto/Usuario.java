package usuarios;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

 /** La clase abstracta Usuario se encarga del modelado general de un usuario. */
abstract class Usuario{
    protected String user;
    protected String password; 

    /**
     * El método constructor Usuario se encarga de la inicialización de los atributos generales del usuario.
     * @param user Este parámetro representa el nombre de usuario del administrador.
     * @param password Este parámetro representa la contraseña de acceso del administrador.
     */
    public Usuario(String user,String password){
        this.user=user;
        this.password=password;
    }

    /** Métodos setters y getters de los atributos generales de un usuario. */
    public void setUser(String user){ this.user=user; }
    public String getUser(){ return user; }
    public void setPassword(String password){ this.password=password; }
    public String getPassword(){ return password; }

    /** 
     * El método abstracto toString() estipula la escritura de un método de devolución de String en todas las clases que hereden de Usuario. 
     * @return Este método regresa los atributos del usuario transformados en un solo String.
     */
    abstract public String toString();

    /** El método abstracto print() estipula la escritura de un método vacío en todas las clases que hereden de Usuario. */
    abstract public void print();
}