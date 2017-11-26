package dtomstrock.bulgariannouns;

/**
 * Created by dtomstrock on 11/19/2017.
 * Updated by dtomstrock on 11/26/2017 to include metathesis and other grammar rules.
 */

public final class MasculineNoun extends Noun {

    private BulgarianString nounSingular,
            nounSingularTheObject,
            nounSingularTheSubject,
            nounPlural,
            nounPluralThe,
            nounPluralCounting;

    public MasculineNoun() {
        super();
    }

    public MasculineNoun(BulgarianString input) {
        super(input);
        nounSingular = new BulgarianString(input);
        nounSingularTheObject = new BulgarianString(createNounSingularTheObject(input));
        nounSingularTheSubject = new BulgarianString(createNounSingularTheSubject(input));
        nounPlural = new BulgarianString(createNounPlural(input));
        nounPluralThe = new BulgarianString(createNounPluralThe(nounPlural));
        nounPluralCounting = new BulgarianString(createNounPluralCounting(input));
    }

    private BulgarianString createNounSingularTheObject(BulgarianString input) {
        if(input.isApophanous())
            input = new BulgarianString(input.apophanyConvert());
        if(input.isMetathetical())
            input = new BulgarianString(input.metathesisConvert());

        if(input.endsWith("тел") || input.endsWith("ар") || input.endsWith("яр"))
            return new BulgarianString(input + "ят");
        else if(input.endsWith("й"))
            return new BulgarianString(input.dropLastLetter() + "ят");
        else if(input.endsWith("зъм"))
            return new BulgarianString(input.dropPenultimateLetter() + "ът");
        else
            return new BulgarianString(input + "ът");
    }

    private BulgarianString createNounSingularTheSubject(BulgarianString input) {
        if(input.isApophanous())
            input = new BulgarianString(input.apophanyConvert());
        if(input.isMetathetical())
            input = new BulgarianString(input.metathesisConvert());

        if(input.endsWith("тел") || input.endsWith("ар") || input.endsWith("яр"))
            return new BulgarianString(input + "я");
        else if(input.endsWith("й"))
            return new BulgarianString(input.dropLastLetter() + "я");
        else if(input.endsWith("зъм"))
            return new BulgarianString(input.dropPenultimateLetter() + "а");
        else
            return new BulgarianString(input + "а");
    }

    private BulgarianString createNounPlural(BulgarianString input) {
        if(input.isApophanous())
            input = new BulgarianString(input.apophanyConvert());
        if(input.isMetathetical())
            input = new BulgarianString(input.metathesisConvert());

        if(input.getNumberOfSyllables() == 1)
            return new BulgarianString(input + "ове");
        else if(input.endsWith("ин"))
            return new BulgarianString(input.dropLastLetter());
        else if(input.penultimateLetterIs("ъ"))
            return new BulgarianString(input.dropPenultimateLetter() + "и");
        else if(input.endsWith("к") || (input.endsWith("г") && !input.endsWith("нг")) || input.endsWith("х"))
            return new BulgarianString(input.convertKGH() + "и");
        else if(input.endsWith("ец"))
            return new BulgarianString(input.dropPenultimateLetter() + "и");
        else if(input.endsWith("й"))
            return new BulgarianString(input.dropLastLetter() + "и");
        else
            return new BulgarianString(input + "и");
    }

    private BulgarianString createNounPluralThe(BulgarianString input) {
        return new BulgarianString(input + "те");
    }

    private BulgarianString createNounPluralCounting(BulgarianString input) {
        return nounSingularTheSubject.endsWith("а") ? new BulgarianString(input + "а") : new BulgarianString(input + "я");
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
