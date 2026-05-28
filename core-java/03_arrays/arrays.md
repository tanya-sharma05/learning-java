# Arrays in Java

## Basics
```java
int[] arr = new int[3]; // reference on Stack → data on Heap
arr[0] = 10; // index starts at 0
arr.length // size of array
int[] arr = {4, 5, 6}; // shorthand init
```
Accessing out-of-bounds → `ArrayIndexOutOfBoundsException`

## Memory Layout
```
arr (stack) → Base Address (heap)
arr[i] = Base + (DataType_Size × i) // O(1) random access
```
Each `int` = 4 bytes. So `arr[3]` at base 100 = address 112.

## Multi-dimensional Arrays
```java
int[][] marks = new int[3][3]; // 3 rows × 3 cols
marks[0][1] = 50;

// Jagged — rows with different lengths
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[4];
```

## 2D Memory Internals
`arr` (stack) → row-reference array (heap) → each row is a separate array on heap.

## Functions
```java
static int sum(int a, int b) { 
    return a + b; 
} // define

int result = sum(4, 5); // call
```

## Recursion
A function that calls itself. Must have a **base case** to stop.
```java
void printNum(int n) {
    if (n == 0) return;   // base case
    
    printNum(n - 1);      // recursive call
    System.out.println(n);
}
```
