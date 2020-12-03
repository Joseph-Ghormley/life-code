package pkg;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation()
	   {
	      class AnimationRunnable implements Runnable
	      {
	         public void run()
	         {
	            try
	            {
	               for(int i=0;i<10;i++)
	               {
	               		final int DISTANCE_MOVED = 10;
	               		final int LOWER_BOUND = 400;
	               		final int RIGHT_BOUND = 300;
	            	   direction = carQueue.deleteQueue();
	            	   switch (direction) {
						   case 0:
							   if (y > 0 && y <= DISTANCE_MOVED)
								   y = Math.abs(y - DISTANCE_MOVED);
							   else if (y == 0)
								   y += DISTANCE_MOVED;
							   else
								   y -= DISTANCE_MOVED;
							   break;
						   case 1:
							   if (y < LOWER_BOUND && y >= LOWER_BOUND - DISTANCE_MOVED)
								   y = LOWER_BOUND - DISTANCE_MOVED + (LOWER_BOUND - y);
							   else if (y == LOWER_BOUND)
								   y -= DISTANCE_MOVED;
							   else
								   y += DISTANCE_MOVED;
							   break;
						   case 2:
							   if (x < RIGHT_BOUND && x >= RIGHT_BOUND - DISTANCE_MOVED)
								   x = RIGHT_BOUND - DISTANCE_MOVED + (RIGHT_BOUND - x);
							   else if (x == RIGHT_BOUND)
								   x -= DISTANCE_MOVED;
							   else
								   x += DISTANCE_MOVED;
							   break;
						   case 3:
							   if (x > 0 && x <= DISTANCE_MOVED)
								   x = Math.abs(x - DISTANCE_MOVED);
							   else if (x == 0)
								   x += DISTANCE_MOVED;
							   else
								   x -= DISTANCE_MOVED;
							   break;
					   }

	            	   repaint();
	            	   Thread.sleep(delay*1000);
					   System.out.println(Thread.currentThread() + "x=" + x + "  y=" + y);
	               }
					System.out.println("finished");
	            }
	            catch (InterruptedException exception)
	            {
	            	
	            }
	            finally
	            {
	            	
	            }
	         }
	      }
	      
	      Runnable r = new AnimationRunnable();
	      Thread t = new Thread(r);
	      t.start();
	   }
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}
