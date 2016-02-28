package example.genka.recycleviewkenburnview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new MainFragment())
                    .commit();

    }
}
