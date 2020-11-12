package pkg;


import java.io.File;
import java.util.Scanner;

public class MorseCodeConverter
{
    private static MorseCodeTree tree = new MorseCodeTree();

    public MorseCodeConverter()
    {
    }
    /*
    returns a string with all the data in the tree in LNR order with an space in between them.
    Uses the toArrayList method in MorseCodeTree It should return the data in this order:
    "h s v i f u e l r a p w j b d x n c k y t z g q m o"
    Note the extra space between j and b - that is because there is an empty string that is the root,
    and in the LNR traversal, the root would come between the right most child of the left tree (j)
    and the left most child of the right tree (b). This is used for testing purposes to make sure the MorseCodeTree has been built properly
    Returns:the data in the tree in LNR order separated by a space.
     */
    public static String printTree()
    {
        String print= "";
        for (String i : tree.toArrayList())
        {
            print += i +" ";
        }
        return print;
    }
    /*
    Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
    Example:code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
    string returned = "Hello World"
    Parameters:code - the morse code
    Returns:the English translation
     */
    public static String convertToEnglish(String code)
    {
        var str = new StringBuilder();
        var wordString =code.split(" / ");
        for( var word: wordString)
        {
            var letterStr = word.split(" ");
            for (var letter: letterStr)
            {
                str.append(tree.fetch(letter));
            }
            str.append(" ");
        }
        return str.toString().trim();
    }
    /*
    Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
    Example:a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
    string returned = "Hello World"
    Parameters:codeFile - name of the File that contains Morse Code
    Returns:the English translation of the file
    Throws:java.io.FileNotFoundException
     */
    public static String convertToEnglish(File codeFile)
            throws java.io.FileNotFoundException
    {
       var file =new Scanner(codeFile);
       var line =new StringBuilder();
       while (file.hasNextLine())
       {
           line.append(convertToEnglish(file.nextLine()));
       }
       file.close();
       return line.toString();

    }
}
