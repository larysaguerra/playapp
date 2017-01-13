package asyral.com.playapp.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.mikepenz.iconics.view.IconicsImageView;

import asyral.com.playapp.R;
import asyral.com.playapp.activity.AppsActivity;
import asyral.com.playapp.data.Category;
import asyral.com.playapp.tools.ActivityUtil;
import asyral.com.playapp.tools.Utils;

/**
 * Created by guerra on 11/01/17.
 * HolderCategory
 */

public class HolderCategory extends RecyclerView.ViewHolder {

    private CardView cardCategory;
    private View iconBack;
    private IconicsImageView iconCategory;
    private TextView nameCategory;

    public HolderCategory(View itemView) {
        super(itemView);
        cardCategory = (CardView) itemView.findViewById(R.id.card_category);
        iconBack = itemView.findViewById(R.id.icon_back);
        iconCategory = (IconicsImageView) itemView.findViewById(R.id.icon_category);
        nameCategory = (TextView) itemView.findViewById(R.id.name_category);
    }

    public void setData(Category category) {
        nameCategory.setText(category.getName());
        iconCategory.setIcon(Utils.getIcon(iconCategory.getContext(), category.getIcon(), Color.WHITE, 60));
    }

    public void setClick(final Category category) {
        cardCategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ViewAnimator
                        .animate(iconCategory).swing().accelerate().interpolator(new LinearInterpolator())
                        .start();
                return false;
            }
        });
        cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startCategoryActivity(v.getContext(), HolderCategory.this, category);
            }
        });
    }

    private void startCategoryActivity(Context context,
                                       final HolderCategory holderCategory,
                                       final Category category) {
        Intent intent = new Intent(context, AppsActivity.class);
        intent.putExtra("category", category);

        Pair<View, String> p1 = new Pair<View, String>(holderCategory.iconBack,
                context.getString(R.string.transition_icon));
        Pair<View, String> p2 = Pair.create((View) holderCategory.nameCategory,
                context.getString(R.string.transition_name));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (ActivityUtil) context, p1, p2);

        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
