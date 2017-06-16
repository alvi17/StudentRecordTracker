package alvi17.studentrecordtracker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference batchReference;
    boolean dataLoaded=false;
    ArrayList<Batch> batches;
    GridView gridView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    CustomGridAdapter customGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        gridView=(GridView)findViewById(R.id.batch_grdiview);
        batches=new ArrayList<>();

        customGridAdapter=new CustomGridAdapter(MainActivity.this,batches);
        gridView.setAdapter(customGridAdapter);

        batchReference= FirebaseDatabase.getInstance().getReference("batchinfo");
        showProgressDialog();
        batchReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(batches.size()>0)
                {
                    batches.clear();
                }
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren())
                {

                    Batch value=messageSnapshot.getValue(Batch.class);
                    batches.add(value);

                    Log.e("MainActivity",value.name+" "+value.year);
                }
                dataLoaded=true;
                customGridAdapter.notifyDataSetChanged();
                hideProgressDialog();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public ProgressDialog mProgressDialog;

    public void showProgressDialog()
    {
        if (mProgressDialog == null)
        {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog()
    {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        hideProgressDialog();
    }

    int backpressed=0;
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        backpressed++;
        if(backpressed==2)
        {
            finishAffinity();
        }else
        {
            Toast.makeText(getApplicationContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
    }
}
