package personas;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class Podrazak extends SuspectedPerson {

    @Override
    public boolean decide(Person opponent) {
        return false;
    }

}
