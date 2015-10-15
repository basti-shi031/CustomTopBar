package basti.coryphaei.com.customtopbar;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TopBar mTopBar;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tf = Typeface.createFromAsset(getAssets(),"fontcn/blackmiddle.TTF");

        mTopBar = (TopBar) findViewById(R.id.topbar);

        //设置alpha
        //mTopBar.setTextAlpha(0.87);
        //设置字体
        mTopBar.setTypeFace(tf);
        //设置部分Item可见/不可见
        //mTopBar.setItemVisible(0,false);

        //点击事件
        mTopBar.setTopBarClickListener(new TopBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(getApplicationContext(),"点击左侧按钮",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(getApplicationContext(),"点击右侧按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
