package example.owner.com.retrofitexample.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by grojass on 07/06/2017.
 */

public class CreateUserResponse {
    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;
}
