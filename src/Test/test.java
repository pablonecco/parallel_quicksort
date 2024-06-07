package Test;

import java.util.Random;

import Funciones.ParallelQuickSort;
import Funciones.QuickSort;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int tam = 1000000; // tamaño del array

		// secuencial

		// se crea e imprime array con numeros aleatorios
		int[] arr = new int[tam];
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt();
		}

		// tiempo inicial
		long startTime = System.nanoTime();
		// se ejecuta el ordenamiento secuencial
		QuickSort.quickSort(arr, 0, arr.length - 1);
		// tiempo final
		long endTime = System.nanoTime();

		// imprimir resultado
		long duration = (endTime - startTime) / 1_000_000; // convertir a milisegundos
		System.out.println("Tiempo de ejecución (secuencial): " + duration + " ms");

		///////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////

		// concurrente

		// se crea e imprime array con numeros aleatorios
		int[] arr2 = new int[tam];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = rand.nextInt();
		}

		// tiempo inicial
		long startTimeMulti = System.nanoTime();
		// se ejecuta el ordenamiento concurrente
		ParallelQuickSort.parallelQuickSort(arr2);
		// tiempo final
		long endTimeMulti = System.nanoTime();

		// imprimir resultado
		long durationMulti = (endTimeMulti - startTimeMulti) / 1_000_000; // convertir a milisegundos
		System.out.println("Tiempo de ejecución (concurrente): " + durationMulti + " ms");

	}

}
