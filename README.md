# Sorting & Selection Algorithms Benchmark

This project implements and benchmarks several divide-and-conquer algorithms in Java, including **MergeSort**, **QuickSort**, **Deterministic Select**, and a **Closest Pair** algorithm. Benchmarks are done using **JMH**, and metrics such as recursion depth, allocations, and comparisons are tracked.

---

## Architecture Notes

- **Metrics tracking**: Each algorithm updates a `Metrics` object that counts:
  - `comparisons` – number of element comparisons,
  - `allocations` – new array/object allocations,
  - `maxDepth` – maximum recursion depth.
- Recursion depth is tracked by incrementing a counter at the start of recursion and decrementing it at exit, updating `maxDepth` if current depth exceeds previous maximum.
- Allocations are counted each time a new array (or object) is created.
- These metrics are written to CSV files for analysis alongside runtime measurements.

---

## Recurrence Analysis

### MergeSort
- **Method**: Standard divide-and-conquer with equal splits.
- **Recurrence**: \(T(n) = 2T(n/2) + Θ(n)\)
- **Analysis**: Master Theorem → \(T(n) = Θ(n \log n)\)
- **Intuition**: Recursion depth grows as \(log n\), linear merging dominates each level.

### QuickSort
- **Method**: Recursive partitioning with smaller-first recursion and randomized pivot.
- **Recurrence**: Average case \(T(n) = T(n/2) + T(n/2) + Θ(n)\), worst case unbalanced.
- **Analysis**: Master Theorem → \(Θ(n \log n)\) average, \(Θ(n^2)\) worst.
- **Intuition**: Randomized pivot ensures splits are likely balanced, minimizing recursion depth.

### Deterministic Select (Median-of-Medians)
- **Method**: Divide input into groups of 5, recursively select pivot.
- **Recurrence**: \(T(n) ≤ T(n/5) + T(7n/10) + Θ(n)\)
- **Analysis**: Akra–Bazzi → \(T(n) = Θ(n)\)
- **Intuition**: Pivot guarantees at least 30% of elements are discarded each step, giving linear time.

### Closest Pair
- **Method**: Divide-and-conquer on 2D points.
- **Recurrence**: \(T(n) = 2T(n/2) + Θ(n)\)
- **Analysis**: Master Theorem → \(Θ(n \log n)\)
- **Intuition**: Recursive division halves the points, merging the closest pairs takes linear time.

---

## Benchmark Results

| Algorithm  | n     | time (ns) | comparisons | allocations | maxDepth |
|------------|-------|-----------|------------|-------------|----------|
| MergeSort  | 5     | 6,600     | 9          | 1           | 1        |
| MergeSort  | 100   | 50,300    | 648        | 1           | 4        |
| QuickSort  | 100   | 512,000   | 722        | 0           | 14       |
| Deterministic Select | 50 | 2,577,400 | 128     | 48         | 6        |

---

## Plots

*(You can generate plots from CSV using Python, Excel, or another tool.)*

- **Time vs n**: MergeSort and QuickSort grow roughly \(n \log n\), Select grows linearly.  
- **Max depth vs n**: Depth tracks recursion; QuickSort shows higher max depth variability.  
- **Allocations vs n**: MergeSort and Select incur extra allocations; QuickSort mostly in-place.

**Discussion**: Constant factors, CPU cache effects, and garbage collection affect measured times. For small `n`, overhead dominates, but for larger `n`, theoretical growth dominates.

---

## Summary

- Measured performance **generally aligns** with theory.  
- MergeSort and QuickSort average behavior matches \(Θ(n \log n)\), Select matches linear time.  
- Small discrepancies are due to implementation overhead, memory allocation, and JVM optimizations.
