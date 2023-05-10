public class Node
{
    private int g = 0;

    private int h = 0;

    private int f = 0;

    private Node parent = null;

    private boolean open = false;

    private boolean closed = false;

    private int x = 0;

    private int y = 0;

    public Node(int x, int y, int g, int h, Node parent)
    {
        this.x = x;
        this.y = y;
        this.g = g + 1;
        this.h = h;
        this.parent = parent;
        this.updateF();
    }

    public void updateF()
    {
        f = g + h;
    }

    //Getters
    public boolean isOpen()
    {
        return open;
    }

    public boolean isClosed()
    {
        return closed;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getF()
    {
        return f;
    }

    public int getG()
    {
        return g;
    }

    public Node getParent()
    {
        return parent;
    }


    //Setters
    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }
    public void setG(int g)
    {
        this.g = g;
    }

    public void setH(int h)
    {
        this.h = h;
    }

    public void setF(int f)
    {
        this.f = f;
    }
}
