package ru.sberbank.ma.rssviewhab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.UUID;

/**
 * Created by internet on 05.02.2018.
 */

public class RecViewControl extends Fragment {
    private TextView mDate;
    private ImageView mImage;
    private TextView mTitle;
    private TextView mDescription;
    private Habr mHabr;

    public static Fragment getInstance() {
        return new RecViewControl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID) getActivity().getIntent().getSerializableExtra(RecViewActivity.INTENT_ID);
        mHabr = HabrPool.getHabr(uuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rec_view, container, false);

        mDate = (TextView) view.findViewById(R.id.rec_view_pub_date);
        mImage = (ImageView) view.findViewById(R.id.rec_view_image);
        mTitle = (TextView) view.findViewById(R.id.rec_view_title);
        mDescription = (TextView) view.findViewById(R.id.rec_view_description);

        mDate.setText(mHabr.getPubDate());

        Picasso.with(getActivity()).load(mHabr.getImageUrl()).into(mImage);
        if (mHabr.getImageUrl() == null)
            mImage.setImageBitmap(mHabr.getImage());

        mTitle.setText(mHabr.getTitle());
        mDescription.setText(mHabr.getDescription());

        return view;
    }
}
