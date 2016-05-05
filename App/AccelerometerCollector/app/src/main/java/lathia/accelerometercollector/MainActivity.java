package lathia.accelerometercollector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button sensingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensingButton = (Button) findViewById(R.id.sensingButton);
        setRecyclerView();
        setButton();
    }

    private void setRecyclerView()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (recyclerView != null)
        {
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            LabelAdapter adapter = new LabelAdapter();
            recyclerView.setAdapter(adapter);
        }
    }

    private void setButton()
    {
        sensingButton.setText(R.string.sensing_start);
    }

    public void onSensingButtonClicked(final View view)
    {
        setButton();
    }
}
