/**
 * Alfáro Fernández Azul    No: 317032471
 * Núñez Luna Aranza Abril  No: 317079867
 */

import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import articulos.Arma;
import articulos.Artefacto;
import articulos.estadistica.Estadistica;
import usuarios.Admin;
import usuarios.Cliente;
import excepciones.CreditoInsuficiente;
import excepciones.ArchivoNoExiste;
import hilos.HiloArtefacto;

/** La clase TiendaMain se encarga de modelar una tienda de armas y artefactos basada en el juego Genshin Impact. */
class TiendaMain{
    /**
     * El método main() es el método ejecutable del proyecto, el cual modela la tienda.
     * @param args Parámetro establecido por Java para main().
     */
    public static void main(String[] args){
        ArchivosUsuarios arch=new ArchivosUsuarios();
        Scanner sc=new Scanner(System.in);
        Console con=System.console();
        Utilities u=new Utilities();
        Menu m=new Menu();
        File f;
        int r,op,i;
        String file,user,password;
        do{
            u.limpiarPantalla();
            System.out.println("\t\t-- Bienvenido! --\n");
            file="Clientes.txt";
            System.out.print("Qu\u00e9 usuario desea acceder?\n 1. Cliente\n 2. Administrador\n 3. Salir\n --> ");
            r=sc.nextInt();
            if(r==1){
                Cliente c;
                do{
                    System.out.print("\nDesea crear cuenta o iniciar sesi\u00f3n?\n 1. Crear cuenta\n 2. Iniciar sesi\u00f3n\n 3. Salir\n --> ");
                    op=sc.nextInt();
                    sc.nextLine();
                    if(op==1){
                        System.out.print("\nInserte nuevo nombre de usuario: ");
                        user=sc.nextLine();
                        System.out.print("Inserte nueva contrase\u00f1a: ");
                        password=new String(con.readPassword());
                        c=new Cliente(user,password);
                        //Crear nuevo usuario y agregarlo al archivo
                        arch.escribir(file,c,true);
                        System.out.println("Usuario creado exitosamente!");
                        System.out.println("\n\t\t-- Datos de usuario --");
                        c.print();
                        u.pausarPantalla();
                    }else if(op==2){
                        try{
                            m.existenciaArchivo(file,"No hay usuarios registrados.");
                            do{
                                System.out.println("\n** Escriba exit o salir para salir **");
                                System.out.print("Inserte nombre de usuario: ");
                                user=sc.nextLine();
                                if(user.toLowerCase().equals("exit")||user.toLowerCase().equals("salir"))
                                    break;
                                System.out.print("Inserte contrase\u00f1a: ");
                                password=new String(con.readPassword());
                                if(password.toLowerCase().equals("exit")||password.toLowerCase().equals("salir"))
                                    break;
                                i=inicioSesion(file,user,password);
                                if(i!=-1){
                                    List<Cliente> list=new LinkedList<>();
                                    arch.leer(file,list);
                                    m.user(list,i);
                                }else
                                    System.out.println("Datos incorrectos");
                            }while(i==-1);
                        }catch(ArchivoNoExiste e){ System.out.println(e.toString()); op=1; }
                        //Si comparación es correcta m.user, sino datos incorrectos y de nuevo
                    }else if(op!=3){
                        System.out.println("Opci\u00f3n no v\u00e1lida");
                        u.pausarPantalla();
                    }
                }while(op!=2&&op!=3);
            }else if (r==2){
                file="Admins.txt";
                Admin a;
                f=new File(file);
                if(f.exists()){
                    do{
                        System.out.println("\n** Escriba exit o salir en el usuario o contrase\u00f1a para salir **");
                        System.out.print("Inserte clave de administrador: ");
                        int key=sc.nextInt();
                        sc.nextLine();
                        System.out.print("Inserte nombre de usuario: ");
                        user=sc.nextLine();
                        if(user.toLowerCase().equals("exit")||user.toLowerCase().equals("salir"))
                            break;
                        System.out.print("Inserte contrase\u00f1a: ");
                        password=new String(con.readPassword());
                        if(password.toLowerCase().equals("exit")||password.toLowerCase().equals("salir"))
                            break;
                        a=inicioSesion(file,user,password,key);
                        if(a!=null)
                            m.admin(a);
                        else
                            System.out.println("Datos incorrectos");
                    }while(a==null);
                }else
                    System.out.println("No hay administradores registrados.");
            }else if(r!=3)
                System.out.println("Opci\u00f3n no v\u00e1lida");
        }while(r!=1&&r!=2&&r!=3);   
    }
    
