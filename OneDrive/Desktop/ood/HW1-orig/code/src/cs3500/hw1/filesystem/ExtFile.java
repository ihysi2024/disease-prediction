package cs3500.hw1.filesystem;

/**
 * Behaviors expected of a file in a read-only file system.
 * At the moment searching is a major operation in this system.
 */
public interface ExtFile {
  /**
   * Returns the name of the file as given on creation.
   * @return the name of the file
   */
  String name();

  /**
   * Returns the size of this specific file. The definition is
   * dependent on the type of file.
   * @return the size of the file in bytes
   */
  long size();

  /**
   * Returns the size of this file and all files contained within.
   * Note that some files may not contain other files.
   * @return the size of file and all contained files in bytes
   */
  long totalSize();

  /**
   * Returns true if and only if the given phrase is contained in
   * a file. Note that a phrase is contained in a file if and only if
   * the phrase is in the name of the file or its contents.
   * @param phrase the phrase to find in the file
   * @return true if and only if the phrase is contained in the file
   */
  boolean search(String phrase);

  String toString();

  /**
   * Produces a visual of the file's contents paths.
   * @return String
   */
  String prettyPrint();
}
