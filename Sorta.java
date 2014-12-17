import java.util.Random;


public class Sorta {

  public static void main(String[] args) {
    Array test = new Array(20);
    Array test2 = new Array(20);
    Array test3 = new Array(20);
    Array test4 = new Array(20);
    test2.array = java.util.Arrays.copyOf(test.array, 20);
    test3.array = java.util.Arrays.copyOf(test.array, 20);
    test4.array = java.util.Arrays.copyOf(test.array, 20);

    System.out.println("Insertion Sort");
    test.iSort();
    System.out.println("\nMerge Sort");
    test2.mSort();
    System.out.println("\nHeap Sort");
    test3.hSort();
    /*
    System.out.println("\nQuick Sort");
    test4.qSort();
    */
  }

  static class Array {

    // Fields
    public int[] array;
    private int heapSize = 0;
    private final int upperBound = 21;

    // Constructor
    Array(int howMany) {
      array = new int[howMany];
      Random rand = new Random();

      for(int i = 0; i < this.array.length; i++) {
        array[i] = rand.nextInt(upperBound);
      }
    }

    // Methods
    public void show() {
      // Uses enhanced for loop.
      for(int element: array)
        System.out.print(element+" ");
      System.out.println("");
    }

    public void iSort() {
      int key = 0;
      int i = 0;
      for(int j = 1; j < this.array.length; j++) {
        this.show();
        key = array[j];
        i = j - 1;
        while(i >= 0 && array[i] > key) {
          array[i + 1] = array[i];
          i--;
        }
        array[i + 1] = key;
      }
      this.show();
    }

    public void mSort() {
      this.mergeSort(0, this.array.length);
    }

    // start is inclusive, end is exclusive.
    private void mergeSort(int start, int end) {
      if ((end - start) > 1) {
        int middle = (end - start) / 2;
        mergeSort(start, start + middle);
        mergeSort(start + middle, end);
        merge(start, start + middle, end);
      }
    }

    private void merge(int start, int middle, int end) {
      //System.out.println("Chiamata merge su "+start+" "+middle+" "+end);
      int[] left = new int[this.array.length];
      int[] right = new int[this.array.length];
      int i = 0;
      int j = 0;
      for(int x = start; x < this.array.length; x++) {
        if(x < middle)
          left[i] = array[x];
        else
          left[i]=2147483647;
        i++;
      }
      while(i < this.array.length) {
        left[i]=2147483647;
        i++;
      }
      for(int y = middle; y < this.array.length; y++) {
        if(y < end)
        right[j] = array[y];
        else
          right[j]=2147483647;
        j++;
      }
      while(j < this.array.length) {
        right[j]=2147483647;
        j++;
      }
      //left = java.util.Arrays.copyOfRange(this.array, start, middle);
      //right = java.util.Arrays.copyOfRange(this.array, middle, end);
      i = 0; //1100 0001 0000    SOQQUADRO
      j = 0;
      for(int k = start; k < end; k++) {
        //System.out.println("Left vale "+left[i]+" Right vale "+right[j]+"End vale "+end);
        if(left[i] <= right[j]) {
          this.array[k] = left[i];
          i++;
        }
        else {
          this.array[k] = right[j];
          j++;
        }
      }
      this.show();
    }

    public void hSort() {
      this.heapSort();
    }

    private void heapSort() {
      buildMaxHeap();
      int buffer = 0;
      for(int i = (this.array.length - 1); i >= 1; i--) {
        this.show();
        buffer = this.array[0];
        this.array[0] = this.array[i];
        this.array[i] = buffer;
        this.heapSize--;
        maxHeapify(0);
      }

    }

    private int parent(int index) {
      // Gestisce il ritorno all'indice 0 del vettore.
      if(index == 1 || index == 2)
        return 0;
      else
        return index / 2;
    }

    private int left(int index) {
      index++;
      index = index * 2;
      index--;
      return index;
    }

    private int right(int index) {
      index++;
      index = index * 2;
      return index;
    }

    private void maxHeapify(int index) {
      int left = left(index);
      int right = right(index);
      int max = 0;
      int buffer = 0;
      // Controlla se il figlio di sinistra è maggiore della radice.
      if(left < this.heapSize && this.array[left] > this.array[index])
        max = left;
      else
        max = index;
      // Controlla se il figlio di destra è maggiore del massimo.
      if(right < this.heapSize && this.array[right] > this.array[max])
        max = right;
      if(max != index) {
        buffer = this.array[index];
        this.array[index] = this.array[max];
        this.array[max] = buffer;
        // Ristabilisce la heapità.
        maxHeapify(max);
      }
    }

    private void buildMaxHeap() {
      // Sia heapSize che length sono cardinali (esclusivi).
      this.heapSize = this.array.length;
      for(int i = (this.heapSize / 2) - 1; i >= 0; i--) {
        maxHeapify(i);
      }
    }

    public void qSort() {
      this.quickSort(0, this.array.length);
    }

    private void quickSort(int start, int end) {
      if(start < end) {
        System.out.println("Avvio quickSort con start="+start+" e end="+end);
        this.show();
        int middle = partition(start, end);
        quickSort(start, middle);
        quickSort(middle, end);
      }
    }

    private int partition(int start, int end) {
      int pivot = this.array[end - 1];
      int pointer = start;
      int buffer = 0;
      for(int i = start + 1; i < end - 1; i++) {
        if(this.array[i] <= pivot) {
          buffer = this.array[pointer];
          this.array[pointer] = this.array[i];
          this.array[i] = buffer;
          pointer++;
        }
      }
      buffer = this.array[pointer];
      this.array[pointer] = this.array[end - 1];
      this.array[end - 1] = buffer;
      return pointer;
    }

  }
}
