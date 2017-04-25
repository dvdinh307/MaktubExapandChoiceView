package swipe.maktub.swipeviewchoice.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import swipe.maktub.swipeviewchoice.R;
import swipe.maktub.swipeviewchoice.utils.Utils;


/**
 * Created by dinhdv on 3/14/2017.
 */

public class ChoicePointView extends RelativeLayout {
    private boolean mIsShow = false;
    private TextView mTvOne, mTvTwo, mTvThree, mTvResult;
    private LinearLayout mBtnOne, mBtnTwo, mBtnThree, mLlViewChoice, mLlViewResult;
    private onActionChoice mAction;
    private ArrayList<Integer> mListValues = new ArrayList<>();
    private int mCurrentValues = 0;

    public ChoicePointView(Context context) {
        super(context);
        initViews(context);
    }

    public ChoicePointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public ChoicePointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        View mRootView = View.inflate(context, R.layout.view_choice_point, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(20, 0, 0, 20);
        mRootView.setLayoutParams(params);
        this.addView(mRootView);
        mTvResult = (TextView) mRootView.findViewById(R.id.tv_result);
        mTvOne = (TextView) mRootView.findViewById(R.id.tv_one);
        mTvTwo = (TextView) mRootView.findViewById(R.id.tv_two);
        mTvThree = (TextView) mRootView.findViewById(R.id.tv_three);
        mBtnOne = (LinearLayout) mRootView.findViewById(R.id.ll_one);
        mBtnTwo = (LinearLayout) mRootView.findViewById(R.id.ll_two);
        mBtnThree = (LinearLayout) mRootView.findViewById(R.id.ll_three);
        mLlViewChoice = (LinearLayout) mRootView.findViewById(R.id.ll_choice);
        mLlViewResult = (LinearLayout) mRootView.findViewById(R.id.ll_result);
        mBtnOne.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenViewResult(mListValues.get(0));
                indexChoice(0);
                if (mAction != null)
                    if (checkChangeSelect(mListValues.get(0)))
                        mAction.onValuesChoice(0, mListValues.get(0));
            }
        });
        mBtnTwo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenViewResult(mListValues.get(1));
                indexChoice(1);
                if (mAction != null)
                    if (checkChangeSelect(mListValues.get(1)))
                        mAction.onValuesChoice(1, mListValues.get(1));
            }
        });
        mBtnThree.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenViewResult(mListValues.get(2));
                indexChoice(2);
                if (mAction != null)
                    if (checkChangeSelect(mListValues.get(2)))
                        mAction.onValuesChoice(2, mListValues.get(2));
            }
        });

        mLlViewResult.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mIsShow) {
                    mLlViewChoice.startAnimation(inFromRightAnimation());
                } else {
                    mLlViewResult.setVisibility(VISIBLE);
                    mLlViewChoice.startAnimation(outToRightAnimation(mLlViewChoice));
                }
                mIsShow = !mIsShow;
            }
        });
    }

    public void setListData(ArrayList<Integer> values) {
        mListValues = values;
        mTvOne.setText(Utils.formatValuesPoint(String.valueOf(mListValues.get(0))));
        mTvTwo.setText(Utils.formatValuesPoint(String.valueOf(mListValues.get(1))));
        mTvThree.setText(Utils.formatValuesPoint(String.valueOf(mListValues.get(2))));
    }

    public void setDefaultValues(int indexChoice, int values) {
        indexChoice(indexChoice);
        mTvResult.setText(Utils.formatValuesPoint(String.valueOf(values)));
        mCurrentValues = values;
    }

    private boolean checkChangeSelect(int values) {
        return mCurrentValues != values;
    }

    private void indexChoice(int index) {
        switch (index) {
            case 0:
                mTvOne.setTextColor(Color.parseColor("#fa405d"));
                mTvTwo.setTextColor(Color.parseColor("#4e5663"));
                mTvThree.setTextColor(Color.parseColor("#4e5663"));
                break;
            case 1:
                mTvOne.setTextColor(Color.parseColor("#4e5663"));
                mTvTwo.setTextColor(Color.parseColor("#fa405d"));
                mTvThree.setTextColor(Color.parseColor("#4e5663"));
                break;
            case 2:
                mTvOne.setTextColor(Color.parseColor("#4e5663"));
                mTvTwo.setTextColor(Color.parseColor("#4e5663"));
                mTvThree.setTextColor(Color.parseColor("#fa405d"));
                break;
        }
    }

    public void hiddenViewResult(int number) {
        if (number > 0)
            mTvResult.setText(Utils.formatValuesPoint(String.valueOf(number)));
        mLlViewResult.setVisibility(VISIBLE);
        if (mIsShow)
            mLlViewChoice.startAnimation(outToRightAnimation(mLlViewChoice));
        mIsShow = false;
    }

    private Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(300);
        inFromRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLlViewChoice.setVisibility(VISIBLE);
                mLlViewResult.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    private Animation outToRightAnimation(final LinearLayout mLlViewChoice) {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(300);
        outtoRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLlViewChoice.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

    public void setListener(onActionChoice action) {
        mAction = action;
    }

    public interface onActionChoice {
        void onValuesChoice(int index, int values);
    }
}