    /**
     * El método inicioSesion() se encarga de la comparación de datos del usuario que trata de acceder con los usuarios registrados.
     * @param name Este parámetro representa el nombre del archivo donde se registran los clientes.
     * @param user Este parámetro representa el nombre de usuario a comparar.
     * @param password Este parámetro representa la contraseña a comparar.
     * @return Este método devuelve el índice donde se encuentre el usuario correspondiente. Devuelve un -1 en caso de no encontrar al usuario indicado.
     */
    static int inicioSesion(String name,String user,String password){
        ArchivosUsuarios arch=new ArchivosUsuarios();
        List<Cliente> list=new LinkedList<>();
        int i=0;
        arch.leer(name,list);
        for(i=0;i<list.size();i++)
            if(list.get(i).getUser().equals(user)&&list.get(i).getPassword().equals(password))
                return i;
        return -1;
    }
        
    /**
     * El método inicioSesion() se encarga de la comparación de datos del usuario que trata de acceder con los administradores registrados.
     * @param name Este parámetro representa el nombre del archivo donde se registran los administradores.
     * @param user Este parámetro representa el nombre de usuario a comparar.
     * @param password Este parámetro representa la contraseña a comparar.
     * @param key Este parámetro representa la clave de administrador a comparar.
     * @return Este método devuelve el administrador encontrado. Devuelve un null en caso de no encontrar al administrador indicado.
     */
    static Admin inicioSesion(String name,String user,String password,int key){
        List<Admin> list=new LinkedList<>();
        ArchivosUsuarios arch=new ArchivosUsuarios();
        arch.leer(name,list,'a');
        for(Admin i:list)
            if(i.getUser().equals(user)&&i.getPassword().equals(password)&&i.getKey()==key)
                return i;
        return null;
    }
}

