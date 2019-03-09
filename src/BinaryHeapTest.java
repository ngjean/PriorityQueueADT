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
		
		assertEquals(testHeap.getHeap().length,3);
		assertEquals(testHeap.getSize(),0);
		
		testHeap.insert("whatever3",3);
		testHeap.insert("whatever1",1);
		testHeap.insert("whatever6",6);
		
		//verifies that the size of inner array has been doubled
		//given that capacity (in constructor argument) has been exceeded
		
		assertEquals(testHeap.getHeap().length,6);
		
		testHeap.insert("whatever4",4);
		testHeap.insert("whatever33",3);
		
		assertNull(testHeap.getHeap()[0]);
		assertEquals(testHeap.getHeap()[1].getData(),"whatever1");
		assertEquals(testHeap.getHeap()[2].getData(),"whatever3");
		assertEquals(testHeap.getHeap()[3].getData(),"whatever6");
		assertEquals(testHeap.getHeap()[4].getData(),"whatever4");
		assertEquals(testHeap.getHeap()[5].getData(),"whatever33");
		assertEquals(testHeap.getSize(),5);
	}
	@Test
	public void removeTest() {
		
		testHeap.insert("whatever3",3);
		testHeap.insert("whatever1",1);
		testHeap.insert("whatever6",6);
		testHeap.insert("whatever4",4);
		testHeap.insert("whatever33",3);
		
		assertEquals(testHeap.remove(),"whatever1");
		assertEquals(testHeap.getHeap()[1].getData(),"whatever3");
		assertNull(testHeap.getHeap()[5]);
		assertEquals(testHeap.getSize(),4);
		
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
	
	@Test
	public void isSortedTest() {
		
		testHeap.insert("whatever3",3);
		testHeap.insert("whatever1",1);
		testHeap.insert("whatever6",6);
		testHeap.insert("whatever4",4);
		testHeap.insert("whatever33",3);
		
		//We expect that these elements should be sorted from our previous insertTest()
		assertEquals(testHeap.isSorted(), true);
		
		testHeap.remove();
		testHeap.remove();
		
		assertEquals(testHeap.isSorted(), true);
	}
	@Test
	public void isEmptyTest() {
		
		assertEquals(testHeap.isEmpty(), true);
		testHeap.insert("whatever3",3);
		assertEquals(testHeap.isEmpty(), false);
		testHeap.remove();
		assertEquals(testHeap.isEmpty(), true);
	}
	@Test
	public void isFullTest() {
		assertEquals(testHeap.isFull(), false);
		testHeap.insert("whatever3",3);
		assertEquals(testHeap.isFull(), false);
		testHeap.insert("whatever1",1);
		assertEquals(testHeap.isFull(), true);
	}

}
