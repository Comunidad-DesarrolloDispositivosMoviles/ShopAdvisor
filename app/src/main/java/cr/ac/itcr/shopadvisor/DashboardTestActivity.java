package cr.ac.itcr.shopadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardTestActivity extends AppCompatActivity {

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardtest);

        textView1 = (TextView) findViewById(R.id.txtInfo);
        Bundle bundle = getIntent().getExtras();
        textView1.setText(bundle.getString("messaje"));
    }
}
