package hilos;

/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import articulos.estadistica.Estadistica;

/**
 * La clase HiloArtefacto modela un hilo diseñado para establecer las características de una estadística.
 * Esta clase hereda de la clase Thread.
 */
public class HiloArtefacto extends Thread{
    Estadistica stat;
    int indice;

    /** 
	 * Método constructor de la clase HiloArtefacto que se encarga de inicializar los atributos.
	 * @param nombre Este parámetro representa el nombre del hilo.
     * @param indice Este parámetro representa el índice del hilo en cuestión.
     * @param stat Este parámetro representa la estadística en donde se guardarán los datos aleatorios.
	 */
	public HiloArtefacto(String nombre,int indice,Estadistica stat){
        super(nombre);
        this.indice=indice;
        this.stat=stat;
	}

	/** El método run() establece lo que hará el hilo al dar inicio. */
	public void run(){
        int bonus,i=1;
        for(int j=0;j<indice;j++)
            i=(int)(Math.random()*4+0.9);
        if(i==1)
            stat.setAspecto("CRIT");
        else if(i==2)
            stat.setAspecto("ATQ");
        else if(i==3)
            stat.setAspecto("HP");
        else
            stat.setAspecto("DEF");
        for(int j=0;j<indice;j++)
        stat.setTipo((int)(Math.random()*1.4+0.5));
        if(stat.getTipo()==0){
            stat.setBonus((int)(Math.random()*15+1)*indice);
        }
        else{
            stat.setBonus((int)(Math.random()*350+100)*indice);
        }
	}
}

