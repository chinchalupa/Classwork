package com.example.jeremy.exam2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectorFragment.OnCompleteListener {

    private RecyclerView equationView;
    private EquationAdapter equationAdapter;
    private List<Equation> equationList;
    private List<Equation> displayedList;

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback;
    private ItemTouchHelper itemTouchHelper;

    private SelectorFragment selectorFragment;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final Equation eq = equationAdapter.removeEquation(viewHolder.getAdapterPosition());
                Snackbar snack = Snackbar.make(equationView, "Answer: " + eq.getResult(), Snackbar.LENGTH_LONG);

                snack.setAction(R.string.again, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        equationAdapter.addToPool(eq);
                    }
                });
                View v = snack.getView();
                TextView text = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
                snack.setActionTextColor(Color.WHITE);
                text.setTypeface(null, Typeface.BOLD);
                text.setTextSize(20);
//                button.setTextColor(getResources().getColor(android.R.color.white, null));
                snack.show();
            }
        };

        selectorFragment = new SelectorFragment();
        selectorFragment.onAttach(this);
        selectorFragment.show(getFragmentManager(), "Selection");

        this.equationList = selectorFragment.getEquationList();

        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        this.displayedList = new ArrayList<>();

        this.equationList = new ArrayList<>();

        this.equationView = (RecyclerView) findViewById(R.id.equation_list);
        this.itemTouchHelper.attachToRecyclerView(equationView);
        this.equationAdapter = new EquationAdapter(this.equationList, this.displayedList, this);

        this.equationView.setAdapter(this.equationAdapter);
        this.equationView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToEquationAdapter();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.add_all:
                while(this.equationList.size() > 0) {
                    this.addItemToEquationAdapter();
                }
                break;
            case R.id.shuffle:
                shuffleEquations();
                break;
            case R.id.reset_all:
                resetEquations();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addItemToEquationAdapter() {
        if (this.equationAdapter != null) {
            if(this.equationList.isEmpty()) {
                String noMoreEquations = getResources().getString(R.string.no_more_equations);
                Toast.makeText(MainActivity.this, noMoreEquations, Toast.LENGTH_SHORT).show();
                return;
            }
            Equation addedEquation = this.equationList.remove(0);
            this.displayedList.add(addedEquation);
            this.equationAdapter.notifyItemInserted(this.displayedList.size());
        }
    }

    public void shuffleEquations() {
        Collections.shuffle(this.displayedList);
        for(int i = 0; i < this.displayedList.size(); i++) {
            this.equationAdapter.notifyItemChanged(i);
        }
    }

    public void resetEquations() {
        while(!this.displayedList.isEmpty()) {
            Equation eq = this.displayedList.remove(0);
            this.equationList.add(eq);
            this.equationAdapter.notifyItemRemoved(0);
        }
    }

    @Override
    public void onComplete(List<Equation> e) {
        this.equationList.addAll(e);
    }
}
