package com.example.sanjeev.alumninetwork.networking;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class showData {
    public static void main(String... args) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        githubClient client = serviceGenerator.createService(githubClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<List<contributors>> call =
                client.get_contributors("fs_opensource", "android-boilerplate");

        try {
            List<contributors> list_contributors = call.execute().body();
            String login_info [] = new String[list_contributors.size()];
            int contri_info [] = new int[list_contributors.size()];
            for(int i = 0;i<list_contributors.size();i++)
            {
                Log.e("human", "i");
                System.out.println(i);
            }

        } catch (IOException e) {
            // handle errors
        }

       /* for (contributors contributor : contributors) {
            System.out.println(
                    contributor.login + " (" + contributor.contributions + ")");
        }*/
    }
    }

