package articulos;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import articulos.estadistica.*;
import hilos.HiloArtefacto;

/**
 * La clase Artefacto se encarga de modelar las características de un artefacto en venta.
 * Esta clase hereda de la clase abstracta Articulo.
 */
public class Artefacto extends Articulo{
    private Estadistica sec1,sec2;

    /** 
     * El método constructor Artefacto se encarga de la creación de una instancias de tipo Estadistica para el atributo heredado stat y sus atributos sec1 y sec2. 
     * */
    public Artefacto(){
        super();
        sec1=new Estadistica();
        sec2=new Estadistica();
    }

    /**
     * El método constructor Artefacto se encarga de la inicialización de los atributos de una instancia tipo Artefacto.
     * En este método se utilizan hilos para asignar las características de las estadísticas.
     * @param nombre Este parámetro representa el nombre del arma.
     * @param precio Este parámetro representa el precio del arma.
     */
    public Artefacto(String nombre, double precio){
        super(nombre,precio);
        sec1=new Estadistica();
        sec2=new Estadistica();
        HiloArtefacto hiloP=new HiloArtefacto("StatPrincipal",10,stat);
        HiloArtefacto hiloS1=new HiloArtefacto("StatSecundaria1",20,sec1);
        HiloArtefacto hiloS2=new HiloArtefacto("StatSecundaria2",30,sec2);
        try{
            hiloP.start();
            hiloS1.start();
            hiloS2.start();
            Thread.sleep(500);
        }catch(InterruptedException e){}
    }

    /** 
     * Métodos setters y getters de los atributos exclusivos de Artefacto: sec1 y sec2. 
     * Estos atributos exclusivos representan estadísticas secundarias del artefacto.
     */
    public void setSec1(Estadistica sec1){ this.sec1=sec1; }
    public Estadistica getSec1(){ return sec1; }
    public void setSec2(Estadistica sec2){ this.sec2=sec2; }
    public Estadistica getSec2(){ return sec2; }

    /**
     * El método toString() se encarga de la transformación de todos los atributos del artefacto a String para la impresión en archivo.
     * Este método se escribe a partir del método abstracto toString() de la clase Articulo.
     * @return Este método regresa los atributos del arma transformados en un solo String.
     */
    public String toString(){
        return nombre+", "+stat.toString()+", "+sec1.toString()+", "+sec2.toString()+", "+precio;
    }
    
    /**
     * El método print() se encarga de imprimir uno a uno los atributos del artefacto.
     * Este método se escribe a partir del método abstracto print() de la clase Articulo.
     */
    public void print(){
        System.out.println("Nombre de artefacto: "+nombre);
        System.out.print("Estadistica principal: ");
        stat.print();
        System.out.print("Estadisticas secundarias:\n -> ");
        stat.print();
        System.out.print(" -> ");
        stat.print();
        System.out.println("Precio: $"+precio);
    }
    
}
