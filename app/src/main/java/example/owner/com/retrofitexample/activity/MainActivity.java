package example.owner.com.retrofitexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import example.owner.com.retrofitexample.R;
import example.owner.com.retrofitexample.client.APIInterface;
import example.owner.com.retrofitexample.client.RetrofitClient;
import example.owner.com.retrofitexample.pojo.MultipleResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * source http://www.journaldev.com/13639/retrofit-android-example-tutorial
 */

public class MainActivity extends AppCompatActivity {
    TextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.responseText);
        apiInterface = RetrofitClient.getClient().create(APIInterface.class);

        Call call = apiInterface.doGetListResources();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("TAG", response.code() + "");
                String displayResponse = "";
                MultipleResource resource = (MultipleResource) response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";
                for(MultipleResource.Datum datum : datumList){
                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                }

                responseText.setText(displayResponse);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

    }
}
