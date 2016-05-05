package lathia.accelerometercollector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.ViewHolder>
{
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;

        public ViewHolder(final TextView v)
        {
            super(v);
            textView = v;
        }
    }

    private final static String SELECTED = "Selected: ";

    private final static String[] LABELS = {
            "Standing",
            "Walking",
            "Running",
            "Stairs",
            "Train"
    };

    @Override
    public LabelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final Context context = parent.getContext();
        TextView v = (TextView) LayoutInflater.from(context).inflate(R.layout.text_field,
                                                                     parent,
                                                                     false);
        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View view)
            {
                TextView textView = (TextView) view;
                String activity = textView.getText().toString();
                LabelPreferences.setLabel(context, activity);
                Toast.makeText(context, SELECTED + activity, Toast.LENGTH_LONG).show();
            }
        });

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.textView.setText(LABELS[position]);
    }

    @Override
    public int getItemCount()
    {
        return LABELS.length;
    }
}
