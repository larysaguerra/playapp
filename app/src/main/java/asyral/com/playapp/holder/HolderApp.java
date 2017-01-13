package asyral.com.playapp.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;

import asyral.com.playapp.R;
import asyral.com.playapp.activity.DetailAppActivity;
import asyral.com.playapp.api.Response.Entry;
import asyral.com.playapp.tools.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by guerra on 12/01/17.
 * HolderApp
 */

public class HolderApp extends RecyclerView.ViewHolder {

    public CardView cardApp;
    public ImageView imageApp;
    public TextView nameApp;
    private TextView titleApp;
    private TextView categoryApp;
    private TextView priceAmountApp;
    private TextView priceCurrencyApp;

    public HolderApp(View itemView) {
        super(itemView);
        cardApp = (CardView) itemView.findViewById(R.id.card_app);
        imageApp = (ImageView) itemView.findViewById(R.id.image_app);
        nameApp = (TextView) itemView.findViewById(R.id.name_app);
        titleApp = (TextView) itemView.findViewById(R.id.title_app);
        categoryApp = (TextView) itemView.findViewById(R.id.category_app);
        priceAmountApp = (TextView) itemView.findViewById(R.id.price_amount_app);
        priceCurrencyApp = (TextView) itemView.findViewById(R.id.price_currency_app);
    }

    public void setData(Entry entry) {
        Utils.setImage(cardApp.getContext(), imageApp,
                entry.getImImage().get(2).getLabel(),
                120, 120);
        nameApp.setText(entry.getImName().getLabel());
        titleApp.setText(entry.getTitle().getLabel());
        categoryApp.setText(entry.getCategory().getAttributes().getLabel());
        priceCurrencyApp.setText(entry.getImPrice().getAttributes().getCurrency());
        priceAmountApp.setText(entry.getImPrice().getAttributes().getAmount());
    }

    public void setClick(final Entry entry) {
        cardApp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ViewAnimator
                        .animate(imageApp).pulse().accelerate().interpolator(new LinearInterpolator())
                        .start();
                return false;
            }
        });
        cardApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DetailAppActivity.startDetailAppActivity(v.getContext(), HolderApp.this, entry);
            }
        });
    }


}