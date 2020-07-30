package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.FragmentAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.TallySheet.TallySheetAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon.CreatePuPohonFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon.ListPengukuranPohonFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class TabDetailTallySheetFragment extends Fragment {

    View tab_detail_tallysheet;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    public static final String MSG_KEY_TALLYSHEET = "id_tallysheet";
    public static String id_tallysheet;
    private SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tab_detail_tallysheet = inflater.inflate(R.layout.fragment_tab_detail_tally_sheet, container, false);

        session = new SessionManager(getActivity());

        Toolbar toolbar = tab_detail_tallysheet.findViewById(R.id.toolbar_tab_detail_tallysheet);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setPreferences(getActivity(), "ses_id_tallysheet", null);

                Fragment fragment = new ListTallySheetFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        try {
            String message = getArguments().getString(MSG_KEY_TALLYSHEET);
            if (message != null) {
                id_tallysheet=message;
            } else {
                id_tallysheet="null";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        initViewPager();

        return tab_detail_tallysheet;
    }

        private void initViewPager() {
            mTabLayout = tab_detail_tallysheet.findViewById(R.id.tab_layout_main_tab_detail_tallysheet);
            mViewPager = tab_detail_tallysheet.findViewById(R.id.view_pager_main_tab_detail_tallysheet);

//            String message = id_tallysheet;
//            Bundle data = new Bundle();
//            data.putString(MSG_KEY_TALLYSHEET, message);
//            Fragment fragment_create = new CreatePuPohonFragment();
//            fragment_create.setArguments(data);
//            Fragment fragment_detail = new DetailTallySheetFragment();
//            fragment_detail.setArguments(data);


            List<String> titles = new ArrayList<>();

            titles.add("Pengukuran Pohon");
            titles.add("Detail Tally Sheet");
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));

            List<Fragment> fragments = new ArrayList<>();
            fragments.add(new ListPengukuranPohonFragment());
            fragments.add(new DetailTallySheetFragment());


            mViewPager.setOffscreenPageLimit(2);

            FragmentAdapter mFragmentAdapter = new FragmentAdapter((getActivity()).getSupportFragmentManager(), fragments, titles);
            mViewPager.setAdapter(mFragmentAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
            mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);

            mViewPager.addOnPageChangeListener(pageChangeListener);
        }

        private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

}