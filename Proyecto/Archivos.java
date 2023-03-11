/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import java.util.LinkedList;
import java.nio.file.*;
import java.util.List;
import java.io.*;
import articulos.Arma;
import articulos.Artefacto;
import usuarios.Admin;
import usuarios.Cliente;

/** La clase ArchivosUsuarios permite el manejo de archivos que almacenan administradores o clientes. */
class ArchivosUsuarios{
    /**
     * El método escribir() se encarga de la impresión de un cliente en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param cliente Este parámetro es el cliente que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     */
    public void escribir(String nombre, Cliente c, boolean mode){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                wr.append(c.toString()+"\n");
            else
                wr.write(c.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); }; 
    }

    /**
     * El método escribir() se encarga de la impresión de un administrador en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param cliente Este parámetro es el administrador que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     */
    public void escribir(String nombre, Admin a, boolean mode){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                wr.append(a.toString()+"\n");
            else
                wr.write(a.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); }; 
    }

    /**
     * El método escribir() se encarga de la impresión de una lista de clientes en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista de clientes que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     */
    public void escribir(String nombre, List<Cliente> list, boolean mode){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                for(Cliente i:list)
                    wr.append(i.toString()+"\n");
            else
                for(Cliente i:list)
                    wr.write(i.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); };
    }

    /**
     * El método escribir() se encarga de la impresión de una lista de administradores en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista de administradores que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     * @param p Este parámetro sirve para diferenciar los métodos de clientes y administradores entre sí.
     * El último parámetro se agregó debido a que Java reconoce a las listas como iguales, aunque estas sean de distintos tipo de objeto.
     */
    public void escribir(String nombre, List<Admin> list, boolean mode,char p){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                for(Admin i:list)
                    wr.append(i.toString()+"\n");
            else
                for(Admin i:list)
                    wr.write(i.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); };
    }

    /**
     * El método leer() se encarga de la lectura de un archivo dado con las características de impresión de clientes.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista donde se almacenarán todos los clientes leídos.
     */
    public void leer(String nombre,List<Cliente> list){
        File f=new File(nombre);
        try{
            FileReader r  = new FileReader(f);
            BufferedReader br=new BufferedReader(r);
            String linea=br.readLine();
            while(linea != null){
                String[] values = linea.split(", ");
                Cliente c=new Cliente(values[0],values[1]);
                c.setCredito(Double.parseDouble(values[2].trim()));
                c.setArtComprados(Integer.parseInt(values[3].trim()));
                c.setArmasCompradas(Integer.parseInt(values[4].trim()));
                c.setArtefactosComprados(Integer.parseInt(values[5].trim()));
                list.add(c);
                linea = br.readLine();
            }
            br.close();
            r.close();
        }catch(IOException e){ System.err.println(e); }
    }

    /**
     * El método leer() se encarga de la lectura de un archivo dado con las características de impresión de administradores.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista donde se almacenarán todos los administradores leídos.
     * @param p Este parámetro sirve para diferenciar los métodos de clientes y administradores entre sí.
     * El último parámetro se agregó debido a que Java reconoce a las listas como iguales, aunque estas sean de distintos tipo de objeto.
     */
    public void leer(String nombre,List<Admin> list,char p){
        File f=new File(nombre);
        try{
            FileReader r  = new FileReader(f);
            BufferedReader br=new BufferedReader(r);
            String linea=br.readLine();
            while(linea != null){
                String[] values = linea.split(", ");
                Admin a=new Admin(values[1],values[2]);
                a.setKey(Integer.parseInt(values[0].trim()));
                list.add(a);
                linea = br.readLine();
            }
            br.close();
            r.close();
        }catch(IOException e){ System.err.println(e); }
    }
}

