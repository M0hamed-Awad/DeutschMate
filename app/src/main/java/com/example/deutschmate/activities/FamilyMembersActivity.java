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

public class FamilyMembersActivity extends AppCompatActivity {

    GridView familyMembersGv;
    MediaPlayer mediaPlayer;

    WordModel[] familyMembers = {
            new WordModel(
                    "Grandfather",
                    "Der Opa",
                    "Images/Family Members/grandfather.png",
                    "Sounds/Family Members/grandfather.mp3"
            ),

            new WordModel(
                    "Grandmother",
                    "Die Oma",
                    "Images/Family Members/grandmother.png",
                    "Sounds/Family Members/grandmother.mp3"
            ),

            new WordModel(
                    "Father",
                    "Der Vater",
                    "Images/Family Members/father.png",
                    "Sounds/Family Members/father.mp3"
            ),

            new WordModel(
                    "Mother",
                    "Die Mutter",
                    "Images/Family Members/mother.png",
                    "Sounds/Family Members/mother.mp3"
            ),

            new WordModel(
                    "Son",
                    "Der Sohn",
                    "Images/Family Members/son.png",
                    "Sounds/Family Members/son.mp3"
            ),

            new WordModel(
                    "Daughter",
                    "Die Tochter",
                    "Images/Family Members/daughter.png",
                    "Sounds/Family Members/daughter.mp3"
            ),

            new WordModel(
                    "Uncle",
                    "Der Onkel",
                    "Images/Family Members/uncle.png",
                    "Sounds/Family Members/uncle.mp3"
            ),


            new WordModel(
                    "Aunt",
                    "Die Tante",
                    "Images/Family Members/aunt.png",
                    "Sounds/Family Members/aunt.mp3"
            ),


            new WordModel(
                    "Cousin (Female)",
                    "Die Cousin",
                    "Images/Family Members/cousin_female.png",
                    "Sounds/Family Members/cousin_female.mp3"
            ),

            new WordModel(
                    "Cousin (Male)",
                    "Die Cousin",
                    "Images/Family Members/cousin_male.png",
                    "Sounds/Family Members/cousin_male.mp3"
            ),

            new WordModel(
                    "Sister",
                    "Die Schwester",
                    "Images/Family Members/sister.png",
                    "Sounds/Family Members/sister.mp3"
            ),

            new WordModel(
                    "Brother",
                    "Der Bruder",
                    "Images/Family Members/brother.png",
                    "Sounds/Family Members/brother.mp3"
            )
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_family_members);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CustomWordsAdapter adapter = new CustomWordsAdapter(this, familyMembers);

        familyMembersGv = findViewById(R.id.family_members_activity_words_gv);
        familyMembersGv.setAdapter(adapter);

        mediaPlayer = new MediaPlayer();

        familyMembersGv.setOnItemClickListener((parent, view, position, id) -> {

            mediaPlayer.reset();

            WordModel word = familyMembers[position];

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
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}