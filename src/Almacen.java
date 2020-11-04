import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class Almacen {

    protected ArrayList<Integer> productos = new ArrayList<>();

    protected StampedLock stampedLock = new StampedLock();


    public void getProductos(int producto) {



      long stamp = stampedLock.tryOptimisticRead();

      consultar(producto,stamp);




    }




    public void consultar(int producto, long stamp){

        int cont =0;

        for(Integer produ : productos) {

            if (produ == producto) {
                cont++;
            }
        }
        if(!stampedLock.validate(stamp)) {
           stamp=stampedLock.readLock();

           try{
           cont=0;
            for (Integer produ : productos) {

                if (produ == producto) {
                    cont++;
                }
            }
        }
           finally {
               stampedLock.unlockRead(stamp);
           }
        }

            System.out.printf("Hay %d productos del tipo %d \n",cont,producto);



    }


    public void añadirProductos() {

        long stamp = stampedLock.writeLock();
        try {
            int añadido = ThreadLocalRandom.current().nextInt(1,4);

            productos.add(añadido);

            System.out.printf("Se ha añadido el producto de tipo %d \n",añadido);

        } finally {
            stampedLock.unlock(stamp);
        }
    }


}
