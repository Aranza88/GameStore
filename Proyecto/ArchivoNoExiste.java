package excepciones;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

 /**
  * La clase ArchivoNoExiste modela una excepción que se da en caso de intentar acceder a un archivo inexistente.
  * Esta clase hereda de la clase Exception.
  */
public class ArchivoNoExiste extends Exception {
    String mensaje;

    /**
     * El método constructor ArchivoNoExiste se encarga de inicializar el atributo mensaje.
     * @param mensaje Este parámetro representa el mensaje que se pasará en caso de que se de una excepción de este tipo.
     */
    public ArchivoNoExiste(String mensaje){
        this.mensaje=mensaje;
    }

    /**
     * El método toString() se encarga de devolver el mensaje de la excepción.
     * @return El método devuelve el String almacenado en mensaje.
     */
    public String toString() {
        return mensaje;
    }
}