package personas;

import ethnicity.Ethnicity;


/**
 * Represents a single person in population (other members point of view).
 *
 * @author Vojtěch Kinkor
 */
public interface Person {

    /**
     * Gets ethnicity of person.
     * @return Ethnicity.
     */
    Ethnicity getEthnicity();

}
