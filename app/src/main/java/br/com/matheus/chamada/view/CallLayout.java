package br.com.matheus.chamada.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.orhanobut.hawk.Hawk;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.LayoutCallBinding;
import br.com.matheus.chamada.helper.PreferencesHelper;

public class CallLayout extends RelativeLayout {

    LayoutCallBinding binding;

    private int count, marks;
    public boolean onlyShow, isEditing;

    public ObservableBoolean oneChecked = new ObservableBoolean(true);
    public ObservableBoolean twoChecked = new ObservableBoolean(false);
    public ObservableBoolean threeChecked = new ObservableBoolean(false);
    public ObservableBoolean fourChecked = new ObservableBoolean(false);

    public CallLayout(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public CallLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    public CallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    public CallLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.layout_call,
                this,
                true);

        binding.setCall(this);

        if (attributeSet != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet,
                    R.styleable.CallLayout,
                    defStyleAttr,
                    defStyleRes);
            try {
                count = typedArray.getInteger(R.styleable.CallLayout_count, 4);
                marks = typedArray.getInteger(R.styleable.CallLayout_marks, 0);
                onlyShow = typedArray.getBoolean(R.styleable.CallLayout_onlyShow, false);
                isEditing = typedArray.getBoolean(R.styleable.CallLayout_isEditing, true);
            } finally {
                typedArray.recycle();
            }
        }

        configureLayout();
    }

    private void configureLayout() {
        switch (count) {
            case 1:
                ((LayoutParams) binding.cbFault1.getLayoutParams()).addRule(CENTER_IN_PARENT);
                binding.cbFault2.setVisibility(GONE);
                binding.cbFault3.setVisibility(GONE);
                binding.cbFault4.setVisibility(GONE);
                break;
            case 2:
                ((LayoutParams) binding.cbFault1.getLayoutParams()).addRule(CENTER_HORIZONTAL);
                ((LayoutParams) binding.cbFault2.getLayoutParams()).addRule(CENTER_HORIZONTAL);
                binding.cbFault3.setVisibility(GONE);
                binding.cbFault4.setVisibility(GONE);
                break;
            case 3:
                binding.cbFault4.setVisibility(GONE);
                break;
            default:
                break;
        }
        switch (marks) {
            case 1:
                oneChecked.set(true);
                break;
            case 2:
                oneChecked.set(true);
                twoChecked.set(true);
                break;
            case 3:
                oneChecked.set(true);
                twoChecked.set(true);
                threeChecked.set(true);
                break;
            case 4:
                oneChecked.set(true);
                twoChecked.set(true);
                threeChecked.set(true);
                fourChecked.set(true);
                break;
            default:
                break;
        }

        if (!isEditing) {
            CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {
                if (Hawk.get(PreferencesHelper.DOUBLE_CHECK)) {
                    switch (buttonView.getId()) {
                        case R.id.cbFault1:
                            oneChecked.set(isChecked);
                            twoChecked.set(isChecked);
                            break;
                        case R.id.cbFault2:
                            oneChecked.set(isChecked);
                            twoChecked.set(isChecked);
                            break;
                        case R.id.cbFault3:
                            threeChecked.set(isChecked);
                            fourChecked.set(isChecked);
                            break;
                        case R.id.cbFault4:
                            threeChecked.set(isChecked);
                            fourChecked.set(isChecked);
                            break;
                    }
                } else if (Hawk.get(PreferencesHelper.TRIPLE_CHECK)) {
                    oneChecked.set(isChecked);
                    twoChecked.set(isChecked);
                    threeChecked.set(isChecked);
                } else if (Hawk.get(PreferencesHelper.ALL_CHECK)) {
                    oneChecked.set(isChecked);
                    twoChecked.set(isChecked);
                    threeChecked.set(isChecked);
                    fourChecked.set(isChecked);
                }
            };

            binding.cbFault1.setOnCheckedChangeListener(listener);
            binding.cbFault2.setOnCheckedChangeListener(listener);
            binding.cbFault3.setOnCheckedChangeListener(listener);
            binding.cbFault4.setOnCheckedChangeListener(listener);
        }
    }

    public int getFaults() {
        int faults = 0;
        if (oneChecked.get()) {
            faults++;
        } else if (twoChecked.get() && count >= 2) {
            faults++;
        } else if (threeChecked.get() && count >= 3) {
            faults++;
        } else if (fourChecked.get() && count == 4) {
            faults++;
        }
        return faults;
    }

    public void setFaults(int faults) {
        switch (faults) {
            case 1:
                oneChecked.set(true);
                break;
            case 2:
                oneChecked.set(true);
                twoChecked.set(true);
                break;
            case 3:
                oneChecked.set(true);
                twoChecked.set(true);
                threeChecked.set(true);
                break;
            case 4:
                oneChecked.set(true);
                twoChecked.set(true);
                threeChecked.set(true);
                fourChecked.set(true);
                break;
            default:
                break;
        }
    }
}
