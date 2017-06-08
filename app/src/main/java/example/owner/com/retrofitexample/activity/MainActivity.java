package example.owner.com.retrofitexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import example.owner.com.retrofitexample.R;
import example.owner.com.retrofitexample.client.APIInterface;
import example.owner.com.retrofitexample.client.RetrofitClient;
import example.owner.com.retrofitexample.pojo.MultipleResource;
import example.owner.com.retrofitexample.pojo.User;
import example.owner.com.retrofitexample.pojo.UserList;
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

        /**
         * GET List Resources
         */

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

        /**
         * Create new user
         */

        User user = new User("morpheus", "leader");
        Call call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                User user1 = (User) response.body();
                Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " "
                        + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

        /**
         * GET List Users
         */

        Call call2 = apiInterface.doGetUserList("2");
        call2.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                UserList userList = (UserList) response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total
                        + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for(UserList.Datum datum : datumList){
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name
                            + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

        /**
         * POST name and job Url encoded.
         */

        Call call3 = apiInterface.doCreateUserWithField("morpheus", "leader");
        call3.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                UserList userList = (UserList) response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n"
                        + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList){
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name
                            + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }
}
