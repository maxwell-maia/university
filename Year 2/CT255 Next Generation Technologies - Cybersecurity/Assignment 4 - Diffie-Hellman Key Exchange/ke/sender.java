
/**
 * Write a description of class sender here.
 *
 * @author Maxwell Maia
 * 
 */
public class sender
{
    //Personal.
    private long XA; //Calculated here. Private.
    private long YA; //Calculated here. Will be sent over public domain.
    
    //From other user.
    private long YB; //Calculated in receiver. Set when the receiver replies with this value.
    
    //DB parameters agreed. Available in public domain. Make them available here too.
    private long a;
    private long p;
    
    //The ultimate goal. We want this to be a shared secret number.
    private long K;

    /**
     * Constructor for objects of class sender
     */
    public sender(long a, long p)
    {
        XA = generatePrivateKey();
        System.out.println("XA privatekeySender = "+XA);
        
        this.a = a;
        this.p = p;
    }
    
    public void calculateK()
    {
        //[YB^XA mod p]
        K = power(YB, XA, p);
        System.out.println("In sender:\nK = " + K);
    }
    
    public void setYB(long YB)
    {
        this.YB = YB;
    }
    
    public void calculateYA()
    {
        
        YA = power(a, XA, p); //[a^XA mod p]
        System.out.println("YA = "+YA);
        
    }
    
    public long getYA()
    {
        return YA;
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
