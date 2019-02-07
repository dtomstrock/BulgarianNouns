package dtomstrock.bulgariannouns;

/**
 * Created by dtomstrock on 11/19/2017.
 * Updated by dtomstrock on 11/26/2017 to include metathesis and to improve efficiency of code.
 */

public class BulgarianString {

    private String bulgarianString;
    private int numberOfSyllables;
    private boolean apophanous, metathetical;
    private final String message = "Invalid input";

    public BulgarianString() {
        bulgarianString = "";
        numberOfSyllables = 0;
        apophanous = false;
        metathetical = false;
    }

    public BulgarianString(String input) {
        bulgarianString = deleteNonBulgarianChar(input);
        if(bulgarianString.length() == 0)
            bulgarianString = message;
        numberOfSyllables = syllableCounter();
        apophanous = determineApophany();
        metathetical = determineMetathesis();
    }

    public BulgarianString(BulgarianString input) {
        bulgarianString = input.getBulgarianString();
        if(bulgarianString.length() == 0)
            bulgarianString = message;
        numberOfSyllables = syllableCounter();
        apophanous = determineApophany();
        metathetical = determineMetathesis();
    }

    //delete any non-Bulgarian characters from the input string
    private String deleteNonBulgarianChar(String input) {
        StringBuffer output = new StringBuffer();
        char[] inputCharArray = input.toCharArray();

        for(int count = 0; count < input.length(); count++) {
            int id = (int) inputCharArray[count];

            if(!(id < 1040 || id == 1067 || id == 1069 || id == 1099 || id == 1101 || id > 1103))
                output.append(inputCharArray[count]);
        }
        return output.toString();
    }

    //convert input BulgarianString to lower case
    public BulgarianString toLower() {
        StringBuffer output = new StringBuffer();
        char[] inputCharArray = this.getBulgarianString().toCharArray();

        for(int count = 0; count < this.getBulgarianString().length(); count++) {
            int id = (int) inputCharArray[count];

            if(id < 1072) {
                int smallid = id + 32;
                inputCharArray[count] = (char) smallid;
            }

            output.append(inputCharArray[count]);
        }
        return new BulgarianString(output.toString());
    }

    //convert input BulgarianString to upp case
    public BulgarianString toUpper() {
        StringBuffer output = new StringBuffer();
        char[] inputCharArray = this.getBulgarianString().toCharArray();

        for(int count = 0; count < this.getBulgarianString().length(); count++) {
            int id = (int) inputCharArray[count];

            if(id > 1071) {
                int bigid = id - 32;
                inputCharArray[count] = (char) bigid;
            }
            output.append(inputCharArray[count]);
        }
        return new BulgarianString(output.toString());
    }

    //count number of syllables in BulgarianString
    private int syllableCounter() {
        int syllableCount = 0;
        char[] inputCharArray = this.getBulgarianString().toCharArray();

        for(int count = 0; count < this.getBulgarianString().length(); count++) {
            if(inputCharArray[count] == 'а' || inputCharArray[count] == 'е' || inputCharArray[count] == 'и' || inputCharArray[count] == 'о' || inputCharArray[count] == 'у' || inputCharArray[count] == 'ъ' || inputCharArray[count] == 'ю' || inputCharArray[count] == 'я')
                syllableCount++;
        }
        return syllableCount;
    }

    //determine if BulgarianString has apophany (i.e. does "я" change to "е" in certain circumstances?)
    private boolean determineApophany() {
        return (numberOfSyllables <= 2 && this.getBulgarianString().contains("я") && !(this.endsWith("я")) && !(this.endsWith("к")));
    }

    //for BulgarianString that has apophany, convert "я" to "е"
    public BulgarianString apophanyConvert() {
        return this.apophanous ? new BulgarianString(this.getBulgarianString().replaceAll("я", "е")) : this;
    }

    private boolean determineMetathesis() {
        return (numberOfSyllables == 1 && this.getBulgarianString().contains("ръ"));
    }

    public BulgarianString metathesisConvert() {
        return this.metathetical ? new BulgarianString(this.getBulgarianString().replaceAll("ръ", "ър")) : this;
    }

    public BulgarianString convertKGH() {
        if(this.endsWith("к"))
            return new BulgarianString(this.getBulgarianString().substring(0, this.getBulgarianString().length() - 1) + "ц");
        else if(this.endsWith("г"))
            return new BulgarianString(this.getBulgarianString().substring(0, this.getBulgarianString().length() - 1) + "з");
        else if(this.endsWith("х"))
            return new BulgarianString(this.getBulgarianString().substring(0, this.getBulgarianString().length() - 1) + "с");
        else
            return this;
    }

    public boolean endsWith(String ending) {
        return (this.getBulgarianString().endsWith(ending));
    }

    public boolean penultimateLetterIs(String penultimateLetter) {
        String temp = bulgarianString.substring(bulgarianString.length() - 2, bulgarianString.length() - 1);
        return (temp.equals(penultimateLetter));
    }

    public BulgarianString dropLastLetter() {
        return new BulgarianString(this.getBulgarianString().substring(0, this.getBulgarianString().length() - 1));
    }

    public BulgarianString dropPenultimateLetter() {
        String firstPart = this.getBulgarianString().substring(0, this.getBulgarianString().length() - 2);
        String secondPart = this.getBulgarianString().substring(this.getBulgarianString().length() - 1);

        return new BulgarianString(firstPart + secondPart);
    }

    @Override
    public String toString() {
        return bulgarianString;
    }

    public String getBulgarianString() {
        return bulgarianString;
    }

    public int getNumberOfSyllables() {
        return numberOfSyllables;
    }

    public boolean isApophanous() {
        return apophanous;
    }

    public boolean isMetathetical() {
        return metathetical;
    }

    public String getMessage() {
        return message;
    }

}
