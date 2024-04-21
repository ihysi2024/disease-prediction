package cs3500.hw1.filesystem;

import java.util.List;

/**
 * Expected behaviors of a Directory. A Directory (or folder)
 * is a file that contains a list of files.
 */
public interface ExtDirectory extends ExtFile {

  /**
   * Returns the list of all files in the directory.
   * @return non-empty list of files contained inside this directory
   */
  List<ExtFile> contents();

  String path(ExtFile file);
}
