package dtomstrock.bulgariannouns;

/**
 * Created by dtomstrock on 11/19/2017.
 * Updated by dtomstrock on 11/26/2017 with new TODO in determineGender
 */

public class Noun {

    private BulgarianString nounSingular,
            nounSingularTheObject,
            nounSingularTheSubject,
            nounPlural,
            nounPluralCounting,
            nounPluralThe;
    private char gender; //f = feminine, m = masculine, n = neuter, x = undetermined
    private boolean exception;

    public Noun(){

        nounSingular = new BulgarianString("");
        nounSingularTheObject = new BulgarianString("");
        nounSingularTheSubject = new BulgarianString("");
        nounPlural = new BulgarianString("");
        nounPluralCounting = new BulgarianString("");
        nounPluralThe = new BulgarianString("");
        gender = 'x';
        exception = false;

    }

    public Noun(BulgarianString input) {
        nounSingular = new BulgarianString(input);
        gender = determineGender(input);
        exception = findException(input);

    }

    private char determineGender(BulgarianString input){

        // TODO find feminine gender exception and return 'f'

        if(input.endsWith("а") || input.endsWith("я") || input.endsWith("ест") || input.endsWith("щ") || input.endsWith("аст") ||
                (input.endsWith("ост") && input.getNumberOfSyllables() > 1))
            return 'f';
        else if(input.endsWith("е") || input.endsWith("и") || input.endsWith("о") || input.endsWith("у") || input.endsWith("ю"))
            return 'n';
        else
            return 'm';
    }

    private boolean findException(BulgarianString input){

        //TODO check database to see if entry exists there

        return false;

    }

    public BulgarianString getNounSingular() {
        return nounSingular;
    }

    public BulgarianString getNounSingularTheObject() {
        return nounSingularTheObject;
    }

    public BulgarianString getNounSingularTheSubject() {
        return nounSingularTheSubject;
    }

    public BulgarianString getNounPlural() {
        return nounPlural;
    }

    public BulgarianString getNounPluralCounting() {
        return nounPluralCounting;
    }

    public BulgarianString getNounPluralThe() {
        return nounPluralThe;
    }

    public char getGender() {
        return gender;
    }

    public boolean isException() {
        return exception;
    }

}
