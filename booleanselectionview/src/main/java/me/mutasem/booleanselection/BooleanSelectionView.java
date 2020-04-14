package me.mutasem.booleanselection;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class BooleanSelectionView extends RadioGroup {

    private RadioButton viewStart, viewEnd;
    private RadioGroup root;

    public BooleanSelectionView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BooleanSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }


    private void init(Context context, @Nullable AttributeSet attrs, int defStyle) {
        inflate(context, R.layout.boolean_selection, this);
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BooleanSelectorView,
                    defStyle, defStyle);
            final int textColor, selectedColor, unSelectedColor;
            String startText, endText;
            textColor = a.getColor(R.styleable.BooleanSelectorView_textColor, ContextCompat.getColor(context, R.color.textColor));
            selectedColor = a.getColor(R.styleable.BooleanSelectorView_selectedColor, ContextCompat.getColor(context, R.color.selectetColor));
            unSelectedColor = a.getColor(R.styleable.BooleanSelectorView_unSelectedColor, ContextCompat.getColor(context, R.color.unselectedColor));
            startText = a.getString(R.styleable.BooleanSelectorView_startText);
            endText = a.getString(R.styleable.BooleanSelectorView_endText);
            root = findViewById(R.id.root);
            //setting the margin
            root.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            //removing the parent margin
            setPadding(0, 0, 0, 0);
            viewStart = findViewById(R.id.viewStart);
            viewEnd = findViewById(R.id.viewEnd);
            viewStart.setTextColor(textColor);
            viewEnd.setTextColor(textColor);
            viewStart.setText(startText);
            viewEnd.setText(endText);

            //setting the view background color
            Drawable rootBg = root.getBackground();
            Drawable rootWrappedDrawable = DrawableCompat.wrap(rootBg);
            DrawableCompat.setTint(rootWrappedDrawable, unSelectedColor);
            //setting the initial BG for left button
            Drawable startBg = viewStart.getBackground();
            Drawable startWrappedDrawable = DrawableCompat.wrap(startBg);
            if (viewStart.isChecked())
                DrawableCompat.setTint(startWrappedDrawable, selectedColor);
            else
                DrawableCompat.setTint(startWrappedDrawable, unSelectedColor);
            //setting the initial BG for right button
            Drawable endBg = viewEnd.getBackground();
            Drawable endWrappedDrawable = DrawableCompat.wrap(endBg);
            if (viewEnd.isChecked())
                DrawableCompat.setTint(endWrappedDrawable, selectedColor);
            else
                DrawableCompat.setTint(endWrappedDrawable, unSelectedColor);
            //listen for changes and change the Bg based on those changes
            viewStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Drawable startBg = viewStart.getBackground();
                    Drawable startWrappedDrawable = DrawableCompat.wrap(startBg);
                    if (viewStart.isChecked())
                        DrawableCompat.setTint(startWrappedDrawable, selectedColor);
                    else
                        DrawableCompat.setTint(startWrappedDrawable, unSelectedColor);
                }
            });
            viewEnd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Drawable endBg = viewEnd.getBackground();
                    Drawable endWrappedDrawable = DrawableCompat.wrap(endBg);
                    if (viewEnd.isChecked())
                        DrawableCompat.setTint(endWrappedDrawable, selectedColor);
                    else
                        DrawableCompat.setTint(endWrappedDrawable, unSelectedColor);
                }
            });
        }
    }

}
