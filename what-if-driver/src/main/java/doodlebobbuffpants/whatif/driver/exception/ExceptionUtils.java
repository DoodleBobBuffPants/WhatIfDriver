package doodlebobbuffpants.whatif.driver.exception;

public class ExceptionUtils {
    public static <T extends Exception> T append(T a, T b) {
        if (a == null) return b;
        a.addSuppressed(b);
        return a;
    }
}
