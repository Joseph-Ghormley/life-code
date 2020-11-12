package pkg;

public class TreeNode<T>
{

    private T datum;;
    private TreeNode<T> leftChild, rightChild;
    /**
     * Create a new TreeNode with left and right child
     * set to null and data set to the dataNode
     */
    public TreeNode(T dataNode)
    {
        datum =dataNode;
        leftChild=null;
        rightChild=null;
    }

    /**
     * used for making deep copies
     * Parameters:
     * node - node to make copy of
     * @param node
     */
    public TreeNode(TreeNode<T> node)
    {
        //leftChild= new TreeNode<>(node.leftChild);
       // rightChild= new TreeNode<>(node.rightChild);
        //datum= node.datum;
        var temp= new TreeNode[1];
        temp[0]= node;
        var clonedTemp = temp.clone();
        datum =(T) clonedTemp[0].datum;
        leftChild=(TreeNode<T>) clonedTemp[0].leftChild;
        rightChild=(TreeNode<T>) clonedTemp[0].rightChild;

    }
    /*
    Return the data within this TreeNode
    Returns:the data within the TreeNode
     */
    public T getData()
    {
     return datum;
    }

    public void setDatum(T datum)
    {
        this.datum = datum;
    }

    public TreeNode<T> getLeftChild()
    {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild)
    {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild()
    {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild)
    {
        this.rightChild = rightChild;
    }
}
