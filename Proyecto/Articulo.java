package articulos;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import articulos.estadistica.*;
import hilos.HiloArtefacto;

/** La clase abstracta Articulo se encarga del modelado general de un artículo en venta. */
abstract class Articulo{
    protected String nombre;
    protected Estadistica stat;
    protected double precio;

    /** El método constructor Articulo se encarga de la creación de una instancia de tipo Estadistica. */
    public Articulo(){
        stat=new Estadistica();
    }

    /**
     * El método constructor Articulo se encarga de la inicialización de los atributos generales del artículo.
     * @param nombre Este parámetro representa el nombre del artículo.
     * @param precio Este parámetro representa el precio del artículo.
     */
    public Articulo(String nombre,double precio){
        this.nombre=nombre;
        this.precio=precio;
        stat=new Estadistica();
    }

    /** Métodos setters y getters de los atributos generales de un artículo. */
    public void setNombre(String nombre){ this.nombre=nombre; }
    public String getNombre(){ return nombre; }
    public void setStat(Estadistica stat){ this.stat=stat; }
    public Estadistica getStat(){ return stat; }
    public void setPrecio(double precio){ this.precio=precio; }
    public double getPrecio(){ return precio; }

    /** 
     * El método abstracto toString() estipula la escritura de un método de devolución de String en todas las clases que hereden de Articulo. 
     * @return Este método regresa los atributos del artículo transformados en un solo String.
     */
    abstract public String toString();

    /** El método abstracto print() estipula la escritura de un método vacío en todas las clases que hereden de Articulo. */
    abstract public void print();
}