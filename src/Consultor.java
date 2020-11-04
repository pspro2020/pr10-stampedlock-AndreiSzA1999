public class Consultor implements Runnable {
    protected Almacen almacen;

    protected int producto;


    public Consultor(Almacen almacen,int producto){

        this.almacen=almacen;
        this.producto=producto;


    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){

            try {

                almacen.getProductos(producto);
                Thread.sleep(1000);
            }catch (InterruptedException e){

                System.out.println("Error");

            }
        }
    }
}
