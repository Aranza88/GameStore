package articulos.estadistica;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

/** La clase Estadistica se encarga de modelar las características de una estadística adicional. */
public class Estadistica{
    private String aspecto;
    private int tipo, bonus;

    /**
     * Métodos setters y getters de los atributos de Estadistica, donde:
     * - aspecto representa el aspecto que el artículo mejora;
     * - tipo representa el tipo de bonus(puntos planos o porcentaje);
     * - bonus representa los puntos adicionales que ofrece el artículo.
     */
    public void setAspecto(String aspecto){ this.aspecto=aspecto; }
    public String getAspecto(){ return aspecto; }
    public void setTipo(int tipo){ this.tipo=tipo; }
    public int getTipo(){ return tipo; }
    public void setBonus(int bonus){ this.bonus=bonus; }
    public int getBonus(){ return bonus; }

    /**
     * El método toString() se encarga de la transformación de todos los atributos de la estadística a String para la impresión en archivo.
     * @return Este método regresa los atributos de la estadística transformados en un solo String.
     */
    public String toString(){
        return aspecto+", "+tipo+", "+bonus;
    }

    /** El método print() se encarga de imprimir uno a uno los atributos de la estadística. */
    public void print(){
        System.out.print(aspecto+" +");
        if(tipo==0)
            System.out.println(bonus+"%");
        else
            System.out.println(bonus);
    }
}