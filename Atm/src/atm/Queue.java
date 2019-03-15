package atm;

public class Queue {
    
    private static int size, back, front, capacity; 
    private static String queue[]; 
  
    Queue(int c) 
    { 
        size = 0;
        back = -1;
        front = 0;
        capacity = c; 
        queue = new String[capacity]; 
    } 
    
    public static boolean isEmpty() {
        return (size == 0);
    }
  
    public static void push (String data) 
    { 
        if (isEmpty()) { 
            back = front;
            size++;
            queue[back] = data;
        } 
        else if(size == capacity) {
            pop();
            back ++;
            queue[back] = data;
        }
        else { 
            back++; 
            size++;
            queue[back] = data;
        } 
    } 
    
    public static void pop() {
        queue[front]=queue[front+1];
        for(int i=1; i<capacity-1; i++){
            queue[i]=queue[i+1];
        }
        back--;
    }
    
   public static String getPrevious(int pos) {
       if(isEmpty() || back==0 || pos==back+1)
           return "Null";
       else
           return queue[back-pos]; 
   }
   
   public static String getNext(int pos) {
       if(isEmpty() || pos==0)
           return "Null";
       else
           return queue[back-pos+1];
   }
}
