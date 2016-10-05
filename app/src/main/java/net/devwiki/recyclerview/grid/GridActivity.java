package net.devwiki.recyclerview.grid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import net.devwiki.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GridActivity extends AppCompatActivity {

    private static final String KEY_HOR_GRID = "HorizontalGridFragment";
    private static final String KEY_VER_GRID = "VerticalGridFragment";
    private static final String KEY_HEADER_GRID = "HeaderGridFragment";
    private static final String KEY_GROUP_GRID = "GroupGridFragment";

    private FragmentManager mFragmentManager;
    private HorizontalGridFragment mHorizontalGridFragment;
    private VerticalGridFragment mVerticalGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
    }

    @OnClick({R.id.hori_grid_btn, R.id.vert_grid_btn, R.id.head_grid_btn, R.id.group_grid_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hori_grid_btn:
                if (mHorizontalGridFragment == null) {
                    mHorizontalGridFragment = HorizontalGridFragment.newInstance();
                }
                mFragmentManager.beginTransaction()
                        .replace(R.id.grid_fragment, mHorizontalGridFragment)
                        .commit();
                break;
            case R.id.vert_grid_btn:
                if (mVerticalGridFragment == null) {
                    mVerticalGridFragment = VerticalGridFragment.newInstance();
                }
                mFragmentManager.beginTransaction()
                        .replace(R.id.grid_fragment, mVerticalGridFragment)
                        .commit();
                break;
            case R.id.head_grid_btn:
                break;
            case R.id.group_grid_btn:
                break;
        }
    }
}
