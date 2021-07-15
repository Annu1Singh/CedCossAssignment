package in.sundram.cedcossassignment.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.sundram.cedcossassignment.R;
import in.sundram.cedcossassignment.adapter.ItemAdapter;
import in.sundram.cedcossassignment.datamodel.MovieDataModel;
import in.sundram.cedcossassignment.utils.VolleyUtil;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;
    private static final String TAG = MainActivity.class.getName();
    private RecyclerView movie_item_rv;
    private ProgressBar pb;
    private List<MovieDataModel> movieDataModelList;
    private ItemAdapter adapter;
    private EditText search_movie_et;
    private LinearLayout movie_item_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //use to initialize the views
        init();
        searchMovie();
    }

    private void init() {
        pb = findViewById(R.id.pb);
        movie_item_rv = findViewById(R.id.movie_item);
        search_movie_et = findViewById(R.id.search_movie_et);
        movie_item_ll = findViewById(R.id.movie_item_ll);
    }

    private void searchMovie() {
        search_movie_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                showMovieListRV();
            }
        });
    }

    private void showMovieListRV() {
      try {
         if (search_movie_et.getText().toString().length()>0){
             pb.setVisibility(View.VISIBLE);
             movieDataModelList = new ArrayList<>();
             VolleyUtil.volleyGetRequest(
                     context,
                     search_movie_et.getText().toString().trim(),
                     object -> {
                         movieDataModelList = (ArrayList<MovieDataModel>) object;
                         if (movieDataModelList.size() > 0) {
                             movie_item_ll.setVisibility(View.VISIBLE);
                                 pb.setVisibility(View.GONE);
                                 movie_item_rv.setHasFixedSize(true);
                                 movie_item_rv.setLayoutManager(new LinearLayoutManager(context));
                                 adapter = new ItemAdapter(context, movieDataModelList,search_movie_et.getText().toString().trim());
                                 movie_item_rv.setAdapter(adapter);
                                 adapter.notifyDataSetChanged();

                         }else{
                             movie_item_ll.setVisibility(View.GONE);
                         }
                     }
             );
         }else{
             movie_item_ll.setVisibility(View.GONE);
         }
      }catch (Exception e){
          e.printStackTrace();
      }
    }
}