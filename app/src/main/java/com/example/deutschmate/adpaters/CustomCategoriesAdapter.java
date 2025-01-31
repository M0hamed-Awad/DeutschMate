package com.example.deutschmate.adpaters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.deutschmate.R;
import com.example.deutschmate.models.CategoryModel;

import java.util.Arrays;

public class CustomCategoriesAdapter extends ArrayAdapter<CategoryModel> {
    private final Context context;

    public CustomCategoriesAdapter(Context context, CategoryModel[] categories) {
        super(context, R.layout.categories_item, Arrays.asList(categories));
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Reuse the view if possible
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);

            /*
                When you call convertView = LayoutInflater.from(context).inflate(...), you're inflating a new row layout
                only when convertView is null. This prevents unnecessarily inflating new views, which can be expensive
                in terms of performance.
            */

            // LayoutInflater.from(context)
            // Retrieves a LayoutInflater instance from the provided context (which is usually the activity).

            // inflate(R.layout.categories_lv_row, parent, false)

            /*
                "R.layout.categories_lv_row": The layout resource that defines the row's UI (your custom list row design).
                "parent": The parent view group (in this case, the ListView) to which the inflated view will eventually be attached.
                "false:" Indicates whether the newly inflated view should be attached to the parent view right away.
            */
        }

        // Populate the views with data
        CategoryModel category = getItem(position);

        if (category != null) {
            ImageView categoryImageIv = convertView.findViewById(R.id.main_activity_category_icon);
            TextView categoryNameTv = convertView.findViewById(R.id.main_activity_category_name);
            TextView categorySubTextTv = convertView.findViewById(R.id.main_activity_category_sub_text);

            categoryImageIv.setImageResource(category.getImageId());
            categoryNameTv.setText(category.getCategoryName());
            categorySubTextTv.setText(category.getCategorySubText());

            GradientDrawable background = (GradientDrawable) convertView.getBackground();

            if (background != null) {
                background.setColor(ContextCompat.getColor(context, category.getColor()));
                convertView.setBackground(background);
            }
        }

        return convertView;
    }
}
