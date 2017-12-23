package utils;
/*
 * some operations about FromUtils.
 * @author cz
 * 2017-12-22
 * */
public class FormUtils {
  public static boolean isMissing(String value) {
    return((value == null) || (value.trim().isEmpty()));
  }
  
  public static boolean isAnyMissing(String... values) {
    boolean isMissing = false;
    for(String value: values) {
      if (isMissing(value)) {
        isMissing = true;
        break;
      }
    }
    return(isMissing);
  }
  
  /* Java 8 version of isAnyMissing: 
  
  private static boolean isAnyMissing(String... values) {
    return(Stream.of(values).anyMatch(s -> isMissing(s)));
  }
  
  Or, you could replace
  s -> isMissing(s) with FormUtils::isMissing.
  
  */
  
  /** Returns the word "copy" in singular or plural. That is, if n is
   *  1, returns "copy" (for use in phrase "1 copy"), otherwise returns
   *  "copies" (for use in phrases like "2 copies").
   */
  public static String wordForCopies(int n) {
    if (n == 1) {
      return("copy");
    } else {
      return("copies");
    }
  }
  
  private FormUtils() {} // Uninstantiatable class. Static methods only.
}
