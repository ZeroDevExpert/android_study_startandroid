package zerodev.p0201_simpleanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int MENU_ALFA_ID = 1;
    final int MENU_SCALE_ID = 2;
    final int MENU_TRANSLATE = 3;
    final int MENU_ROTATE_ID = 4;
    final int MENU_COMBO_ID = 5;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        tv.startAnimation(AnimationUtils.loadAnimation(this,R.anim.myalpha));
        //registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        switch (v.getId()){
            case R.id.tv:
                menu.add(0,MENU_ALFA_ID,0,"alpha");
                menu.add(0,MENU_SCALE_ID,0,"scale");
                menu.add(0,MENU_TRANSLATE,0,"translate");
                menu.add(0,MENU_ROTATE_ID,0,"rotate");
                menu.add(0,MENU_COMBO_ID,0,"combo");
            break;
        }
        super.onCreateContextMenu(menu,v,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        Animation anim = null;
        switch (item.getItemId()){
            case MENU_ALFA_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.myalpha);
                break;
            case MENU_SCALE_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.myscale);
                break;
            case MENU_TRANSLATE:
                anim = AnimationUtils.loadAnimation(this,R.anim.mytrans);
                break;
            case MENU_ROTATE_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.myrotate);
                break;
            case MENU_COMBO_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.mycombo);
                break;
        }
        tv.startAnimation(anim);
        return super.onContextItemSelected(item);
    }
}
