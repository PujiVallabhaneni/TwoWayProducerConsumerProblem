

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
 
 
public class Randomizer implements Runnable{
	
    private  BlockingQueue<ModelClass> dataQueue;
    private  BlockingQueue<Model2> resultQueue;
    ModelClass model = new ModelClass();
    Model2 m2 =new Model2();
    public Randomizer(BlockingQueue<ModelClass> dataQueue,BlockingQueue<Model2> resultQueue) {
        this.dataQueue = dataQueue;
        this.resultQueue=resultQueue;
    }
    
    @Override
	public void run() {
	writeToConsumer();
	try {
		Thread.sleep(10);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	readFromConsumer();
	}  
    public synchronized void writeToConsumer()
    {
       for (int i = 1; i <= 10; i++) 
          {
            try {
            	Thread.sleep(10);
                Random rnd = new Random(); 
                Integer randomNumber = rnd.nextInt(100);
                model.setRandNo(randomNumber);
                System.out.println("Randomizer - " + model.getRandNo() + " produced.");
                dataQueue.put(model);
                //Thread.sleep(10);
                 } 
                catch (Exception e) {
                System.out.println("Exception:" + e);
                   }
             }
       }
    public void readFromConsumer()
       {
    	
    	try {
        while(true)
      	   {
             while(resultQueue.size()!=-1)
        	//for (int i=0; i<resultQueue.size();i++)	
            {     
		         m2 = resultQueue.take();
		         System.out.println(m2.getRandNo() +" "+m2.getFlag());
		       //System.out.println(i);
		           } 
        	 }
    	}
              catch (InterruptedException e) 
              {
			  e.printStackTrace();
		      }
	         
    	   }
    
    public static void main(String[] args) {
    	System.out.println("Let's get started. Producer / Consumer Test Started.\n");
        BlockingQueue<ModelClass> dataQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<Model2> resultQueue = new ArrayBlockingQueue<>(10);
      
      Thread p1thread = new Thread(new Randomizer(dataQueue,resultQueue),"p1");
      p1thread.start();
 
      Thread c1thread = new Thread(new PrimeRandomizer(dataQueue,resultQueue),"c1");
      c1thread.start();
       
    }   
}

     class ModelClass {
    	 
		private int randNo;
    	public int getRandNo() {
    	 return randNo;
    	}
    	public void setRandNo(int randNo) {
    	 this.randNo = randNo;
    	}
    	}
    
     
    


		
