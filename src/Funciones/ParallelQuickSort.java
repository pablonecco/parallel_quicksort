package Funciones;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

@SuppressWarnings("serial")
public class ParallelQuickSort extends RecursiveAction {
    private int[] arr; //array que se va a ordenar
    private int low; //indice mínimo del arary
    private int high; //indice máximo del array
    
    //constructor
    public ParallelQuickSort(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = high;
    }
    
    //metodo que se ejecuta cada vez que la tarea va a ser invocada
    @Override
    protected void compute() {
        if (low < high) {
            int pi = partition(arr, low, high); //particiona el array y obtiene el indice que va a pivotar
            //se crean las tareas para los nuevos subarrays
            ParallelQuickSort leftTask = new ParallelQuickSort(arr, low, pi - 1);
            ParallelQuickSort rightTask = new ParallelQuickSort(arr, pi + 1, high);
            //ejecuta ambas tareas en paralelo
            invokeAll(leftTask, rightTask);
        }
    }
    
    //metodo para particionar el array
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    
    //metodo para iniciar el ordenamiento
    public static void parallelQuickSort(int[] arr) {
    	ParallelQuickSort mainTask = new ParallelQuickSort(arr, 0, arr.length - 1); //se crea la tarea principal
        ForkJoinPool pool = new ForkJoinPool(); //se crea el pool de hilos
        System.out.println("Cantidad de hilos del pool: " +pool.getParallelism()); //se imprime por pantalla la cantidad de hilos disponibles
        pool.invoke(mainTask); //se invoca la tarea principal
    }

}
