package com.bfar.sampledrawerwithtab.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.bfar.sampledrawerwithtab.CustomizePage;
import com.bfar.sampledrawerwithtab.MySingleton;
import com.bfar.sampledrawerwithtab.R;
import com.squareup.otto.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentWithTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentWithTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentWithTab extends Fragment{

    public static final String TAG = FragmentWithTab.class
            .getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    CustomizePage pager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentWithTab newInstance(String param1, String param2) {
        FragmentWithTab fragment = new FragmentWithTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentWithTab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_with_tab, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        MySingleton.getInstance().getFragmentTabBus().register(this);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        MySingleton.getInstance().getFragmentTabBus().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view
                .findViewById(R.id.tabs);

        pager = (CustomizePage) view.findViewById(R.id.pager);
        pager.setPagingEnabled(false);
//        pager.setCurrentItem();


        PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

    }

    @Subscribe
    public void receivedFeedback(Bundle bundle){
        if(!bundle.getBoolean("status")){
            pager.loadNextFragment(bundle.getInt("fragment"));
        }else{
            Toast.makeText(getActivity(),"Please input all fields",Toast.LENGTH_SHORT).show();
        }

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    /*
    *  PAGER ADAPTER
    * */
    public class PagerAdapter extends FragmentPagerAdapter{

        Fragment fragment = null;

        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        private final String[] TITLES = { "Categories", "Home", "Top Paid"};

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position){

                case 0:
                    fragment = FragmentCategories.newInstance("","");
                    break;

                case 1:
                    fragment = FragmentHome.newInstance("","");
                    break;

                case 2:
                    fragment = FragmentTopPaid.newInstance("","");
                    break;
            }

            Log.e(TAG,"onViewCreated:"+ position);
            return fragment;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }



    }
}
