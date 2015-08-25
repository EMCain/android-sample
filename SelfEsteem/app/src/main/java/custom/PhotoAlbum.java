package custom;

import android.graphics.drawable.Drawable;

import net.emcain.selfesteem.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Emily on 5/9/2015.
 */
public class PhotoAlbum {
    private List<Integer> pics;

    public PhotoAlbum() {
        pics = new ArrayList<Integer>();

//        pics.add(R.drawable.chats);
//        pics.add(R.drawable.kittens);
//        pics.add(R.drawable.penguins);

        pics.add(R.drawable.cat_sweater);
        pics.add(R.drawable.chai_on_couch);
        pics.add(R.drawable.dog_look_up);
        pics.add(R.drawable.fuzzy_belly);
        pics.add(R.drawable.happy_husky);
        pics.add(R.drawable.husky_and_cats);
        pics.add(R.drawable.liam_in_straw);
        pics.add(R.drawable.lulo_belly);
        pics.add(R.drawable.tabby_face);
        pics.add(R.drawable.tux_cat);
        pics.add(R.drawable.tux_on_towel);

    }

    public int getPicId(){
        int id = 0;
        // randomly select a fact
        Random randomGenerator = new Random(); // Construct a new random number generator
        int randomNumber = randomGenerator.nextInt(pics.size());
        //determine fact based on random number

        id = pics.get(randomNumber);
        return id;

    }
}
