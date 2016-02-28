package example.genka.recycleviewkenburnview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Collections;
import java.util.List;

import example.genka.recycleviewkenburnview.R;
import example.genka.recycleviewkenburnview.helper.ItemSelectedChange;
import example.genka.recycleviewkenburnview.helper.ItemTouchHelperAdapter;
import example.genka.recycleviewkenburnview.helper.OnStartDragListener;
import example.genka.recycleviewkenburnview.model.PersonCard;
import example.genka.recycleviewkenburnview.view.KenBurnsView;

/**
 * Created by Genka on 06.10.2015.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MainHolder>
        implements ItemTouchHelperAdapter {

    private List<PersonCard> listPerson;
    private Context context;
    private OnStartDragListener mDragStartListener;

    MyClickListener myClickListener;

    public RecycleAdapter ( List<PersonCard> _listPerson, OnStartDragListener mDragStartListener, Context _context) {
        listPerson = _listPerson;
        context = _context;
        this.mDragStartListener = mDragStartListener;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_main_item, parent, false);
        MainHolder viewHolder = new MainHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final MainHolder holder, int position) {
        PersonCard currentPerson = listPerson.get(position);
        holder.textTitle.setGravity(position % 2 == 0 ? Gravity.RIGHT : Gravity.LEFT);
        holder.textTitle.setText(currentPerson.getTitle());
        holder.textDescrip.setText(currentPerson.getShortDescrip());
        Picasso.with(context)
                .load(currentPerson.getImageUrl())
                .error(R.drawable.nature_photo5)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        holder.image.setResourceIds(bitmap, bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });


        holder.imageDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPerson.size();
    }



    public void setOnItemClickListener(MyClickListener _myClickListener) {
        myClickListener = _myClickListener;
    }


    public void clear() {
        listPerson.clear();
        notifyDataSetChanged();
    }

    @Override
    public boolean onDismiss(int position) {
        listPerson.remove(position);
        notifyItemRemoved(position);
        return false;
    }

    @Override
    public boolean onDrag(int fromPosition, int toPosition) {
        Collections.swap(listPerson, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }


    public class MainHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, ItemSelectedChange {

        TextView textTitle;
        TextView textDescrip;
        ImageView imageDrag;
        KenBurnsView image;
        RelativeLayout relativeLayout;


        public MainHolder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.title_text);
            textDescrip = (TextView) itemView.findViewById(R.id.descip_text);
            imageDrag = (ImageView) itemView.findViewById(R.id.imageView);
            image = (KenBurnsView) itemView.findViewById(R.id.image_title);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public void onItemSelected() {
            relativeLayout.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            relativeLayout.setBackgroundColor(Color.CYAN);
        }
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

}
