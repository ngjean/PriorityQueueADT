import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;
import java.util.HashMap;
/*
 * Array based implementation of binary heap. First element at index 1.
 * Respects FIFO scheme for nodes with identical keys.
 * 
 */


//@invariant ({
//	"$this.capacity>=1",
//	"$this.size >=0 ",
//	"$this.size <= $this.capacity",
//	"$this.heap[0] == null"
//	})

public class BinaryHeap<ElementType, KeyType extends Comparable<KeyType>>
implements PriorityQueue<ElementType, KeyType>{
	
	private HashMap<KeyType, Integer> hashMap;
	private Node<ElementType, KeyType>[] heap;
	private int size;
	private int capacity;
	
//	@requires({"capacity >= 1"})
//	@ensures({"$this.heap != null"})
	public BinaryHeap(int capacity) {
		
		this.hashMap = new HashMap<KeyType, Integer>();
		this.capacity = capacity;
		this.heap = (Node<ElementType, KeyType>[]) new Node <?,?>[10];
		this.size = 0;
		
	}
	
//	@requires({"true"})
	//@ensures({"$this.result>=0"})
	public int getSize(){
		
		return this.size;
	}
	
//	@requires({"true"})
	//@ensures({"$this.result>=2"})
	public int getCapacity(){
		
		return this.capacity;
	}
//	@requires({"true"})
//	@ensures({"$this.result != null"})
	public Node<ElementType, KeyType>[] getHeap() {
		
		return this.heap;
	}
	
//	@requires({
//		"key != null",
//		"$this.isFull() == false",
//		"$this.isSorted() == true"
//		})
//	@ensures({
//		"$this.size == $old($this.size) + 1",
//		"$this.heap[size] != null ",
//		"$this.isSorted() == true"
//		})
	public void insert(ElementType el,KeyType key){
		
		Node<ElementType, KeyType> newNode = new Node(el,key);
		Integer hashValue = hashMap.get(key);
		if( hashValue == null) {
			hashMap.put(key, 0);
			newNode.copyID = 0;
		}
		else {
			hashMap.put(key, hashValue +1);
			newNode.setCopyID(hashMap.get(key));
		}
		
		if(this.size==0) {
			heap[1] = newNode;
			++size;
			return;
		}
		
		if(this.size == heap.length-1) {
			
			doubleSizeArray();
		}
		int insertPosition = ++size;
		while(newNode.key.compareTo(heap[insertPosition/2].key)<0) {
			heap[insertPosition] = heap[insertPosition/2];
			insertPosition = insertPosition/2;
			if(insertPosition==1){
				break;
			}
		}
		
		heap[insertPosition]= newNode;
		
		return;
	}
	
//	@requires({"true"})
//	@ensures({"$this.result == true"})
	public boolean isSorted(){
		for(int i = 1; i< heap.length;i++){
			
			if(heap[i] == null) {
				break;
			}
			else {
				if((i*2)<heap.length && heap[i*2] != null
						&& heap[i].key.compareTo(heap[i*2].key)>0) {
					return false;
				}
				if((i*2)+1<heap.length
						&& heap[(i*2)+1] != null && heap[i].key.compareTo(heap[(i*2)+1].key)>0) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public boolean isEmpty() {
		return this.size==0;
	}
	
	public boolean isFull() {
		return this.size==this.capacity;
	}
//	@requires({"$this.isFull() == true"})
//	@ensures({"$this.heap.length == $old(this.heap.length)*2"})
	private void doubleSizeArray(){
		
		Node<ElementType, KeyType>[] newArray = (Node<ElementType, KeyType>[]) new Node <?,?>[this.heap.length * 2];
		for(int i = 0;i < this.heap.length;i++) {
			newArray[i] = this.heap[i];
		}
		this.heap = newArray;
		
		return;
	}
//	@requires({
//		"$this.isEmpty() == false",
//		"$this.isSorted() == true"
//		})
//	@ensures({
//		"$this.size == $old($this.size) - 1",
//		"$this.heap[size] == null ",
//		"$this.isSorted() == true"
//		})
	public ElementType remove() {
		

		KeyType rootKey = heap[1].key;
		int numberCopiesRoot = hashMap.get(rootKey);
		
		if(numberCopiesRoot == 0) {
			hashMap.remove(rootKey);
		}
		else {
			
			int copiesFound = 0;
			for(int i=2;i<heap.length;i++) {
				if(heap[i].key.compareTo(rootKey)== 0) {
					if(heap[i].copyID == 0) {
						Node<ElementType, KeyType>temp = heap[i];
						heap[i] = heap[1];
						heap[1] = temp;
						temp = null;
					}
					copiesFound++;
					heap[i].copyID --;
				}
				if(copiesFound == numberCopiesRoot)
					break;
			}
			this.hashMap.put(rootKey, numberCopiesRoot - 1);
		}
		
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
			
			if(heap[leftChildIndex].key.compareTo(heap[rightChildIndex].key)<= 0){
				
				minIndex = leftChildIndex;
				
			}
			else {
				
				minIndex = rightChildIndex;
			}
		}
		if(heap[n].key.compareTo(heap[minIndex].key) >= 0) {
			
			tempData = heap[n];
			heap[n] = heap[minIndex];
			heap[minIndex] = tempData;
			tempData = null;
			bubbleDown(minIndex);
		}
	}
	//Should there be post conditions here?
//	@requires({"$this.isEmpty() == false"})
	public  ElementType min() {
		
		return heap[1].data;
	}
	
//	@invariant({
//		"$this.key != null"
//	})
	public static class Node<Data,Key>{
		
		private Data data;
		private Key key;
		private int copyID;
		
		public Node(Data data,Key key) {
			this.data = data;
			this.key = key;
			this.copyID = 0;
			
		}
		
		public int getCopyID(){
			return this.copyID;
		}
		public void setCopyID(int newCopyID){
			this.copyID = newCopyID;
		}
		
		public Data getData(){
			return this.data;
		}
		public void setData(Data newData){
			this.data = newData;
		}
		public Key getKey(){
			return this.key;
		}
		public void setKey(Data newKey){
			this.data = newKey;
		}
		public boolean equals(Node<Data,Key> otherNode) {
			return this.key.equals(otherNode.key) && this.data.equals(otherNode.data);
		}
	}
}