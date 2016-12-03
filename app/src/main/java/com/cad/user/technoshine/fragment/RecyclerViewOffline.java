package com.cad.user.technoshine.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
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
import com.cad.user.technoshine.RecycleOfflineAdapter;
import com.cad.user.technoshine.RecyclerItemClickListener;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewOffline extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    int[] myImageList = new int[]{R.drawable.tattoo, R.drawable.brain, R.drawable.counter,
            R.drawable.robotics, R.drawable.gg, R.drawable.poy, R.drawable.sc, R.drawable.t, R.drawable.tc};
    String[] eventname = new String[]{"TATTOO MAKING", "BRAIN MARATHON", "COUNTER STRIKE",
            "ROBOTICS", "GADGET GURU", "PERFORMER OF THE YEAR", "SOCKET COMBAT", "TRICKOLOGY", "TOTAL CHAOS"};

    String[] myTitleList = new String[]{
            "Come participate and bring out the artist in you.\n" +
                    "1) We will provide you with water colors and paint brushes (2 max for every participant). But if you wish to bring your own brushes,glitter or other accessories feel free to do so. Sky is the limit.\n" +
                    "2) The CATEGORIES are:\n" +
                    "\ta) Nature\n" +
                    "\tb) Cartoons or Anime\n" +
                    "\tc) Tribal\n" +
                    "\td) Geometric\n" +
                    "3) Participant will have to bring a willing accomplice upon whom they will paint.\n" +
                    "4) They will have to explain their creation to the judges so their tattoo should be meaningful and should belong to any one of the categories mentioned above.\n" +
                    "5) Time : 1hour.\n",
            "ACE THE BRAIN RACE\n" +
                    "\n" +
                    "Talent hits a target no one else can hit, Genius hits a target no one else can see.So guys time to shake your brains up!!! Get the best out of yourself. As The Quiz always floats slightly above the ground!!! And only you can do it. Here you go...\n" +
                    "\n" +
                    "Rules:\n" +
                    "\n" +
                    "1) The questions for quiz are based on general knowledge, current affairs\n" +
                    "\n" +
                    "2) There will be only 1 round, which is paper based.\n" +
                    "\n" +
                    "3) 30 questions of 3 marks each. There will be no negative marking.\n" +
                    "\n" +
                    "4) Two members in each team.\n" +
                    "\n" +
                    "5) There will be strict prohibition on any source like internet for help.",
                    "Round:\tOne 1 minute 45 second cycle on a map\n\n" +
                    "Match:\tFirst team to win 7 total rounds, or the single overtime round\n\n" +
                    "Overtime:\tIn the case of a 6-6 score at the end of regulation play, 3 overtime rounds will be played. The start money for each team will be set at $8,000 per player for overtime.\n\n\n" +
                    "Game Format:\n\n" +
                    "Competition Method:\t5 vs. 5\n\n" +
                    "Players:\t10 total (5 on each team)\n\n" +
                    "Rounds:\t12 rounds for regulation play\n\n" +
                    "Max Rounds Format:\t6 rounds as Offense, and 6 rounds as Defense per team until the victory condition is met\n" +
                    "Victory Condition:\tThe first team to win 6rounds in regulation or the team that wins the overtime period.\n\n" +
                    "Buy time:\t15 seconds\n\n" +
                    "Start money:\t$800 for regulation play, $10,000 for overtime periods\n\n" +
                    "Freeze time:\t5 seconds\n\n" +
                    "C4 Timer:\t35 seconds\n\n" +
                    "Map Pool:\n" +
                    "\t\t• de_dust2, de_inferno, de_nuke, de_train\n" +
                    "\n" +
                    "Side Selection:\n" +
                    "\t\t• By knife round / coin toss, at admin discretion\n" +
                    "\t\t• At half time, teams will switch sides\n" +
                    "\t\t• All players names must be in the format of their team tag + gaming alias \n" +
                    "(i.e. TEAM | Player)\n" +
                    "\n" +
                    "Setup and Configuration:\n" +
                    "\t\t•Non-standard game settings or third party applications that affect game play are not permitted.\n" +
                    "\t\t•Players are restricted from opening the console.\n",
            "PROBLEM STATEMENT:\n\n" +
                    "To design and implement a ground vehicle (rover) using a micro-controller, equipped with sensors that can traverse a room which has some severe obstacles. The room is GSM and GPS challenged hence the rover solely depends on offline data and an on-spot decision system. Hence it is suitable that it logs the surrounding co-ordinates of the present obstacle and a scope to generate a 3-D Map of the room after the data are fed in proper simulators.\n" +
                    "\n" +
                    "ROBOT SPECIFICATIONS:\n \n" +
                    "There will be no restriction to the length, width or height of the robot vehicle as long as the vehicle fits within the area bounded. The vehicle must be fully self-contained and not receive assistance from external sources and all parts of the vehicle must travel to the target. The judge may, however, allow participants to retrieve and restart their vehicles in the event of a collision or other situations when a restart is required.\n" +
                    "The vehicle must not attempt to change or damage its environment.\n" +
                    "Course:\n" +
                    " The course will be outdoors with both natural and manmade terrain and obstacles. The terrain may include pavement, dirt, small rocks, grass, gullies, trees, curbs and weeds.\n" +
                    "All robots must travel on the surface of the domain. Robot vehicles are required to travel within the specified domain and no part of the vehicle must come in direct contact with regions outside the domain. Overhanging within the boundary is allowed.\n" +
                    "General rules:\n" +
                    "1. Winner is declared based on point rating and not on its time score. \n" +
                    "2. Winner is declared by the competition chair, based on the rating of the judges.\n" +
                    "3. Teams should respect security rules, and be “fair play”. \n" +
                    "4. During the competitions, only the team leader is authorized to present the robot. \n" +
                    "5. During the competitions, only the team leader is authorized to contact committee members for any claim or specific need.\n" +
                    "6. No servicing or repairs of any kind are allowed during the time allocation to each entry. Should the need arise to replace exhausted battery supplies, permission must be sought from the judges to perform this function. The decision to allow this is left at the discretion of the judges.\n",
            " Unleash The ‘Geekzmo’ in You…\n" +
                    "Rules:\n" +
                    "This events for specially for who loves the gadget’s..   The whole event based on latest Gadget ‘s.\n" +
                    "1.\tThere will be team of  two  members.\n" +
                    "2.\tWhole event completed in two round. \n" +
                    "3.\tThe first  round  is Paper based .. there will be 20 question based on latest gadgets . time limit  20 minutes..\n" +
                    "4.\tSecond round is  App based ..  consisting of 20 questions time limit 20 minutes..\n",
            "1. Performances should not exceed 4 minutes.\n" +
                    "2. Only individual performances allowed.\n" +
                    "3. Performances can include acting, singing, dancing, musical instrument, poetry-recital or stand-up comedy.\n" +
                    "4. Selected performers from the first round will compete against others in a second round.\n" +
                    "5. Performers are to bring their sound tracks, songs, props and other such requisites themseleves.",
            "Crusading the Network Arena\n\nPlease visit www.cadnitd.co.in for details.",
            "1. It is a team event, each team has 2 members.\n" +
                    "2. It will consist of 3 rounds ,round 1 is paper based ,selected team from round 1 goes to round 2, which is app based & shortlisted team will face the third round which is purely based on coding.\n" +
                    "3. Round 1 & 2 will be of 30 minutes each & consist of multiple choice question.\n" +
                    "4. Round 3 is the coding  round which is of 2 hours.\n" +
                    "5. For round 3 participants should have to bring their own laptops.\n",
            "Who let The Chaos out?\n" +
                    "Rules:\n" +
                    "1.\tThere will be team of  two  members.\n" +
                    "2.\tWhole event completed in two round\n" +
                    "3.\t1st round is App based… there will be 30 question based on (Programming, GK , Logical Reasoning ,Computer tricky questions) . Time limit   30 minutes..\n" +
                    "4.\tSecond round is surprise.\n"


    };

    public static RecyclerViewOffline newInstance() {
        return new RecyclerViewOffline();
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
        mAdapter = new RecyclerViewMaterialAdapter(new RecycleOfflineAdapter(myImageList));
        mRecyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                Dialog dialog = new Dialog(getActivity(), R.style.DialogSlideAnim);
                dialog.setContentView(R.layout.info1);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.ivFullScreen);
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




