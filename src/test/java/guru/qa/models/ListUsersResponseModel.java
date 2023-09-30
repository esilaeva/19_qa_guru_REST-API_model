package guru.qa.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListUsersResponseModel {

    int page;
    @JsonProperty("per_page")
    int per_page;
    int total;
    @JsonProperty("total_pages")
    int total_pages;
    List<ListUsersDataResponseModel> data;
    ListUsersSupportDataResponseModel support;
}
