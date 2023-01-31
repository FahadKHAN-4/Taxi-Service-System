package taxi.algorithm;

public class DisjointSet {
  private int[] arr;
  private int size;

  public DisjointSet(int size) {
    arr = new int[size];
    this.size = size;
    for (int i = 0; i < size; i++)
      arr[i] = i;
  }

  public int getSize() {
    return size;
  }

  public int find(int node) {
    return arr[node];
  }

  public boolean unite(int node1, int node2) {
    if (arr[node1] == arr[node2])
      return false;
    int dest = arr[node2];
    for (int i = 0; i < size; i++)
      if (arr[i] == dest)
        arr[i] = arr[node1];
    return true;
  }

  public boolean isForest() {
    for (int i = 0; i < size - 1; i++) {
      if (arr[i] != arr[i + 1])
        return true;
    }
    return false;
  }
}
