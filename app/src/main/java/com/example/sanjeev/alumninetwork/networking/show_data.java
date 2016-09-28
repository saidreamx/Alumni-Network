package com.example.sanjeev.alumninetwork.networking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.json_model.json_format;

import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

    //Class having OnItemClickListener to handle the clicks on list
    public class show_data extends AppCompatActivity implements ListView.OnItemClickListener {

        //Root URL of our web service
        public static final String ROOT_URL = "http://getsanjeev.esy.es/";

        //Strings to bind with intent will be used to send data to other activity
        public static final String KEY_BOOK_ID = "key_book_id";
        public static final String KEY_BOOK_NAME = "key_book_name";
        public static final String KEY_BOOK_PRICE = "key_book_price";
        public static final String KEY_BOOK_STOCK = "key_book_stock";

        //List view to show data
        private ListView listView;

        //List of type books this list will store type Book which is our data model
        private List<json_format> location_data;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_display);

            //Initializing the list view
            listView = (ListView) findViewById(R.id.list_locations);

            //Calling the method that will fetch data
            get_Location_data();

            //Setting onItemClickListener to listview
            listView.setOnItemClickListener(this);
        }

        private void get_Location_data(){
            //While the app fetched data we are displaying a progress dialog
            final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

            //Creating a rest adapter
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL)
                    .build();

            //Creating an object of our api interface
            request api = adapter.create(request.class);

            //Defining the method
            api.get_location_details(new Callback<List<json_format>>() {
                @Override
                public void success(List<json_format> list, Response response) {
                    //Dismissing the loading progressbar
                    loading.dismiss();

                    //Storing the data in our list
                    location_data = list;

                    //Calling a method to show the list
                    showList();
                }

                @Override
                public void failure(RetrofitError error) {
                    //you can handle the errors here
                }
            });
        }

        //Our method to show list
        private void showList(){
            //String array to store all the book names
            String[] items = new String[location_data.size()];

            //Traversing through the whole list to get all the names
            for(int i=0; i<location_data.size(); i++){
                //Storing names to string array
                items[i] = location_data.get(i).getlatitude();
            }

            //Creating an array adapter for list view
            ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,items);

            //Setting adapter to listview
            listView.setAdapter(adapter);
        }


        //This method will execute on listitem click
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Creating an intent
           // Intent intent = new Intent(this, ShowBookDetails.class);

            //Getting the requested book from the list
          //  Book book = books.get(position);

            //Adding book details to intent
            /*intent.putExtra(KEY_BOOK_ID,book.getBookId());
            intent.putExtra(KEY_BOOK_NAME,book.getName());
            intent.putExtra(KEY_BOOK_PRICE,book.getPrice());
            intent.putExtra(KEY_BOOK_STOCK,book.getInStock());*/

            //Starting another activity to show book details
           // startActivity(intent);
        }
    }

