package personas;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public abstract class Person {

    static int lastID = 0;

    private int id;

    public Person() {
        this.id = lastID++;
    }

    public int getId() {
        return id;
    }

    public abstract boolean decide();

    public abstract Person newPerson();


//    public boolean decide() {
//        switch (this.strategy){
//            case kavka:
//                return true;
//            case podrazak:
//                return false;
//            case rozmar:
//                return new Random().nextDouble() < cooperationProbability;
//            default:
//                System.err.println("Unsupported strategy. Choosing randomly.");
//                return new Random().nextDouble() < cooperationProbability;
//        }
//    }
}
