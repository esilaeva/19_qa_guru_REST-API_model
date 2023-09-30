package guru.qa.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListUsersDataResponseModel {

    int id;
    String email;
    @JsonProperty("first_name")
    String first_name;
    @JsonProperty("last_name")
    String last_name;
    String avatar;
}
