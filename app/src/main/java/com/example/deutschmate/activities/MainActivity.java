package com.example.deutschmate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deutschmate.R;
import com.example.deutschmate.adpaters.CustomCategoriesAdapter;
import com.example.deutschmate.models.CategoryModel;


public class MainActivity extends AppCompatActivity {

    ListView categoriesLv;

    CategoryModel[] categories = {
            new CategoryModel(
                    "Meet the Family",
                    "12 Members",
                    R.color.family_members_category_color,
                    R.drawable.family_members_icon),

            new CategoryModel(
                    "Know Your Body",
                    "14 Organs",
                    R.color.body_parts_category_color,
                    R.drawable.body_parts_icon),

            new CategoryModel(
                    "Let's Paint",
                    "9 Colors",
                    R.color.colors_category_color,
                    R.drawable.colors_icon),

            new CategoryModel(
                    "Counting Fun",
                    "10 Numbers",
                    R.color.numbers_category_color,
                    R.drawable.numbers_icon),

            new CategoryModel(
                    "Career Quest",
                    "14 Jobs",
                    R.color.jobs_category_color,
                    R.drawable.jobs_icon),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(64, systemBars.top, 64, systemBars.bottom);
            return insets;
        });


        CustomCategoriesAdapter adapter = new CustomCategoriesAdapter(
                this,
                categories
        );

        categoriesLv = findViewById(R.id.main_activity_categories_lv);
        categoriesLv.setAdapter(adapter);

        categoriesLv.setOnItemClickListener((parent, view, position, id) -> {
            navigateToActivity(position);
        });

    }

    public void navigateToActivity(int position){
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, FamilyMembersActivity.class);
                break;

            case 1:
                intent = new Intent(MainActivity.this, BodyPartsActivity.class);
                break;

            case 2:
                intent = new Intent(MainActivity.this, ColorsActivity.class);
                break;

            case 3:
                intent = new Intent(MainActivity.this, NumbersActivity.class);
                break;

            case 4:
                intent = new Intent(MainActivity.this, JobsActivity.class);
                break;

            default:
                intent = new Intent(MainActivity.this, MainActivity.class);
                break;
        }
        startActivity(intent);
    }
}