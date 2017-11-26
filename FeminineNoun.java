package dtomstrock.bulgariannouns;

/**
 * Created by dtomstrock on 11/19/2017.
 * Updated by dtomstrock on 11/26/2017 to include metathesis.
 */

public final class FeminineNoun extends Noun {

    private BulgarianString nounSingular,
            nounSingularTheObject,
            nounSingularTheSubject,
            nounPlural,
            nounPluralThe,
            nounPluralCounting;

    public FeminineNoun() {
        super();
    }

    public FeminineNoun(BulgarianString input) {
        super(input);
        nounSingular = new BulgarianString(input);
        nounSingularTheObject = new BulgarianString(createNounSingularTheObject(input));
        nounSingularTheSubject = new BulgarianString(createNounSingularTheSubject(input));
        nounPlural = new BulgarianString(createNounPlural(input));
        nounPluralThe = new BulgarianString(createNounPluralThe(nounPlural));
        nounPluralCounting = new BulgarianString(createNounPluralCounting(input));
    }

    private BulgarianString createNounSingularTheObject(BulgarianString input) {
        return new BulgarianString(input + "та");
    }

    private BulgarianString createNounSingularTheSubject(BulgarianString input) {
        return nounSingularTheObject;
    }

    private BulgarianString createNounPlural(BulgarianString input) {
        if(input.isApophanous())
            input = new BulgarianString(input.apophanyConvert());
        if(input.isMetathetical())
            input = new BulgarianString(input.metathesisConvert());

        return input.endsWith("а") || input.endsWith("я") ? new BulgarianString(input.dropLastLetter() + "и") : new BulgarianString(input + "и");
    }

    private BulgarianString createNounPluralThe(BulgarianString input) {
        return new BulgarianString(input + "те");
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
