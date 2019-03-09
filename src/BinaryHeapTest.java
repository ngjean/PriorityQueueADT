import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class BinaryHeapTest {
	
	private BinaryHeap<String,Integer> testHeap;
	private BinaryHeap.Node<String,Integer>[] innerArray;

	@Before
	public void setUp() throws Exception {
		testHeap = new BinaryHeap(2);
		
	}

	@Test
	public void inserTtest() {
		
		testHeap.insert("whatever3",3);
		testHeap.insert("whatever1",1);
		testHeap.insert("whatever6",6);
		testHeap.insert("whatever4",4);
		testHeap.insert("whatever33",3);
		
		assertNull(testHeap.getHeap()[0]);
		assertEquals(testHeap.getHeap()[1].getData(),"whatever1");
		assertEquals(testHeap.getHeap()[2].getData(),"whatever3");
		assertEquals(testHeap.getHeap()[3].getData(),"whatever6");
		assertEquals(testHeap.getHeap()[4].getData(),"whatever4");
		assertEquals(testHeap.getHeap()[5].getData(),"whatever33");
	}
	@Test
	public void removetest() {
		
		testHeap.insert("whatever3",3);
		testHeap.insert("whatever1",1);
		testHeap.insert("whatever6",6);
		testHeap.insert("whatever4",4);
		testHeap.insert("whatever33",3);
		
		assertEquals(testHeap.remove(),"whatever1");
		assertEquals(testHeap.getHeap()[1].getData(),"whatever3");
		assertNull(testHeap.getHeap()[5]);
		
		assertEquals(testHeap.remove(),"whatever3");
		assertEquals(testHeap.getHeap()[1].getData(),"whatever33");
		assertNull(testHeap.getHeap()[4]);
		
		assertEquals(testHeap.remove(),"whatever33");
		assertEquals(testHeap.getHeap()[1].getData(),"whatever4");
		assertNull(testHeap.getHeap()[3]);
		
		assertEquals(testHeap.remove(),"whatever4");
		assertEquals(testHeap.getHeap()[1].getData(),"whatever6");
		assertNull(testHeap.getHeap()[2]);
		
		assertEquals(testHeap.remove(),"whatever6");
		assertNull(testHeap.getHeap()[1]);
		
		assertNull(testHeap.getHeap()[0]);
		
	}
	
	@Test
	public void minTest() {
		
		testHeap.insert("whatever3",3);
		assertEquals(testHeap.min(),"whatever3");
		testHeap.insert("whatever1",1);
		assertEquals(testHeap.min(),"whatever1");
		testHeap.insert("whatever6",6);
		assertEquals(testHeap.min(),"whatever1");
		
		testHeap.remove();
		assertEquals(testHeap.min(),"whatever3");
		testHeap.remove();
		assertEquals(testHeap.min(),"whatever6");
	}

}
