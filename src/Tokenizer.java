import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Tokenizer {

    private Queue<String> tokens;

    /**
     * Creates a tokenizer that loads the tokens for an expression in the string
     * argument
     * 
     * @param input
     *            An expression to be tokenized
     */
    public Tokenizer(String input) {
        loadString(input);
    }

    /**
     * Erases the existing tokens and loads tokens for an expression from a
     * string
     * 
     * @param input
     *            An expression to be tokenized
     */
    public void loadString(String input) {
        tokens = new LinkedList<String>();
        String[] toks = input.split(" +");
        for (String s : toks) {
            tokens.add(s);
        }
    }

    /**
     * Erases the existing tokens and reads from a file to populate the store of
     * tokens. for this Tokenizer
     * 
     * @param filename
     *            The name of the file containing expressions to be tokenized,
     *            line-by-line
     */
    public void loadFile(String filename) {
        tokens = new LinkedList<String>();
        try {
            BufferedReader rd = new BufferedReader(new FileReader(filename));
            String line = rd.readLine();
            while (line != null) {
                String[] toks = line.split(" +");
                for (String s : toks) {
                    tokens.add(s);
                }
                line = rd.readLine();
            }
            rd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provides an iterator for the store of tokens
     * 
     * @return An iterator for the store of tokens
     */
    public Iterator<String> getIterator() {
        return tokens.iterator();
    }

    /**
     * Get the next token
     * 
     * @return The next token
     */
    public String getNextToken() {
        return tokens.remove();
    }

    /**
     * Checks if there is another token
     * 
     * @return True if there are more tokens, False otherwise
     */
    public Boolean hasNextToken() {
        return !tokens.isEmpty();
    }

}
