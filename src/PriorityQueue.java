package src;

public interface PriorityQueue<ElementType, KeyType extends Comparable<KeyType>> {
	
	public void insert(ElementType el,KeyType key);
	public ElementType remove();
	public ElementType min();
	
}
