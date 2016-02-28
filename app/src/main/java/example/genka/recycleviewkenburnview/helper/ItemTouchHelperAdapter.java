package example.genka.recycleviewkenburnview.helper;

/**
 * Created by Genka on 05.11.2015.
 */
public interface ItemTouchHelperAdapter {

    boolean onDismiss (int position);

    boolean onDrag(int fromPosition, int toPosition);
}
