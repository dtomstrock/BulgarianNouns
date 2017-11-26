package dtomstrock.bulgariannouns;

/**
 * Created by dtomstrock on 11/19/2017.
 * Updated by dtomstrock on 11/19/2017 to improve code efficiency.
 */

public final class NeuterNoun extends Noun {

    private BulgarianString nounSingular,
            nounSingularTheObject,
            nounSingularTheSubject,
            nounPlural,
            nounPluralThe,
            nounPluralCounting;

    public NeuterNoun() {
        super();
    }

    public NeuterNoun(BulgarianString input) {
        super(input);
        nounSingular = new BulgarianString(input);
        nounSingularTheObject = new BulgarianString(createNounSingularTheObject(input));
        nounSingularTheSubject = new BulgarianString(createNounSingularTheSubject(input));
        nounPlural = new BulgarianString(createNounPlural(input));
        nounPluralThe = new BulgarianString(createNounPluralThe(nounPlural));
        nounPluralCounting = new BulgarianString(createNounPluralCounting(input));
    }

    private BulgarianString createNounSingularTheObject(BulgarianString input) {
        return new BulgarianString(input + "то");
    }

    private BulgarianString createNounSingularTheSubject(BulgarianString input) {
        return nounSingularTheObject;
    }

    private BulgarianString createNounPlural(BulgarianString input) {
        if(input.isApophanous())
            input = new BulgarianString(input.apophanyConvert());

        if(input.endsWith("ие"))
            return new BulgarianString(input.dropLastLetter() + "я");
        else if(input.endsWith("но"))
            return new BulgarianString(input.dropLastLetter() + "и");
        else if(input.endsWith("о") || input.endsWith("це") || input.endsWith("ще"))
            return new BulgarianString(input.dropLastLetter() + "а");
        else if(input.endsWith("ме"))
            return new BulgarianString(input + "на");
        else
            return new BulgarianString(input + "та");
    }

    private BulgarianString createNounPluralThe(BulgarianString input) {
        return input.endsWith("и") ? new BulgarianString(input + "те") : new BulgarianString(input + "та");
    }

    private BulgarianString createNounPluralCounting(BulgarianString input) {
        return nounPlural;
    }

    @Override
    public String toString() {
        return "Singular: " + nounSingular + System.lineSeparator() +
                "Singular with definite article (object): " + nounSingularTheObject + System.lineSeparator() +
                "Singular with definite article (subject): " + nounSingularTheSubject + System.lineSeparator() +
                "Plural: " + nounPlural + System.lineSeparator() +
                "Plural with definite article: " + nounPluralThe + System.lineSeparator() +
                "Counting Plural: " + nounPluralCounting + System.lineSeparator();
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

    public BulgarianString getNounPluralThe() {
        return nounPluralThe;
    }

    public BulgarianString getNounPluralCounting() {
        return nounPluralCounting;
    }

}
