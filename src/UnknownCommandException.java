/**
 * @author: Muhammadjon Hakimov
 * created: 20.05.2019 18:36:03
 */

class UnknownCommandException extends Exception {
    UnknownCommandException(String token) {
        super("Expected \'-dir\', found: " + token + " - unknown command!");
    }
}
