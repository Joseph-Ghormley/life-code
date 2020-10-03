
public class main 
{

	public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException
	{
	NotationQueue<Integer> x  = new NotationQueue<>(10);
	for(int i = 0;i < 10; i++ )
		{
			x.enqueue(i+1);
		}
	System.out.println(x.toString());
	
	for(int i = 0;i < 10; i++ )
	{
			x.dequeue();
	}
	System.out.println(x.size());
	for(int i = 0;i < 10; i++ )
	{
		x.enqueue(i+100);
	}
	
	System.out.println(x.toString());

}
	
}
