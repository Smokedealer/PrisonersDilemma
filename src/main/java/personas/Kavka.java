package personas;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class Kavka extends Person {
    public Person newPerson() {
        return new Kavka();
    }

    public boolean decide() {
        return true;
    }
}
