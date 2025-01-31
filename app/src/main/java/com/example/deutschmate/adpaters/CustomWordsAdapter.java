package com.example.deutschmate.adpaters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.deutschmate.R;
import com.example.deutschmate.models.WordModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class CustomWordsAdapter extends ArrayAdapter<WordModel> {
    private final int[] colors = {
            R.color.card_red_color,
            R.color.card_blue_color,
            R.color.card_green_color,
            R.color.card_yellow_color,
            R.color.card_cyan_color,
            R.color.card_pink_color,
    };

    private final Context context;

    public CustomWordsAdapter(@NonNull Context context, @NonNull WordModel[] words) {
        super(context, R.layout.word_item, Arrays.asList(words));
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        }

        WordModel word = getItem(position);

        if (word != null) {
            ImageView wordImageIv = convertView.findViewById(R.id.word_icon);
            TextView wordInEnglishTv = convertView.findViewById(R.id.english_text_tv);
            TextView wordInGermanTv = convertView.findViewById(R.id.german_text_tv);

            try {
                InputStream inputStream = context.getAssets().open(word.getImageAssetPath());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                wordImageIv.setImageBitmap(bitmap);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            wordInEnglishTv.setText(word.getEnglishText());
            wordInGermanTv.setText(word.getGermanText());

            int color = getColorForPosition(position);

            GradientDrawable background = (GradientDrawable) convertView.getBackground();

            if (background != null) {
                background.setColor(color);
                convertView.setBackground(background);
            }
        }

        return convertView;
    }

    private int getColorForPosition(int position) {
        int colorIndex = position % colors.length;
        return ContextCompat.getColor(context, colors[colorIndex]);
    }
}
