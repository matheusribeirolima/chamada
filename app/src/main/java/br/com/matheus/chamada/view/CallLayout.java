package br.com.matheus.chamada.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.LayoutCallBinding;

public class CallLayout extends RelativeLayout {

    LayoutCallBinding binding;

    public CallLayout(Context context) {
        super(context);
        init(null);
    }

    public CallLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.layout_call,
                this,
                false);

//        if (attributeSet != null) {
//            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet,
//                    R.styleable.,
//                    0,
//                    0);
//            try {
//                editCampaign = typedArray.getBoolean(R.styleable., false);
//            } finally {
//                typedArray.recycle();
//            }
//        }

        configureLayout();
    }

    private void configureLayout() {
        
    }
}
