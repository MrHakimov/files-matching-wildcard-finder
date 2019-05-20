/**
 * @author: Muhammadjon Hakimov
 * created: 20.05.2019 18:45:12
 */

class IncorrectNumberOfArgumentsException extends Exception {
    IncorrectNumberOfArgumentsException() {
        super("The number of arguments should be no less than 2!");
    }
}
