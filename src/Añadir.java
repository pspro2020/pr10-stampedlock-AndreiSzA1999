public class Añadir implements Runnable{
    protected Almacen almacen;

    public Añadir(Almacen almacen){

        this.almacen=almacen;

    }


    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()){

            try {

                almacen.añadirProductos();
                Thread.sleep(2000);
            }catch (InterruptedException e){

                System.out.println("Error");

            }
        }

    }
}
