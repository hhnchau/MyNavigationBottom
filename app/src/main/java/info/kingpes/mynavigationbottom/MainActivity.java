package info.kingpes.mynavigationbottom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyNavigationBottom nav = findViewById(R.id.nav);

        List<NavItem> lst = new ArrayList<>();
        lst.add(new NavItem(R.drawable.ic_action_active, R.drawable.ic_action_deactive));
        lst.add(new NavItem(R.drawable.ic_action_active, R.drawable.ic_action_deactive));
        lst.add(new NavItem(R.drawable.ic_action_active, R.drawable.ic_action_deactive));
        //lst.add(new NavItem(R.drawable.ic_action_active, R.drawable.ic_action_deactive));
        //lst.add(new NavItem(R.drawable.ic_action_active, R.drawable.ic_action_deactive));

        nav.setTabList(lst);
        nav.setBadge("1", MyNavigationBottom.TAB2);

        nav.setOnNavigationListener(new MyNavigationBottom.OnNavListener() {
            @Override
            public void onListener(int tab) {
                if (tab == MyNavigationBottom.TAB2)
                    nav.clearBadge();
                if (tab == MyNavigationBottom.TAB5)
                    nav.setBadge("9+", MyNavigationBottom.TAB5);
            }
        });
    }
}
