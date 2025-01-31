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

public class JobsActivity extends AppCompatActivity {

    GridView jobsGv;
    MediaPlayer mediaPlayer;

    WordModel[] jobs = {
            new WordModel(
                    "Astronaut",
                    "Astronaut",
                    "Images/Jobs/astronaut.png",
                    "Sounds/Jobs/astronaut.mp3"
            ),
            new WordModel(
                    "Computer Technician",
                    "Informatiker",
                    "Images/Jobs/computer_technician.png",
                    "Sounds/Jobs/computer_technician.mp3"
            ),
            new WordModel(
                    "Doctor",
                    "Arzt",
                    "Images/Jobs/doctor.png",
                    "Sounds/Jobs/doctor.mp3"
            ),
            new WordModel(
                    "Lawyer",
                    "Rechtsanwalt",
                    "Images/Jobs/lawyer.png",
                    "Sounds/Jobs/lawyer.mp3"
            ),
            new WordModel(
                    "Manager",
                    "Manager",
                    "Images/Jobs/manager.png",
                    "Sounds/Jobs/manager.mp3"
            ),
            new WordModel(
                    "Mechanic",
                    "Mechaniker",
                    "Images/Jobs/mechanic.png",
                    "Sounds/Jobs/mechanic.mp3"
            ),
            new WordModel(
                    "Nurse",
                    "Krankenschwester",
                    "Images/Jobs/nurse.png",
                    "Sounds/Jobs/nurse.mp3"
            ),
            new WordModel(
                    "Pilot",
                    "Pilot",
                    "Images/Jobs/pilot.png",
                    "Sounds/Jobs/pilot.mp3"
            ),
            new WordModel(
                    "Policeman",
                    "Polizist",
                    "Images/Jobs/policeman.png",
                    "Sounds/Jobs/policeman.mp3"
            ),
            new WordModel(
                    "Reporter",
                    "Reporter",
                    "Images/Jobs/reporter.png",
                    "Sounds/Jobs/reporter.mp3"
            ),
            new WordModel(
                    "Seller",
                    "Verkäufer",
                    "Images/Jobs/seller.png",
                    "Sounds/Jobs/seller.mp3"
            ),
            new WordModel(
                    "Solider",
                    "Soldat",
                    "Images/Jobs/solider.png",
                    "Sounds/Jobs/solider.mp3"
            ),
            new WordModel(
                    "Taxi Driver",
                    "Taxifahrer",
                    "Images/Jobs/taxi_driver.png",
                    "Sounds/Jobs/taxi_driver.mp3"
            ),
            new WordModel(
                    "Teacher",
                    "Lehrer",
                    "Images/Jobs/teacher.png",
                    "Sounds/Jobs/teacher.mp3"
            ),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jobs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CustomWordsAdapter adapter = new CustomWordsAdapter(this, jobs);

        jobsGv = findViewById(R.id.jobs_activity_words_gv);
        jobsGv.setAdapter(adapter);

        mediaPlayer = new MediaPlayer();

        jobsGv.setOnItemClickListener((parent, view, position, id) -> {

            mediaPlayer.stop();
            mediaPlayer.reset();

            WordModel word = jobs[position];

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