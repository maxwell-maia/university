
/**
 * Write a description of class receiver here.
 *
 * @author Maxwell Maia
 * 
 */
public class receiver
{
    //Personal.
    private long XB; //Private.
    private long YB; //Calculated here. Will be sent over public domain.
    
    //From other user.
    private long YA; //Calculated in sender. Set when given this value at the first communication with the sender.
    
    //DB parameters agreed. Available in public domain. Make them available here too.
    private long a;
    private long p;
    
    //The ultimate goal. We want this to be a shared secret number.
    private long K;

    /**
     * Constructor for objects of class receiver
     */
    public receiver(long a, long p, long YA)
    {
        XB = generatePrivateKey();
        System.out.println("XB privatekeyReceiver = "+XB);
        
        this.a = a;
        
        this.p = p;
        
        this.YA = YA;
    }
    
    public long getYB()
    {
        return YB;
    }
    
    public void calculateK()
    {
        //[YA^XB mod p]
        K = power(YA, XB, p);
        System.out.println("In receiver:\nK = " + K);
    }
    
    public void calculateYB()
    {
        
        YB = power(a, XB, p); //[a^XB mod p]
        System.out.println("YB = "+YB);
        
    }
    
    public int randomInt(int min, int max)
    {
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    
    
    public int generatePrivateKey()
    {
        int privateKey = randomInt(0, 9999);
        return privateKey;
    }
    
    public long power(long a, long X, long p)
    {
        long result = 1;
        
        //In case a is more than or equal to p. 
        a = a % p;
        
        while (X > 0)
        {
            //Multiply a with the result mod p, if X is odd.
            if (X % 2 == 1)
            {
                result = (result * a) % p;
            }
            
            //Therefore, X is now even
            X = X >> 1; // y = y/2
            a = (a * a) % p;
        }
        return result;
    }
}
