package cs3500.hw1.filesystem;

import java.util.Objects;

/**
 * A content file that only contains strings (chunk of characters) as content.
 * The size of a StringFile is the number of characters in its content.
 */

public class StringFile implements ContentFile {
  private String name;
  private String content;

  /**
   * Creates a file with string information.
   * @param name name of the file
   * @param contents information to be stored in the file
   * @throws NullPointerException if name or contents are null
   * @throws IllegalArgumentException if name is empty (0 characters)
   */
  public StringFile(String name, String contents) {
    this.name = Objects.requireNonNull(name);
    if (name.isEmpty()) {
      throw new IllegalArgumentException("name of file cannot be empty");
    }
    this.content = Objects.requireNonNull(contents);
  }

  @Override
  public String contents() {
    return this.content;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public long size() {
    return (this.content.length() * Size.BYTE.inBytes);
  }

  public String path(ExtFile file){
    return this.name();
  }

  @Override
  public boolean search(String phrase) {
    return (this.name.contains(phrase) || this.content.contains(phrase));
  }

  public String prettyPrint(){
    return "";
  }

  public long totalSize() {
    return this.size();
  }

  public String toString() {
    return this.name + "(" + this.size() + " b)";
  }
}
