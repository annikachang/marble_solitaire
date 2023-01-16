import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This class represents a corrupted class that implements the Readable for testing purposes.
 * The methods inherited by the Readable interface will always return an IOException.
 */
public class TestingReadableCorruptedClass implements Readable {

  /**
   * This constructor is for testing purposes so that this class can be called to test the
   * controller and any IOExceptions thrown.
   */
  public TestingReadableCorruptedClass() {
    // empty constructor for testing.
  }

  /**
   * Overrides the original method in the Readable so that an IOException is always thrown.
   * Throws an IOException regardless.
   *
   * @param cb the buffer to read characters into
   */
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
