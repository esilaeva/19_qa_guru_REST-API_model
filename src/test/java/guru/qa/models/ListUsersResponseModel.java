package guru.qa.models;

import lombok.Data;

@Data
public class ListUsersResponseModel {

    String page, per_page, total, total_pages;
    String[] data ={};

    /*
      "data": [
        {
            "id": 7,
            "email": "michael.lawson@reqres.in",
            "first_name": "Michael",
            "last_name": "Lawson",
            "avatar": "https://reqres.in/img/faces/7-image.jpg"
        },

     */
}
