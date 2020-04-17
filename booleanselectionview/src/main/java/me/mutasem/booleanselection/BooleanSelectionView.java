package me.mutasem.booleanselection;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class BooleanSelectionView extends RadioGroup {
    private int selection = Selection.None;
    private RadioButton viewStart, viewEnd;
    private RadioGroup root;
    private SelectionListener selectionListener;

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
            try {
                final int textColor, selectedColor, unSelectedColor;
                String startText, endText;
                textColor = a.getColor(R.styleable.BooleanSelectorView_textColor, ContextCompat.getColor(context, R.color.textColor));
                selectedColor = a.getColor(R.styleable.BooleanSelectorView_selectedColor, ContextCompat.getColor(context, R.color.selectetColor));
                unSelectedColor = a.getColor(R.styleable.BooleanSelectorView_unSelectedColor, ContextCompat.getColor(context, R.color.unselectedColor));
                startText = a.getString(R.styleable.BooleanSelectorView_startText);
                endText = a.getString(R.styleable.BooleanSelectorView_endText);
                if (a.hasValue(R.styleable.BooleanSelectorView_selection))
                    selection = a.getInteger(R.styleable.BooleanSelectorView_selection, Selection.None);

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
                //setting default selection
                if (selection == Selection.Start)
                    viewStart.setChecked(true);
                else if (selection == Selection.End)
                    viewEnd.setChecked(true);
                //setting the view background color
                Drawable rootBg = root.getBackground();
                Drawable rootWrappedDrawable = DrawableCompat.wrap(rootBg);
                DrawableCompat.setTint(rootWrappedDrawable, unSelectedColor);
                //setting the initial BG for left button
                Drawable startBg = viewStart.getBackground();
                final Drawable startWrappedDrawable = DrawableCompat.wrap(startBg);
                if (viewStart.isChecked())
                    DrawableCompat.setTint(startWrappedDrawable, selectedColor);
                else
                    DrawableCompat.setTint(startWrappedDrawable, unSelectedColor);
                //setting the initial BG for right button
                Drawable endBg = viewEnd.getBackground();
                final Drawable endWrappedDrawable = DrawableCompat.wrap(endBg);
                if (viewEnd.isChecked())
                    DrawableCompat.setTint(endWrappedDrawable, selectedColor);
                else
                    DrawableCompat.setTint(endWrappedDrawable, unSelectedColor);
                //listen for changes and change the Bg based on those changes
                viewStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (viewStart.isChecked()) {
                            DrawableCompat.setTint(startWrappedDrawable, selectedColor);
                            if (selectionListener != null)
                                selectionListener.onSelectionChanged(Selection.Start, viewStart.getText() != null ? viewStart.getText().toString() : "");
                        } else {
                            DrawableCompat.setTint(startWrappedDrawable, unSelectedColor);
                            if (selectionListener != null)
                                selectionListener.onSelectionChanged(Selection.End, viewEnd.getText() != null ? viewEnd.getText().toString() : "");
                        }
                    }
                });
                viewEnd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (viewEnd.isChecked()) {
                            DrawableCompat.setTint(endWrappedDrawable, selectedColor);
                        } else {
                            DrawableCompat.setTint(endWrappedDrawable, unSelectedColor);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                a.recycle();
            }
        }
    }

    public void setStartText(@NonNull String text) {
        viewStart.setText(text);
    }

    public void setEndText(@NonNull String text) {
        viewEnd.setText(text);
    }

    public int getSelection() {
        if (viewStart.isChecked())
            return Selection.Start;
        else if (viewEnd.isChecked())
            return Selection.End;
        return Selection.None;
    }

    public void setSelection(int s) {
        if (s == Selection.Start)
            viewStart.setChecked(true);
        else if (s == Selection.End)
            viewEnd.setChecked(true);
    }

    public SelectionListener getSelectionListener() {
        return selectionListener;
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    public static class Selection {
        public static final int None = 0, Start = 1, End = 2;
    }

    public interface SelectionListener {
        void onSelectionChanged(int selection, String selectedText);
    }
}
