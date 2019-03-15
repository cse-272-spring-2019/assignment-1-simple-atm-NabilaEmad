package atm;

public interface AccountGui {
    
    public String getCurrentBalance();
    
    public void deposit(String Amount);
    
    public boolean withdraw(String Amount);
    
    public String previous();
    
    public String next();
    
}
