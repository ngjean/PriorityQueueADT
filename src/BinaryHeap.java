import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;

@invariant ({
	"$this.capacity>=2",
	"$this.size >=0 ",
	"$this.size < this.capacity",
	"$this.heap[0] == null"
	})

public class BinaryHeap<ElementType, KeyType extends Comparable<KeyType>>
implements PriorityQueue<ElementType, KeyType>{
	
	private Node<ElementType, KeyType>[] heap;
	private int size;
	private int capacity;
	
	@requires({"true"})
	@ensures({"$this.heap != null"})
	public BinaryHeap() {
		
		this(10);
	}
	
	@requires({"capacity >= 2"})
	@ensures({"$this.heap != null"})
	public BinaryHeap(int capacity) {
		
		this.capacity = capacity;
		heap = (Node<ElementType, KeyType>[]) new Node <?,?>[capacity];
		this.size = 0;
		
	}
	
	@requires({"true"})
	@ensures({"$this.result>=0"})
	public int getSize(){
		
		return this.size;
	}
	
	@requires({"true"})
	@ensures({"$this.result>=2"})
	public int getCapacity(){
		
		return this.capacity;
	}
	@requires({"true"})
	@ensures({"$this.result != null"})
	public Node<ElementType, KeyType>[] getHeap() {
		
		return this.heap;
	}
	
	@requires({
		"key != null",
		"$this.isFull() == false"
		})
	@ensures({
		"$this.size == $old($this.size) + 1",
		//"$this.contains(new Node<>(el,key)) == true "
		})
	public void insert(ElementType el,KeyType key){
		
		Node<ElementType, KeyType> newNode = new Node<>(el,key);
		
		if(this.size==0) {
			heap[1] = newNode;
			++size;
			return;
		}
		if(this.isFull()) {
			doubleSizeArray();
		}
		
		int insertPosition = ++size;
		for(; newNode.key.compareTo(heap[insertPosition/2].key)<0;insertPosition = insertPosition/2 ) {
			heap[insertPosition]=heap[insertPosition/2];
		}
		heap[insertPosition]= newNode;
		
		return;
	}
	
//	private boolean contains(Node<ElementType, KeyType> InputNode) {
//		for(Node<ElementType, KeyType> node: heap) {
//			if(node != null) {
//				if(node.key.compareTo(InputNode.key) == 0 && node.data.compareTo(InputNode.data)==0 ) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	public boolean isEmpty() {
		return this.size==0;
	}
	
	public boolean isFull() {
		return this.size==this.capacity-1;
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