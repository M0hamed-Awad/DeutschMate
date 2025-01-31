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

public class ColorsActivity extends AppCompatActivity {

    GridView colorsGv;
    MediaPlayer mediaPlayer;

    WordModel[] colors = {
            new WordModel(
                    "Blue",
                    "Blau",
                    "Images/Colors/blue.png",
                    "Sounds/Colors/blue.mp3"
            ),
            new WordModel(
                    "Black",
                    "Schwarz",
                    "Images/Colors/black.png",
                    "Sounds/Colors/black.mp3"
            ),
            new WordModel(
                    "Brown",
                    "Braun",
                    "Images/Colors/brown.png",
                    "Sounds/Colors/brown.mp3"
            ),
            new WordModel(
                    "Yellow",
                    "Gelb",
                    "Images/Colors/yellow.png",
                    "Sounds/Colors/yellow.mp3"
            ),
            new WordModel(
                    "Gray",
                    "Grau",
                    "Images/Colors/gray.png",
                    "Sounds/Colors/gray.mp3"
            ),
            new WordModel(
                    "Green",
                    "Grün",
                    "Images/Colors/green.png",
                    "Sounds/Colors/green.mp3"
            ),
            new WordModel(
                    "Red",
                    "Rot",
                    "Images/Colors/red.png",
                    "Sounds/Colors/red.mp3"
            ),
            new WordModel(
                    "White",
                    "Weiß",
                    "Images/Colors/white.png",
                    "Sounds/Colors/white.mp3"
            ),
            new WordModel(
                    "Orange",
                    "Orange",
                    "Images/Colors/orange.png",
                    "Sounds/Colors/orange.mp3"
            ),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_colors);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CustomWordsAdapter adapter = new CustomWordsAdapter(this, colors);

        colorsGv = findViewById(R.id.colors_activity_words_gv);
        colorsGv.setAdapter(adapter);

        mediaPlayer = new MediaPlayer();

        colorsGv.setOnItemClickListener((parent, view, position, id) -> {

            mediaPlayer.stop();
            mediaPlayer.reset();

            WordModel word = colors[position];

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

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}