package dtomstrock.bulgariannouns;

/**
 * Created by dtomstrock on 11/19/2017.
 */

public class BulgarianString {

    private String bulgarianString;
    private int numberOfSyllables;
    private boolean apophanous;

    public BulgarianString() {
        bulgarianString = "";
        numberOfSyllables = 0;
        apophanous = false;
    }

    public BulgarianString(String input) {
        bulgarianString = deleteNonBulgarianChar(input);
        numberOfSyllables = syllableCounter();
        apophanous = determineApophany();
    }

    public BulgarianString(BulgarianString input) {
        bulgarianString = input.getBulgarianString();
        numberOfSyllables = syllableCounter();
        apophanous = determineApophany();
    }

    //delete any non-Bulgarian characters from the input string
    private String deleteNonBulgarianChar(String input) {
        String output = "";
        char[] inputCharArray = input.toCharArray();

        for(int count = 0; count < input.length(); count++) {
            int id = (int) inputCharArray[count];

            if(!(id < 1040 || id == 1067 || id == 1069 || id == 1099 || id == 1101 || id > 1103))
                output += inputCharArray[count];
        }
        return output;
    }

    //convert input BulgarianString to lower case
    public BulgarianString toLower() {
        String output = "";
        char[] inputCharArray = this.getBulgarianString().toCharArray();

        for(int count = 0; count < this.getBulgarianString().length(); count++) {
            int id = (int) inputCharArray[count];

            if(id < 1072) {
                int smallid = id + 32;
                inputCharArray[count] = (char) smallid;
            }

            output += inputCharArray[count];
        }
        return new BulgarianString(output);
    }

    //convert input BulgarianString to upp case
    public BulgarianString toUpper() {
        String output = "";
        char[] inputCharArray = this.getBulgarianString().toCharArray();

        for(int count = 0; count < this.getBulgarianString().length(); count++) {
            int id = (int) inputCharArray[count];

            if(id > 1071) {
                int bigid = id - 32;
                inputCharArray[count] = (char) bigid;
            }
            output += inputCharArray[count];
        }
        return new BulgarianString(output);
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
        return (numberOfSyllables <= 2 && this.getBulgarianString().contains("я") && !(this.endsWith("я")));
    }

    //for BulgarianString that has apophany, convert "я" to "е"
    public BulgarianString apophanyConvert() {
        if(this.apophanous)
            return new BulgarianString(this.getBulgarianString().replaceAll("я", "е"));
        else
            return this;
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

}
