package example.genka.recycleviewkenburnview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thedeanda.lorem.Lorem;

import java.util.ArrayList;
import java.util.List;

import example.genka.recycleviewkenburnview.helper.OnStartDragListener;
import example.genka.recycleviewkenburnview.adapter.RecycleAdapter;
import example.genka.recycleviewkenburnview.model.PersonCard;
import example.genka.recycleviewkenburnview.utils.MyRecycleCallbacks;

/**
 * Created by Genka on 06.10.2015.
 */
public class MainFragment  extends Fragment  implements OnStartDragListener{

    private static final String LOG = "Genka";
    private View mainView;

    private RecycleAdapter recycleAdapter;
    private RecyclerView recyclerView;
    ItemTouchHelper touchHelper;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_main, container, false);
        loadView();
        setAdapter();
        return mainView;

    }

    private void loadView() {
        recyclerView = (RecyclerView) mainView.findViewById(R.id.recycler_view);
    }

    private void setAdapter () {
        recycleAdapter = new RecycleAdapter(getSampleList(), this, getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleAdapter);

        recycleAdapter.setOnItemClickListener(new RecycleAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(getActivity(), "Position View: "
                        + position, Toast.LENGTH_SHORT).show();
            }
        });


        MyRecycleCallbacks recycleCallbacks = new MyRecycleCallbacks(recycleAdapter);
        touchHelper = new ItemTouchHelper(recycleCallbacks);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }


    private List<PersonCard> getSampleList() {
        List<PersonCard> cardList = new ArrayList<>();

        for (int i = 0; i <=6; i ++) {
            String imageUrl;
            if (i == 0)
                imageUrl = "http://gamingpot.ru/images/nature-photo.png";
            else
                imageUrl = "http://gamingpot.ru/images/nature-photo" + i + ".png";

            cardList.add(new PersonCard(Lorem.getWords(2),
                    Lorem.getParagraphs(10, 20), imageUrl));
            Log.d(LOG, "Image url is: " + imageUrl);
        }
        Log.d(LOG, "List size is: " + cardList.size());
        return cardList;

    }


}
