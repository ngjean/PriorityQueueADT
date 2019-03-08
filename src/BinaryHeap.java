
public class BinaryHeap<ElementType, KeyType extends Comparable<KeyType>> implements PriorityQueue<ElementType, KeyType>{
	
	Node<ElementType, KeyType>[] heap;
	int size;
	int capacity;
	
	//precondition capacity >=2, skip index 0
	public BinaryHeap() {
		
		this(2);
	}
	public BinaryHeap(int capacity) {
		
		this.capacity = capacity;
		heap = (Node<ElementType, KeyType>[]) new Node <?,?>[capacity];
		this.size = 0;
		
	}
	
	public int getSize(){
		
		return this.size;
	}
	
	public int getCapacity(){
		
		return this.capacity;
	}
	
	
	public void insert(ElementType el,KeyType key){
		
		Node<ElementType, KeyType> newNode = new Node<>(el,key);
		
		if(this.size==0) {
			heap[1] = newNode;
			++size;
			return;
		}
		if(this.size==this.capacity-1) {
			doubleSizeArray();
		}
		
		int insertPosition = ++size;
		for(; newNode.key.compareTo(heap[insertPosition/2].key)<0;insertPosition = insertPosition/2 ) {
			heap[insertPosition]=heap[insertPosition/2];
		}
		heap[insertPosition]= newNode;
		
		return;
	}
	
	private void doubleSizeArray(){
		
		Node<ElementType, KeyType>[] newArray = (Node<ElementType, KeyType>[]) new Node <?,?>[capacity * 2];
		
		for(int i = 0;i<= heap.length;i++) {
			newArray[0] = this.heap[0];
		}
		this.heap = newArray;
		
		return;
	}
	
	public ElementType remove() {
		
		ElementType dataRoot = heap[1].data;
		heap[1] = heap[this.size];
		heap[this.size] = null;
		this.size --;
		
		if(this.size > 0) {
			bubbleDown(1);
		}
		return dataRoot;
	}
		
	private void bubbleDown(int n) {
		
		int leftChildIndex = n*2, rightChildIndex = (n*2)+1, minIndex;
		Node<ElementType, KeyType> tempData;
		
		if(rightChildIndex > this.size) {
			
			if(leftChildIndex > this.size) {
				return;
			}
			else {
				minIndex = leftChildIndex;
			}
			
		}
		else {
			
			if(heap[leftChildIndex].key.compareTo(heap[rightChildIndex].key)< 1){
				
				minIndex = leftChildIndex;
				
			}
			else {
				
				minIndex = rightChildIndex;
			}
		}
		if(heap[n].key.compareTo(heap[minIndex].key) > 1) {
			
			tempData = heap[n];
			heap[n] = heap[minIndex];
			heap[minIndex] = tempData;
			tempData = null;
			bubbleDown(minIndex);
		}
	}
	public  ElementType min() {
		
		return heap[1].data;
	}
	
	
	private static class Node<Data,Key>{
		
		private Data data;
		private Key key;
		
		public Node(Data data,Key key) {
			this.data = data;
			this.key = key;
		}
	}
	
}