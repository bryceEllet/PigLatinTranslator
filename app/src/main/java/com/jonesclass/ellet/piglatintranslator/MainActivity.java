package com.jonesclass.ellet.piglatintranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView wordTextView;
    TextView newWordTextView;
    boolean vowel = false;
    String word = "";
    String value;
    String letter;
    String rest = "";
    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> vowels = new ArrayList<>(Arrays.asList("a","e","i","o","u","y"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newWordTextView = findViewById(R.id.textView_newWord);

        Button translateButton = findViewById(R.id.button_translate);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newWordTextView.setText("");
                wordTextView = findViewById(R.id.editText_word);
                value = "";
                word = "";
                words.clear();
                value = String.valueOf(wordTextView.getText());
                letter = String.valueOf(value.charAt(value.length()-1));
                if (!letter.equals(" ")) {
                    value += " ";
                }
                // break inputted values into words
                for (int i = 0; i < value.length(); i++) {
                    String character = String.valueOf(value.charAt(i));
                    if (!character.equals(" ")) {
                        word += character;
                    } else {
                        words.add(word);
                        word = "";
                    }
                }
                System.out.println(words);

                for (int i = 0; i < words.size(); i++) {
                    word = words.get(i).toLowerCase();
                    letter = String.valueOf(word.charAt(0));
                    for (int j = 0; j < vowels.size(); j++) {
                        if (letter.equals(vowels.get(j))) {
                            vowel = true;
                            break;
                        }
                    }
                    if (vowel) {
                        vowel();
                    } else {
                        for (int j = 0; j < vowels.size(); j++) {
                            String get = vowels.get(j);
                            if (word.contains(get)) {
                                int position = word.indexOf(get);
                                notVowel(position);
                                break;
                            }
                        }
                    }

                }
            }
        });
    }

    private void vowel() {
        word += "way";
        letter = String.valueOf(word.charAt(0)).toUpperCase();
        rest = word.substring(1);
        word = letter + rest;
        newWordTextView.setText(newWordTextView.getText() + " " + word);
        vowel = false;
    }

    private void notVowel(int position) {
        letter = word.substring(0,position) + "ay";
        word = word.substring(position) + letter;
        letter = String.valueOf(word.charAt(0)).toUpperCase();
        rest = word.substring(1);
        word = letter + rest;
        newWordTextView.setText(newWordTextView.getText() + " " + word);
    }
}