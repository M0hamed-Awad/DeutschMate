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

public class BodyPartsActivity extends AppCompatActivity {

    GridView bodyPartsGv;
    MediaPlayer mediaPlayer;

    WordModel[] bodyParts = {
            new WordModel(
                    "Arm",
                    "Arm",
                    "Images/Body Parts/arm.png",
                    "Sounds/Body Parts/arm.mp3"
            ),
            new WordModel(
                    "Ear",
                    "Ohr",
                    "Images/Body Parts/ear.png",
                    "Sounds/Body Parts/ear.mp3"
            ),
            new WordModel(
                    "Elbow",
                    "Ellbogen",
                    "Images/Body Parts/elbow.png",
                    "Sounds/Body Parts/elbow.mp3"
            ),
            new WordModel(
                    "Eye",
                    "Auge",
                    "Images/Body Parts/eye.png",
                    "Sounds/Body Parts/eye.mp3"
            ),
            new WordModel(
                    "Finger",
                    "F‌inger",
                    "Images/Body Parts/finger.png",
                    "Sounds/Body Parts/finger.mp3"
            ),
            new WordModel(
                    "Foot",
                    "Fuß",
                    "Images/Body Parts/foot.png",
                    "Sounds/Body Parts/foot.mp3"
            ),
            new WordModel(
                    "Hair",
                    "Haar",
                    "Images/Body Parts/hair.png",
                    "Sounds/Body Parts/hair.mp3"
            ),
            new WordModel(
                    "Hand",
                    "Hand",
                    "Images/Body Parts/hand.png",
                    "Sounds/Body Parts/hand.mp3"
            ),
            new WordModel(
                    "Head",
                    "Kopf",
                    "Images/Body Parts/head.png",
                    "Sounds/Body Parts/head.mp3"
            ),
            new WordModel(
                    "Leg",
                    "Bein",
                    "Images/Body Parts/leg.png",
                    "Sounds/Body Parts/leg.mp3"
            ),
            new WordModel(
                    "Nose",
                    "Nase",
                    "Images/Body Parts/nose.png",
                    "Sounds/Body Parts/nose.mp3"
            ),
            new WordModel(
                    "Tongue",
                    "Zunge",
                    "Images/Body Parts/tongue.png",
                    "Sounds/Body Parts/tongue.mp3"
            ),
            new WordModel(
                    "Tooth",
                    "Zahn",
                    "Images/Body Parts/tooth.png",
                    "Sounds/Body Parts/tooth.mp3"
            ),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_body_parts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CustomWordsAdapter adapter = new CustomWordsAdapter(this, bodyParts);

        bodyPartsGv = findViewById(R.id.body_parts_activity_words_gv);
        bodyPartsGv.setAdapter(adapter);

        mediaPlayer = new MediaPlayer();

        bodyPartsGv.setOnItemClickListener((parent, view, position, id) -> {

            mediaPlayer.stop();
            mediaPlayer.reset();

            WordModel word = bodyParts[position];

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