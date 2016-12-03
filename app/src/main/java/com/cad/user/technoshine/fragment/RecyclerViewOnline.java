package com.cad.user.technoshine.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cad.user.technoshine.MainActivity;
import com.cad.user.technoshine.R;
import com.cad.user.technoshine.RecycleOnlineAdapter;
import com.cad.user.technoshine.RecyclerItemClickListener;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewOnline extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    int[] myImageList = new int[]{R.drawable.justclick, R.drawable.codezip, R.drawable.net, R.drawable.inspire, R.drawable.cw};

    String[] eventname = new String[]{"JUST CLICK", "CODE ZIP", "NET HUNT",
            "INSPIRE INDIA", "CODE WAR"};
    String[] myTitleList = new String[]{"For participating you need to register on www.cadnitd.co.in\n" +
            "\n" +
            "You need to submit between 28 sept 2015 and 1st oct 2015\n" +
            "\n" +
            "The Submission must be a digital photo clicked by yourself\n" +
            "\n" +
            "The Submission must be entrant's original creation and owned one hundred percent (100%) by the entrant\n" +
            "\n" +
            "The Submission should not have been submitted previously in a promotion of any kind or exhibited or displayed publicly through any means\n" +
            "\n" +
            "The Submission must demonstrate one of the following contest themes: Open, Landscape \n" +
            "\n" +
            "Picture will be uploaded on our facebook page www.facebook.com/technoshinex.5, you have to get likes on your picture on our facebook page as much as possible before 2nd NOV 2015\n" +
            "\n" +
            "You have to like Technoshine X.5 page and then your likes will be consider\n" +
            "\n" +
            "Winners will be decided with weightage of 50% from number of Likes received on your photo on our FB page and weightage of 50% will be on our judges decision, respectively.",

            "1. Go to Hackerrank (login) and regsiter for the contest \"CODEZIP\"\n" +
                    "2. The contest will be a 3 hour of coding. \n" +
                    "3. Participation will be Individual.\n" +
                    "4. There will be 3 questions of different difficulties optimization manner.\n" +
                    "6. winner will be selected according to the leaderboard of hackerrank.\n" +
                    "So come and zip the source code.\n" +
                    " \n",
            "Keep Calm And Google\n\nAwesome Cash Prizes to Win\n\nPlay Nethunt @ www.cadnitd.co.in",
            "1.\tParticipants are supposed to register at www.cadnitd.co.in. Entries from unregistered participants will not be considered for evaluation.\n" +
                    "2.\tParticipants have to send their write-ups as .doc/.docx files to inspireindiats@gmail.com before 1st October 11 PM.\n" +
                    "3.\tParticipants must choose  any one of the following:\n" +
                    "A short story based on theme- RURAL INDIAN YOUTH  (Max Word Count- 400)\n" +
                    "An article on INDIA OF MY DREAMS (Max Word Count- 500)\n" +
                    "A poem based on the theme- “FREEDOM” AFTER 6 DECADES OF INDEPENDENCE\n" +
                    "An article on PROUD? TO BE AN INDIAN (Max Word Count-500)\n" +
                    "Note: Please give apt titles for  the poem and short story \n" +
                    "4.\tEach of the entries should be an original creation of the participant. \n",
            "1. Go to Codechef login and regsiter for the contest \"CODEWAR\"\n" +
                    "2. The contest will be a 5 hour coding marathon \n" +
                    "3. Participation will be Individual\n" +
                    "4. There will be five questions of different difficulties.\n" +
                    "6. winner will be selected according to the leaderboard.\n" +
                    "So come and win the epic battle of coding.\n"};

    public static RecyclerViewOnline newInstance() {
        return new RecyclerViewOnline();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerViewMaterialAdapter(new RecycleOnlineAdapter(myImageList));
        mRecyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                Dialog dialog = new Dialog(getActivity(), R.style.DialogSlideAnim);
                dialog.setContentView(R.layout.info1);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.ivFullScreen);
                imageView.setImageResource(myImageList[position - 1]);
                TextView textView = (TextView) dialog.findViewById(R.id.detailTitle);

                TextView title = (TextView) dialog.findViewById(R.id.title);
                title.setText(eventname[position - 1]);
                textView.setText(myTitleList[position - 1]);
                imageView.setImageResource(myImageList[position - 1]);

                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap blurred = MainActivity.blurRenderScript(bitmap, 25);//second parameter is radius
                imageView.setImageBitmap(blurred);

                //dialog.getWindow().setBackgroundDrawableResource(myImageList[position - 1]);
                dialog.getWindow().setFormat(PixelFormat.TRANSLUCENT);
                dialog.show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));
    }
}




