
import java.util.concurrent.BlockingQueue;

 
public class PrimeRandomizer implements Runnable  {
    
    private BlockingQueue<ModelClass> dataQueue;
    private  BlockingQueue<Model2> resultQueue; 
   
    public PrimeRandomizer(BlockingQueue<ModelClass> dataQueue,BlockingQueue<Model2> resultQueue)
   {
      this.dataQueue = dataQueue;
      this.resultQueue=resultQueue;
    }
    public void run() 
    {
		readFromProducer();
	}
    public synchronized void readFromProducer() 
    {  
    	while(true)
    	{
    	while ((dataQueue.size()>0))
             {
            	try
            	{
             int value = dataQueue.take().getRandNo();
             int count=0;
             Model2 model2 = new Model2();
             for(int j=2;j<value;j++)
             {
            	
              count++;
              if(value%j == 0)
                 {
            	  model2.setFlag(false);
            	  count=0;	
                  break;
                 }
                }
              if(count!=0) 
             {
              
              model2.setFlag(true);
              }
           model2.setRandNo(value);
           resultQueue.put(model2);
           // System.out.println(model2.getFlag()+"   in consumer    "+ model2.getRandNo());
            	}
         catch (InterruptedException e) {
          e.printStackTrace();
         }
            	
    }
    }
    	
}
}
   class Model2
    {
     private int randNo;
        private boolean flag;

      public int getRandNo() {
       return randNo;
      }
      public void setRandNo(int randNo) {
       this.randNo = randNo;
      }
      public boolean getFlag() {
       return flag;
      }
      public void setFlag(boolean flag) {
       this.flag = flag;
      }
      } 