/** La clase Menu se encarga del modelado de menús y opciones para cada tipo de usuario. */
class Menu{
    /**
     * El método admin() construye el menú correspondiente a los administradores y las acciones que los administradores pueden llevar a cabo.
     * @param a Este parámetro representa al administrador activo.
     */
    public void admin(Admin a){
        Scanner sc=new Scanner(System.in);
        Utilities u=new Utilities();
        Console con=System.console();
        String file;
        int r,i;
        do{
            u.limpiarPantalla();
            System.out.println("\t\t -- Usuario activo --\n");
            a.print();
            System.out.print("\nQu\u00e9 acci\u00f3n desea realizar?\n 1. Agregar administrador\n 2. Modificar tienda de armas\n 3. Modificar tienda de artefactos\n 4. Salir\n --> ");
            r=sc.nextInt();
            if(r==1){
                ArchivosUsuarios arch=new ArchivosUsuarios();
                file="Admins.txt";
                sc.nextLine();
                System.out.print("\nInserte nuevo nombre de usuario: ");
                String user=sc.nextLine();
                System.out.print("Inserte nueva contrase\u00f1a: ");
                String password=new String(con.readPassword());
                Admin nuevo=new Admin(user,password);
                arch.escribir(file,nuevo,true);
                System.out.println("Administrador creado exitosamente!");
                System.out.println("\n\t\t-- Datos de administrador --");
                nuevo.print();
                u.pausarPantalla();
            }else if(r==2){
                ArchivosArticulos arch=new ArchivosArticulos();
                List<Arma> list;
                file="Armas.txt";
                int cantidad=-1;
                do{
                    list=new LinkedList<>();
                    u.limpiarPantalla();
                    System.out.println("\t\t -- Tienda de armas --\n");
                    try{
                        existenciaArchivo(file,"No hay armas a la venta");
                        arch.leer(file,list);
                        cantidad=list.size();
                        System.out.println("Cantidad de armas: "+cantidad);
                        for(i=0;i<list.size();i++){
                            System.out.println("-> Arma "+(i+1)+":");
                            list.get(i).print(); 
                        }
                    }catch(ArchivoNoExiste e){ System.out.println(e.toString()); }
                    if(cantidad==0) System.out.println("No hay armas a la venta");
                    System.out.println();
                    System.out.print("Qu\u00e9 modificaci\u00f3n desea hacer?\n 1. Agregar arma\n 2. Eliminar arma\n 3. Salir\n --> ");
                    i=sc.nextInt();
                    sc.nextLine();
                    if(i==1){
                        System.out.print("\nInserte nombre del arma: ");
                        String nombre=sc.nextLine();
                        System.out.print("Inserte efecto del arma: ");
                        String efecto=sc.nextLine();
                        System.out.print("Inserte precio del arma: ");
                        double precio=sc.nextDouble();
                        sc.nextLine();
                        Arma arma=new Arma(nombre,efecto,precio);
                        arch.escribir(file,arma,true);
                        System.out.println("Arma creada exitosamente!");
                        System.out.println("\n\t\t-- Datos de arma --");
                        arma.print();
                        u.pausarPantalla();
                    }else if(i==2){
                        if(cantidad!=0){
                            List<Integer> indices;
                            boolean b=false;
                            int k;
                            do{
                                System.out.println("\n** Escriba exit o salir para salir **");
                                System.out.print("Inserte el nombre del arma a eliminar: ");
                                String nombre=sc.nextLine();
                                if(nombre.toLowerCase().equals("exit")||nombre.toLowerCase().equals("salir"))
                                    break;
                                indices=u.busquedaArma(nombre,list);
                                if(indices.size()==0){
                                    System.out.println("El arma indicada no existe.");
                                    b=true;
                                }else{
                                    System.out.println("\nLos resultados de busqueda son: ");
                                    for(Integer j:indices){
                                        System.out.println("-> Arma "+(j.intValue()+1)+":");
                                        list.get(j.intValue()).print();
                                    }
                                    do{
                                        System.out.print("\nInserte n\u00famero de arma a eliminar: ");
                                        k=sc.nextInt();
                                        if(indices.contains(new Integer(k-1))){
                                            list.remove(k-1);
                                            arch.escribir(file,list,false);
                                            System.out.println("\nArma eliminada exitosamente!");
                                        }else{
                                            System.out.println("N\u00famero de arma no coincidente.");
                                            k=-1;
                                        }
                                        u.pausarPantalla();
                                    }while(k==-1);
                                    b=false;
                                }
                            }while(b);
                        }else{
                            System.out.println("No hay armas a la venta.");
                            u.pausarPantalla();
                        }
                    }else if(i!=3)
                        System.out.println("Opci\u00f3n no v\u00e1lida");
                }while(i!=3);
            }else if(r==3){
                ArchivosArticulos arch=new ArchivosArticulos();
                List<Artefacto> list;
                file="Artefactos.txt";
                int cantidad=-1;
                do{
                    u.limpiarPantalla();
                    list=new LinkedList<>();
                    System.out.println("\t\t -- Tienda de artefactos --\n");
                    try{
                        existenciaArchivo(file,"No hay artefactos a la venta");
                        arch.leer(file,list,'a');
                        cantidad=list.size();
                        System.out.println("Cantidad deartefactos: "+cantidad);
                        for(i=0;i<list.size();i++){
                            System.out.println("-> Artefacto "+(i+1)+":");
                            list.get(i).print(); 
                        }
                    }catch(ArchivoNoExiste e){ System.out.println(e.toString()); }
                    if(cantidad==0) System.out.println("No hay artefactos a la venta");
                    System.out.println();
                    System.out.print("Qu\u00e9 modificaci\u00f3n desea hacer?\n 1. Agregar artefacto\n 2. Eliminar artefacto\n 3. Salir\n --> ");
                    i=sc.nextInt();
                    sc.nextLine();
                    if(i==1){
                        System.out.print("\nInserte nombre del artefacto: ");
                        String nombre=sc.nextLine();
                        System.out.print("Inserte precio del artefacto: ");
                        double precio=sc.nextDouble();
                        sc.nextLine();
                        Artefacto art=new Artefacto(nombre,precio);
                        arch.escribir(file,art,true);
                        System.out.println("Artefacto creado exitosamente!");
                        System.out.println("\n\t\t-- Datos de artefacto --");
                        art.print();
                        u.pausarPantalla();
                    }else if(i==2){
                        if(cantidad!=0){
                            List<Integer> indices;
                            boolean b=false;
                            int k;
                            do{
                                System.out.println("\n** Escriba exit o salir para salir **");
                                System.out.print("Inserte el nombre del artefacto a eliminar: ");
                                String nombre=sc.nextLine();
                                if(nombre.toLowerCase().equals("exit")||nombre.toLowerCase().equals("salir"))
                                    break;
                                indices=u.busquedaArtefacto(nombre,list);
                                if(indices.size()==0){
                                    System.out.println("El artefacto indicado no existe.");
                                    b=true;
                                }else{
                                    System.out.println("\nLos resultados de busqueda son: ");
                                    for(Integer j:indices){
                                        System.out.println("-> Artefacto "+(j.intValue()+1)+":");
                                        list.get(j.intValue()).print();
                                    }
                                    do{
                                        System.out.print("\nInserte n\u00famero de artefacto a eliminar: ");
                                        k=sc.nextInt();
                                        if(indices.contains(new Integer(k-1))){
                                            list.remove(k-1);
                                            arch.escribir(file,list,false,'a');
                                            System.out.println("\nArtefacto eliminada exitosamente!");
                                        }else{
                                            System.out.println("N\u00famero de artefacto no coincidente.");
                                            k=-1;
                                        }
                                        u.pausarPantalla();
                                    }while(k==-1);
                                }
                            }while(b);
                        }else{
                            System.out.println("No hay artefactos a la venta.");
                            u.pausarPantalla();
                        }
                    }else if(i!=3)
                        System.out.println("Opci\u00f3n no v\u00e1lida");
                }while(i!=3);                    
            }else if(r!=4)
                System.out.println("Opci\u00f3n no v\u00e1lida"); 
        }while(r!=4);
    }

