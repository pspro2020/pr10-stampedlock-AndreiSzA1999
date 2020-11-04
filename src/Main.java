import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Almacen almacen = new Almacen();

        Thread consulta1 = new Thread(new Consultor(almacen,1));
        Thread consulta2 = new Thread(new Consultor(almacen,2));
        Thread consulta3 = new Thread(new Consultor(almacen,3));


        consulta1.start();
        consulta2.start();
        consulta3.start();

        Thread añadir = new Thread(new Añadir(almacen));
        añadir.start();

        TimeUnit.SECONDS.sleep(60);


        consulta1.interrupt();
        consulta2.interrupt();
        consulta3.interrupt();
        añadir.interrupt();
    }



}
