package example.owner.com.retrofitexample.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grojass on 07/06/2017.
 */

public class UserList {
    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("data")
    public List data = new ArrayList();

    public class Datum {
        @SerializedName("id")
        public Integer id;
        @SerializedName("first_name")
        public String fist_name;
        @SerializedName("last_name")
        public String last_name;
        @SerializedName("avatar")
        public String avatar;
    }
}
