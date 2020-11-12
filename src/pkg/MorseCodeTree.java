package pkg;
import java.util.ArrayList;

/*
This is a MorseCodeTree which is specifically used for the conversion of morse code to english
It relies on a root (reference to root of the tree) The root is set to null when the tree is empty.
The class uses an external generic TreeNode class which consists of a reference to the data and a reference to the left and right child.
The TreeNode is parameterized as a String, TreeNode This class uses a private member root (reference to a TreeNode) The constructor will call the buildTree method
 */

public class MorseCodeTree implements LinkedConverterTreeInterface<String>
{
    private TreeNode<String> root;

    //Constructor - calls the buildTree method
    public MorseCodeTree()
    {
        root= new TreeNode<String>("");
        buildTree();
    }

    /*
    Returns a reference to the root
     Specified by: getRoot in interface LinkedConverterTreeInterface<java.lang.String>
     Returns:reference to root
     */
    public TreeNode<String> getRoot() {
        return new TreeNode<>(root);
    }

    /*
    sets the root of the MorseCodeTree
      Specified by:setRoot in interface LinkedConverterTreeInterface<java.lang.String>
      Parameters:newNode - a copy of newNode will be the new root
     */
    public void setRoot(TreeNode<String> newNode)
    {
        root = new TreeNode<>(newNode);
    }

    /*
    sets the root of the MorseCodeTree
    Specified by:setRoot in interface LinkedConverterTreeInterface<java.lang.String>
    Parameters:newNode - a copy of newNode will be the new root
     */
    public LinkedConverterTreeInterface<String> insert(String code, String result)
    {
        addNode(root, code, result);
        return this;
    }

    /*
    This is a recursive method that adds element to the correct position in the tree based on the code. A '.' (dot)
    means traverse to the left. A "-" (dash) means traverse to the right. The code ".-"
    would be stored as the right child of the left child of the root Algorithm for the recursive method:
  1. if there is only one character
  a. if the character is '.' (dot) store to the left of the current root
  b. if the character is "-" (dash) store to the right of the current root
  c. return
  2. if there is more than one character
  a. if the first character is "." (dot) new root becomes the left child
  b. if the first character is "-" (dash) new root becomes the right child
  c. new code becomes all the remaining charcters in the code (beyond the first character)
  d. call addNode(new root, new code, letter)
  Specified by:
  addNode in interface LinkedConverterTreeInterface<java.lang.String>
  Parameters:
  root - the root of the tree for this particular recursive instance of addNode
  code - the code for this particular recursive instance of addNode
  letter - the data of the new TreeNode to be added
     */
    public void addNode(TreeNode<String> root, String code, String letter) {
        if (code.length() == 1)
        {
            if (code.equals(".")) {
                root.setLeftChild(new TreeNode<>(letter));
            } else if (code.equals("-"))
            {
                root.setRightChild(new TreeNode<>(letter));
            }
        }
        else
        {
            if (code.charAt(0) == '.')
            {
                addNode(root.getLeftChild(), code.substring(1), letter);
            }
            else if (code.charAt(0) == '-')
            {
                addNode(root.getRightChild(), code.substring(1), letter);
            }

        }
    }

    /*
    Fetch the data in the tree based on the code This method will call the recursive method fetchNode
     Specified by:fetch in interface LinkedConverterTreeInterface<java.lang.String>
     Parameters:code - the code that describes the traversals to retrieve the string (letter)
     Returns:the string (letter) that corresponds to the code
     */
    public String fetch(String code) {
        return fetchNode(root, code);
    }

    /*
    This is the recursive method that fetches the data of the TreeNode that corresponds with the code A '.' (dot)
    means traverse to the left. A "-" (dash) means traverse to the right.
    The code ".-" would fetch the data of the TreeNode stored as the right child of the left child of the root
    Specified by:fetchNode in interface LinkedConverterTreeInterface<java.lang.String>
    Parameters:root - the root of the tree for this particular recursive instance of addNode
    code - the code for this particular recursive instance of addNode
    Returns:the string (letter) corresponding to the code
     */
    public String fetchNode(TreeNode<String> root, String code)
    {
       String letter = "";
       if (code.length() == 1) {
           if (code.equals(".")) {
               letter = root.getLeftChild().getData();
           } else if (code.equals("-")) {
               letter = root.getRightChild().getData();
           }
       } else {
           if (!code.isEmpty()) {
               if (code.charAt(0) == '.') {
                   letter = fetchNode(root.getLeftChild(), code.substring(1));
               } else if (!code.isEmpty() && code.charAt(0) == '-') {
                   letter = fetchNode(root.getRightChild(), code.substring(1));
               }
           }
       }
       return letter;

    }

       /*
       This operation is not supported in the MorseCodeTree
        Specified by: delete in interface LinkedConverterTreeInterface<java.lang.String>
        Parameters: data - data of node to be deleted
        Returns: reference to the current tree
        Throws: java.lang.UnsupportedOperationException
        */
        public LinkedConverterTreeInterface<String> delete (String data) throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }

        /*
        This operation is not supported in the MorseCodeTree
        Specified by: update in interface LinkedConverterTreeInterface<java.lang.String>
        Returns: reference to the current tree
        Throws:java.lang.UnsupportedOperationException
         */
        public LinkedConverterTreeInterface<String> update () throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }

       /*
       This method builds the MorseCodeTree by inserting the nodes of the tree level by level based on the code.
       The root will have a value of "" (empty string) level one: insert(".", "e"); insert("-", "t");
       level two: insert("..", "i"); insert(".-", "a"); insert("-.", "n"); insert("--", "m"); etc.
       Look at the tree and the table of codes to letters in the assignment description.
        Specified by:buildTree in interface LinkedConverterTreeInterface<java.lang.String>
        */
        public void buildTree ()
        {
            //insert("", "");//level 0

            insert(".", "e");//leve 1
            insert("-", "t");

            insert("..", "i");//level 2
            insert(".-", "a");
            insert("-.", "n");
            insert("--", "m");

            insert("...", "s");//level 3
            insert("..-", "u");
            insert(".-.", "r");
            insert(".--", "w");
            insert("-..", "d");
            insert("-.-", "k");
            insert("--.", "g");
            insert("---", "o");

            insert("....", "h");//level4
            insert("...-", "v");
            insert("..-.", "f");
            insert(".-..", "l");
            insert(".--.", "p");
            insert(".---", "j");
            insert("-...", "b");
            insert("-..-", "x");
            insert("-.-.", "c");
            insert("-.--", "y");
            insert("--..", "z");
            insert("--.-", "q");

        }

       /*
       Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order Used for testing to make sure tree is built correctly
       Specified by:toArrayList in interface LinkedConverterTreeInterface<java.lang.String>
       Returns:an ArrayList of the items in the linked Tree
        */
        public ArrayList<String> toArrayList ()
        {
            ArrayList<String> tList = new ArrayList<>();
            LNRoutputTraversal(root, tList);
            return tList;
        }

        /*
        The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
        Specified by: LNRoutputTraversal in interface LinkedConverterTreeInterface<java.lang.String>
        Parameters:root - the root of the tree for this particular recursive instance
        list - the ArrayList that will hold the contents of the tree in LNR order
         */
        public void LNRoutputTraversal (TreeNode < String > root, ArrayList < String > list)
        {
            if(root==null)
            {
                return;
            }
            LNRoutputTraversal(root.getLeftChild(),list);
            list.add(root.getData());
            LNRoutputTraversal(root.getRightChild(),list);
        }
    }