    /**
     * El método user() construye el menú correspondiente a los usuarios y las acciones que los usuarios pueden llevar a cabo.
     * @param list Este parámetro representa la lista de usuarios registrados en el programa.
     * @param c Este parámetro indica el índice del usuario actual en la lista de usuarios registrados.
     */
    public void user(List<Cliente> list,int c){
        ArchivosArticulos archA=new ArchivosArticulos();
        ArchivosUsuarios archU=new ArchivosUsuarios();
        Scanner sc=new Scanner(System.in);
        Utilities u=new Utilities();
        String file;
        int r,i,k;
        do{
            u.limpiarPantalla();
            System.out.println("\t\t -- Usuario activo --\n");
            list.get(c).print();
            System.out.print("\nQu\u00e9 acci\u00f3n desea realizar?\n 1. Depositar cr\u00e9dito\n 2. Tienda de armas\n 3. Tienda de artefactos\n 4. Salir\n --> ");
            r=sc.nextInt();
            if(r==1){
                System.out.print("\nInserte la cantidad a depositar: ");
                list.get(c).setCredito(list.get(c).getCredito()+sc.nextDouble());
                sc.nextLine();
                archU.escribir("Clientes.txt",list,false);
                System.out.println("Cr\u00e9dito depositado!");
                u.pausarPantalla();
            }else if(r==2){
                List<Arma> lista;
                int cantidad;
                boolean flag;
                file="Armas.txt";
                do{
                    u.limpiarPantalla();
                    System.out.println("\t\t -- Tienda de armas --\n");
                    try{
                        existenciaArchivo(file,"No hay armas a la venta");
                        lista=new LinkedList<>();
                        archA.leer(file,lista);
                        cantidad=lista.size();
                        System.out.println("Cantidad de armas: "+cantidad);
                        for(i=0;i<lista.size();i++){
                            System.out.println("-> Arma "+(i+1)+":");
                            lista.get(i).print(); 
                        }
                    }catch(ArchivoNoExiste e){ System.out.println(e.toString()); u.pausarPantalla(); break; }
                    if(cantidad==0){
                        System.out.println("No hay armas a la venta");
                        u.pausarPantalla();
                        break;
                    }else{
                        System.out.println();
                        System.out.print("Desea comprar?\n 1. S\u00ed\n 2. No\n --> ");
                        i=sc.nextInt();
                        if(i==1){
                            do{
                                System.out.print("\nInserte n\u00famero de arma a comprar: ");
                                k=sc.nextInt();
                                if((k-1<lista.size())&&(k-1>=0)){
                                    try{
                                        list.get(c).consultarCredito(lista.get(k-1).getPrecio());
                                        list.get(c).setCredito(list.get(c).getCredito()-lista.get(k-1).getPrecio());
                                        list.get(c).setArtComprados(list.get(c).getArtComprados()+1);
                                        list.get(c).setArmasCompradas(list.get(c).getArmasCompradas()+1);
                                        archU.escribir("Clientes.txt",list,false);
                                        System.out.println("Compra realizada exitosamente!");
                                        System.out.println("Cr\u00e9dito restante: "+list.get(c).getCredito());
                                    }catch(CreditoInsuficiente e){ System.out.println(e.toString()); }
                                }else{
                                    System.out.println("N\u00famero de arma no coincidente.");
                                    k=-1;
                                }
                                u.pausarPantalla();
                            }while(k==-1);
                        }else if(i!=2){
                            System.out.println("Opci\u00f3n no v\u00e1lida"); 
                            u.pausarPantalla();
                        }
                    }
                }while(i!=2);
            }else if(r==3){
                List<Artefacto> lista;
                int cantidad;
                boolean flag;
                file="Artefactos.txt";
                do{
                    u.limpiarPantalla();
                    System.out.println("\t\t -- Tienda de artefactos --\n");
                    try{
                        existenciaArchivo(file,"No hay artefactos a la venta");
                        lista=new LinkedList<>();
                        archA.leer(file,lista,'a');
                        cantidad=lista.size();
                        System.out.println("Cantidad de artefactos: "+cantidad);
                        for(i=0;i<lista.size();i++){
                            System.out.println("-> Artefacto "+(i+1)+":");
                            lista.get(i).print(); 
                        }
                    }catch(ArchivoNoExiste e){ System.out.println(e.toString()); u.pausarPantalla(); break; }
                    if(cantidad==0){
                        System.out.println("No hay artefactos a la venta");
                        u.pausarPantalla();
                        break;
                    }else{
                        System.out.println();
                        System.out.print("Desea comprar?\n 1. S\u00ed\n 2. No\n --> ");
                        i=sc.nextInt();
                        if(i==1){
                            do{
                                System.out.print("\nInserte n\u00famero de artefacto a comprar: ");
                                k=sc.nextInt();
                                if((k-1<lista.size())&&(k-1>=0)){
                                    try{
                                        list.get(c).consultarCredito(lista.get(k-1).getPrecio());
                                        list.get(c).setCredito(list.get(c).getCredito()-lista.get(k-1).getPrecio());
                                        list.get(c).setArtComprados(list.get(c).getArtComprados()+1);
                                        list.get(c).setArtefactosComprados(list.get(c).getArtefactosComprados()+1);
                                        archU.escribir("Clientes.txt",list,false);
                                        System.out.println("Compra realizada exitosamente!");
                                        System.out.println("Cr\u00e9dito restante: "+list.get(c).getCredito());
                                    }catch(CreditoInsuficiente e){ System.out.println(e.toString()); }
                                }else{
                                    System.out.println("N\u00famero de artefacto no coincidente.");
                                    k=-1;
                                }
                                u.pausarPantalla();
                            }while(k==-1);
                        }else if(i!=2){
                            System.out.println("Opci\u00f3n no v\u00e1lida"); 
                            u.pausarPantalla();
                        }
                    }
                }while(i!=2);
            }else if(r!=4)
                System.out.println("Opci\u00f3n no v\u00e1lida"); 
        }while(r!=4);

        /**
         * Opciones
         * - Agregar credito 
         * - Tienda de armas (bloqueada si no hay credito)
         *   -> Elegir arma a comprar y verificar si tiene credito suficiente
         *   -> Preguntar si quiere compar otra cosa
         *   -> Ahí o en otra tienda
         * - Tienda de artefactos (bloqueada si no hay credito)
         *   -> Elegir artefacto o comprar y verificar si tiene credito suficiente
         *   -> Preguntar si quiere compar otra cosa
         *   -> Ahí o en otra tienda
         */
    }

