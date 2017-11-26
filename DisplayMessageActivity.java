package dtomstrock.bulgariannouns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        BulgarianString messageBG = new BulgarianString(message);
        messageBG = messageBG.toLower();

        Noun inputNoun = new Noun(messageBG);

        switch(inputNoun.getGender()) {
            case 'f': inputNoun = new FeminineNoun(messageBG);
                break;
            case 'm': inputNoun = new MasculineNoun(messageBG);
                break;
            case 'n': inputNoun = new NeuterNoun(messageBG);
                break;
        }

        //Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(inputNoun.toString());
    }
}
