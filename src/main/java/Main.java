import com.epam.Director;
import com.epam.exception.CreatorException;

public class Main {
    public static void main(String[] args) throws CreatorException, InterruptedException {
        Director director = new Director();
        director.runShips();
    }
}
