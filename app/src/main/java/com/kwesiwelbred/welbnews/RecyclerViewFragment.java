package com.kwesiwelbred.welbnews;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ArrayList<NewsData> items = new ArrayList <>();
    RecyclerAdapter adapter;

    Context context;
    int pos;


    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    public RecyclerViewFragment(int position){
        this.pos = position;

    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerViewFragment.
     */

    private static RecyclerViewFragment newInstance(String param1, String param2) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
      //  args.putString(ARG_PARAM1, param1);
      //  args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          //  mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView);


        String urltemp="";
        if (pos == 0) {
            urltemp = "http://newsapi.org/v2/top-headlines?sources=vice-news&apiKey=653d14a9add54ed4848433b59062a0c8";
        }
        else if(pos == 1)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=ary-news&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 2)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 3)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 4)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=usa-today&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 5)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=cnn&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 6)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=fox-news&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 7)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=google-news&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 8)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=the-verge&apiKey=653d14a9add54ed4848433b59062a0c8";
        else if(pos == 9)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=news24&apiKey=653d14a9add54ed4848433b59062a0c8";
        else
        {
            urltemp = "http://newsapi.org/v2/top-headlines?sources=abc-news&apiKey=653d14a9add54ed4848433b59062a0c8";
        }

        Ion.with(RecyclerViewFragment.this).load("GET", urltemp).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

              //  String status = result.get("status").toString();
                if (result !=null) {
                    JsonArray array = result.get("articles").getAsJsonArray();

                    for (int i = 0; i < array.size(); i++) {
                        JsonObject object = array.get(i).getAsJsonObject();

                        String author = object.get("author").toString();

                        String title = object.get("title").toString();
                        title = title.substring(1, title.length() - 1);

                        String url = object.get("url").toString();
                        url = url.substring(1, url.length() - 1);

                        String urlToImage = object.get("urlToImage").toString();
                        urlToImage = urlToImage.substring(1, urlToImage.length() - 1);

                        String date = object.get("publishedAt").toString();
                        String content = object.get("content").toString();
                        content = content.substring(1, content.length() - 1);

                        items.add(new NewsData(title, author, date, urlToImage, url));
                    }

                    adapter = new RecyclerAdapter(getActivity(), items);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);


                    Log.e("WelbNews", "success");
                } else {
                    Log.e("WelbNews", "error");
                }
            }
        });

    }
}
