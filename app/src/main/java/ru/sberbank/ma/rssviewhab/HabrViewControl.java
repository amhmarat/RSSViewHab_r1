package ru.sberbank.ma.rssviewhab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by internet on 05.02.2018.
 */

public class HabrViewControl extends Fragment {
    private static String HABR_RSS_URL = "https://habrahabr.ru/rss/all/";
    ;
    private RecyclerCellViewAdapter adapter;
    private List<Habr> mHabrList;

    public static Fragment getInstance() {
        return new HabrViewControl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskToExecute(HABR_RSS_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerCellViewAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    private class RecyclerCellViewAdapter extends RecyclerView.Adapter<RecyclerCellViewHolder> {

        @Override
        public RecyclerCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new RecyclerCellViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerCellViewHolder holder, int position) {
            Habr habr = mHabrList.get(position);
            holder.bindView(habr);
        }

        @Override
        public int getItemCount() {
            if (mHabrList != null)
                return mHabrList.size();
            return 0;
        }
    }

    private class RecyclerCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImage;
        private TextView mTitle;
        private Habr mHabr;

        public RecyclerCellViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.recycler_cell, viewGroup, false));
            mImage = (ImageView) itemView.findViewById(R.id.cl_view_image);
            mTitle = (TextView) itemView.findViewById(R.id.cl_text_title);
            itemView.setOnClickListener(this);
        }

        public void bindView(Habr habr) {
            mHabr = habr;
            mTitle.setText(mHabr.getTitle());

            Picasso.with(getActivity()).load(mHabr.getImageUrl()).into(mImage);
            if (mHabr.getImageUrl() == null)
                mImage.setImageBitmap(mHabr.getImage());
        }

        @Override
        public void onClick(View v) {
        }
    }

    private void taskToExecute(String url) {
        SendRequest request = new SendRequest();
        request.execute(url);
    }

    private void upgradeUI() {
        adapter.notifyDataSetChanged();
    }

    private class SendRequest extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mHabrList = HabrPool.getHabrList();
            upgradeUI();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                String result = new HabrConnect().getUrlString(params[0]);
                HabrParser.xmlParser(getResources(), result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
