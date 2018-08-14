package com.android.Uga;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Layout3 extends Fragment {
TextView aboutTV,aboutTV1,aboutTV2;
    Typeface typeface1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout3, null);
        aboutTV=(TextView)view.findViewById(R.id.aboutTV);
        aboutTV1=(TextView)view.findViewById(R.id.aboutTV1);
        aboutTV2=(TextView)view.findViewById(R.id.aboutTV2);


        typeface1= Typeface.createFromAsset(getActivity().getAssets(), "Uga_fonts/Montserrat-Regular.ttf");
        aboutTV.setTypeface(typeface1);
        aboutTV1.setTypeface(typeface1);
        aboutTV2.setTypeface(typeface1);

        aboutTV.setMovementMethod(LinkMovementMethod.getInstance());
        aboutTV1.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
