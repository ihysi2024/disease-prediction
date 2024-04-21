import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class WriterDecoratorDemo {
  public static void main(String [] args) {
    Writer out = null;

    try {
      out = new FileWriter("outwriter.txt");
    }
    catch (IOException e) {

    }
    String input = "I love Object Oriented Design. "
                   + "THIS IS A GREAT COURSE!";



    //a regular print writer, writing to file.
    PrintWriter pw = new PrintWriter(out);
    pw.println(input);

    //a printing, lower-casing writer. Note, to the same file!
    pw = new PrintWriter(new LowerCaseWriter(out));
    pw.println(input);

    //a printing, shift-ciphering writer. Note, to the same file!
    pw = new PrintWriter(new ShiftCipherWriter(out));
    pw.println(input);

    //a printing, lower-casing,shift-ciphering writer. Note, to the
    // same file!
    pw = new PrintWriter(new LowerCaseWriter(new ShiftCipherWriter(out)));

    pw.println(input);


    pw.close();
  }

  /**
   * This class implements a simple one-character shift cipher on
   * characters in a writer. This means 'a' will be 'b', 'b' will be 'c', and so on
   * with 'z' being 'a'. The same happens to upper case characters.
   * Non-alphabetic characters are left untouched.
   */

  static class ShiftCipherWriter extends FilterWriter {

    /**
     * Creates an filter writer built on top of the specified underlying
     * writer.
     *
     * @param out the underlying writer to be assigned to the field
     *            <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying writer.
     */
    public ShiftCipherWriter(Writer out) {
      super(out);
    }

    @Override
    public void write(String str,int off,int len) throws IOException{
      String out = "";
      for (int i=0;i<len;i++) {
        char b = str.charAt(off+i);
        if (Character.isUpperCase(b)) {
          int offset = b - (int)'A';
          offset = (int)'A' + (offset + 1) % 26;
          out = out + (char)offset;
        }
        else if (Character.isLowerCase(b)) {
          int offset = b - (int)'a';
          offset = (int)'a' + (offset + 1) % 26;
          out = out + (char)offset;
        }
        else {
          out = out + b;
        }
      }
      super.write(out,0,out.length());
    }
  }

  /**
   * This class implements a lower case writer. Each character is
   * converted to lower case before sending it out. If the character is a
   * non-alphabetic then it is left untouched.
   */

  static class LowerCaseWriter extends FilterWriter {
    /**
     * Creates a filter writer built on top of the specified underlying
     * output stream.
     *
     * @param out the underlying writer to be assigned to the field
     *            <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying writer.
     */
    public LowerCaseWriter(Writer out) {
      super(out);
    }

    @Override
    public void write(String str,int off,int len) throws IOException {
      super.write(str.toLowerCase(),off,len);
    }
  }
}
