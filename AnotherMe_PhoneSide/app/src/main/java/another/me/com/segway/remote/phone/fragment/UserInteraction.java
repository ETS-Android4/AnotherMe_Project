package another.me.com.segway.remote.phone.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import another.me.com.segway.remote.phone.R;
import another.me.com.segway.remote.phone.fragment.base.RemoteFragment;
import another.me.com.segway.remote.phone.util.CommandStringFactory;
import another.me.com.segway.remote.phone.service.ConnectionService;


public class UserInteraction extends RemoteFragment implements Button.OnClickListener{

    private static final String TAG = "EmojiFragment";
    private EditText speechInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View emojiFragment = inflater.inflate(R.layout.user_interaction, container, false);



        Button soundTestButton = emojiFragment.findViewById(R.id.sound_test);
        soundTestButton.setOnClickListener(mButtonClickListener);
        speechInput = emojiFragment.findViewById(R.id.speech_input);



        // add click listener to all buttons on fragment
        ArrayList<View> buttons1 = emojiFragment.findViewById(R.id.emoji_layout_1).getTouchables();
        ArrayList<View> buttons2 = emojiFragment.findViewById(R.id.emoji_layout_2).getTouchables();
        ArrayList<View> buttons3 = emojiFragment.findViewById(R.id.emoji_layout_3).getTouchables();
        for (View v : buttons1) {
            v.setOnClickListener(this);
        }
        for (View v : buttons2) {
            v.setOnClickListener(this);
        }
        for (View v : buttons3) {
            v.setOnClickListener(this);
        }

        //String[] message = {"settings", EmojiController.KEY_EMOJI, "false"};
        //ConnectionService.getInstance().send(CommandStringFactory.getStringMessage(message));

        return emojiFragment;

    }


    @Override
    public void onClick(View v) {
        String[] message = {"settings", EmojiController.KEY_EMOJI, "true"};
        ConnectionService.getInstance().send(CommandStringFactory.getStringMessage(message));
        String[] message1 = {"emoji", v.getTag().toString()};
        getLoomoService().send(CommandStringFactory.getStringMessage(message1));


    }

    private View.OnClickListener mButtonClickListener = new View.OnClickListener() {

        public void onClick(View view) {
            String speak = speechInput.getText().toString().trim();
            Log.i(TAG, "Trying to say: " + speak);
            getLoomoService().sendSound(speak);
        }
    };







}// end class UserInteraction

