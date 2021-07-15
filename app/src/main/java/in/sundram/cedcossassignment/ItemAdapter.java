package in.sundram.cedcossassignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<MovieDataModel> dataModelsList;
    private String movieName;
    public ItemAdapter(Context context, List<MovieDataModel> dataModelsList,String movieName) {
        this.context = context;
        this.dataModelsList = dataModelsList;
        this.movieName=movieName;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemAdapter.ViewHolder holder, int position) {
        try {
            if(movieName.equalsIgnoreCase(dataModelsList.get(position).getOriginal_title().trim().split(" ")[0])){
                holder.container.setCardBackgroundColor(context.getResources().getColor(R.color.teal_200));
            }else{
                holder.container.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            }
            holder.title.setText(dataModelsList.get(position).getOriginal_title());
            holder.year.setText(dataModelsList.get(position).getRelease_date());
            holder.runtime.setText(dataModelsList.get(position).getVote_average());
            String image_path = Constant.IMAGE_URL + dataModelsList.get(position).getPoster_path();
            Glide.with(context)
                    .load(image_path)
                    .into(holder.image);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView container;
        private TextView title, year, runtime;
        private ImageView image;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            runtime = itemView.findViewById(R.id.runtime);
            image = itemView.findViewById(R.id.image);
        }
    }
}