/** La clase ArchivosArticulos permite el manejo de archivos que almacenan armas o artefactos. */
class ArchivosArticulos{
    /**
     * El método escribir() se encarga de la impresión de un arma en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param a Este parámetro es el arma que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     */
    public void escribir(String nombre, Arma a, boolean mode){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                wr.append(a.toString()+"\n");
            else
                wr.write(a.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); }; 
    }

    /**
     * El método escribir() se encarga de la impresión de una lista de armas en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista de armas que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     */
    public void escribir(String nombre, List<Arma> list, boolean mode){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                for(Arma i:list)
                    wr.append(i.toString()+"\n");
            else
                for(Arma i:list)
                    wr.write(i.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); };
    }

    /**
     * El método leer() se encarga de la lectura de un archivo dado con las características de impresión de armas.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista donde se almacenarán todos las armas leídos.
     */
    public void leer(String nombre,List<Arma> list){
        File f=new File(nombre);
        try{
            FileReader r  = new FileReader(f);
            BufferedReader br=new BufferedReader(r);
            String linea=br.readLine();
            while(linea != null){
                String[] values = linea.split(", ");
                Arma a=new Arma();
                a.setNombre(values[0]);
                a.getStat().setAspecto(values[1]);
                a.getStat().setTipo(Integer.parseInt(values[2].trim()));
                a.getStat().setBonus(Integer.parseInt(values[3].trim()));
                a.setEfecto(values[4]);
                a.setPrecio(Double.parseDouble(values[5].trim()));
                list.add(a);
                linea = br.readLine();
            }
            br.close();
            r.close();
        }catch(IOException e){ System.err.println(e); }
    }

    /**
     * El método escribir() se encarga de la impresión de un artefacto en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param a Este parámetro es el artefacto que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     */
    public void escribir(String nombre, Artefacto a, boolean mode){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                wr.append(a.toString()+"\n");
            else
                wr.write(a.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); }; 
    }

    /**
     * El método escribir() se encarga de la impresión de una lista de artefactos en un archivo determinado.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista de artefactos que se imprimirá en el archivo.
     * @param mode Este parámetro indica si se quiere sobreescribir el archivo o anexar la nueva información.
     * @param p Este parámetro sirve para diferenciar los métodos de armas y artefactos entre sí.
     * El último parámetro se agregó debido a que Java reconoce a las listas como iguales, aunque estas sean de distintos tipo de objeto.
     */
    public void escribir(String nombre, List<Artefacto> list, boolean mode,char p){ //true para agregar, false para borrar
        File f=new File(nombre);
        try{
            FileWriter w=new FileWriter(f,mode);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            if(mode)
                for(Artefacto i:list)
                    wr.append(i.toString()+"\n");
            else
                for(Artefacto i:list)
                    wr.write(i.toString()+"\n");
            wr.close();
            bw.close();
            w.close();
        }catch(IOException e){ System.err.println(e); };
    }

    /**
     * El método leer() se encarga de la lectura de un archivo dado con las características de impresión de artefactos.
     * @param nombre Este parámetro se refiere al nombre del archivo.
     * @param list Este parámetro es la lista donde se almacenarán todos los artefactos leídos.
     * @param p Este parámetro sirve para diferenciar los métodos de armas y artefactos entre sí.
     * El último parámetro se agregó debido a que Java reconoce a las listas como iguales, aunque estas sean de distintos tipo de objeto.
     */
    public void leer(String nombre,List<Artefacto> list,char p){
        File f=new File(nombre);
        try{
            FileReader r  = new FileReader(f);
            BufferedReader br=new BufferedReader(r);
            String linea=br.readLine();
            while(linea != null){
                String[] values = linea.split(", ");
                Artefacto a=new Artefacto();
                a.setNombre(values[0]);
                a.getStat().setAspecto(values[1]);
                a.getStat().setTipo(Integer.parseInt(values[2].trim()));
                a.getStat().setBonus(Integer.parseInt(values[3].trim()));
                a.getSec1().setAspecto(values[4]);
                a.getSec1().setTipo(Integer.parseInt(values[5].trim()));
                a.getSec1().setBonus(Integer.parseInt(values[6].trim()));
                a.getSec2().setAspecto(values[7]);
                a.getSec2().setTipo(Integer.parseInt(values[8].trim()));
                a.getSec2().setBonus(Integer.parseInt(values[9].trim()));
                a.setPrecio(Double.parseDouble(values[10].trim()));
                list.add(a);
                linea = br.readLine();
            }
            br.close();
            r.close();
        }catch(IOException e){ System.err.println(e); }
    }
}