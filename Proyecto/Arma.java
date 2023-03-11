package articulos;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import articulos.estadistica.*;
import hilos.HiloArtefacto;

/**
 * La clase Arma se encarga de modelar las características de un arma en venta.
 * Esta clase hereda de la clase abstracta Articulo.
 */
public class Arma extends Articulo{
    private String efecto;

    /** El método constructor Arma se encarga de la creación de una instancia de tipo Estadistica a través del constructor de Articulo. */
    public Arma(){
        super();
    }

    /**
     * El método constructor Arma se encarga de la inicialización de los atributos de una instancia tipo Arma.
     * En este método se utilizan hilos para asignar las características de la estadística.
     * @param nombre Este parámetro representa el nombre del arma.
     * @param efecto Este parámetro representa la pasiva o ventaja que ofrece el arma en combate.
     * @param precio Este parámetro representa el precio del arma.
     */
    public Arma(String nombre,String efecto,double precio){
        super(nombre,precio);
        this.efecto=efecto;
        HiloArtefacto hiloP=new HiloArtefacto("StatPrincipal",1,stat);
        hiloP.start();
        try{
            Thread.sleep(750);
        }catch(InterruptedException e){}
    }

    /** Métodos setter y getter del atributo exclusivo de Arma: efecto. */
    public void setEfecto(String efecto){ this.efecto=efecto; }
    public String getEfecto(){ return efecto; }

    /**
     * El método toString() se encarga de la transformación de todos los atributos del arma a String para la impresión en archivo.
     * Este método se escribe a partir del método abstracto toString() de la clase Articulo.
     * @return Este método regresa los atributos del arma transformados en un solo String.
     */
    public String toString(){
        return nombre+", "+stat.toString()+", "+efecto+", "+precio;
    }
    
    /**
     * El método print() se encarga de imprimir uno a uno los atributos del arma.
     * Este método se escribe a partir del método abstracto print() de la clase Articulo.
     */
    public void print(){
        System.out.println("Nombre de arma: "+nombre);
        System.out.print("Esadist\u00edca: ");
        stat.print();
        System.out.println("Efecto: "+efecto);
        System.out.println("Precio: $"+precio);
    }
}