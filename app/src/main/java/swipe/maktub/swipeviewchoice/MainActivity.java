package swipe.maktub.swipeviewchoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import swipe.maktub.swipeviewchoice.view.ChoicePointView;

public class MainActivity extends AppCompatActivity {
    private ChoicePointView mChoiceView;
    private RelativeLayout mRlMain;
    private ArrayList<Integer> mListItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChoiceView = (ChoicePointView) findViewById(R.id.view_choice);
        mRlMain = (RelativeLayout) findViewById(R.id.rl_main);
        // Ony three item.
        mListItem.add(19);
        mListItem.add(20);
        mListItem.add(30);
        mChoiceView.setListData(mListItem);
        mChoiceView.setListener(new ChoicePointView.onActionChoice() {
            @Override
            public void onValuesChoice(int index, int values) {
                mChoiceView.setDefaultValues(index, values);
                Toast.makeText(MainActivity.this, "Values Choice :" + values, Toast.LENGTH_SHORT).show();
            }
        });
        mRlMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mChoiceView.hiddenViewResult(0);
                return false;
            }
        });

    }
}
