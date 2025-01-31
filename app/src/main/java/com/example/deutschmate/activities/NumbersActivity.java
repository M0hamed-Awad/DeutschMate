package com.example.deutschmate.activities;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deutschmate.R;
import com.example.deutschmate.adpaters.CustomWordsAdapter;
import com.example.deutschmate.models.WordModel;

import java.io.IOException;

public class NumbersActivity extends AppCompatActivity {

    GridView numbersGv;
    MediaPlayer mediaPlayer;

    WordModel[] numbers = {
            new WordModel(
                    "One",
                    "Eins",
                    "Images/Numbers/one.png",
                    "Sounds/Numbers/one.mp3"
            ),
            new WordModel(
                    "Two",
                    "Zwei",
                    "Images/Numbers/two.png",
                    "Sounds/Numbers/two.mp3"
            ),
            new WordModel(
                    "Three",
                    "Drei",
                    "Images/Numbers/three.png",
                    "Sounds/Numbers/three.mp3"
            ),
            new WordModel(
                    "Four",
                    "Vier",
                    "Images/Numbers/four.png",
                    "Sounds/Numbers/four.mp3"
            ),
            new WordModel(
                    "Five",
                    "Fünf",
                    "Images/Numbers/five.png",
                    "Sounds/Numbers/five.mp3"
            ),
            new WordModel(
                    "Six",
                    "Sechs",
                    "Images/Numbers/six.png",
                    "Sounds/Numbers/six.mp3"
            ),
            new WordModel(
                    "Seven",
                    "Sieben",
                    "Images/Numbers/seven.png",
                    "Sounds/Numbers/seven.mp3"
            ),
            new WordModel(
                    "Eight",
                    "Acht",
                    "Images/Numbers/eight.png",
                    "Sounds/Numbers/eight.mp3"
            ),
            new WordModel(
                    "Nine",
                    "Neun",
                    "Images/Numbers/nine.png",
                    "Sounds/Numbers/nine.mp3"
            ),
            new WordModel(
                    "Ten",
                    "Zehn",
                    "Images/Numbers/ten.png",
                    "Sounds/Numbers/ten.mp3"
            ),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numbers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CustomWordsAdapter adapter = new CustomWordsAdapter(this, numbers);

        numbersGv = findViewById(R.id.numbers_activity_words_gv);
        numbersGv.setAdapter(adapter);

        mediaPlayer = new MediaPlayer();

        numbersGv.setOnItemClickListener((parent, view, position, id) -> {

            mediaPlayer.stop();
            mediaPlayer.reset();

            WordModel word = numbers[position];

            try {
                AssetFileDescriptor afd = getAssets().openFd(word.getSoundAssetPath());
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();

                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                Toast.makeText(this, "Error playing sound", Toast.LENGTH_SHORT).show();
            }
        });
    }
}