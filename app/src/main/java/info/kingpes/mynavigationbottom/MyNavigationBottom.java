package info.kingpes.mynavigationbottom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyNavigationBottom extends LinearLayout implements View.OnClickListener {
    private static final int DURATION = 100;
    private static final float SCALE = 0.8f;
    public static final int TAB1 = 0;
    public static final int TAB2 = 1;
    public static final int TAB3 = 2;
    public static final int TAB4 = 3;
    public static final int TAB5 = 4;
    private ConstraintLayout[] tab;
    private ImageView[] icon;
    private TextView[] badge;
    private List<NavItem> lstTab;

    public MyNavigationBottom(Context context) {
        super(context);
        init(context);
    }

    public MyNavigationBottom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyNavigationBottom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_navigation_bottom, this, true);
        ConstraintLayout tab1 = v.findViewById(R.id.tab1);
        tab1.setOnClickListener(this);
        ConstraintLayout tab2 = v.findViewById(R.id.tab2);
        tab2.setOnClickListener(this);
        ConstraintLayout tab3 = v.findViewById(R.id.tab3);
        tab3.setOnClickListener(this);
        ConstraintLayout tab4 = v.findViewById(R.id.tab4);
        tab4.setOnClickListener(this);
        ConstraintLayout tab5 = v.findViewById(R.id.tab5);
        tab5.setOnClickListener(this);
        tab = new ConstraintLayout[]{tab1, tab2, tab3, tab4, tab5};

        ImageView icon1 = v.findViewById(R.id.img1);
        ImageView icon2 = v.findViewById(R.id.img2);
        ImageView icon3 = v.findViewById(R.id.img3);
        ImageView icon4 = v.findViewById(R.id.img4);
        ImageView icon5 = v.findViewById(R.id.img5);
        icon = new ImageView[]{icon1, icon2, icon3, icon4, icon5};

        TextView badge1 = v.findViewById(R.id.badge1);
        TextView badge2 = v.findViewById(R.id.badge2);
        TextView badge3 = v.findViewById(R.id.badge3);
        TextView badge4 = v.findViewById(R.id.badge4);
        TextView badge5 = v.findViewById(R.id.badge5);
        badge = new TextView[]{badge1, badge2, badge3, badge4, badge5};
    }

    public void setTabList(List<NavItem> lst) {
        lstTab = lst;
        for (int i = 0; i < lstTab.size(); i++) {
            tab[i].setVisibility(VISIBLE);
            icon[i].setVisibility(VISIBLE);
            icon[i].setImageResource(lstTab.get(i).getIconInactive());
            scaleDown(i);
            if (i == TAB1) setTabActive(i);
        }
    }

    public void setTabActive(int tab) {
        icon[tab].setImageResource(lstTab.get(tab).getIconActive());
        scaleUp(tab);
    }

    private void clearTab() {
        for (int i = 0; i < lstTab.size(); i++) {
            icon[i].setImageResource(lstTab.get(i).getIconInactive());
            scaleDown(i);
        }
    }

    public void setBadge(String value, int tab) {
        if (tab > -1 && tab < 5) {
            badge[tab].setText(value);
            badge[tab].setVisibility(VISIBLE);
        }
    }

    public void clearBadge() {
        for (int i = 0; i < lstTab.size(); i++) {
            badge[i].setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        clearTab();
        if (v.getId() == R.id.tab1) {
            if (onNavListener != null) onNavListener.onListener(TAB1);
            setTabActive(TAB1);
        } else if (v.getId() == R.id.tab2) {
            if (onNavListener != null) onNavListener.onListener(TAB2);
            setTabActive(TAB2);
        } else if (v.getId() == R.id.tab3) {
            if (onNavListener != null) onNavListener.onListener(TAB3);
            setTabActive(TAB3);
        } else if (v.getId() == R.id.tab4) {
            if (onNavListener != null) onNavListener.onListener(TAB4);
            setTabActive(TAB4);
        } else if (v.getId() == R.id.tab5) {
            if (onNavListener != null) onNavListener.onListener(TAB5);
            setTabActive(TAB5);
        }
    }

    private OnNavListener onNavListener;

    public void setOnNavigationListener(OnNavListener onNavListener) {
        this.onNavListener = onNavListener;
    }

    public interface OnNavListener {
        void onListener(int tab);
    }

    private void scaleDown(int tab) {
        ObjectAnimator scaleXDownAnimator = ObjectAnimator.ofFloat(icon[tab], "scaleX", SCALE);
        ObjectAnimator scaleYDownAnimator = ObjectAnimator.ofFloat(icon[tab], "scaleY", SCALE);
        AnimatorSet downSet = new AnimatorSet();
        downSet.setDuration(DURATION);
        downSet.setInterpolator(new AccelerateInterpolator());
        downSet.playTogether(scaleXDownAnimator, scaleYDownAnimator);
        downSet.cancel();
        downSet.start();
    }

    private void scaleUp(int tab) {
        ObjectAnimator scaleXUpAnimator = ObjectAnimator.ofFloat(icon[tab], "scaleX", 1.0f);
        ObjectAnimator scaleYUpAnimator = ObjectAnimator.ofFloat(icon[tab], "scaleY", 1.0f);
        AnimatorSet upSet = new AnimatorSet();
        upSet.setDuration(DURATION);
        upSet.setInterpolator(new FastOutSlowInInterpolator());
        upSet.playTogether(scaleXUpAnimator, scaleYUpAnimator);
        upSet.cancel();
        upSet.start();
    }

}