    /**
     * El método existenciaArchivo se encarga de determinar si un archivo existe.
     * Este método arroja una excepción ArchivoNoExiste en caso de que el archivo indicado no exista.
     * @param name Este parámetro representa el nombre del archivo que se busca.
     * @param msg Este parámetro representa el mensaje a imprimir en caso de arrojar una excepción.
     */
    public void existenciaArchivo(String name,String msg) throws ArchivoNoExiste{
        File file=new File(name);
        if(!(new File(name).exists()))
            throw new ArchivoNoExiste(msg);
    }
}

/** La clase Utilities contiene métodos útiles para el desarrollo del menú. */
class Utilities{
    /** El método pausarPantalla() se encarga de pausar la consola hasta que el usuario presione enter. */
    public static void pausarPantalla(){
        Scanner sc=new Scanner(System.in);
        String r="a";
        while(!r.equals("")){
            System.out.print("\nPresione enter para continuar ");
            r=sc.nextLine();
        }
    }

    /** El método limpiarPantalla() se encarga de limpiar la consola. */
    public static void limpiarPantalla(){
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch(Exception e){}
    }

    /**
     * El método busquedaArma() se encarga de la búsqueda lineal de un arma en una lista. Esto a través de la comparación de nombres.
     * @param nombre Este parámetro representa el nombre del arma que se busca.
     * @param list Este parámetro representa la lista de armas donde se buscará la indicada.
     * @return Este método devuelve una lista con los índices de las coincidencias.
     */
    public List<Integer> busquedaArma(String nombre,List<Arma> list){
        List<Integer> indices=new LinkedList<>();
        for(int i=0;i<list.size();i++)
            if(list.get(i).getNombre().toLowerCase().equals(nombre.toLowerCase()))
                indices.add(new Integer(i));
        return indices;
    }

    /**
     * El método busquedaArtefactos() se encarga de la búsqueda lineal de un artefacto en una lista. Esto a través de la comparación de nombres.
     * @param nombre Este parámetro representa el nombre del artefacto que se busca.
     * @param list Este parámetro representa la lista de artefactos donde se buscará el indicado.
     * @return Este método devuelve una lista con los índices de las coincidencias.
     */
    public List<Integer> busquedaArtefacto(String nombre,List<Artefacto> list){
        List<Integer> indices=new LinkedList<>();
        for(int i=0;i<list.size();i++)
            if(list.get(i).getNombre().toLowerCase().equals(nombre.toLowerCase()))
                indices.add(new Integer(i));
        return indices;
    }
}